CREATE TABLE if NOT EXISTS ORDER_PRODUCTS
(
    order_product_id BIGINT PRIMARY KEY,
    order_id BIGINT NOT NULL REFERENCES ORDERS(order_id),
    product_id BIGINT NOT NULL REFERENCES PRODUCTS(product_id),
    productQuantity INTEGER NOT NULL
);

CREATE SEQUENCE order_product_seq;
