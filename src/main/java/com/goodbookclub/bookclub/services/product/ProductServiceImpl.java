package com.goodbookclub.bookclub.services.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodbookclub.bookclub.domains.Product;
import com.goodbookclub.bookclub.repositories.ProductRepository;
import com.goodbookclub.bookclub.services.jms.SendTextMessageService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
	
	private ProductRepository productRepository;
	private SendTextMessageService sendTextMessageService;

	@Autowired
	public void setSendTextMessageService(SendTextMessageService sendTextMessageService) {
		this.sendTextMessageService = sendTextMessageService;
	}

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> listOfProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		sendTextMessageService.sendTextMessage("Listing products");
		log.info("Total no. of products: "+products.size());
		return products;
	}

	@Override
	public Product findProductById(Integer id) {
		sendTextMessageService.sendTextMessage("Requested product id: "+id);
		Product product = productRepository.findById(id).orElse(null);
		if(product==null) {
			log.error("Product doesn't exist with id: "+id);
		}else {
			log.info("Product found: "+product);
		}
		return product;
	}

	@Override
	public Product saveOrUpdateProduct(Product product) {
		if(product.getId()==null) {
			sendTextMessageService.sendTextMessage("New product added");
			log.info("Product saved: "+product);
		}else {
			sendTextMessageService.sendTextMessage("product details updated for id: "+product.getId());
			log.info("Product updated: "+product);
		}
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Integer id) {
		sendTextMessageService.sendTextMessage("Request to delete product id: "+id);
		Product product = productRepository.findById(id).orElse(null);
		if(product==null)
			log.error("Product doesn't exist with id: "+id);
		else {
			log.info("deleted the product with id: "+id);
			productRepository.deleteById(id);
		}

	}

}
