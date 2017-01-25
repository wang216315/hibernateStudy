package org.will.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="car_inf")
public class Car
{
	//ID
	@Id @Column(name="car_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer carId;
	
	//品牌
	private String carBrand;
	
	//系列
	private String carSeries;
	
	//型号
	private String carModel;
	
	@ManyToOne(targetEntity=Person.class)
	@JoinColumn(name="person_id",referencedColumnName="person_id",nullable=false)
	private Person person;

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarSeries() {
		return carSeries;
	}

	public void setCarSeries(String carSeries) {
		this.carSeries = carSeries;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
	
	
	
	
}
