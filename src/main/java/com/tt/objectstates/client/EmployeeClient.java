package com.tt.objectstates.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tt.objectstates.model.Employee;
import com.tt.objectstates.util.SessionFactoryProvider;

public class EmployeeClient {

	public static void main(String[] args) {
		
		//Three states of object in Hibernate
		
		Employee employee = new Employee(101, "John Doe", "HR", 30000.0);
		
		
		SessionFactory  factory = SessionFactoryProvider.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(employee); //employee object goes into persistant state
		employee.setEmployeeSalary(70000);
		session.evict(employee);// employee object goes to detached state
		
		tx.commit();
		// session.clear(); // All objects go into detached state.
		session.close(); // all objects in  session will move to detached state.
		factory.close();
	}
}
