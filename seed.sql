insert into project values
	(100, '304 Project', 'Database Project');

insert into project values
	(101, '319 Project', 'Project for 319');

insert into project values
	(103, 'Test Project', 'Test description');

insert into project values
	(108, 'Android Project', 'Project for Android app');

insert into project values
	(109, 'Development Tools', 'In house tool development');

insert into teammember values
	(1000, 'Bobby123@someemail.com', 'pass0', 'Bobby Lee', 6829341744);

insert into teammember values
	(1001, 'Jenny321@someemail.com', 'pass1', 'Jenny Kent', 6912430294);

insert into teammember values
	(1005, 'smith@someemail.com', 'pass2', 'Rob Smith', 3513920471);

insert into teammember values
	(1020, 'fiyds@someemail.com', 'pass3', 'Camilla Wun', 1039582346);

insert into teammember values
	(1021, 'Ethan@someemail.com', 'pass4', 'Ethan Chan', 5783125567);

insert into teammember values
	(1022, 'QA3@someemail.com', 'pass5', 'QA3', 6339184372);

insert into teammember values
	(1023, 'QA4@someemail.com', 'pass6', 'QA4', 6386318371);

insert into teammember values
	(1024, 'QA5@someemail.com', 'pass7', 'QA5', 8712345678);

insert into teammember values
	(1025, 'Client2@someemail.com', 'pass8', 'Client2', 4645864564);

insert into teammember values
	(1026, 'Client3@someemail.com', 'pass9', 'Client3', 6543123154);

insert into teammember values
	(1027, 'Client4@someemail.com', 'pass10', 'Client4', 6453123788);

insert into teammember values
	(1028, 'Client5@someemail.com', 'pass11', 'Client5', 3122345264);

insert into teammember values
	(1029, 'Developer2@someemail.com', 'pass12', 'Developer2', 4189465323);

insert into teammember values
	(1030, 'Developer3@someemail.com', 'pass13', 'Developer3', 4654864563);

insert into teammember values
	(1031, 'Developer4@someemail.com', 'pass14', 'Developer4', 2315779870);

insert into teammember values
	(1032, 'Developer5@someemail.com', 'pass15', 'Developer5', 7656423123);

insert into teammember values
	(1033, 'Manager2@someemail.com', 'pass16', 'Manager2', 9751324885);

insert into teammember values
	(1034, 'Manager3@someemail.com', 'pass17', 'Manager3', 4564321312);

insert into teammember values
	(1035, 'Manager4@someemail.com', 'pass18', 'Manager4', 5464564231);

insert into teammember values
	(1036, 'Manager5@someemail.com', 'pass19', 'Manager5', 6456789700);

insert into ptmcontains values
	(100, 1000);

insert into ptmcontains values
	(100, 1001);

insert into ptmcontains values
	(101, 1005);

insert into ptmcontains values
	(109, 1020);

insert into ptmcontains values
	(109, 1021);

insert into ptmcontains values
    (100, 1005);
	
insert into ptmcontains values
    (100, 1020);
	
insert into ptmcontains values
    (100, 1021);
	
insert into ptmcontains values
    (100, 1022);
	
insert into ptmcontains values
    (100, 1023);
	
insert into ptmcontains values
    (100, 1024);
	
insert into ptmcontains values
    (100, 1025);
	
insert into ptmcontains values
    (100, 1026);
	
insert into ptmcontains values
    (100, 1027);
	
insert into ptmcontains values
    (100, 1028);
	
insert into ptmcontains values
    (100, 1029);
	
insert into ptmcontains values
    (100, 1030);
	
insert into ptmcontains values
    (100, 1031);
	
insert into ptmcontains values
    (100, 1032);
	
insert into ptmcontains values
    (100, 1033);
	
insert into ptmcontains values
    (100, 1034);
	
insert into ptmcontains values
    (100, 1035);
	
insert into ptmcontains values
    (100, 1036);

insert into qualityassurance values
	(1000);

insert into qualityassurance values
	(1021);

insert into qualityassurance values
	(1022);

insert into qualityassurance values
	(1023);

insert into qualityassurance values
	(1024);

insert into client values
	(1001);

insert into client values
	(1025);

insert into client values
	(1026);

insert into client values
	(1027);

insert into client values
	(1028);

insert into developer values
	(1005);

insert into developer values
	(1029);

insert into developer values
	(1030);

insert into developer values
	(1031);

insert into developer values
	(1032);

insert into manager values
	(1020);

insert into manager values
	(1033);

insert into manager values
	(1034);

insert into manager values
	(1035);

insert into manager values
	(1036);

insert into task values
	(10000, 'ER-diagram', 'Make ER Diagram of the database', 
		TO_DATE('14/02/2016 16:33', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('14/02/2016 20:00', 'dd/mm/yyyy hh24:mi'), 
		NULL, 
		4, 1005, 1020, 100);

insert into task values
	(10001, 'Create table schema', 'Make schema for all the tables',
		TO_DATE('13/02/2016 13:30', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('13/02/2016 16:00', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('14/02/2016 12:00', 'dd/mm/yyyy hh24:mi'), 
		3, 1005, 1020, 100);

insert into task values
	(10003, 'Submit milestone', 'Submit the second part of the project for mark',
		TO_DATE('10/02/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('15/02/2016 18:00', 'dd/mm/yyyy hh24:mi'),
		NULL, 
		5, 1029, 1036, 100);

insert into task values
	(10004, 'Create github', 'Create github repository for team members',
		TO_DATE('18/01/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('19/01/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('19/01/2016 15:00', 'dd/mm/yyyy hh24:mi'),
		5, 1030, 1035, 101);

insert into task values
	(10008, 'Android app crashing at start', 'App crashing at the start when using galaxy 4',
		TO_DATE('20/01/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('22/1/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		NULL, 
		3, 1030, 1034, 108);

insert into task values
	(10010, 'Add user options', 'Add user options to configure  android app settings',
		TO_DATE('20/1/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('22/1/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('22/1/2016 11:00', 'dd/mm/yyyy hh24:mi'), 
		2, 1030, 1033, 108);

insert into task values
	(10011, 'Cant insert to database', 'Can’t insert new data tuple to Project database',
		TO_DATE('10/2/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('22/2/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		NULL, 
		3, 1031, 1033, 100);

insert into task values
	(10012, 'Cant delete database data', 'Can’t delete any tuple from Project database',
		TO_DATE('10/2/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('22/2/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('22/2/2016 10:00', 'dd/mm/yyyy hh24:mi'), 
		3, 1032, 1034, 100);

insert into task values
	(10013, 'Test bug report', 'Test bug report description',
		TO_DATE('12/2/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('21/2/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		NULL, 
		1, 1005, 1020, 103);

insert into task values
	(10014, 'History not saving', 'User preference history is not saving for development tools',
		TO_DATE('10/2/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('10/2/2016 10:00', 'dd/mm/yyyy hh24:mi'),
		TO_DATE('10/2/2016 11:00', 'dd/mm/yyyy hh24:mi'), 
		3, 1029, 1036, 109);

insert into bug values
	(10008, 1000);

insert into bug values
	(10011, 1021);

insert into bug values
	(10012, 1023);

insert into bug values
	(10013, 1023);

insert into bug values
	(10014, 1023);

insert into feature values
	(10000, 1001);

insert into feature values
	(10001, 1025);

insert into feature values
	(10003, 1025);

insert into feature values
	(10004, 1028);

insert into feature values
	(10010, 1027);