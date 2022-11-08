create table contract(
  id            int8            NOT NULL GENERATED BY DEFAULT AS IDENTITY,
  code          varchar(255)    not null,
  document      varchar(255)    not null,
  loan          numeric(15,4)   not null,
  portion       int8            not null,
  modality      varchar(15)     not null,
  date_creation timestamp       not null,
  birth_date    timestamp       not null,
  salary        numeric(15,4)   not null,
  score         int8            not null,
  rate          numeric(15,4)   not null,
  fees          numeric(15, 4)  not null,
  primary key (id)
);

create sequence hibernate_sequence
