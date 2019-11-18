package com.myretail.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myretail.response.PriceResponse;

public class UpdatePriceRequest {
	@JsonProperty("id")
	Long product_id;
	
	@JsonProperty("name")
	String name;
	
	@JsonProperty("current_price")
	PriceRequest currentPrice;

	public Long getId() {
		return product_id;
	}

	public void setId(Long id) {
		this.product_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PriceRequest getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(PriceRequest currentPrice) {
		this.currentPrice = currentPrice;
	}




}
