package com.myretail.service.inf;

import com.myretail.request.UpdatePriceRequest;
import com.myretail.response.PriceResponse;
import com.myretail.response.ProductPriceResponse;

public interface ProductPriceServiceInf {

	ProductPriceResponse fetchproductDetails(Long productId);

	PriceResponse fetchpriceDetails(Long productId);

	String updateProductPrice(Long productId, UpdatePriceRequest updatePriceRequest);



}
