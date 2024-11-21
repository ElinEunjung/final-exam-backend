CREATE TABLE if NOT EXISTS CUSTOMER_ADDRESSES
(
    customer_address_id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES CUSTOMERS(customer_id),
    address VARCHAR(100) NOT NULL
);

CREATE SEQUENCE customer_address_seq;
