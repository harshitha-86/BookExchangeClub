package com.goodbookclub.bookclub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.goodbookclub.bookclub.domains.Product;
import com.goodbookclub.bookclub.services.product.ProductService;

@RestController
public class ProductController {
	
	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return productService.listOfProducts();
	}
	
	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable Integer id) {
		return productService.findProductById(id);
	}
	
	@PostMapping("/products/new")
	public Product saveProduct(@RequestBody Product product) {
		return productService.saveOrUpdateProduct(product);
	}
	
	@PutMapping("/products/edit/{id}")
	public Product updateProduct(@RequestBody Product product) {
		return productService.saveOrUpdateProduct(product);
	}
	
	@DeleteMapping("/products/delete/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		productService.deleteProduct(id);
	}

}
