package com.myretail.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;

import com.myretail.Exception.ProductNotFoundException;
import com.myretail.dao.inf.ProductPriceDaoInf;
import com.myretail.pojo.Price;
import com.myretail.response.PriceResponse;
import com.myretail.response.ProductPriceResponse;
import com.myretail.service.impl.ProductPriceServiceImpl;

public class PriceServiceImplTest {
	
	@Autowired
	@InjectMocks
	private ProductPriceServiceImpl productPriceServiceImpl;

	@Value("${url.retrievePrice}")
	String retrievePriceUrl;
	

	
	
	
	@Mock
	private ProductPriceDaoInf productPriceDao;
	
	 @Before
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	    }
	 
	@Test
	public void fetchPriceCurrency()
	{
		Long productId=(long) 13860428;
		Price  price=new Price();
		price.setValue((double)1200);
		price.setCurrency("rupees");
		when(productPriceDao.retrivePriceByProductId(productId)).thenReturn(price);
PriceResponse priceResponse=this.productPriceServiceImpl.fetchpriceDetails(productId);
System.out.print(priceResponse.toString());
		assertEquals("rupees", priceResponse.getCurrency_code());
	}
	
	@Test
	public void fetchPriceValue()
	{
		Long productId=(long) 13860428;
		Double value=1200D;
		Price  price=new Price();
		price.setValue((double)1200);
		price.setCurrency("rupees");
		when(productPriceDao.retrivePriceByProductId(productId)).thenReturn(price);
PriceResponse priceResponse=this.productPriceServiceImpl.fetchpriceDetails(productId);
System.out.print(priceResponse.toString());
assertEquals(value, priceResponse.getValue());
	}
	
	
	@Test(expected = ProductNotFoundException.class)
	public void fetchProductNotFound() throws ProductNotFoundException
	{
		Long productId=(long) 123;
		Double value=(double)1200;
		Price  price=new Price();
		price.setValue((double)1200);
		price.setCurrency("rupees");
		when(productPriceDao.retrivePriceByProductId(productId)).thenThrow(new ProductNotFoundException("product not found"));
		try
		{
PriceResponse priceResponse=this.productPriceServiceImpl.fetchpriceDetails(productId);
		}catch(ProductNotFoundException ex)
		{
			System.out.println("");
			throw ex;
		}
	}
	
	
	@Test
	public void fetchProductDetails()
	{
		Long productId=13860428L;
		PriceResponse  priceResponse=new PriceResponse();
		priceResponse.setValue(Double.valueOf(1200));
		priceResponse.setCurrency_code("rupees");
		ProductPriceServiceImpl productPriceService = new ProductPriceServiceImpl();
		ProductPriceServiceImpl productPriceServiceSpy = Mockito.spy(productPriceService);
		
		Price  price=new Price();
		price.setValue(Double.valueOf(1200));
		price.setCurrency("rupees");		
		Mockito.doReturn(priceResponse).when(productPriceServiceSpy).retrivePrice(Mockito.any()); 
		Mockito.doReturn("The Big Lebowski (Blu-ray)").when(productPriceServiceSpy).retriveNameByProductId(Mockito.any()); 
		ProductPriceResponse productPriceResponse=productPriceServiceSpy.fetchproductDetails(productId);
		System.out.print("obj"+productPriceResponse);
		assertEquals("The Big Lebowski (Blu-ray)", productPriceResponse.getName());
		assertEquals(Double.valueOf(1200), productPriceResponse.getCurrentPrice().getValue());
		assertEquals("rupees", productPriceResponse.getCurrentPrice().getCurrency_code());
		assertEquals(productId, productPriceResponse.getId());
		
	}
	
}
