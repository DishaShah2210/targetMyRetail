package com.myretail.dao.impl;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.Exception.ProductNotFoundException;
import com.myretail.dao.inf.ProductPriceDaoInf;
import com.myretail.pojo.Price;
import com.myretail.pojo.Product;
import com.myretail.request.UpdatePriceRequest;



@Service
public class ProductPriceDaoImpl implements ProductPriceDaoInf {



@Autowired
private EntityManager entityManager;

public Session getSession()
{
	Session session = entityManager.unwrap(Session.class);
	return session;
	
}

	@Transactional
	public Price retrivePriceByProductId(Long productId)  throws ProductNotFoundException{
		Product product=null;
	
		Session session = getSession();
		String query="select * from Product where product_id=:product_id ";
		Query qry=session.createSQLQuery(query).addEntity(Product.class).setParameter("product_id", productId);
		product=  (Product) qry.uniqueResult();
			if(product !=  null)
			{
				Price price=product.getPrice();
			
			return price;
			}
			else
			{
				
				throw new ProductNotFoundException("Product : "+productId+" not found");	
			}
	
	}

	@Override
	@Transactional
	public String updatePriceByProductId(Long productId, UpdatePriceRequest updatePriceRequest) {
		Session session = getSession();
		String query="select * from Product where product_id=:product_id ";
		Query qry=session.createSQLQuery(query).addEntity(Product.class).setParameter("product_id", productId);
		Product product=  (Product) qry.uniqueResult();
	if(product !=null)
	{
		Price price=new Price();
		price.setValue(updatePriceRequest.getCurrentPrice().getValue());
		price.setCurrency(updatePriceRequest.getCurrentPrice().getCurrency_code());
		session.saveOrUpdate(price);
		product.setPrice(price);
		session.saveOrUpdate(product);
	}
	else
	{
		Price price=new Price();
		price.setValue(updatePriceRequest.getCurrentPrice().getValue());
		price.setCurrency(updatePriceRequest.getCurrentPrice().getCurrency_code());
		Product newproduct=new Product();
		newproduct.setProductId(updatePriceRequest.getId());
		newproduct.setName(updatePriceRequest.getName());
		newproduct.setPrice(price);
		session.saveOrUpdate(newproduct);
	}
		return "success";
	}

}
