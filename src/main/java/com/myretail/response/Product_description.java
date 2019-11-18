package com.myretail.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product_description {
	 private String title;
	   public String getTitle ()
	    {
	        return title;
	    }

	    public void setTitle (String title)
	    {
	        this.title = title;
	    }

}
