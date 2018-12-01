package com.tt.objectstates.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.tt.objectstates.model.Employee;

public class SessionFactoryProvider {

	
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	static {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure().build();
		sessionFactory = new MetadataSources(registry)
				.addAnnotatedClass(Employee.class)
				.buildMetadata().buildSessionFactory();
	}
}
