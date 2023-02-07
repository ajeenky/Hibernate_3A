package second.level.cache.sessionFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {

// if we want to use second level cache then every dependency must be in the form of newer version
// in hibernate.cfg.xml we need to provide exact properties to enable second level cache which was disabled by default
// which actually means first level cache are enabled by default we cannot disable them
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Student stu = (Student) session.load(Student.class, 1);
		System.out.println(stu.getId()+" "+stu.getFirstName()+" "+stu.getLastName());
		
		Session session1 = sessionFactory.openSession();
//		we can create 2 session objects but still the query will be executed only once because the cache are held at sessionFactory
		
		Student stu1 = (Student) session1.load(Student.class, 1);
		System.out.println(stu1.getId()+" "+stu1.getFirstName()+" "+stu1.getLastName());
	}

}
