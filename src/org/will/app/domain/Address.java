package org.will.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="address_inf")
public class Address
{
	@Id @Column(name="address_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressId;
	
	private String addressDetail;
	
	public Address()
	{
		
	}
	
	public Address(String addressDetail)
	{
		this.addressDetail = addressDetail;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	
	
}
