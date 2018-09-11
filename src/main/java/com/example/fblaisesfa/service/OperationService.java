package com.example.fblaisesfa.service;

import java.util.List;

import com.example.fblaisesfa.model.Client;
import com.example.fblaisesfa.model.Command;
import com.example.fblaisesfa.model.CommandEntry;
import com.example.fblaisesfa.model.Product;
import com.example.fblaisesfa.model.Sale;
import com.example.fblaisesfa.model.SaleEntry;
import com.example.fblaisesfa.model.User;

public interface OperationService {
	public Command findCommandById(Long id);

	public List<Command> findAllCommands();

	public Command createEmptyCommand(User user);

	public Long saveClient(Client client);

	public Long updateClient(Client clientWithNewValues);

	public void deleteClient(Long id);

	public Product findProductById(Long id);

	public List<Product> findAllProducts();

	public Long saveProduct(Product product);

	public Long updateProduct(Product productWithNewValues);

	public void deleteProduct(Long id);

	Command saveCommand(Command command);

	void saveCommandEntry(CommandEntry entry, Command command);

	Command updateCommand(Command commandWithNewValues);

	public void deleteCommand(Long commandId);

	public Command removeEntryToCommand(Long commandId, Long entryId);

	public List<Sale> findAllSales();

	public Sale createEmptySale(User userConnected, Command command);

	public Sale findSaleById(Long saleId);

	public Sale removeEntryToSale(long saleId, long entryId);

	public void deleteSale(Long saleId, boolean isDirectSale);

	public void saveSaleEntry(SaleEntry entry, Sale sale);

	public CommandEntry findCommandEntry(Command command, Product product);

	public Sale removeEntryToDirectSale(long saleId, long entryId);

	void executeCommand(Command command, Client client);

	void closeSale(Sale sale, boolean isDirecteSale);

}