package com.myretail.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Price")
public class Price {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="price_id")
	Integer id ;
	
	@Column(name="value")
	Double value;
	
	@Column(name="currency")
	String currency;
	
	public Price() {

	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "Price [id=" + id + ", value=" + value + ", currency=" + currency + "]";
	}
	
}
