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
  bio                       longtext,
  type                      varchar(255),
  footstyle                 varchar(255),
  country                   varchar(255),
  vid_url                   varchar(255),
  posted_on                 datetime,
  constraint pk_surfer primary key (slug))
;

create table surfer_update (
  id                        bigint auto_increment not null,
  date                      datetime,
  action                    varchar(255),
  surfer                    varchar(255),
  constraint pk_surfer_update primary key (id))
;

create table user (
  email                     varchar(255) not null,
  first                     varchar(255),
  last                      varchar(255),
  password                  varchar(255),
  admin                     tinyint(1) default 0,
  constraint pk_user primary key (email))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table surfer;

drop table surfer_update;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

