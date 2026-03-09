package com.capgemini.hibernate_jpa.main;

import java.util.Scanner;
import com.capgemini.hibernate_jpa.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JavaApp {

    public static void execution() {

        Scanner sc = new Scanner(System.in);

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Employee");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        // CREATE
        tx.begin();

        System.out.println("How many employees do you want to create?");
        int n = sc.nextInt();
        sc.nextLine(); // clear buffer

        for(int i = 0; i < n; i++) {

            Employee emp = new Employee();

            System.out.println("Enter Employee ID:");
            emp.setId(sc.nextInt());
            sc.nextLine();

            System.out.println("Enter Name:");
            emp.setName(sc.nextLine());

            System.out.println("Enter Email:");
            emp.setEmail(sc.nextLine());

            System.out.println("Enter Contact Number:");
            emp.setContactNumber(sc.nextLong());
            sc.nextLine();

            em.persist(emp);
        }

        tx.commit();

        // READ
        System.out.println("\nEnter Employee ID to read:");
        int readId = sc.nextInt();

        Employee emp = em.find(Employee.class, readId);

        if(emp != null)
            System.out.println(emp);
        else
            System.out.println("Employee not found");

        // UPDATE
        tx.begin();

        System.out.println("\nEnter Employee ID to update:");
        int updateId = sc.nextInt();
        sc.nextLine();

        Employee updateEmp = em.find(Employee.class, updateId);

        if(updateEmp != null) {

            System.out.println("Enter new Email:");
            updateEmp.setEmail(sc.nextLine());

            System.out.println("Enter new Contact Number:");
            updateEmp.setContactNumber(sc.nextLong());
            sc.nextLine();

            System.out.println("Employee updated successfully");
        }
        else {
            System.out.println("Employee not found");
        }

        tx.commit();

        // DELETE
        tx.begin();

        System.out.println("\nEnter Employee ID to delete:");
        int deleteId = sc.nextInt();

        Employee deleteEmp = em.find(Employee.class, deleteId);

        if(deleteEmp != null) {
            em.remove(deleteEmp);
            System.out.println("Employee deleted successfully");
        }
        else {
            System.out.println("Employee not found");
        }

        tx.commit();

        em.close();
        emf.close();
        sc.close();

        System.out.println("\nProgram Finished");
    }
}