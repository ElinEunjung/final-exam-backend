CREATE TABLE if NOT EXISTS PRODUCTS
(
    product_id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(200),
    price REAL NOT NULL,
    status VARCHAR(20) NOT NULL,
    quantityInStock INTEGER NOT NULL
);

CREATE SEQUENCE product_seq;
