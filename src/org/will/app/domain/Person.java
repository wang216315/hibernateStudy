package org.will.app.domain;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="person_inf")
public class Person
{
	@Id @Column(name="person_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Name name;
	
	private int age;
	//�������ԣ������ö��������ѧУ��List<String>
	@ElementCollection(targetClass=String.class)
	//������¼ѧУ�б���ӱ�
	@CollectionTable(name="school_inf",joinColumns=@JoinColumn(name="person_id" , nullable=false))
	//ָ�����漯��Ԫ����������
	@Column(name="school_name")
	//ӳ�伯��Ԫ����������
	@OrderColumn(name="list_order")
	private List<String> schools = new ArrayList<>();
	
	@ElementCollection(targetClass=Float.class)
	@CollectionTable(name="score_inf",joinColumns=@JoinColumn(name="person_id",nullable=false))
	@MapKeyColumn(name="subject_name")
	@MapKeyClass(String.class)
	@Column(name="mark")
	private Map<String,Float> scores = new HashMap<>();
	
    //N-1 �޹�ϵ�������ϵ
	@ManyToOne(targetEntity=Address.class)
	@JoinColumn(name="address_id",nullable=false)
	@Cascade(CascadeType.ALL)
	private Address address;
	
	//N-1 �й�ϵ�������ϵ
	@ManyToOne(targetEntity=News.class)
	@JoinTable(name="person_news",
	//ָ��person���person_id�ֶε�����ǹ�ϵ���person_id�ֶΣ���Ϊ��N-1�����Բ��ظ���
	//�ƶ�news��id�ֶε�����ǹ�ϵ���news_id�ֶΡ�
	joinColumns=@JoinColumn(name="person_id",referencedColumnName="person_id",unique=true),	
	inverseJoinColumns=@JoinColumn(name="news_id",referencedColumnName="id"))
	@Cascade(CascadeType.ALL)
	private News news;
	

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}


	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public List<String> getSchools()
	{
		return schools;
	}

	public void setSchools(List<String> schools)
	{
		this.schools = schools;
	}

	public Map<String,Float> getScores() {
		return scores;
	}

	public void setScores(Map<String,Float> scores) {
		this.scores = scores;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}
	
}
