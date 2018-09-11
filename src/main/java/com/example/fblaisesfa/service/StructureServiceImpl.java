package com.example.fblaisesfa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fblaisesfa.model.Client;
import com.example.fblaisesfa.model.Product;
import com.example.fblaisesfa.repository.ClientRepository;
import com.example.fblaisesfa.repository.ProductRepository;

@Service("structureService")
public class StructureServiceImpl implements StructureService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ClientRepository clientRepository;


	@Override
	public Long saveClient(Client client) {
		clientRepository.save(client);
		return client.getId();
	}

	@Override
	public Long updateClient(Client clientWithNewValues) {
		Client client = clientRepository.findById(clientWithNewValues.getId());
		client.copyValuesFrom(clientWithNewValues);
		clientRepository.save(client);
		return client.getId();
	}


	@Override
	public List<Client> findAllClients() {
		return clientRepository.findAll();
	}

	@Override
	public Client findClientById(Long id) {
		return clientRepository.findById(id);
	}

	@Override
	public void deleteClient(Long id) {
		clientRepository.delete(id);
	}

	@Override
	public Product findProductById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Long saveProduct(Product product) {
		productRepository.save(product);
		return product.getId();
	}

	@Override
	public Long updateProduct(Product productWithNewValues) {
		Product product = productRepository.findById(productWithNewValues.getId());
		product.copyValuesFrom(productWithNewValues);
		productRepository.save(product);
		return product.getId();
	}

	@Override
	public void deleteProduct(Long id) {
//		if(Product.isActuallyUsed(id))
		productRepository.delete(id);
	}

}
