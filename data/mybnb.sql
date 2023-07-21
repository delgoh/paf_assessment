drop database if exists mybnb;
create database mybnb;

use mybnb;

create table acc_occupancy (
	acc_id varchar(10) not null,
    vacancy int,
    constraint pk_acc_id
		primary key (acc_id)
);

create table reservations (
	resv_id char(8) not null unique,
    name varchar(128),
    email varchar(128),
    acc_id varchar(10),
    arrival_date date,
    duration int,
    constraint pk_resv_id
		primary key (resv_id),
	constraint fk_reservations_acc_id
		foreign key (acc_id)
        references acc_occupancy(acc_id)
);