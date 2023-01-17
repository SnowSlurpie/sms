package sms.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	public static Session getConnection() {
		 SessionFactory f = new Configuration().configure().buildSessionFactory();
		 Session session = f.openSession();
		 return session;
	}
}
