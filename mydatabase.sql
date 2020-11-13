CREATE DATABASE IF NOT EXISTS coaching;
USE coaching;

CREATE TABLE IF NOT EXISTS LoginAccount
(
  username VARCHAR(20) NOT NULL,
  password VARCHAR(256) NOT NULL,
  role VARCHAR(20) NOT NULL,
  enabled BOOL NOT NULL,
  PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS Teacher
(
  teacherId INT NOT NULL auto_increment,
  teacherName VARCHAR(40) NOT NULL,
  joiningDate DATE NOT NULL,
  contactNumber CHAR(10) NOT NULL,
  address VARCHAR(100) NOT NULL,
  dateOfBirth DATE NOT NULL,
  gender VARCHAR(10) NOT NULL,
  username VARCHAR(20) NOT NULL,
  PRIMARY KEY (teacherId),
  FOREIGN KEY (username) REFERENCES LoginAccount(username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Batch
(
  batchId VARCHAR(10) NOT NULL,
  batchName VARCHAR(40) NOT NULL,
  startingDate DATE NOT NULL,
  endDate DATE NOT NULL,
  isOpen BOOL NOT NULL,
  PRIMARY KEY (batchId)
);

CREATE TABLE IF NOT EXISTS Subject
(
  subjectId VARCHAR(10) NOT NULL,
  subjectName VARCHAR(40) NOT NULL,
  batchId VARCHAR(10) NOT NULL,
  teacherId INT NOT NULL,
  PRIMARY KEY (subjectId, batchId),
  FOREIGN KEY (batchId) REFERENCES Batch(batchId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (teacherId) REFERENCES teacher(teacherId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Lecture
(
  lectureId INT NOT NULL auto_increment,
  lectureName VARCHAR(40) NOT NULL,
  subjectId VARCHAR(10) NOT NULL,
  batchId VARCHAR(10) NOT NULL,
  PRIMARY KEY (lectureId, subjectId, batchId),
  FOREIGN KEY (subjectId, batchId) REFERENCES subject(subjectId, batchId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Test
(
  testId INT NOT NULL auto_increment,
  testName VARCHAR(40) NOT NULL,
  testDate DATE NOT NULL,
  totalMarks INT NOT NULL,
  PRIMARY KEY (testId)
);

CREATE TABLE IF NOT EXISTS BatchTestRelation
(
  batchId VARCHAR(10) NOT NULL,
  testId INT NOT NULL,
  PRIMARY KEY (batchId, testId),
  FOREIGN KEY (batchId) REFERENCES Batch(batchId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (testId) REFERENCES Test(testId) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Student
(
  studentId INT NOT NULL auto_increment,
  studentName VARCHAR(40) NOT NULL,
  dateOfBirth DATE NOT NULL,
  gender VARCHAR(10) NOT NULL,
  address VARCHAR(100) NOT NULL,
  contactNumber CHAR(10) NOT NULL,
  batchId VARCHAR(10) NOT NULL,
  username VARCHAR(20) NOT NULL,
  PRIMARY KEY (studentId),
  FOREIGN KEY (batchId) REFERENCES Batch(batchId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (username) REFERENCES LoginAccount(username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Book
(
  bookId INT NOT NULL auto_increment,
  bookName VARCHAR(40) NOT NULL,
  PRIMARY KEY (bookId)
);

CREATE TABLE IF NOT EXISTS StudentBookRelation
(
  issueDate DATE NOT NULL,
  returnDueDate DATE NOT NULL,
  studentId INT NOT NULL,
  bookId INT NOT NULL,
  PRIMARY KEY (bookId),
  FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (bookId) REFERENCES Book(bookId) ON DELETE CASCADE ON UPDATE CASCADE
);

insert into loginaccount values('admin', '$2a$10$8QmT9eFcvmPYUwixPmk0guQYS7Ff9IiUFaO3ieM0Iq1F1XYs9j5GO', 'ROLE_admin', 1);