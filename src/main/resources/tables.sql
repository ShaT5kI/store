CREATE TABLE suppliers
(
    id                SERIAL PRIMARY KEY,
    name              TEXT NOT NULL,
    documents_package BOOLEAN,
    guarantee         BOOLEAN
);

CREATE TABLE products
(
    id          SERIAL PRIMARY KEY,
    name        TEXT    NOT NULL,
    category    TEXT,
    price       NUMERIC(10, 2) DEFAULT 0.00
);

CREATE TABLE orders
(
    id             SERIAL PRIMARY KEY,
    date_ordered   DATE NOT NULL,
    date_delivered DATE,
    supplier_id    INTEGER REFERENCES suppliers (id),
    delivery_price NUMERIC(10, 2) DEFAULT 0.00,
    total_price    NUMERIC(10, 2) DEFAULT 0.00
);

CREATE TABLE stock
(
    id          SERIAL PRIMARY KEY,
    product_id  INTEGER REFERENCES products (id),
    cell_number INTEGER,
    quantity    INTEGER DEFAULT 0
);

CREATE TABLE returns
(
    id            SERIAL PRIMARY KEY,
    date_returned DATE    NOT NULL,
    product_id    INTEGER REFERENCES products (id),
    quantity      INTEGER NOT NULL DEFAULT 0,
    reason        TEXT,
    supplier_id   INTEGER REFERENCES suppliers (id),
    notes         TEXT
);

CREATE TABLE defects
(
    id            SERIAL PRIMARY KEY,
    product_id    INTEGER REFERENCES products (id),
    supplier_id   INTEGER REFERENCES suppliers (id),
    date_detected DATE NOT NULL,
    notes         TEXT
);

CREATE TABLE customer_requests
(
    id             SERIAL PRIMARY KEY,
    product_id     INTEGER REFERENCES products (id),
    quantity       INTEGER DEFAULT 0,
    customer_name  TEXT
);