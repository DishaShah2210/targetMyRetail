package com.myretail.util;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Configuration;
@Configuration
public class HibernateUtil {

	public SessionFactory myretailsessionFactory(EntityManagerFactory entityManagerFactory)
	{
	    if(entityManagerFactory.unwrap(SessionFactory.class) == null){
	        throw new NullPointerException("factory is not a hibernate factory");
	      }
	      return entityManagerFactory.unwrap(SessionFactory.class);
	    }
}
