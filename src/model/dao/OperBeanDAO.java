package model.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.NurseBean;
import model.OperBean;
import model.Interface.IDAO;
import model.misc.HibernateUtil;

public class OperBeanDAO implements IDAO<OperBean> {
	private static SessionFactory factory;
	private static OperBeanDAO dao;

	public static void main(String[] args) {
		factory = HibernateUtil.getSessionFactory();
		dao = new OperBeanDAO(factory);

		selectTest();
		insertTest();
		// deleteTest();
		// updateTest();
	}

	public OperBeanDAO(SessionFactory factory) {
		this.factory = factory;

	}

	public static void deleteTest() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction trx = session.beginTransaction();
		int id = 5;
		try {
			Boolean insert = dao.delete(id);
			System.out.println("insert test" + insert);
			trx.commit();
		} catch (Exception e) {
			for (StackTraceElement s : e.getStackTrace())
				System.out.print(s.toString());
			trx.rollback();
		}

	}

	public static void updateTest() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction trx = session.beginTransaction();
		Date current = new Date();
		OperBean bean = new OperBean("工作1", current);
		bean.setOperId(0);
		try {
			OperBean update = dao.update(bean);
			System.out.println("update test" + update);
			trx.commit();
		} catch (Exception e) {
			for (StackTraceElement s : e.getStackTrace())
				System.out.print(s.toString());
			trx.rollback();
		}

	}

	public static void insertTest() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction trx = session.beginTransaction();
		Date current = new Date();
		OperBean bean = new OperBean("工作1", current);
		bean.setOperId(0);
		try {
			OperBean insert = dao.insert(bean);
			System.out.println("insert test" + insert);
			trx.commit();
		} catch (Exception e) {
			for (StackTraceElement s : e.getStackTrace())
				System.out.print(s.toString());
			System.out.println(e.getMessage());

			trx.rollback();
		}

	}

	public static void selectTest() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction trx = session.beginTransaction();
		System.out.println("------");
		try {
			OperBean select = dao.select(1);
			System.out.println("select" + select);
			trx.commit();
		} catch (Exception e) {
			for (StackTraceElement s : e.getStackTrace())
				System.out.print(s.toString());
			trx.rollback();
		}

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
		return factory.getCurrentSession();
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

	@Override
	public OperBean insert(OperBean bean) {
		OperBean tmp = select(bean.getOperId());
		if (tmp == null) {
			this.getSession().save(bean);
		}
		return bean;
	}

}
