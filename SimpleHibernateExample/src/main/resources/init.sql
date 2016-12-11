create table EMPLOYEE (
   id INT NOT  NULL auto_increment,
   first_name  VARCHAR(20) NULL,
   last_name   VARCHAR(20) NULL,
   salary      INT  NULL,
   street_name VARCHAR(40) NULL,
   city_name   VARCHAR(40) NULL,
   state_name  VARCHAR(40) NULL,
   zipcode     VARCHAR(10) NULL,
   address INT NOT NULL,
   PRIMARY KEY (id)
);
create table IF NOT EXISTS CERTIFICATE (
    id INT NOT NULL auto_increment PRIMARY KEY,
    certificate_name VARCHAR(30) NULL,
    employee_id INT NULL
);
create table ADDRESS (
   id INT NOT NULL auto_increment PRIMARY KEY,
   street_name VARCHAR(40) NULL,
   city_name VARCHAR(40) NULL,
   state_name VARCHAR(40) NULL,
   zipcode VARCHAR(10) NULL
);
create table DIPLOMA (
   id INT NOT NULL auto_increment PRIMARY KEY,
   certificate_name VARCHAR(30) NULL
);
create table EMP_DIP (
   employee_id INT NOT NULL,
   certificate_id INT NOT NULL,
   PRIMARY KEY (employee_id,certificate_id)
);