package model;

// default package
// Generated 2017/3/22 下午 10:41:27 by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

 
/**
 * Nurse generated by hbm2java
 */
@Entity
@Table (name="nurse",schema ="nurse")
 public class NurseBean implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private Integer id;
 	private String name;
 	private String nurseId;
 	private Date updateTime;

 	@ManyToMany
	@JoinTable(name="NurseOper"
		,joinColumns =@JoinColumn(name="nurse_id")
		,inverseJoinColumns=@JoinColumn(name="oper_id") // 中介 table foregin key 對到對方的ＰＫ	
			)
	private Set<OperBean> OperBean;
 	
 	
	public String getNurseId() {
		return nurseId;
	}

	public void setNurseId(String nurseId) {
		this.nurseId = nurseId;
	}

	public Set<OperBean> getOperBean() {
		return OperBean;
	}

	public void setOperBean(Set<OperBean> operBean) {
		OperBean = operBean;
	}

	public NurseBean() {
	}

	public NurseBean(String nurseId, String name, Date updateTime) {
		this.name = name;
		this.updateTime = updateTime;
		this.nurseId = nurseId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "NurseBean [id=" + id + ", name=" + name + ", nurseId=" + nurseId + ", updateTime=" + updateTime
				+ ", OperBean=" + OperBean + "]";
	}

	
}
