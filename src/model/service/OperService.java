package model.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.NurseBean;
import model.OperBean;
import model.Interface.IService;
import model.dao.NurseBeanDAO;
import model.dao.OperBeanDAO;
import model.misc.HibernateUtil;

public class OperService implements IService<OperBean> {
	private static OperService service;
	private static OperBeanDAO dao;
	private static Transaction trx;
	private static SessionFactory factory;

	public static void main(String[] args) {
		factory = HibernateUtil.getSessionFactory();
		dao = new OperBeanDAO(factory);
		service = new OperService(dao);
		insertOperTest();
		insertTest();
		selectTest();
 
	}

	 
	private static void insertOperTest() {
		try {
			trx = factory.getCurrentSession().beginTransaction();
			Date current = new Date();
			OperBean operBean = new OperBean("站點33", current);
			operBean.setOperId(0);
			OperBean insert = service.insert(operBean);
			trx.commit();
		} catch (Exception e) {
			System.out.println("e Test" + e.getMessage());
			for (StackTraceElement s : e.getStackTrace())
				System.out.println(s.toString());
			trx.rollback();
		}

	}

	private static void insertTest() {
 
	}

	private static void selectTest() {
		try {
			trx = factory.getCurrentSession().beginTransaction();
			List<OperBean> select = service.select();
			System.out.println("select" + select);
			trx.commit();
		} catch (Exception e) {
			System.out.println("e Test" + e.getMessage());
			for (StackTraceElement s : e.getStackTrace())
				System.out.print(s.toString());
			trx.rollback();
		}
	}

	public OperService(OperBeanDAO dao) {
		this.dao = dao;
	}

	@Override
	public OperBean insert(NurseBean nurseBean,OperBean operBean) {
		return null;
	}

	@Override
	public List<OperBean> select() {
		return dao.select();
	}

	@Override
	public Boolean delete(OperBean bean) {
		return dao.delete(bean.getOperId());
	}

	@Override
	public Boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public OperBean update(OperBean bean) {
		return dao.update(bean);
	}

	@Override
	public OperBean select(int id) {
		return dao.select(id);
	}

	public List<OperBean> select(String name) {
		return dao.select(name);
	}

	@Override
	public OperBean select(OperBean bean) {
		return dao.select(bean);
	}

	@Override
	public Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public OperBean insert(OperBean bean) {
		return dao.insert(bean);
	}

}
