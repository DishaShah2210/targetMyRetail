package com.myretail.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.myretail.MyRetailApplication;
import com.myretail.Exception.PriceEmptyException;
import com.myretail.Exception.ProductNotFoundException;
import com.myretail.Exception.ServiceException;
import com.myretail.dao.inf.ProductPriceDaoInf;
import com.myretail.pojo.Price;
import com.myretail.request.UpdatePriceRequest;
import com.myretail.response.PriceResponse;
import com.myretail.response.ProductDescriptionResponse;
import com.myretail.response.ProductPriceResponse;
import com.myretail.service.inf.ProductPriceServiceInf;

@Service
public class ProductPriceServiceImpl implements ProductPriceServiceInf{

	@Autowired
	ProductPriceDaoInf productPriceDaoInf;
	
	@Value("${redskyUrl}")
	String redskyUrl;
	
	
	@Value("${redskyExclude}")
	String redskyExclude;
	
	
	
	@Value("${url.retrievePrice}")
	String retrievePriceUrl;
	

	 RestTemplate restTemplate = new RestTemplate();
	
	public ProductPriceResponse fetchproductDetails(Long productId) throws ServiceException,ProductNotFoundException {
		ProductPriceResponse  productPriceResponse=null;
		//get price
		try {
		PriceResponse currentPrice=retrivePrice(productId);
		MyRetailApplication.logger.info("Price for ProductId "+productId +"is :"+currentPrice.toString());
		productPriceResponse= new ProductPriceResponse();
		productPriceResponse.setCurrentPrice(currentPrice);
		
		//get name for a product
		productPriceResponse.setId(productId);
		String productName=retriveNameByProductId(productId);
		productPriceResponse.setName(productName);
		MyRetailApplication.logger.info("productName for ProductId "+productId +"is :"+productName);
		}
		catch(ServiceException | ProductNotFoundException exception)
		{
			MyRetailApplication.logger.error("StackTrace fetchproductDetails",exception.getMessage());
			throw exception;	
		}
		
		return productPriceResponse;
	}

	public PriceResponse retrivePrice(Long productId) {
		
		String url=this.retrievePriceUrl+productId+"/price";

	        HttpHeaders headers = new HttpHeaders();
	 
	        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<String> entity = new HttpEntity<String>(headers);
	    	MyRetailApplication.logger.info("price url"+url);
		ResponseEntity<PriceResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, PriceResponse.class);
		MyRetailApplication.logger.info("Response code "+response.getStatusCode());
		if(response.getBody()!=null)
			return	response.getBody();
		else
			return null;
		
	}

	public String retriveNameByProductId(Long productId) throws ServiceException{
		 String url = this.redskyUrl + productId + this.redskyExclude;

	        HttpHeaders headers = new HttpHeaders();
	 
	        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));

	        headers.setContentType(MediaType.APPLICATION_JSON);
	
	        HttpEntity<String> entity = new HttpEntity<String>(headers);
	  
	    MyRetailApplication.logger.info("redsky url:"+url);
	    try {
		ResponseEntity<ProductDescriptionResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, ProductDescriptionResponse.class);
		MyRetailApplication.logger.info("Response code "+response.getStatusCode());
		if(response.getStatusCodeValue()==200)
		{
			return response.getBody().getProduct().getItem().getProduct_description().getTitle();

		}
		else
		{	
			MyRetailApplication.logger.error("StackTrace retriveNameByProductId");
			throw new ServiceException("Error in External Product Api ,Product Not Found");
		}
	    }
		catch(HttpClientErrorException ex)
		{
			throw new ServiceException("Error in External Product Api,Product Not Found");
		}
	}
		


	@Override
	public PriceResponse fetchpriceDetails(Long productId) throws ProductNotFoundException{
		try
		{
		Price price=productPriceDaoInf.retrivePriceByProductId(productId);
		MyRetailApplication.logger.info("Price for ProductId "+productId +"is :"+price.toString());
		PriceResponse currentPrice=new PriceResponse();
		currentPrice.setValue(price.getValue());
		currentPrice.setCurrency_code(price.getCurrency());
		return currentPrice;
		}
		catch(ProductNotFoundException exception)
		{
			MyRetailApplication.logger.error("StackTrace fetchpriceDetails",exception.getMessage());
			 throw new ProductNotFoundException("Product : "+productId+" not found");		
		}
	}

	@Override
	public String updateProductPrice(Long productId, UpdatePriceRequest updatePriceRequest) throws ServiceException,PriceEmptyException{
		String result=null;
		MyRetailApplication.logger.info("Insert/Update product/price if price and currency value is not null ");
		if(updatePriceRequest.getCurrentPrice().getValue()!=null && updatePriceRequest.getCurrentPrice().getCurrency_code() !=null) 
		result=productPriceDaoInf.updatePriceByProductId(productId,updatePriceRequest);
		
		else
		{
			if(updatePriceRequest.getCurrentPrice().getValue()==null)
			throw new PriceEmptyException("Price is Empty");
			if(updatePriceRequest.getCurrentPrice().getCurrency_code() ==null) 
				throw new PriceEmptyException("Currency is Empty");
		}
		return result;
	}





}
