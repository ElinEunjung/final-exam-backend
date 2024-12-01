--V01__init.sql
DROP TABLE IF EXISTS customer_addresses;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_products;
DROP TABLE IF EXISTS products;

CREATE TYPE product_status AS ENUM ('AVAILABLE', 'OUT_OF_STOCK', 'DISCONTINUED');

CREATE SEQUENCE customer_seq;
CREATE TABLE CUSTOMERS
(
    customer_id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL ,
    phone_number VARCHAR(40),
    email VARCHAR(100) NOT NULL
);


CREATE SEQUENCE customer_address_seq;
CREATE TABLE  CUSTOMER_ADDRESSES
(
    customer_address_id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES CUSTOMERS(customer_id) ON DELETE CASCADE,
    address VARCHAR(100) NOT NULL
);


CREATE SEQUENCE product_seq;
CREATE TABLE  PRODUCTS
(
    product_id BIGINT PRIMARY KEY,
    candy_name VARCHAR(100) NOT NULL,
    description TEXT,
    price REAL NOT NULL,
    status VARCHAR(20) NOT NULL,
    quantity_in_stock INTEGER NOT NULL
);


CREATE SEQUENCE order_seq;
CREATE TABLE ORDERS
(
    order_id BIGINT PRIMARY KEY,
    shipping_address VARCHAR(100) NOT NULL,
    shipping_charge REAL NOT NULL,
    total_price REAL NOT NULL,
    is_shipped BOOLEAN DEFAULT FALSE,
    customer_id BIGINT NOT NULL REFERENCES CUSTOMERS(customer_id) ON DELETE CASCADE
);


CREATE SEQUENCE order_product_seq;
CREATE TABLE ORDER_PRODUCTS
(
    order_product_id BIGINT PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES ORDERS(order_id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL REFERENCES PRODUCTS(product_id) ON DELETE CASCADE,
    product_quantity INTEGER NOT NULL
);







