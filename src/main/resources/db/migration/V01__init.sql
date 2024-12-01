--V01__init.sql
DROP TABLE IF EXISTS customer_addresses;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_products;
DROP TABLE IF EXISTS products;

CREATE SEQUENCE customer_seq;
CREATE SEQUENCE customer_address_seq;
CREATE SEQUENCE product_seq;
CREATE SEQUENCE order_seq;
CREATE SEQUENCE order_product_seq;


ALTER SEQUENCE customer_seq RESTART WITH 1;
ALTER SEQUENCE customer_address_seq RESTART WITH 1;
ALTER SEQUENCE product_seq RESTART WITH 1;
ALTER SEQUENCE order_seq RESTART WITH 1;
ALTER SEQUENCE order_product_seq RESTART WITH 1;

CREATE TYPE product_status AS ENUM ('AVAILABLE', 'OUT_OF_STOCK', 'DISCONTINUED');


CREATE TABLE CUSTOMERS
(
    customer_id BIGINT PRIMARY KEY DEFAULT nextval('customer_seq'),
    name VARCHAR(100) NOT NULL ,
    phone_number VARCHAR(40),
    email VARCHAR(100) NOT NULL
);



CREATE TABLE  CUSTOMER_ADDRESSES
(
    customer_address_id BIGINT PRIMARY KEY DEFAULT nextval('customer_address_seq'),
    customer_id BIGINT NOT NULL REFERENCES CUSTOMERS(customer_id) ON DELETE CASCADE,
    address VARCHAR(100) NOT NULL
);



CREATE TABLE  PRODUCTS
(
    product_id BIGINT PRIMARY KEY DEFAULT nextval('product_seq'),
    candy_name VARCHAR(100) NOT NULL,
    description TEXT,
    price REAL NOT NULL,
    status VARCHAR(20) NOT NULL,
    quantity_in_stock INTEGER NOT NULL
);



CREATE TABLE ORDERS
(
    order_id BIGINT PRIMARY KEY DEFAULT nextval('order_seq'),
    shipping_address VARCHAR(100) NOT NULL,
    shipping_charge REAL NOT NULL,
    total_price REAL NOT NULL,
    is_shipped BOOLEAN DEFAULT FALSE,
    customer_id BIGINT NOT NULL REFERENCES CUSTOMERS(customer_id) ON DELETE CASCADE
);



CREATE TABLE ORDER_PRODUCTS

(
    order_product_id BIGINT PRIMARY KEY DEFAULT nextval('order_product_seq'),
    order_id BIGINT NOT NULL REFERENCES ORDERS(order_id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL REFERENCES PRODUCTS(product_id) ON DELETE CASCADE,
    product_quantity INTEGER NOT NULL
);






