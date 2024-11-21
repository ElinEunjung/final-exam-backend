CREATE TABLE if NOT EXISTS order_product
(
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL ,
    phoneNumber VARCHAR(40),
    email VARCHAR(100),
    addresses BIGINT,
    history BIGINT
);

CREATE SEQUENCE order_product_seq;
