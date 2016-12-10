package ru.egalvi.hibernatetutorial.simpleexample;

import java.util.Set;

/**
 *
 */
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    private Set certificates;
    private Address address;

    public Employee() {}
    public Employee(String fname, String lname, int salary, Address address ) {
        this.firstName = fname;
        this.lastName = lname;
        this.salary = salary;
        this.address = address;
    }
    public int getId() {
        return id;
    }
    public void setId( int id ) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName( String first_name ) {
        this.firstName = first_name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName( String last_name ) {
        this.lastName = last_name;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary( int salary ) {
        this.salary = salary;
    }
    public Set getCertificates() {
        return certificates;
    }
    public void setCertificates( Set certificates ) {
        this.certificates = certificates;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress( Address address ) {
        this.address = address;
    }
}

/*create table EMPLOYEE (
   id INT NOT NULL auto_increment,
   first_name VARCHAR(20) default NULL,
   last_name  VARCHAR(20) default NULL,
   salary     INT  default NULL,
   PRIMARY KEY (id)
);*/