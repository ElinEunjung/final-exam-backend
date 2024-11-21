CREATE TABLE if NOT EXISTS CUSTOMERS
(
    customer_id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL ,
    phoneNumber VARCHAR(40),
    email VARCHAR(100)
   );

CREATE SEQUENCE customer_seq;
