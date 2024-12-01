ALTER TABLE ORDERS
    RENAME COLUMN shippingaddress TO shipping_address;

ALTER TABLE ORDERS
    RENAME COLUMN shippingcharge TO shipping_charge;

ALTER TABLE ORDERS
    RENAME COLUMN totalprice TO total_price;

ALTER TABLE ORDERS
    RENAME COLUMN isshipped TO is_shipped;

ALTER TABLE PRODUCTS
    RENAME COLUMN quantityinstock TO quantity_in_stock;

ALTER TABLE ORDER_PRODUCTS
    RENAME COLUMN productQuantity TO product_quantity;
