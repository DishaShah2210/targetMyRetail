package com.myretail.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private Product_description product_description;
    public Product_description getProduct_description ()
    {
        return product_description;
    }

    public void setProduct_description (Product_description product_description)
    {
        this.product_description = product_description;
    }
}
