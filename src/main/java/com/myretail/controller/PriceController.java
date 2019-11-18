package com.myretail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.MyRetailApplication;
import com.myretail.Exception.PriceEmptyException;
import com.myretail.Exception.ProductNotFoundException;
import com.myretail.Exception.ServiceException;
import com.myretail.request.UpdatePriceRequest;
import com.myretail.response.PriceResponse;
import com.myretail.response.ProductPriceResponse;
import com.myretail.service.inf.ProductPriceServiceInf;



@RestController
public class PriceController {

	@Autowired
	ProductPriceServiceInf productService;
	
	@RequestMapping(value="/products/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> fetchproductDetails(@PathVariable("id") Long productId)
	{
		try
		{
			MyRetailApplication.logger.info("Input parameter Product Id:"+productId);
		ProductPriceResponse productPriceResponse=productService.fetchproductDetails(productId);
		return new ResponseEntity<>(productPriceResponse, HttpStatus.OK);
		}
		catch(ServiceException | ProductNotFoundException exception)
		{
			MyRetailApplication.logger.error("StackTrace",exception.getMessage());
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
		}

		
	}
	
	@RequestMapping(value="/product/{productId}/price",method = RequestMethod.GET)
	public ResponseEntity<?> fetchpriceDetails(@PathVariable("productId") Long productId)
	{	
		try
		{
		MyRetailApplication.logger.info("Input parameter Product Id:"+productId);
		PriceResponse priceResponse=productService.fetchpriceDetails(productId);	
		return new ResponseEntity<>(priceResponse, HttpStatus.OK);	
		}
		catch(PriceEmptyException exception)
		{
			MyRetailApplication.logger.error("StackTrace",exception);
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	
	@RequestMapping(value="/products/{id}",method = {RequestMethod.PUT,RequestMethod.POST})
	public ResponseEntity<?> updateProductPrice(@PathVariable("id") Long productId,@RequestBody UpdatePriceRequest updatePriceRequest)
	{	
		try {
		MyRetailApplication.logger.info("Input parameter Product Id:"+productId);
		MyRetailApplication.logger.info("Request Body:"+updatePriceRequest.toString());
		String result=productService.updateProductPrice(productId,updatePriceRequest);	
		return new ResponseEntity<>(result, HttpStatus.OK);	
		}
	catch(PriceEmptyException exception)
		{
			MyRetailApplication.logger.error("StackTrace",exception);
			return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);	
		}

	}
	
}
