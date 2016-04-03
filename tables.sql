drop table PTMContains;
drop table Bug;
drop table feature;
drop table QualityAssurance;
drop table Client;
drop table Task;
drop table Developer;
drop table Manager;
drop table TeamMember;
drop table Project;


CREATE TABLE Project
(
	pid INTEGER,
	name CHAR(50) NOT NULL,
	description CHAR(255),	
	PRIMARY KEY (pid)
);

CREATE TABLE TeamMember
(
	tmid INTEGER,
	email CHAR(50) NOT NULL,
	password CHAR(24) NOT NULL,
	name CHAR(50) NOT NULL,
	phone_number INTEGER,
	PRIMARY KEY (tmid),
	UNIQUE (email)
);



CREATE TABLE PTMContains
(	
	pid INTEGER,
	tmid INTEGER,
	PRIMARY KEY (pid, tmid),
	FOREIGN KEY (pid) REFERENCES Project(pid) ON DELETE CASCADE,
	FOREIGN KEY (tmid) REFERENCES TeamMember(tmid) ON DELETE SET NULL
);

CREATE TABLE Developer
(
	tmid INTEGER,
	PRIMARY KEY (tmid),
	FOREIGN KEY (tmid) REFERENCES TeamMember(tmid) ON DELETE CASCADE
);

CREATE TABLE Manager
(
	tmid INTEGER,
	PRIMARY KEY (tmid),
	FOREIGN KEY (tmid) REFERENCES TeamMember(tmid) ON DELETE CASCADE
);

CREATE TABLE QualityAssurance
(
	tmid INTEGER,
	PRIMARY KEY (tmid),
	FOREIGN KEY (tmid) REFERENCES TeamMember(tmid) ON DELETE CASCADE
);

CREATE TABLE Client
(
	tmid INTEGER,
	PRIMARY KEY (tmid),
	FOREIGN KEY (tmid) REFERENCES TeamMember(tmid) ON DELETE CASCADE
);

CREATE TABLE Task
(
	tid INTEGER,
	title CHAR(255) NOT NULL,
	description CHAR(255),
	submitted_date TIMESTAMP NOT NULL,
	estimated_date TIMESTAMP,
	completed_date TIMESTAMP,
	priority INTEGER NOT NULL,
	d_id INTEGER,
	m_id INTEGER,
	pid INTEGER NOT NULL,
	PRIMARY KEY (tid),
	FOREIGN KEY (d_id) REFERENCES Developer(tmid) ON DELETE SET NULL,
	FOREIGN KEY (m_id) REFERENCES Manager(tmid) ON DELETE SET NULL,
	FOREIGN KEY (pid) REFERENCES Project(pid) ON DELETE CASCADE
);


CREATE TABLE Bug
(
	tid INTEGER,
	qa_id INTEGER NOT NULL,
	PRIMARY KEY (tid),
	FOREIGN KEY (tid) REFERENCES Task(tid) ON DELETE CASCADE,
	FOREIGN KEY (qa_id) REFERENCES QualityAssurance(tmid) ON DELETE SET NULL
);

CREATE TABLE Feature
(
	tid INTEGER,
	client_id INTEGER NOT NULL,
	PRIMARY KEY (tid),
	FOREIGN KEY (tid) REFERENCES Task(tid) ON DELETE CASCADE,
	FOREIGN KEY (client_id) REFERENCES Client(tmid) ON DELETE SET NULL
);

