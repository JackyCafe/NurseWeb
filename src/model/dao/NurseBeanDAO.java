package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.NurseBean;
import model.OperBean;
import model.Interface.IDAO;
import model.misc.HibernateUtil;

public class NurseBeanDAO implements IDAO<NurseBean> {
	private static SessionFactory factory;
	private static NurseBeanDAO dao ;
	public static void main(String[] args) {
		factory = HibernateUtil.getSessionFactory();
		dao = new NurseBeanDAO(factory);
 		insertTest();
 		selectTest();	
		deleteTest();
		updateTest();
	}
	
	public NurseBeanDAO(SessionFactory factory)
	{
		this.factory = factory;

	}
	
	public static void deleteTest()
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction trx = session.beginTransaction();
		
		int id = 1;
  		try{
 			Boolean delete = dao.delete(id);
			System.out.println("delete test" + delete);
			trx.commit();
		}catch (Exception e) {
			for (StackTraceElement s :e.getStackTrace())
				System.out.print(s.toString());
			trx.rollback();
 		}
	 	
	}

	public static void updateTest()
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction trx = session.beginTransaction();
		Date current  = new Date();
		NurseBean bean = new NurseBean("N001","護士丁", current);
		bean.setId(6);
  		try{
 			NurseBean update = dao.update(bean);
			System.out.println("update test" + update);
			trx.commit();
		}catch (Exception e) {
			for (StackTraceElement s :e.getStackTrace())
				System.out.print(s.toString());
			trx.rollback();
 		}
	 	
	}
	
	public static void insertTest()
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction trx = session.beginTransaction();
		Date current = new Date();
		NurseBean bean = new NurseBean("N03","護士丙",  current); 
		bean.setId(0);
 		try{
 			NurseBean insert = dao.insert(bean);
			System.out.println("insert test" + insert);
			trx.commit();
		}catch (Exception e) { 
			trx.rollback();
 		}
		
		
		
	}
	
	public static void selectTest()
	{
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction trx = session.beginTransaction();
 		try{
 			List<NurseBean> select = dao.select();
 			trx.commit();
		}catch (Exception e) {
			for (StackTraceElement s :e.getStackTrace())
				System.out.println(s.toString());
			trx.rollback();
 		}
		
	}
	
	@Override
	public List<NurseBean> select() {
   		return this.getSession().createQuery("from NurseBean", NurseBean.class).getResultList();

	}

	@Override
	public NurseBean select(int id) {
		System.out.println("select");
 		return this.getSession().get(NurseBean.class, id);
	}
	
	 
	public List<NurseBean> select(String name) {
		System.out.println("select");
		
 		return this.getSession()
 				.createQuery("from NurseBean where name = :name",NurseBean.class)
 				.setParameter("name", name)
 				.getResultList();
	}
	
	@Override
	public NurseBean select(NurseBean bean) {
 		return select(bean.getId());
	}
	
	@Override
	public NurseBean insert(NurseBean bean) {
		NurseBean tmp = select(bean) ;
		System.out.println("select  tmp" +bean);
		if ( tmp == null)
		{
			this.getSession().save(bean);
		}
 		return bean;
	}

	@Override
	public Boolean delete(NurseBean bean) {
		NurseBean temp = select(bean.getId());
		if(temp != null)
		{
			this.getSession().delete(temp);
			return true;
		}else
		{
			return false;
		}
	}

	@Override
	public NurseBean update(NurseBean bean) {
		NurseBean temp = this.select(bean.getId());
		if(temp != null)
		{
			temp.setId(bean.getId());
			temp.setName(bean.getName());
			temp.setUpdateTime(bean.getUpdateTime());
		}
		return temp;

	}

	

	@Override
	public Session getSession() {
 		return factory.getCurrentSession();
	}

	@Override
	public Boolean delete(int id) {
		NurseBean tmp = select (id);
		if(tmp != null)
		{
			this.getSession().delete(tmp);
			return true;
		} else {
 		return false;
 		}
	}

	@Override
	public OperBean insert(OperBean operBean, NurseBean nurseBean) {
		// TODO Auto-generated method stub
		return null;
	}

	

 

}
