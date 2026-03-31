--liquibase formatted sql

--changeset tpe:1
create table address_entity
(
    id      int auto_increment
        primary key,
    city    varchar(255) null,
    country varchar(150) null,
    number  varchar(20)  null,
    street  varchar(255) null
);

create table category_entity
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

create table client_entity
(
    id         int auto_increment
        primary key,
    created_at datetime(6)  null,
    email      varchar(255) not null,
    is_active  bit          null,
    password   varchar(255) null,
    updated_at datetime(6)  null,
    username   varchar(255) null,
    address_id int          null,
    constraint UKkmppj0slc23iap61bqoil9wk6
        unique (email),
    constraint FKrycawy7tdhg2uymsn4ysqhbhc
        foreign key (address_id) references address_entity (id)
);

create table comment_entity
(
    id         int auto_increment
        primary key,
    body       varchar(255) null,
    created_at datetime(6)  null,
    header     varchar(255) null,
    updated_at datetime(6)  null,
    client_id  int          null,
    constraint FK89uco3i6ylu07r76bni3v3irx
        foreign key (client_id) references client_entity (id)
);

create table order_entity
(
    id        int auto_increment
        primary key,
    create_at datetime(6) null,
    is_done   bit         null,
    client_id int         null,
    constraint FKr6wyb1ksam8lrmmsrxet9xouf
        foreign key (client_id) references client_entity (id)
);

create table product_entity
(
    id          int auto_increment
        primary key,
    description varchar(255) null,
    name        varchar(255) null,
    price       double       null,
    quantity    int          null,
    url         varchar(255) null,
    category_id int          null,
    constraint FK8kxxmqdokh3lthvw0t148w0bc
        foreign key (category_id) references category_entity (id)
);

create table image_entity
(
    id         int auto_increment
        primary key,
    path       varchar(255) null,
    product_id int          null,
    constraint FKrna9so4bjernm1yis2t27g99x
        foreign key (product_id) references product_entity (id)
);

create table label_entity
(
    id             int auto_increment
        primary key,
    description_en varchar(255) null,
    description_fr varchar(255) null,
    description_ru varchar(255) null,
    title_en       varchar(255) null,
    title_fr       varchar(255) null,
    title_ru       varchar(255) null,
    product_id     int          null,
    constraint UK997xr1cruurhk9lu4iia9mp0a
        unique (product_id),
    constraint FKt2qrqkttyohuejtcb0km19iw1
        foreign key (product_id) references product_entity (id)
);

create table order_product_entity
(
    id               int auto_increment
        primary key,
    quantity_product int null,
    order_id         int null,
    product_id       int null,
    constraint FK6c2ka8wdi4ex825504smlmbys
        foreign key (product_id) references product_entity (id),
    constraint FKhsxw661ao11rdxc1pkclbptn8
        foreign key (order_id) references order_entity (id)
);

create table role_entity
(
    id   int auto_increment
        primary key,
    name enum ('ROLE_ADMIN', 'ROLE_USER') null
);

create table client_role_entity
(
    client_id int not null,
    role_id   int not null,
    primary key (client_id, role_id),
    constraint FK3o8arngdjhearngbm162c455i
        foreign key (role_id) references role_entity (id),
    constraint FKgverq2163rxgo1798wckphxfo
        foreign key (client_id) references client_entity (id)
);

create table supplier_entity
(
    id                  int auto_increment
        primary key,
    registration_number varchar(150) null,
    address_id          int          null,
    product_id          int          null,
    constraint FK4hr98q6f3iu0wuyo1e7ku325v
        foreign key (product_id) references product_entity (id),
    constraint FKdc7aoci2itm4ybc160dur3kka
        foreign key (address_id) references address_entity (id)
);