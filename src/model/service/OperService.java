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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.NurseBean;
import model.OperBean;
import model.Interface.IService;
import model.dao.NurseBeanDAO;
import model.dao.OperBeanDAO;
 
public class OperService implements IService<OperBean> {
	private static OperService service;
	private static OperBeanDAO dao;
	private static Transaction trx;
	private static SessionFactory sessionFactory;
 	 
	public OperService(OperBeanDAO dao) {
		this.dao = dao;
	}

	@Override
	public OperBean insert(NurseBean nurseBean,OperBean operBean) {
		return dao.insert(operBean);
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
		return sessionFactory.getCurrentSession();
	}

	@Override
	public OperBean insert(OperBean bean) {
		return dao.insert(bean);
	}

}
