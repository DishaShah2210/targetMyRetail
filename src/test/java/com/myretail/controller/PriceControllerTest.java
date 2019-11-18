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


}
