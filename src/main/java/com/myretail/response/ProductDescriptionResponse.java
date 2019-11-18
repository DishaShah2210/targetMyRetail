package com.myretail.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDescriptionResponse {

    private Product product;

    public Product getProduct ()
    {
        return product;
    }

    public void setProduct (Product product)
    {
        this.product = product;
    }

	@Override
	public String toString() {
		return "ProductDescriptionResponse [product=" + product + "]";
	}

	
}
