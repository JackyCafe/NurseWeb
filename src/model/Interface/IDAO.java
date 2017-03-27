package model.Interface;

import java.util.List;

import org.hibernate.Session;

import model.NurseBean;
import model.OperBean;

public interface IDAO<T> {
	public abstract List<T> select();
	public abstract T insert(T bean);
	public abstract Boolean delete(T bean);
	public abstract Boolean delete (int id);
	public abstract T update(T bean);
	public abstract T select(int id);
	public abstract T select(T bean);
	public abstract List<T> select(String name);
	public abstract OperBean insert(OperBean operBean, NurseBean nurseBean);

	public abstract Session getSession();
	
	
}
