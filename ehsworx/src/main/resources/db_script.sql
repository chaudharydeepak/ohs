CREATE TABLE "APP"."EHS_SECURITY_USERPROFILE" (
		"ID" INTEGER,
		"USERNAME" VARCHAR(200) NOT NULL CONSTRAINT ESU_PK PRIMARY KEY,
		"PASSWORD" VARCHAR(100) NOT NULL,
		"FIRST_NAME" VARCHAR(100) NOT NULL,
		"LAST_NAME" VARCHAR(100) NOT NULL,
		"CREATED_DT" DATE NOT NULL,
		"CREATED_BY" VARCHAR(50),
		"MODIFIED_DT" DATE,
		"MODIFIED_BY" VARCHAR(50)
	);
	
alter table app.EHS_SECURITY_USERPROFILE add active boolean;
	
CREATE TABLE "APP"."EHS_SECURITY_USERAUTHORITY" (
		"USERNAME" VARCHAR(200) NOT NULL,
		"ROLE" VARCHAR(50) NOT NULL
	);
	
ALTER TABLE APP.EHS_SECURITY_USERAUTHORITY
ADD FOREIGN KEY (USERNAME) 
REFERENCES APP.EHS_SECURITY_USERPROFILE(USERNAME)	;

ALTER TABLE APP.EHS_SECURITY_USERAUTHORITY
ADD PRIMARY KEY(USERNAME);

insert into app.EHS_SECURITY_USERPROFILE(id, USERNAME, password, first_name, last_name, created_dt , active ) values(
1,
'chaudharydeepak08@gmail.com',
'Jan2010$',
'Deepak',
'Chaudhary',
CURRENT_DATE,
true);

insert into app.EHS_SECURITY_USERAUTHORITY values (
'chaudharydeepak08@gmail.com',
'ROLE_ADMIN');


create table app.usertoken ( id int not null , token varchar(500) not null , 
username VARCHAR(200) NOT NULL , expiryDate date not null);

ALTER TABLE APP.usertoken
ADD FOREIGN KEY(username) 
REFERENCES APP.EHS_SECURITY_USERPROFILE;

ALTER TABLE APP.usertoken
ADD expiryDate timestamp not null default current_timestamp;

ALTER TABLE APP.usertoken
ADD created_dt timestamp not null default current_timestamp;


alter table APP.usertoken drop column expiryDate;
alter table APP.usertoken drop column created_dt;


delete from app.usertoken;

create table app.obervationsMetadata( id int not null, meta_key varchar(100) not null, meta_value varchar(200) not null , active boolean);
ALTER TABLE APP.obervationsMetadata
ADD PRIMARY KEY(meta_key,meta_value);

insert into app.obervationsMetadata values ( 1, 'location','location_1',true);
insert into app.obervationsMetadata values ( 2, 'location','location_2',true);
insert into app.obervationsMetadata values ( 3, 'location','location_3',true);
insert into app.obervationsMetadata values ( 4, 'department','department_1',true);
insert into app.obervationsMetadata values ( 5, 'department','department_2',true);
insert into app.obervationsMetadata values ( 6, 'department','department_3',true);
insert into app.obervationsMetadata values ( 7, 'who_observed','obeservertype_1',true);
insert into app.obervationsMetadata values ( 8, 'who_observed','obeservertype_2',true);
insert into app.obervationsMetadata values ( 9, 'who_observed','obeservertype_3',true);
insert into app.obervationsMetadata values ( 10, 'type_of_observation','shoc_1',true);
insert into app.obervationsMetadata values ( 11, 'type_of_observation','shoc_2',true);
insert into app.obervationsMetadata values ( 12, 'type_of_observation','shoc_3',true);
insert into app.obervationsMetadata values ( 13, 'classification','classification_1',true);
insert into app.obervationsMetadata values ( 14, 'classification','classification_2',true);
insert into app.obervationsMetadata values ( 15, 'classification','classification_3',true);

select * from app.obervationsMetadata;

insert into app.obervationsMetadata values ( 16, 'areas','area_1',true);
insert into app.obervationsMetadata values ( 17, 'areas','area_2',true);
insert into app.obervationsMetadata values ( 18, 'areas','area_3',true);

insert into app.obervationsMetadata values ( 20, 'project','project_1',true);
insert into app.obervationsMetadata values ( 21, 'project','project_2',true);
insert into app.obervationsMetadata values ( 22, 'project','project_3',true);

delete from app.obervationsMetadata where meta_key = 'Areas'

CREATE SEQUENCE app.observation_number_id
AS INT
START WITH 1;

CREATE table app.ObservationMaster ( 
obs_id int not null CONSTRAINT OBS_PK PRIMARY KEY , 
status varchar(100) not null ,
active boolean not null,
reference varchar(200),  
location varchar(100) , 
department varchar(100), 
observrType varchar(100) not null, 
behalfOf varchar(100),
contact_info varchar(100), 
shoc varchar(100) not null, 
classification varchar(100) not null, 
obsTxt varchar(1000) not null,
respMgr varchar(100) not null , 
initiatedBy varchar(100) not null , 
creatd_dt date not null, 
creatd_by varchar(100) , 
modfd_dt date,
modfd_by varchar(100));

alter table app.ObservationMaster add obs_date date;

alter table app.ObservationMaster add project varchar(500);

alter table app.ObservationMaster add actiontxt varchar(500);

alter table app.ObservationMaster add area varchar(100);

CREATE table app.ObservationActions (
obs_id int not null,
action_id int not null,
action_txt varchar(500)
);

ALTER TABLE APP.ObservationActions
ADD FOREIGN KEY (obs_id) 
REFERENCES APP.ObservationMaster(obs_id)	;

CREATE table app.ObservationAttachmnts (
obs_id int not null,
attch_id int not null,
attach_type varchar(50),
attach_name varchar(500),
attach_path varchar(100));

ALTER TABLE APP.ObservationAttachmnts
ADD FOREIGN KEY (obs_id) 
REFERENCES APP.ObservationMaster(obs_id)	;


CREATE TABLE APP.OBSERVATIONACTION (
obs_id int not null,
actionText varchar(500),
actionBy varchar(100),
actionDt date
)

drop table APP.OBSERVATIONACTION

update "APP"."EHS_SECURITY_USERPROFILE" set password = '$2a$10$vHVPkHxxFTyB/sXOUBuhaOH5gRqA/ZZ0aW3VK7fK0yvZBRGkvKigG' where username = 'chaudharydeepak08@gmail.com';

select * from app.ObservationMaster 

select respMgr from app.ObservationMaster where obs_id=216

update APP.ObservationMaster set status = 'Assigned', active = true where obs_id = 224;

select * from app.ObservationActions


select FIRST_NAME || ' ' || LAST_NAME  user from app.EHS_SECURITY_USERPROFILE

select * from APP.usertoken

update APP.usertoken set expirydate = current_timestamp + 24;

values ({fn TIMESTAMPADD(SQL_TSI_HOUR, 24, CURRENT_TIMESTAMP)})

Select * from app.usertoken where token='4301653b-9386-4775-956f-ae4002c7814a' and username='chaudharydeepak08@gmail.com'