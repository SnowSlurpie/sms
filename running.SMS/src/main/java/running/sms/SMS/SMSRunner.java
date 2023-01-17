package running.sms.SMS;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jpa.service.MenuService;

public class SMSRunner 
{
    public static void main( String[] args )
    {
        System.out.println( "Entry Point main()" );
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		System.out.println("Session is open");
		
		Transaction trans = session.beginTransaction();
			MenuService menu = new MenuService();
			menu.menu(session);
        
        trans.commit();
        factory.close();
        session.close();
    }
}