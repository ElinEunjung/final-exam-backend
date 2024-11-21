CREATE TABLE if NOT EXISTS customer
(
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL ,
    phoneNumber VARCHAR(40),
    email VARCHAR(100),
    addresses BIGINT,
    history BIGINT
);

CREATE SEQUENCE customer_seq;

