package org.will.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="news_inf")
public class News
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	
	public void setId(Integer id)
	{
		this.id = id;
		
	}
	public Integer getId()
	{
		return id;
	}
		
	private String title;
	
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	private String content;
	
	public String getContent() {
		return content;
	}
	
	@Formula("(select concat(nt.title,nt.content)" + " from news_inf nt where nt.id=id)")
	private String fullcontent;
	
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getFullcontent() 
	{
		return fullcontent;
	}
	public void setFullcontent(String fullcontent)
	{
		this.fullcontent = fullcontent;
	}

}
