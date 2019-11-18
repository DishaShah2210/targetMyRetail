package com.myretail.response;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ProductPriceResponse {
	
	@JsonProperty("id")
	Long id;
	
	@JsonProperty("name")
	String name;
	
	@JsonProperty("current_price")
	PriceResponse currentPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PriceResponse getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(PriceResponse currentPrice) {
		this.currentPrice = currentPrice;
	}




}
