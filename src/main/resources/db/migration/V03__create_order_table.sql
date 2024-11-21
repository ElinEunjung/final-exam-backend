CREATE TABLE if NOT EXISTS ORDERS
(
    order_id BIGINT PRIMARY KEY,
    shippingAddress VARCHAR(100) NOT NULL,
    shippingCharge REAL NOT NULL,
    totalPrice REAL NOT NULL,
    isShipped BOOLEAN NOT NULL DEFAULT FALSE,
    customer_id BIGINT NOT NULL REFERENCES CUSTOMERS(customer_id)
);

CREATE SEQUENCE order_seq;
