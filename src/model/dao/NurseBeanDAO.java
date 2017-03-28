package model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.NurseBean;
import model.OperBean;
import model.Interface.IDAO;
 
public class NurseBeanDAO implements IDAO<NurseBean> {
	private static SessionFactory sessionFactory;
	private static NurseBeanDAO dao ;
 	
	public NurseBeanDAO(SessionFactory factory)
	{
		this.sessionFactory = factory;
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
		 
   		return sessionFactory.getCurrentSession();
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
