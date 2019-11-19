package com.myretail.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.myretail.response.PriceResponse;
import com.myretail.response.ProductPriceResponse;
import com.myretail.service.impl.ProductPriceServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

  
    @Test
    public void priceDetails() throws Exception {
		mockMvc.perform(get("/product/13860428/price").contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());
    }
	
	   @Test
    public void priceDetailsException() throws Exception {
		mockMvc.perform(get("/products/138604").contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().is5xxServerError());
    }
    
    
    @Test
    public void priceApi() throws Exception {
		mockMvc.perform(get("/product/138604/price").contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().is2xxSuccessful());
    }

	    @Test
    public void productPriceDetailsResponse() throws Exception {
		MvcResult mvcResult =	mockMvc.perform(get("/products/13860428").contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		ProductPriceResponse response = new ObjectMapper().readValue(content, ProductPriceResponse.class);

		if(response !=null) {
				assert("The Big Lebowski (Blu-ray)".equals(response.getName()));
				assert(response.getCurrentPrice().getValue() == 1300D);
			
		}
    }

    
    @Test
    public void priceDetailsResponse() throws Exception {
		MvcResult mvcResult =	mockMvc.perform(get("/product/13860428/price").contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		PriceResponse response = new ObjectMapper().readValue(content, PriceResponse.class);

		if(response !=null) {
				assert(response.getValue() == 1300D);
			
		}
    }

	
	@Test
	public void updateProductPrice() throws Exception {
		String productPrice ="{\n" + 
				"    \"id\": 13860428,\n" + 
				"    \"name\": \"The Big Lebowski (Blu-ray)\",\n" + 
				"    \"current_price\": {\n" + 
				"        \"value\": 1300,\n" + 
				"        \"currency_code\": \"rupee\"\n" + 
				"    }\n" + 
				"}";
	
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post("/products/13860428")
				.accept(MediaType.APPLICATION_JSON).content(productPrice)
				.contentType(MediaType.APPLICATION_JSON)
				).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

}
