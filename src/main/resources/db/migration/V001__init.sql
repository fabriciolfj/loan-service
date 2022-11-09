create table customer(
  id            int8            NOT NULL GENERATED BY DEFAULT AS IDENTITY,
  birth_date    timestamp       not null,
  salary        numeric(15,4)   not null,
  document      varchar(255)    not null,
  score         int8            not null,
  primary key (id)
);

create table contract(
  id            int8            NOT NULL GENERATED BY DEFAULT AS IDENTITY,
  code          varchar(255)    not null,
  loan          numeric(15,4)   not null,
  portion       int8            not null,
  modality      varchar(15)     not null,
  date_creation timestamp       not null,
  rate          numeric(15,4)   not null,
  fees          numeric(15, 4)  not null,
  status        varchar(20)     not null,
  customer_id   int8            not null,
  primary key (id),
  constraint fk_customer_constract foreign key (customer_id) references customer(id)
);

create sequence hibernate_sequence