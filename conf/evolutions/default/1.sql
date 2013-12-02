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




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table surfer;

SET FOREIGN_KEY_CHECKS=1;

