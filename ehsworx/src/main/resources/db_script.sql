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

create table app.obervationsMetadata( id int not null, meta_key varchar(100) not null, meta_value varchar(200) not null , active boolean);
ALTER TABLE APP.obervationsMetadata
ADD PRIMARY KEY(meta_key,meta_value);

insert into app.obervationsMetadata values ( 1, 'location','location_1',true);
insert into app.obervationsMetadata values ( 2, 'location','location_2',true);
insert into app.obervationsMetadata values ( 3, 'location','location_3',true);
insert into app.obervationsMetadata values ( 4, 'department','department_1',true);
insert into app.obervationsMetadata values ( 5, 'department','department_2',true);
insert into app.obervationsMetadata values ( 6, 'department','department_3',true);
insert into app.obervationsMetadata values ( 7, 'obeservertype','obeservertype_1',true);
insert into app.obervationsMetadata values ( 8, 'obeservertype','obeservertype_2',true);
insert into app.obervationsMetadata values ( 9, 'obeservertype','obeservertype_3',true);
insert into app.obervationsMetadata values ( 10, 'shoc','shoc_1',true);
insert into app.obervationsMetadata values ( 11, 'shoc','shoc_2',true);
insert into app.obervationsMetadata values ( 12, 'shoc','shoc_3',true);
insert into app.obervationsMetadata values ( 13, 'classification','classification_1',true);
insert into app.obervationsMetadata values ( 14, 'classification','classification_2',true);
insert into app.obervationsMetadata values ( 15, 'classification','classification_3',true);

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

update "APP"."EHS_SECURITY_USERPROFILE" set password = '$2a$10$vHVPkHxxFTyB/sXOUBuhaOH5gRqA/ZZ0aW3VK7fK0yvZBRGkvKigG' where username = 'chaudharydeepak08@gmail.com';