package ru.egalvi.hibernatetutorial.simpleexample;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {
    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        ManageEmployee ME = new ManageEmployee();

        /* Let us have a set of diplomas for the first employee  */
        HashSet diplomas = new HashSet();

        diplomas.add(new Diploma("MCA"));
        diplomas.add(new Diploma("MBA"));
        diplomas.add(new Diploma("PMP"));

        /* Let us have a set of certificates for the first employee  */
        HashSet certificates1 = new HashSet();
        certificates1.add(new Certificate("MCA"));
        certificates1.add(new Certificate("MBA"));
        certificates1.add(new Certificate("PMP"));

        /* Let us have one address object */
        Address address = ME.addAddress("Kondapur", "Hyderabad", "AP", "532");

        /* Let us have one work address object, this one will be embedded */
        Address workAddress = new Address("Kondapur", "Hyderabad", "AP", "532");

        /* Add employee records in the database */
        Integer empID1 = ME.addEmployee("Manoj", "Kumar", 4000, certificates1, address,workAddress, diplomas);

        /* Another set of certificates for the second employee  */
        HashSet certificates2 = new HashSet();
        certificates2.add(new Certificate("BCA"));
        certificates2.add(new Certificate("BA"));

        /* Add another employee record in the database */
        Integer empID2 = ME.addEmployee("Dilip", "Kumar", 3000, certificates2, address,workAddress, diplomas);

        /* List down all the employees */
        ME.listEmployees();

        /* Update employee's salary records */
        ME.updateEmployee(empID1, 5000);

        /* Delete an employee from the database */
        ME.deleteEmployee(empID2);

        /* List down all the employees */
        ME.listEmployees();

    }

    /* Method to add an employee record in the database */
    public Integer addEmployee(String fname, String lname,
                               int salary, Set cert, Address address, Address workAddress, Set diplomas) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try {
            tx = session.beginTransaction();
            Employee employee = new Employee(fname, lname, salary, address, workAddress);
            employee.setCertificates(cert);
            employee.setDiplomas(diplomas);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    /* Method to list all the employees detail */
    public void listEmployees() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Employee").list();
            for (Iterator iterator1 =
                 employees.iterator(); iterator1.hasNext(); ) {
                Employee employee = (Employee) iterator1.next();
                System.out.print("First Name: " + employee.getFirstName());
                System.out.print("  Last Name: " + employee.getLastName());
                System.out.println("  Salary: " + employee.getSalary());
                Set certificates = employee.getCertificates();
                Address address = employee.getAddress();
                System.out.println("Address ");
                System.out.println("\tStreet: " +  address.getStreet());
                System.out.println("\tCity: " + address.getCity());
                System.out.println("\tState: " + address.getState());
                System.out.println("\tZipcode: " + address.getZipcode());
                Address workAddress = employee.getWorkAddress();
                System.out.println("Work address ");
                System.out.println("\tStreet: " +  workAddress.getStreet());
                System.out.println("\tCity: " + workAddress.getCity());
                System.out.println("\tState: " + workAddress.getState());
                System.out.println("\tZipcode: " + workAddress.getZipcode());
                for (Iterator iterator2 =
                     certificates.iterator(); iterator2.hasNext(); ) {
                    Certificate certName = (Certificate) iterator2.next();
                    System.out.println("Certificate: " + certName.getName());
                }
                Set diplomas = employee.getDiplomas();
                for (Iterator iterator2 =
                     diplomas.iterator(); iterator2.hasNext(); ) {
                    Diploma certName = (Diploma) iterator2.next();
                    System.out.println("Diploma: " + certName.getName());
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to update salary for an employee */
    public void updateEmployee(Integer EmployeeID, int salary) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee =
                    (Employee) session.get(Employee.class, EmployeeID);
            employee.setSalary(salary);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to delete an employee from the records */
    public void deleteEmployee(Integer EmployeeID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee =
                    (Employee) session.get(Employee.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to add an address record in the database */
    public Address addAddress(String street, String city,
                              String state, String zipcode) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer addressID = null;
        Address address = null;
        try {
            tx = session.beginTransaction();
            address = new Address(street, city, state, zipcode);
            addressID = (Integer) session.save(address);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return address;
    }
}
