insert into users
values(seq_userid.nextval, 'example1', 'd3rmpn', 'john', '778-993-9326');

insert into users
values(seq_userid.nextval, 'example2', 'du0qug', 'denis ', '778-632-9469');

insert into users
values(seq_userid.nextval, 'example3', '2dzqjr', 'gehrke', '778-532-3928');

insert into users
values(seq_userid.nextval, 'example4', 'lgq4hz', 'mary', '778-439-4915');

insert into users
values(seq_userid.nextval, 'example5', 'u7sces', 'bjarne', '778-903-3917');

insert into users
values(seq_userid.nextval, 'example6', 'bo4vjo', 'dr. mitchell', '778-993-4738');

insert into users
values(seq_userid.nextval, 'example7', 'qqc81p', 'dr. lawrence', '778-632-9369');

insert into users
values(seq_userid.nextval, 'example8', 'ap1xbb', 'dr. patrice', '778-532-3888');

insert into users
values(seq_userid.nextval, 'example9', 'go8uqo', 'dr. bernard', '778-439-4944');

insert into users
values(seq_userid.nextval, 'example10', 'ket2bp', 'dr. leroy', '778-903-3438');

insert into users
values(seq_userid.nextval, 'example11', 't96rk2', 'suzi', '778-934-8391');

insert into users
values(seq_userid.nextval, 'example12', 'jc0p9n', 'ramakrishnan', '778-432-4381');

insert into users
values(seq_userid.nextval, 'example13', 'tk5w4p', 'lee', '778-764-4313');

insert into users
values(seq_userid.nextval, 'example14', '9oqzpv', 'jame', '778-813-4817');

insert into users
values(seq_userid.nextval, 'example15', 'dlho9s', 'tupel', '778-481-5718');

insert into users
values(seq_userid.nextval, 'example16', 'fajinn92', 'peter', '778-829-3857');

insert into users
values(seq_userid.nextval, 'example17', 'dixhh93', 'felix', '778-999-8183');

insert into users
values(seq_userid.nextval, 'example18', 'fdsai972', 'marcus', '778-444-8189');

insert into users
values(seq_userid.nextval, 'example19', 'dlhfdda', 'finn', '778-222-3819');

insert into users
values(seq_userid.nextval, 'example20', 'dlhd9s', 'jeff', '778-111-9391');



insert into patient
values(1, '1979-10-14', 'm', 'filler...');

insert into patient
values(2, '1990-05-01', 'm', 'filler...');

insert into patient
values(3, '1943-12-18', 'f', 'filler...');

insert into patient
values(4, '1948-02-07', 'f', 'filler...');

insert into patient
values(5, '1982-06-10', 'm', 'filler...');



insert into doctor
values(6);

insert into doctor
values(7);

insert into doctor
values(8);

insert into doctor
values(9);

insert into doctor
values(10);



insert into receptionist
values(11);

insert into receptionist
values(12);

insert into receptionist
values(13);

insert into receptionist
values(14);

insert into receptionist
values(15);



insert into nurse
values(16);

insert into nurse
values(17);

insert into nurse
values(18);

insert into nurse
values(19);

insert into nurse
values(20);



insert into bed
values(seq_bedid.nextval, '300a');

insert into bed
values(seq_bedid.nextval, '300a');

insert into bed
values(seq_bedid.nextval, '300a');

insert into bed
values(seq_bedid.nextval, '300a');

insert into bed
values(seq_bedid.nextval, '300a');

insert into bed
values(seq_bedid.nextval, '300b');

insert into bed
values(seq_bedid.nextval, '300b');

insert into bed
values(seq_bedid.nextval, '300b');

insert into bed
values(seq_bedid.nextval, '300b');

insert into bed
values(seq_bedid.nextval, '300b');



insert into assignedto
values(1, 1, '2016-10-16 12:30:00.000000', '2016-10-24 17:00:00.000000');

insert into assignedto
values(2, 2, '2016-10-20 09:00:00.000000', null);

insert into assignedto
values(3, 3, '2016-10-19 15:00:00.000000', '2016-10-27 11:00:00.000000');

insert into assignedto
values(4, 4, '2016-10-17 14:00:00.000000', '2016-10-25 17:30:00.000000');

insert into assignedto
values(5, 5, '2016-10-21 11:30:00.000000', null);



insert into has_activity
values(seq_activityid.nextval, 1, 6, null, '2016-10-17 15:30:00', '2016-10-17 15:35:00');

insert into has_activity
values(seq_activityid.nextval, 2, 7, null, '2016-10-21 15:30:00', '2016-10-21 15:35:00');

insert into has_activity
values(seq_activityid.nextval, 3, 8, null, '2016-10-20 10:00:00', '2016-10-20 10:05:00');

insert into has_activity
values(seq_activityid.nextval, 4, 9, 16, '2016-10-18 12:30:00', '2016-10-18 12:35:00');

insert into has_activity
values(seq_activityid.nextval, 5, 10, 17, '2016-10-22 13:45:00', '2016-10-22 13:50:00');

insert into has_activity
values(seq_activityid.nextval, 1, 6, null, '2016-10-16 15:30:00', '2016-10-16 16:30:00');

insert into has_activity
values(seq_activityid.nextval, 2, 7, null, '2016-10-20 15:30:00', '2016-10-20 16:00:00');

insert into has_activity
values(seq_activityid.nextval, 3, 8, null, '2016-10-19 10:00:00', '2016-10-19 12:00:00');

insert into has_activity
values(seq_activityid.nextval, 4, 9, 16, '2016-10-17 12:30:00', '2016-10-17 14:30:00');

insert into has_activity
values(seq_activityid.nextval, 5, 10, 17, '2016-10-21 13:45:00', '2016-10-21 14:00:00');



insert into appointment
values(1, 1, 6, 'filler....');

insert into appointment
values(2, 2, 7, 'filler....');

insert into appointment
values(3, 3, 8, 'filler....');

insert into appointment
values(4, 4, 9, 'filler....');

insert into appointment
values(5, 5, 10, 'filler....');



insert into operation
values(6, 1, 6, 'appendectomy', 100000.00);

insert into operation
values(7, 2, 7, 'breast biopsy', 1000000.00);

insert into operation
values(8, 3, 8, 'cataract surgery', 500000.00);

insert into operation
values(9, 4, 9, 'cholecystectomy', 650000.00);

insert into operation
values(10, 5, 10, 'tonsillectomy', 750000.00);



-- you should be able to assume has_dayschedule entries for dayid = 1 -> 7 are created upon creation of the user otherwise default to 9-5
-- but in this case we will specify
insert into has_dayschedule values(1, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(1, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(1, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(1, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(1, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(1, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(1, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(2, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(2, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(2, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(2, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(2, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(2, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(2, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(3, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(3, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(3, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(3, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(3, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(3, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(3, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(4, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(4, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(4, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(4, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(4, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(4, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(4, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(5, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(5, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(5, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(5, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(5, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(5, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(5, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(6, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(6, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(6, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(6, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(6, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(6, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(6, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(7, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(7, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(7, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(7, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(7, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(7, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(7, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(8, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(8, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(8, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(8, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(8, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(8, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(8, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(9, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(9, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(9, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(9, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(9, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(9, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(9, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(10, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(10, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(10, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(10, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(10, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(10, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(10, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(11, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(11, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(11, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(11, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(11, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(11, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(11, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(12, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(12, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(12, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(12, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(12, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(12, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(12, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(13, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(13, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(13, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(13, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(13, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(13, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(13, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(14, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(14, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(14, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(14, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(14, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(14, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(14, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(15, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(15, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(15, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(15, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(15, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(15, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(15, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(16, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(16, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(16, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(16, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(16, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(16, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(16, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(17, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(17, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(17, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(17, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(17, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(17, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(17, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(18, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(18, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(18, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(18, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(18, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(18, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(18, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(19, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(19, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(19, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(19, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(19, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(19, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(19, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule values(20, 1, '0001-01-01 09:00:00', '0001-01-01 17:00:00'); 
insert into has_dayschedule values(20, 2, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(20, 3, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(20, 4, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(20, 5, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(20, 6, '0001-01-01 09:00:00', '0001-01-01 17:00:00');
insert into has_dayschedule values(20, 7, '0001-01-01 09:00:00', '0001-01-01 17:00:00');

insert into has_dayschedule
values(6, seq_scheduleid.nextval, '2016-10-16 00:00:00', '2016-10-16 00:00:00');


insert into has_bill
values(1, seq_billid.nextval, 100000.00, 100000.00, '2016-10-24 17:00:00.000000');

insert into has_bill
values(2, seq_billid.nextval, null, null, null);

insert into has_bill
values(3, seq_billid.nextval, 500000.00, 500000.00, '2016-10-27 11:00:00.000000');

insert into has_bill
values(4, seq_billid.nextval, 650000.00, 650000.00, '2016-10-25 17:30:00.000000');

insert into has_bill
values(5, seq_billid.nextval, null, null, null);



insert into generatesbedbill
values(1, 1, 1, 11);

insert into generatesbedbill
values(2, 2, 2, 12);

insert into generatesbedbill
values(3, 3, 3, 13);

insert into generatesbedbill
values(4, 4, 4, 14);

insert into generatesbedbill
values(5, 5, 5, 15);



insert into generatesoperationbill
values(6, 1, 6, 1, 11);

insert into generatesoperationbill
values(7, 2, 7, 2, 12);

insert into generatesoperationbill
values(8, 3, 8, 3, 13);

insert into generatesoperationbill
values(9, 4, 9, 4, 14);

insert into generatesoperationbill
values(10, 5, 10, 5, 15);



insert into monitors
values(1, 16, null);

insert into monitors
values(2, 17, null);

insert into monitors
values(3, 18, null);

insert into monitors
values(4, 19, null);

-- for division
insert into monitors values(5, 16, null);
insert into monitors values(5, 17, null);
insert into monitors values(5, 18, null);
insert into monitors values(5, 19, null);
insert into monitors values(5, 20, null);



insert into gives_task
values(6, 16, 'stop eating the iv bags');

insert into gives_task
values(7, 17, 'clean up this room');

insert into gives_task
values(8, 18, 'unclog drainage pipe');

insert into gives_task
values(9, 19, 'can you make sure this patient only eats gluten-free gluten');

insert into gives_task
values(10, 20, 'refill soap in room 41b');



insert into has_diagnosis
values(1, 1, 6, seq_diagnosisid.nextval);

insert into has_diagnosis
values(2, 2, 7, seq_diagnosisid.nextval);

insert into has_diagnosis
values(3, 3, 8, seq_diagnosisid.nextval);

insert into has_diagnosis
values(4, 4, 9, seq_diagnosisid.nextval);

insert into has_diagnosis
values(5, 5, 10, seq_diagnosisid.nextval);



insert into has_ailment
values(1, 1, 6, 1, seq_ailmentid.nextval, 'severe');

insert into has_ailment
values(2, 2, 7, 2, seq_ailmentid.nextval, 'mild');

insert into has_ailment
values(3, 3, 8, 3, seq_ailmentid.nextval, 'pretty bad');

insert into has_ailment
values(4, 4, 9, 4, seq_ailmentid.nextval, 'recovering');

insert into has_ailment
values(5, 5, 10, 5, seq_ailmentid.nextval, 'certain death');



insert into has_prescription
values(1, 1, 6, 1, seq_prescriptionid.nextval, '50 mg/day');

insert into has_prescription
values(2, 2, 7, 2, seq_prescriptionid.nextval, 'two times a day before meals');

insert into has_prescription
values(3, 3, 8, 3, seq_prescriptionid.nextval, 'once a day');

insert into has_prescription
values(4, 4, 9, 4, seq_prescriptionid.nextval, 'monthly');

insert into has_prescription
values(5, 5, 10, 5, seq_prescriptionid.nextval, 'come in every week');



insert into has_note
values(1, 1, 6, 1, seq_noteid.nextval, 'infection spreading to kidneys');

insert into has_note
values(2, 2, 7, 2, seq_noteid.nextval, 'shows symptoms of returning ailment');

insert into has_note
values(3, 3, 8, 3, seq_noteid.nextval, 'keep confined to ward');

insert into has_note
values(4, 4, 9, 4, seq_noteid.nextval, 'recovering well');

insert into has_note
values(5, 5, 10, 5, seq_noteid.nextval, 'he''s gonna die soon');