package com.myretail.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	  private Item item;
	    public Item getItem ()
	    {
	        return item;
	    }

	    public void setItem (Item item)
	    {
	        this.item = item;
	    }
}
