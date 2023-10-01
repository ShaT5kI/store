create table if not exists persons
(
    id   bigint       not null primary key,
    name varchar(255) not null
);

create table if not exists suppliers
(
    id                bigserial primary key,
    documents_package boolean      not null,
    guarantee         boolean      not null,
    name              varchar(255) not null
);

create table if not exists owners
(
    id          bigint not null primary key references persons (id),
    supplier_id bigint unique references suppliers (id)
);

create table if not exists customers
(
    id bigint not null primary key references persons (id)
);

create table if not exists products
(
    id          bigserial primary key,
    name        varchar(255) not null,
    price       integer      not null,
    supplier_id bigint references suppliers (id)
);

create table if not exists cars
(
    id          bigint       not null primary key,
    name        varchar(255) not null,
    customer_id bigint references customers (id)
);

create table if not exists orders
(
    id          bigserial primary key,
    is_open     boolean not null,
    customer_id bigint references customers,
    supplier_id bigint references suppliers (id)
);

create table if not exists stocks
(
    id          bigserial primary key,
    cell_number integer not null,
    quantity    integer not null,
    product_id  bigint references products (id)
);

create table if not exists cars_orders_products
(
    id         bigint not null primary key,
    car_id     bigint references cars (id),
    order_id   bigint references orders (id),
    product_id bigint references products (id)
);

create sequence persons_seq
    increment by 50;

create sequence cars_seq
    increment by 50;

create sequence cars_orders_products_seq
    increment by 50;

