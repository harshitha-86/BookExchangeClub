package com.goodbookclub.bookclub.services.product;

import java.util.List;

import com.goodbookclub.bookclub.domains.Product;

public interface ProductService {

	List<Product> listOfProducts();
	Product findProductById(Integer id);
	Product saveOrUpdateProduct(Product product);
	void deleteProduct(Integer id);
}
