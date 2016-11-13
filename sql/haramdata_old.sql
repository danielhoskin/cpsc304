drop table has_note;
drop table has_prescription;
drop table has_ailment;
drop table has_diagnosis;
drop table gives_task;
drop table monitors;
drop table generatesbedbill;
drop table generatesoperationbill;
drop table has_bill;
drop table has_dayschedule;
drop table operation;
drop table appointment;
drop table has_activity;
drop table assignedto;
drop table bed;
drop table nurse;
drop table receptionist;
drop table doctor;
drop table patient;
drop table users;

drop sequence seq_userid;
drop sequence seq_scheduleid;
drop sequence seq_bedid;
drop sequence seq_activityid;
drop sequence seq_billid;
drop sequence seq_diagnosisid;
drop sequence seq_ailmentid;
drop sequence seq_prescriptionid;
drop sequence seq_noteid;

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
	unique(username));

create table patient(
	patientid int not null,
	dateofbirth date null,
	sex char(1) null,
	medicalhistory varchar(2000) null,
	primary key(patientid),
	foreign key(patientid)
		references users
		on delete cascade);

create table doctor(
	doctorid int not null,
	primary key(doctorid),
	foreign key(doctorid)
		references users
		on delete cascade);

create table receptionist(
	receptionistid int not null,
	primary key(receptionistid),
	foreign key(receptionistid)
		references users
		on delete cascade);

create table nurse(
	nurseid int not null,
	primary key(nurseid),
	foreign key(nurseid)
		references users
		on delete cascade);

create table bed(
	bedid int not null,
	location char(4) not null,
	primary key(bedid));

create table assignedto(
	patientid int not null,
	bedid int not null,
	admissiondate timestamp default systimestamp not null,		#changed datatype
	releasedate timestamp null,									#changed datatype
	primary key(patientid, bedid),
	foreign key(patientid)
		references patient,
	foreign key(bedid)
		references bed);

create table has_activity(
	activityid int not null,
	patientid int not null,
	doctorid int not null,
	nurseid int null,
	starttime timestamp not null,		#changed datatype
	endtime timestamp not null,			#changed datatype
	primary key(activityid, patientid, doctorid),
	foreign key(patientid)
		references patient,
	foreign key(doctorid)
		references doctor);

create table appointment(
	activityid int not null,
	patientid int not null,
	doctorid int not null,
	notes varchar(2000) null,
	primary key(activityid, patientid, doctorid),
	foreign key(activityid, patientid, doctorid)
		references has_activity
		on delete cascade);

create table operation(
	activityid int not null,
	patientid int not null,
	doctorid int not null,
	type varchar(20) not null,
	cost float not null,
	primary key(activityid, patientid, doctorid),
	foreign key(activityid, patientid, doctorid)
		references has_activity
		on delete cascade);

create table has_dayschedule(
	userid int not null,
	dayid int not null,					#changed datatype | 1 = sunday, 7 = saturday
	timefrom timestamp default '00-00-00 09:00:00.000000' null,			#changed datatype
	timeto timestamp default '00-00-00 17:00:00.000000' null,			#changed datatype
	available boolean not null,		#added variable
	primary key(userid, dayid),
	foreign key(userid)
		references users
		on delete cascade);

create table has_bill(
	patientid int not null,
	billid int not null,
	amountdue float null,
	amountpaid float null,
	day timestamp default systimestamp null,				#changed datatype, added default value
	primary key(patientid, billid),
	foreign key(patientid)
		references patient
		on delete cascade);

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
		references receptionist);

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
		references receptionist);

create table monitors(
	patientid int not null,
	nurseid int not null,
	notes varchar(2000) null,
	primary key(patientid, nurseid),
	foreign key(patientid)
		references patient,
	foreign key(nurseid)
		references nurse);

create table gives_task(
	doctorid int not null,
	nurseid int not null,
	notes varchar(2000) not null,
	primary key(doctorid, nurseid),
	foreign key(doctorid)
		references doctor,
	foreign key(nurseid)
		references nurse);

create table has_diagnosis(
	activityid int not null,
	patientid int not null,
	doctorid int not null,
	diagnosisid int not null,
	primary key(activityid, patientid, doctorid, diagnosisid),
	foreign key(activityid, patientid, doctorid) 
		references has_activity
		on delete cascade);

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
		on delete cascade);

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
		on delete cascade);

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
		on delete cascade);



insert into users
values(54839217, 'example1', 'd3rmpn', 'john', '778-993-9326');

insert into users
values(54921583, 'example2', 'du0qug', 'denis ', '778-632-9469');

insert into users
values(38993158, 'example3', '2dzqjr', 'gehrke', '778-532-3928');

insert into users
values(43219832, 'example4', 'lgq4hz', 'mary', '778-439-4915');

insert into users
values(58302157, 'example5', 'u7sces', 'bjarne', '778-903-3917');

insert into users
values(18392183, 'example6', 'bo4vjo', 'dr. mitchell', '778-993-4738');

insert into users
values(84910375, 'example7', 'qqc81p', 'dr. lawrence', '778-632-9369');

insert into users
values(38103827, 'example8', 'ap1xbb', 'dr. patrice', '778-532-3888');

insert into users
values(47583921, 'example9', 'go8uqo', 'dr. bernard', '778-439-4944');

insert into users
values(49193857, 'example10', 'ket2bp', 'dr. leroy', '778-903-3438');

insert into users
values(68965438, 'example11', 't96rk2', 'suzi', '778-934-8391');

insert into users
values(48372917, 'example12', 'jc0p9n', 'ramakrishnan', '778-432-4381');

insert into users
values(43817392, 'example13', 'tk5w4p', 'lee', '778-764-4313');

insert into users
values(47381037, 'example14', '9oqzpv', 'jame', '778-813-4817');

insert into users
values(38173921, 'example15', 'dlho9s', 'tupel', '778-481-5718');

insert into users
values(49103857, 'example16', 'fajinn92', 'peter', '778-829-3857');

insert into users
values(18392058, 'example17', 'dixhh93', 'felix', '778-999-8183');

insert into users
values(18385093, 'example18', 'fdsai972', 'marcus', '778-444-8189');

insert into users
values(48217282, 'example19', 'dlhfdda', 'finn', '778-222-3819');

insert into users
values(12938183, 'example20', 'dlhd9s', 'jeff', '778-111-9391');



insert into patient
values(54839217, '19791014', 'm', 'filler...');

insert into patient
values(54921583, '19900501', 'm', 'filler...');

insert into patient
values(38993158, '19431218', 'f', 'filler...');

insert into patient
values(43219832, '19480207', 'f', 'filler...');

insert into patient
values(58302157, '19820610', 'm', 'filler...');



insert into doctor
values(18392183);

insert into doctor
values(84910375);

insert into doctor
values(38103827);

insert into doctor
values(47583921);

insert into doctor
values(49193857);



insert into receptionist
values(68965438);

insert into receptionist
values(48372917);

insert into receptionist
values(43817392);

insert into receptionist
values(47381037);

insert into receptionist
values(38173921);



insert into nurse
values(49103857);

insert into nurse
values(18392058);

insert into nurse
values(18385093);

insert into nurse
values(48217282);

insert into nurse
values(12938183);



insert into bed
values(1, '300a');

insert into bed
values(2, '300a');

insert into bed
values(3, '300a');

insert into bed
values(4, '300a');

insert into bed
values(5, '300a');

insert into bed
values(6, '300b');

insert into bed
values(7, '300b');

insert into bed
values(8, '300b');

insert into bed
values(9, '300b');

insert into bed
values(10, '300b');



insert into assignedto
values(54839217, 1, '20161016', '20161024');

insert into assignedto
values(54921583, 2, '20161020', null);

insert into assignedto
values(38993158, 3, '20161019', '20161027');

insert into assignedto
values(43219832, 4, '20161017', '20161025');

insert into assignedto
values(58302157, 5, '20161021', null);



insert into has_activity
values(511, 54839217, 18392183, null, '20161016 15:30:00', '20161016 17:30:00');

insert into has_activity
values(591, 54921583, 84910375, null, '20161020 15:30:00', '20161020 16:00:00');

insert into has_activity
values(596, 38993158, 38103827, null, '20161019 10:00:00', '20161019 12:00:00');

insert into has_activity
values(574, 43219832, 47583921, 4910385, '20161017 12:30:00', '20161017 14:30:00');

insert into has_activity
values(518, 58302157, 49193857, 1839205, '20161021 13:45:00', '20161021 14:00:00');

insert into has_activity
values(611, 54839217, 18392183, null, '20161017 15:30:00', '20161017 15:35:00');

insert into has_activity
values(691, 54921583, 84910375, null, '20161021 15:30:00', '20161021 15:35:00');

insert into has_activity
values(696, 38993158, 38103827, null, '20161020 10:00:00', '20161020 10:05:00');

insert into has_activity
values(674, 43219832, 47583921, 4910385, '20161018 12:30:00', '20161018 12:35:00');

insert into has_activity
values(618, 58302157, 49193857, 18392058, '20161022 13:45:00', '20161022 13:50:00');



insert into appointment
values(511, 54839217, 18392183, 'filler....');

insert into appointment
values(591, 54921583, 84910375, 'filler....');

insert into appointment
values(596, 38993158, 38103827, 'filler....');

insert into appointment
values(574, 43219832, 47583921, 'filler....');

insert into appointment
values(518, 58302157, 49193857, 'filler....');



insert into operation
values(611, 54839217, 18392183, 'appendectomy', 100000.00);

insert into operation
values(691, 54921583, 84910375, 'breast biopsy', 1000000.00);

insert into operation
values(696, 38993158, 38103827, 'cataract surgery', 500000.00);

insert into operation
values(674, 43219832, 47583921, 'cholecystectomy', 650000.00);

insert into operation
values(618, 58302157, 49193857, 'tonsillectomy', 750000.00);



insert into has_dayschedule
values(18392183, '20161016', '08:00:00', '18:00:00');

insert into has_dayschedule
values(54839217, '20161016', '08:00:00', '18:00:00');

insert into has_dayschedule
values(84910375, '20161020', '08:00:00', '18:00:00');

insert into has_dayschedule
values(54921583, '20161020', '08:00:00', '18:00:00');

insert into has_dayschedule
values(38993158, '20161019', '08:00:00', '18:00:00');

insert into has_dayschedule
values(38103827, '20161019', '08:00:00', '18:00:00');

insert into has_dayschedule
values(43219832, '20161017', '08:00:00', '18:00:00');

insert into has_dayschedule
values(47583921, '20161017', '08:00:00', '18:00:00');

insert into has_dayschedule
values(58302157, '20161021', '08:00:00', '18:00:00');

insert into has_dayschedule
values(49193857, '20161021', '08:00:00', '18:00:00');



insert into has_bill
values(54839217, 1, 100000.00, 100000.00, '20161024');

insert into has_bill
values(54921583, 2, null, null, null);

insert into has_bill
values(38993158, 3, 500000.00, 500000.00, '20161027');

insert into has_bill
values(43219832, 4, 650000.00, 650000.00, '20161025');

insert into has_bill
values(58302157, 5, null, null, null);



insert into generatesoperationbill
values(611, 54839217, 18392183, 1, 68965438);

insert into generatesoperationbill
values(691, 54921583, 84910375, 2, 48372917);

insert into generatesoperationbill
values(696, 38993158, 38103827, 3, 43817392);

insert into generatesoperationbill
values(674, 43219832, 47583921, 4, 47381037);

insert into generatesoperationbill
values(618, 58302157, 49193857, 5, 38173921);



insert into generatesbedbill
values(54839217, 1, 1, 68965438);

insert into generatesbedbill
values(54921583, 2, 2, 48372917);

insert into generatesbedbill
values(38993158, 3, 3, 43817392);

insert into generatesbedbill
values(43219832, 4, 4, 47381037);

insert into generatesbedbill
values(58302157, 5, 5, 38173921);



insert into monitors
values(54839217, 49103857, null);

insert into monitors
values(54921583, 18392058, null);

insert into monitors
values(38993158, 18385093, null);

insert into monitors
values(43219832, 48217282, null);

insert into monitors
values(58302157, 12938183, null);



insert into gives_task
values(18392183, 49103857, 'stop eating the iv bags');

insert into gives_task
values(84910375, 18392058, 'clean up this room');

insert into gives_task
values(38103827, 18385093, 'unclog drainage pipe');

insert into gives_task
values(47583921, 48217282, 'can you make sure this patient only eats gluten-free gluten');

insert into gives_task
values(49193857, 12938183, 'refill soap in room 41b');




insert into has_diagnosis
values(511, 54839217, 18392183, 91446272);

insert into has_diagnosis
values(591, 54921583, 84910375, 12632847);

insert into has_diagnosis
values(596, 38993158, 38103827, 94827107);

insert into has_diagnosis
values(574, 43219832, 47583921, 17296750);

insert into has_diagnosis
values(518, 58302157, 49193857, 48322025);



insert into has_ailment
values(511, 54839217, 18392183, 91446272, 5, 'severe');

insert into has_ailment
values(591, 54921583, 84910375, 12632847, 12, 'mild');

insert into has_ailment
values(596, 38993158, 38103827, 94827107, 18, 'pretty bad');

insert into has_ailment
values(574, 43219832, 47583921, 17296750, 21, 'recovering');

insert into has_ailment
values(518, 58302157, 49193857, 48322025, 51, 'certain death');



insert into has_prescription
values(511, 54839217, 18392183, 91446272, 992, '50 mg/day');

insert into has_prescription
values(591, 54921583, 84910375, 12632847, 135, 'two times a day before meals');

insert into has_prescription
values(596, 38993158, 38103827, 94827107, 6314, 'once a day');

insert into has_prescription
values(574, 43219832, 47583921, 17296750, 467, 'monthly');

insert into has_prescription
values(518, 58302157, 49193857, 48322025, 123, 'come in every week');



insert into has_note
values(511, 54839217, 18392183, 91446272, 17, 'infection spreading to kidneys');

insert into has_note
values(591, 54921583, 84910375, 12632847, 12, 'shows symptoms of returning ailment');

insert into has_note
values(596, 38993158, 38103827, 94827107, 62, 'keep confined to ward');

insert into has_note
values(574, 43219832, 47583921, 17296750, 67, 'recovering well');

insert into has_note
values(518, 58302157, 49193857, 48322025, 23, 'he''s gonna die soon');