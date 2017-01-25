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
	
	// �����Addressʵ�����й�����Personʵ��
	@ManyToMany(targetEntity=Person.class)
	// ӳ�����ӱ�ָ�����ӱ�ı���Ϊperson_address
	@JoinTable(name="person_phone",
		// ӳ�����ӱ�����Ϊaddress_id������У�
		// ���в��յ�ǰʵ���Ӧ���address_id������
		joinColumns=@JoinColumn(name="phone_id"
			, referencedColumnName="phone_id"),
		// ӳ�����ӱ�����Ϊperson_id������У�
		// ���в��յ�ǰʵ���Ӧ���person_id������
		inverseJoinColumns=@JoinColumn(name="person_id"
			, referencedColumnName="person_id"))
	//���ﲻ�ܼ���	@Cascade(CascadeType.ALL)����Ȼ�ᵼ��ͬ������sql����ֻ��Ҫ��Person�˼����OK
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
