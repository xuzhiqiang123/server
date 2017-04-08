package util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	public static final SessionFactory sessionFactory;

	static {
		try {
			// ����Ĭ�ϵ�hibernate.cfg.xml������һ��Configuration��ʵ��
			Configuration cfg = new Configuration().configure();
			// ��Configurationʵ��������SessionFactoryʵ��
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties())
					.build();
			sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {
		Session s = sessionFactory.openSession();
		return s;
	}
	
	public static void addOneMessage(Session s,Object obj){
		if(s != null && obj != null){
			Transaction t = s.beginTransaction();
			s.save(obj);
			t.commit();
			s.close();
		}
	}
	
	public static Object getOneMessage(Session s,Class clazz,Serializable id){
		if(s != null && id != null){
			Object obj = s.get(clazz, id);
			s.close();
			return obj;
		}else{
			return null;
		}
	}
	
	public static void addMoreMessages(Session s,List<Object> list){
		if(s != null){
			Transaction t = s.beginTransaction();
			for (int i = 0; i < list.size(); i++) {
				s.save(list.get(i));
				if(i != 0 && i % 20 == 0){
					s.flush();
					s.clear();
				}
			}
			t.commit();
		}
	}

	// �ر�Session
	public static void closeSession(Session s) throws HibernateException {
		if (s != null){
			s.close();
		}
	}
	
	public static void executeCompleteSave(Object obj){
		Session s = getSession();
		addOneMessage(s, obj);
		closeSession(s);
	}

}
