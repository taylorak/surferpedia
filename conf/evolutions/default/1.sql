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
  constraint pk_surfer primary key (slug))
;

create table user_info (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  admin                     tinyint(1) default 0,
  constraint pk_user_info primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table surfer;

drop table user_info;

SET FOREIGN_KEY_CHECKS=1;

