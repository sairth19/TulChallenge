CREATE TABLE PRODUCTS
(
    UUID        UUID        NOT NULL,
    NAME        VARCHAR(50) NOT NULL,
    SKU         VARCHAR(20) NOT NULL UNIQUE,
    DESCRIPTION VARCHAR(255) NOT NULL
);

CREATE TABLE CARTS
(
    UUID   UUID        NOT NULL,
    USER   VARCHAR(20) NOT NULL,
    STATUS CHAR(2)     NOT NULL DEFAULT 'PE'
);

CREATE TABLE PRODUCT_CARS
(
    PRODUCT_UUID UUID   NOT NULL,
    CART_UUID    UUID   NOT NULL,
    QUANTITY     INTEGER NOT NULL,
    FOREIGN KEY (PRODUCT_UUID) REFERENCES PRODUCTS (UUID),
    FOREIGN KEY (CART_UUID) REFERENCES CARTS (UUID)
);


INSERT INTO products
VALUES (RANDOM_UUID(), 'Samsung Galaxy S5', '6426276 ', 'Smartphone');
INSERT INTO products
VALUES (RANDOM_UUID(), 'Xbox One', '6415222',
        'The Xbox One is a line of home video game consoles developed by Microsoft. Announced in May 2013');
INSERT INTO products
VALUES (RANDOM_UUID(), 'Play Station 4', '5850905', 'Video game console');
INSERT INTO products
VALUES (RANDOM_UUID(), 'Xbox Series X', '6428324', 'Video game console');
INSERT INTO products
VALUES (RANDOM_UUID(), 'Play Station 5', '6426149', 'Video game console');
INSERT INTO products
VALUES (RANDOM_UUID(), 'Halo Infinite', '6414163', 'Video game console');
INSERT INTO products
VALUES (RANDOM_UUID(), 'God of War Ragnarok', '5358600', 'Video game console');
