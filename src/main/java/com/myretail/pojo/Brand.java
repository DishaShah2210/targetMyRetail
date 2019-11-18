package com.myretail.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Brand")
public class Brand {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="brand_id")
	Integer id;
	
	@Column(name="brand_name")
	String brand;
	
	@Column(name="facetId")
	String facetId;
	
	public Brand() {

	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getFacetId() {
		return facetId;
	}
	public void setFacetId(String facetId) {
		this.facetId = facetId;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", brand=" + brand + ", facetId=" + facetId + "]";
	}
	
	
}
