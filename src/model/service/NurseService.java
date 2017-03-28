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
 
public class NurseService implements IService<NurseBean> {
	private static NurseService service;
	private static NurseBeanDAO dao;
	private static Transaction trx;
	private static SessionFactory sessionFactory ;
 
	
 
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
		return sessionFactory.getCurrentSession();
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
