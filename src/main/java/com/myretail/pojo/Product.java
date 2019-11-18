package com.myretail.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class Product {
	
	public Product() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="productId")
	private Long productId;
	
	@Column(name="name")
	private String name;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name="price_id")
	private Price price;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name="brand_id")
	private Brand brand;
	
	
	public Product(Long productId, String name, Price price, Brand brand) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.brand = brand;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Price getPrice() {
		return price;
	}
	public void setPrice(Price price) {
		this.price = price;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", brand=" + brand + "]";
	}
	

}
