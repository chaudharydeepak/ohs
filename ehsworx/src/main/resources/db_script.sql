drop table app.ehs_Security

CREATE TABLE "APP"."EHS_SECURITY" (
		"ID" INTEGER,
		"USERNAME" VARCHAR(50) PRIMARY KEY NOT NULL,
		"PASSWORD" VARCHAR(100) NOT NULL,
		"FIRST_NAME" VARCHAR(100) NOT NULL,
		"LAST_NAME" VARCHAR(100) NOT NULL,
		"EMAIL" VARCHAR(200) NOT NULL,
		"ROLE" VARCHAR(100) NOT NULL,
		"CREATED_DT" DATE NOT NULL,
		"CREATED_BY" VARCHAR(50),
		"MODIFIED_DT" DATE,
		"MODIFIED_BY" VARCHAR(50)
	);
	
	CREATE UNIQUE INDEX "APP"."SQL171124131807270" ON "APP"."EHS_SECURITY" ("USERNAME" ASC);
	
select * from app.ehs_security

insert into app.ehs_security(id, username, password, first_name, last_name, email, role, created_dt ) values(
1,
'deepak',
'Jan2010$',
'Deepak',
'Chaudhary',
'chaudharydeepak08@gmail.com',
'APP_ADMIN',
CURRENT_DATE)