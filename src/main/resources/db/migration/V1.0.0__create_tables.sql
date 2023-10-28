create table categories
(
    active tinyint      null,
    id     bigint auto_increment
        primary key,
    name   varchar(255) null
);

create table products
(
    active      tinyint       null,
    price       float         null,
    stock       int           null,
    category_id bigint        null,
    id          bigint auto_increment
        primary key,
    description mediumtext    null,
    image       varchar(1000) null,
    name        varchar(255)  null,
    sku         varchar(100)  null,
    constraint FKog2rp4qthbtt2lfyhfo32lsw9
        foreign key (category_id) references categories (id)
);

create table user
(
    admin    bit          null,
    id       bigint auto_increment
        primary key,
    password varchar(100) null,
    username varchar(100) null
);