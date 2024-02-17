package com.ecomerce.user_new.service;

import com.ecomerce.user_new.dto.requestDto.UserRequestDto;
import com.ecomerce.user_new.dto.responseDto.SessionResponseDto;
import com.ecomerce.user_new.dto.responseDto.UserResponseDto;
import com.ecomerce.user_new.enums.SessionStatus;
import com.ecomerce.user_new.exception.MaxSessionLimitException;
import com.ecomerce.user_new.exception.SessionNotFoundException;
import com.ecomerce.user_new.exception.UserNotFoundException;
import com.ecomerce.user_new.model.Session;
import com.ecomerce.user_new.model.User;
import com.ecomerce.user_new.repository.SessionRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import lombok.AllArgsConstructor;
import org.hibernate.metamodel.internal.StandardEmbeddableInstantiator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.security.Security;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class AuthService {
    UserService userService;
    SessionRepository sessionRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final int MAX_ACTIVE_SESSION_LIMIT = 2;
    public ResponseEntity<SessionResponseDto> login(UserRequestDto userRequestDto){
        //Check if the user exists:
        if (userService.isExistingUser(userRequestDto)){
            Optional<User> user = userService.getUserFromDb(userRequestDto.getEmail());
            if (user.isPresent() && bCryptPasswordEncoder.matches(userRequestDto.getPassword(), user.get().getPassword())){
                //Check max session limit
                List<Session> sessions = sessionRepository.getSessionsByUser(user.get());
                long actSession = sessions.stream().filter(s->s.getSessionStatus().equals(SessionStatus.ACTIVE)).count();
                if (actSession == MAX_ACTIVE_SESSION_LIMIT)
                    throw new MaxSessionLimitException("Max session limit exceeded, logout from one of the earlier sessions");


                //Set expiry time 5 minutes from now
                LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

                MacAlgorithm algo = Jwts.SIG.HS256;
                SecretKey key = algo.key().build();

                Map<String, Object> jsonForJwt = new HashMap<>();
                jsonForJwt.put("Email", user.get().getEmail());
                jsonForJwt.put("roles", user.get().getRoles());

                String token = Jwts.builder()
                        .claims(jsonForJwt)
                        .signWith(key, algo)
                        .compact();

                Session session = Session.builder()
                        .token(token)
                        .expiringAt(expiryTime)
                        .sessionStatus(SessionStatus.ACTIVE)
                        .user(user.get())
                        .build();

                /**
                 * Parse JWT
                 *  Jws<Claims> claimsJws = Jwts.parser()
                 *                         .build()
                 *                         .parseSignedClaims(token);
                 *
                 *                 String email =(String) claimsJws.getPayload().get("email");
                 */



                Claims claims = Jwts.parser()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                Claims claims1 =  Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();


                Session session1 = sessionRepository.save(session);
                SessionResponseDto resp =  session1.toSessionResponseDto();
                MultiValueMapAdapter<String,String> header = new MultiValueMapAdapter<>(new HashMap<>());
                header.add(HttpHeaders.SET_COOKIE, "auth-token = "+resp.getToken());
                return new ResponseEntity<>(
                        resp,
                        header,
                        HttpStatus.OK
                );
            }
        }

        throw new UserNotFoundException("User does not exist, Kindly provide correct username and password");
    }


    public SessionResponseDto logout(String tokenId){
        Optional<Session> session = sessionRepository.getSessionsByToken(tokenId);
        boolean isSessionActive = (session.isPresent() && session.get().getSessionStatus() == SessionStatus.ACTIVE);
        if (!isSessionActive) {
            throw new SessionNotFoundException("Session already logged out or not found");
        }
        Session session1 = session.get();
        session1.setSessionStatus(SessionStatus.INACTIVE);
        Session session2 = sessionRepository.save(session1);
        return session2.toSessionResponseDto();
    }
}
