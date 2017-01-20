package org.will.app.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Parent;





@Embeddable
public class Name 
{
	@Column(name="person_firstname")
	private String firstname;
	
	@Column(name="person_lastname")
	private String lastname;
	
	@Parent
	private Person owner;
	
	
	public Name()
	{
		
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}




	
}
