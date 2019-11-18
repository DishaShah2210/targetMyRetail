package com.myretail.dao.inf;

import com.myretail.pojo.Price;
import com.myretail.request.UpdatePriceRequest;

public interface ProductPriceDaoInf {
	public Price retrivePriceByProductId(Long ProductId);

	public String updatePriceByProductId(Long productId, UpdatePriceRequest updatePriceRequest);
}
