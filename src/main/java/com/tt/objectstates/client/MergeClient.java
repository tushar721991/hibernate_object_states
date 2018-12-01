package com.tt.objectstates.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tt.objectstates.model.Employee;
import com.tt.objectstates.util.SessionFactoryProvider;

public class MergeClient {

	public static void main(String[] args) {

		SessionFactory  factory = SessionFactoryProvider.getSessionFactory();
		Session session = factory.openSession();
		Employee e1 = session.get(Employee.class, 101); 
		e1.setEmployeeSalary(1111);
		session.close();
		
		Session session2 = factory.openSession();
		Employee e2 = session2.get(Employee.class, 101); 
		Transaction tx = session2.beginTransaction();
		Employee e3 = (Employee) session2.merge(e1);  
		
		// all properties of e1 will be overrieded in e2(any object with same primary key)
		
		e2.setEmployeeName("Name Changed");			
		tx.commit();
		
		session2.close(); // all objects in  session will move to detached state.
		factory.close();
	}
}
