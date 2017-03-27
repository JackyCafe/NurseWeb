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
import model.misc.HibernateUtil;

public class NurseService implements IService<NurseBean> {
	private static NurseService service;
	private static NurseBeanDAO dao;
	private static Transaction trx;
	private static SessionFactory factory ;
 
	
	public static void main(String[] args) {
		factory = HibernateUtil.getSessionFactory();
		dao = new NurseBeanDAO(factory);
		service = new NurseService(dao);
		insertNurseTest();
		insertNurseOper();
		selectTest();
  	}

	private static void insertNurseTest() {
		try {
			trx = factory.getCurrentSession().beginTransaction();
			Date current = new Date();
			NurseBean bean = new NurseBean("C1802","護士丙丙", current);
			bean.setId(0);
   			NurseBean insert = service.insert(bean);
 			trx.commit();
		} catch (Exception e) {
			System.out.println("e Test"+e.getMessage());
			for (StackTraceElement s :e.getStackTrace())
				System.out.print(s.toString());
			trx.rollback();
		}
		
	}
	
	
	private static void insertNurseOper()
	{
		try {
			trx = factory.getCurrentSession().beginTransaction();
			Date current = new Date();
			NurseBean nurseBean = new NurseBean("N02","護士乙", current);
			nurseBean.setId(4);
			OperBean operBean = new OperBean("工作4", current);
			operBean.setOperId(5);
			NurseBean insert = service.insert(nurseBean,operBean);
 			trx.commit();
		} catch (Exception e) {
			System.out.println("e Test"+e.getMessage());
			for (StackTraceElement s :e.getStackTrace())
				System.out.println(s.toString());
			trx.rollback();
		}
		

	}

	private static void selectTest() {
		try {
			trx = factory.getCurrentSession().beginTransaction();
  			List<NurseBean> select = service.select();
			System.out.println("select" + select);
			trx.commit();
		} catch (Exception e) {
			System.out.println("e Test"+e.getMessage());
			for (StackTraceElement s :e.getStackTrace())
				System.out.print(s.toString());
			trx.rollback();
		}
	}

	public NurseService(NurseBeanDAO dao) {
		this.dao = dao;
	}

	@Override
	public NurseBean insert(NurseBean bean) {
		System.out.println(bean);
		return dao.insert(bean);
	}

	@Override
	public List<NurseBean> select() {
		return dao.select();
	}

	@Override
	public Boolean delete(NurseBean bean) {
		return dao.delete(bean.getId());
	}

	@Override
	public Boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public NurseBean update(NurseBean bean) {
		return dao.update(bean);
	}

	@Override
	public NurseBean select(int id) {
		return dao.select(id);
	}

	@Override
	public NurseBean select(NurseBean bean) {
		return dao.select(bean);
	}

	@Override
	public Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public NurseBean insert(NurseBean nurseBean,OperBean operBean) {
		NurseBean tmp = null;
		if(select(nurseBean.getName()).size()>0){
			tmp = select(nurseBean.getName()).get(0);
			System.out.println("tmp"+tmp);
		} 
	// 新護士
		if(tmp == null)
		{
			Set<OperBean> operSet = new HashSet<>();
			operSet.add(operBean);
			nurseBean.setOperBean(operSet);
 	 		return dao.insert(nurseBean);
		} else if(tmp.getOperBean() == null) //舊護士,沒被分配到站點
		{
			Set<OperBean> operSet = new HashSet<>();
			operSet.add(operBean);
			tmp.setOperBean(operSet);
	 		return dao.insert(tmp);
			
		}else
		{
 			tmp.getOperBean().add(operBean);	
  			return dao.insert(tmp);
			
		}
 	}

	@Override
	public List<NurseBean> select(String name) {
 		return dao.select(name);
	}

}
