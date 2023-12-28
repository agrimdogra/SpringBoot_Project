package com.ecomerce.dograshopping.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@SuperBuilder
@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
//    @GeneratedValue(generator = "myGenerator")
//    @GenericGenerator(name = "myGenerator", type = org.hibernate.id.uuid.UuidGenerator.class)
//    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

//    public BaseModel() {
//        this.id =  UUID.randomUUID();
//    }
}
