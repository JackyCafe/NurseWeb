package model.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.NurseBean;
import model.OperBean;
import model.Interface.IDAO;
 
public class OperBeanDAO implements IDAO<OperBean> {
	private static SessionFactory sessionFactory;
	private static OperBeanDAO dao;
	
 	public OperBeanDAO(SessionFactory factory) {
		this.sessionFactory = factory;

	}

	 
	@Override
	public List<OperBean> select() {
		return this.getSession().createQuery("from OperBean", OperBean.class).getResultList();

	}

	@Override
	public OperBean select(int id) {
		System.out.println("select");
		return this.getSession().get(OperBean.class, id);
	}

	@Override
	public OperBean select(OperBean bean) {
		return select(bean.getOperId());
	}

	@Override
	public OperBean insert(OperBean operBean, NurseBean nurseBean) {
		Date current = new Date();
		
		System.out.println("insert  tmp" + operBean);
  		this.getSession().save(operBean);
		return operBean;
	}

	public List<OperBean> select(String name) {
		System.out.println("select");
		return this.getSession().createQuery("from OperBean where name = :name", OperBean.class)
				.setParameter("name", name).getResultList();
	}

	@Override
	public Boolean delete(OperBean bean) {
		OperBean temp = select(bean.getOperId());
		if (temp != null) {
			this.getSession().delete(temp);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public OperBean update(OperBean bean) {
		OperBean temp = this.select(bean.getOperId());
		if (temp != null) {
			temp.setOperId(bean.getOperId());
			temp.setName(bean.getName());
		}
		return temp;

	}

	@Override
	public Session getSession() {
		 
    		return sessionFactory.getCurrentSession();

	}

	@Override
	public Boolean delete(int id) {
		OperBean tmp = select(id);
		if (tmp != null) {
			this.getSession().delete(tmp);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unused")
	@Override
	public OperBean insert(OperBean bean) {
		System.out.println("here bean"+bean);
		OperBean tmp = select(bean.getOperId());
		System.out.println("here tmp"+tmp);

 		if (tmp == null) {
			this.getSession().save(bean);
			System.out.println("here111");

		}
		return bean;
	}

}
