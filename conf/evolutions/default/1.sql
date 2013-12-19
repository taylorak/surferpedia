# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table surfer (
  slug                      varchar(255) not null,
  name                      varchar(255),
  home                      varchar(255),
  awards                    varchar(255),
  carousel_url              varchar(255),
  bio_url                   varchar(255),
  bio                       clob,
  type                      varchar(255),
  footstyle                 varchar(255),
  country                   varchar(255),
  vid_url                   varchar(255),
  id                        bigint,
  constraint pk_surfer primary key (slug))
;

create table surfer_update (
  id                        bigint not null,
  date                      timestamp,
  action                    varchar(255),
  surfer                    varchar(255),
  constraint pk_surfer_update primary key (id))
;

create table user (
  email                     varchar(255) not null,
  first                     varchar(255),
  last                      varchar(255),
  password                  varchar(255),
  admin                     boolean,
  constraint pk_user primary key (email))
;

create sequence surfer_seq;

create sequence surfer_update_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists surfer;

drop table if exists surfer_update;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists surfer_seq;

drop sequence if exists surfer_update_seq;

drop sequence if exists user_seq;

