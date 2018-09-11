package com.example.fblaisesfa.service;

import java.util.List;

import com.example.fblaisesfa.model.Client;
import com.example.fblaisesfa.model.Product;

public interface StructureService {
	public Client findClientById(Long id);

	public List<Client> findAllClients();

	public Long saveClient(Client client);

	public Long updateClient(Client clientWithNewValues);

	public void deleteClient(Long id);
	
	public Product findProductById(Long id);

	public List<Product> findAllProducts();

	public Long saveProduct(Product product);

	public Long updateProduct(Product productWithNewValues);

	public void deleteProduct(Long id);
}