package org.will.app.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;




@Entity
@Table(name="phone_inf")
public class Phone
{
	@Id @Column(name="phone_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer phoneId;
	
	
	private String phoneMode;
	
	private String phoneNumber;
	
	// 定义该Address实体所有关联的Person实体
	@ManyToMany(targetEntity=Person.class)
	// 映射连接表，指定连接表的表名为person_address
	@JoinTable(name="person_phone",
		// 映射连接表中名为address_id的外键列，
		// 该列参照当前实体对应表的address_id主键列
		joinColumns=@JoinColumn(name="phone_id"
			, referencedColumnName="phone_id"),
		// 映射连接表中名为person_id的外键列，
		// 该列参照当前实体对应表的person_id主键列
		inverseJoinColumns=@JoinColumn(name="person_id"
			, referencedColumnName="person_id"))
	//这里不能加入	@Cascade(CascadeType.ALL)，不然会导致同步插入sql错误，只需要在Person端加入就OK
	private Set<Person> workPersons  = new HashSet<>();
	

	public Phone()
	{
		
	}
	



	public String getPhoneMode() {
		return phoneMode;
	}


	public void setPhoneMode(String phoneMode) {
		this.phoneMode = phoneMode;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Set<Person> getWorkPersons() {
		return workPersons;
	}


	public void setWorkPersons(Set<Person> workPersons) {
		this.workPersons = workPersons;
	}




	public Integer getPhoneId() {
		return phoneId;
	}




	public void setPhoneId(Integer phoneId) {
		this.phoneId = phoneId;
	}
}
