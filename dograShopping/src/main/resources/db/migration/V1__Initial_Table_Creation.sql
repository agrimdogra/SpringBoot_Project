CREATE TABLE category
(
    id   BINARY(16) NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id           BINARY(16) NOT NULL,
    order_date   VARCHAR(255) NULL,
    order_vendor VARCHAR(255) NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE products
(
    id                 BINARY(16) NOT NULL,
    title              VARCHAR(255) NULL,
    `description`      VARCHAR(255) NULL,
    price              INT NOT NULL,
    image              VARCHAR(255) NULL,
    `category details` BINARY(16) NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY_DETAILS FOREIGN KEY (`category details`) REFERENCES category (id);