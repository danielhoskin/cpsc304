CREATE SEQUENCE seq_userid
MINVALUE 1
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE seq_scheduleid
MINVALUE 1
START WITH 8
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE seq_bedid
MINVALUE 1
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE seq_activityid
MINVALUE 1
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE seq_billid
MINVALUE 1
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE seq_diagnosisid
MINVALUE 1
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE seq_ailmentid
MINVALUE 1
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE seq_prescriptionid
MINVALUE 1
START WITH 1
INCREMENT BY 1
NOCACHE;

CREATE SEQUENCE seq_noteid
MINVALUE 1
START WITH 1
INCREMENT BY 1
NOCACHE;

create table users
	(userid int not null,
	username varchar(30) not null,
	password varchar(30) not null,
	name varchar(30) not null,
	phonenumber char(12) not null,
	primary key(userid),
	unique(username))
SEGMENT CREATION IMMEDIATE;

create table patient(
	patientid int not null,
	dateofbirth date null,
	sex char(1) null,
	medicalhistory varchar(2000) null,
	primary key(patientid),
	foreign key(patientid)
		references users
		on delete cascade)
SEGMENT CREATION IMMEDIATE;

create table doctor(
	doctorid int not null,
	primary key(doctorid),
	foreign key(doctorid)
		references users
		on delete cascade)
SEGMENT CREATION IMMEDIATE;

create table receptionist(
	receptionistid int not null,
	primary key(receptionistid),
	foreign key(receptionistid)
		references users
		on delete cascade)
SEGMENT CREATION IMMEDIATE;

create table nurse(
	nurseid int not null,
	primary key(nurseid),
	foreign key(nurseid)
		references users
		on delete cascade)
SEGMENT CREATION IMMEDIATE;

create table bed(
	bedid int not null,
	location char(4) not null,
	primary key(bedid))
SEGMENT CREATION IMMEDIATE;

create table assignedto(
	patientid int not null,
	bedid int not null,
	admissiondate timestamp default systimestamp not null,		--changed datatype
	releasedate timestamp null,									--changed datatype
	primary key(patientid, bedid),
	foreign key(patientid)
		references patient,
	foreign key(bedid)
		references bed)
SEGMENT CREATION IMMEDIATE;

create table has_activity(
	activityid int not null,
	patientid int not null,
	doctorid int not null,
	nurseid int null,
	starttime timestamp not null,		--changed datatype
	endtime timestamp not null,			--changed datatype
	primary key(activityid, patientid, doctorid),
	foreign key(patientid)
		references patient,
	foreign key(doctorid)
		references doctor)
SEGMENT CREATION IMMEDIATE;

create table appointment(
	activityid int not null,
	patientid int not null,
	doctorid int not null,
	notes varchar(2000) null,
	primary key(activityid, patientid, doctorid),
	foreign key(activityid, patientid, doctorid)
		references has_activity
		on delete cascade)
SEGMENT CREATION IMMEDIATE;

create table operation(
	activityid int not null,
	patientid int not null,
	doctorid int not null,
	type varchar(20) not null,
	cost float not null,
	primary key(activityid, patientid, doctorid),
	foreign key(activityid, patientid, doctorid)
		references has_activity
		on delete cascade)
SEGMENT CREATION IMMEDIATE;

create table has_dayschedule(
	userid int not null,
	dayid int not null,					--changed datatype | 1 = sunday, 7 = saturday
	timefrom timestamp null,			--changed datatype
	timeto timestamp null,				--changed datatype
	primary key(userid, dayid),
	foreign key(userid)
		references users
		on delete cascade)
SEGMENT CREATION IMMEDIATE;

create table has_bill(
	patientid int not null,
	billid int not null,
	amountdue float null,
	amountpaid float null,
	day timestamp default systimestamp null,				--changed datatype, added default value
	primary key(patientid, billid),
	foreign key(patientid)
		references patient
		on delete cascade)
SEGMENT CREATION IMMEDIATE;

create table generatesoperationbill(
	activityid int not null,
	patientid int not null,
	doctorid int not null,
	billid int not null,
	receptionistid int not null,
	primary key(activityid, patientid, doctorid, billid, receptionistid),
	foreign key(activityid, patientid, doctorid)
		references operation
		on delete cascade,
	foreign key(patientid, billid)
		references has_bill,
	foreign key(receptionistid)
		references receptionist)
SEGMENT CREATION IMMEDIATE;

create table generatesbedbill(
	patientid int not null,
	bedid int not null,
	billid int not null,
	receptionistid int not null,
	primary key(patientid, bedid, billid, receptionistid),
	foreign key(patientid, bedid)
		references assignedto
		on delete cascade,
	foreign key(patientid, billid)
		references has_bill,
	foreign key(receptionistid)
		references receptionist)
SEGMENT CREATION IMMEDIATE;

create table monitors(
	patientid int not null,
	nurseid int not null,
	notes varchar(2000) null,
	primary key(patientid, nurseid),
	foreign key(patientid)
		references patient,
	foreign key(nurseid)
		references nurse)
SEGMENT CREATION IMMEDIATE;

create table gives_task(
	doctorid int not null,
	nurseid int not null,
	notes varchar(2000) not null,
	primary key(doctorid, nurseid),
	foreign key(doctorid)
		references doctor,
	foreign key(nurseid)
		references nurse)
SEGMENT CREATION IMMEDIATE;

create table has_diagnosis(
	activityid int not null,
	patientid int not null,
	doctorid int not null,
	diagnosisid int not null,
	primary key(activityid, patientid, doctorid, diagnosisid),
	foreign key(activityid, patientid, doctorid) 
		references has_activity
		on delete cascade)
SEGMENT CREATION IMMEDIATE;

create table has_ailment(
	activityid int not null,
	patientid int not null,
	doctorid int not null,
	diagnosisid int not null,
	ailmentid int not null,
	details varchar(200) null,
	primary key(activityid, patientid, doctorid, diagnosisid, ailmentid),
	foreign key(activityid, patientid, doctorid, diagnosisid)
		references has_diagnosis
		on delete cascade)
SEGMENT CREATION IMMEDIATE;

create table has_prescription(
	activityid int not null,
	patientid int not null,
	doctorid int not null,
	diagnosisid int not null,
	prescriptionid int not null,
	details varchar(200) null,
	primary key(activityid, patientid, doctorid, diagnosisid, prescriptionid),
	foreign key(activityid, patientid, doctorid, diagnosisid)
		references has_diagnosis
		on delete cascade)
SEGMENT CREATION IMMEDIATE;

create table has_note(
	activityid int not null,
	patientid int not null,
	doctorid int not null,
	diagnosisid int not null,
	noteid int not null,
	body varchar(2000) null,
	primary key(activityid, patientid, doctorid, diagnosisid, noteid),
	foreign key(activityid, patientid, doctorid, diagnosisid)
		references has_diagnosis
		on delete cascade)
SEGMENT CREATION IMMEDIATE;