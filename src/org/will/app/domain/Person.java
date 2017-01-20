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
	//集合属性，保留该对象关联的学校，List<String>
	@ElementCollection(targetClass=String.class)
	//建立记录学校列表的子表
	@CollectionTable(name="school_inf",joinColumns=@JoinColumn(name="person_id" , nullable=false))
	//指定保存集合元素索引的列
	@Column(name="school_name")
	//映射集合元素索引的列
	@OrderColumn(name="list_order")
	private List<String> schools = new ArrayList<>();
	
	@ElementCollection(targetClass=Float.class)
	@CollectionTable(name="score_inf",joinColumns=@JoinColumn(name="person_id",nullable=false))
	@MapKeyColumn(name="subject_name")
	@MapKeyClass(String.class)
	@Column(name="mark")
	private Map<String,Float> scores = new HashMap<>();
	
    //N-1 无关系表关联关系
	@ManyToOne(targetEntity=Address.class)
	@JoinColumn(name="address_id",nullable=false)
	@Cascade(CascadeType.ALL)
	private Address address;
	
	//N-1 有关系表关联关系
	@ManyToOne(targetEntity=News.class)
	@JoinTable(name="person_news",
	//指定person表的person_id字段的外键是关系表的person_id字段，因为是N-1，所以不重复。
	//制定news表id字段的外键是关系表的news_id字段。
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
