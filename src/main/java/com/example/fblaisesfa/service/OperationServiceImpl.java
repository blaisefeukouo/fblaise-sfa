package com.example.fblaisesfa.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fblaisesfa.exception.GenerateOutOfBoundOperationNumberException;
import com.example.fblaisesfa.model.Client;
import com.example.fblaisesfa.model.Command;
import com.example.fblaisesfa.model.CommandEntry;
import com.example.fblaisesfa.model.CommandState;
import com.example.fblaisesfa.model.Counter;
import com.example.fblaisesfa.model.Product;
import com.example.fblaisesfa.model.Sale;
import com.example.fblaisesfa.model.SaleEntry;
import com.example.fblaisesfa.model.User;
import com.example.fblaisesfa.repository.ClientRepository;
import com.example.fblaisesfa.repository.CommandEntryRepository;
import com.example.fblaisesfa.repository.CommandRepository;
import com.example.fblaisesfa.repository.CounterRepository;
import com.example.fblaisesfa.repository.ProductRepository;
import com.example.fblaisesfa.repository.SaleEntryRepository;
import com.example.fblaisesfa.repository.SaleRepository;

@Service("operationService")
public class OperationServiceImpl implements OperationService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private CommandEntryRepository commandEntryRepository;
	@Autowired
	private SaleEntryRepository saleEntryRepository;
	@Autowired
	private CommandRepository commandRepository;
	@Autowired
	private SaleRepository saleRepository;
	@Autowired
	private CounterRepository counterRepository;

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
	public List<Command> findAllCommands() {
		return commandRepository.findAll();
	}

	@Override
	public Command findCommandById(Long id) {
		return commandRepository.findById(id);
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
		// if(Product.isActuallyUsed(id))
		productRepository.delete(id);
	}

	@Override
	public Command saveCommand(Command command) {
		return commandRepository.save(command);
	}

	@Override
	public Command updateCommand(Command commandWithNewValues) {
		Command command = commandRepository.findById(commandWithNewValues.getId());
		command.copyValuesFrom(commandWithNewValues);
		commandRepository.save(command);
		return command;
	}

	@Override
	public Command createEmptyCommand(User user) {
		Command command = new Command(user, new Date());
		return commandRepository.save(command);
	}

	@Override
	public void saveCommandEntry(CommandEntry entry, Command command) {
		CommandEntry existingEntry = commandEntryRepository.findByProductAndCommand(entry.getProduct(), command);
		if (existingEntry != null) {
			existingEntry.setQuantity(existingEntry.getQuantity() + entry.getQuantity());
			entry = commandEntryRepository.save(existingEntry);
		} else {
			entry = commandEntryRepository.save(entry);
		}
		command.addEntry(entry);
	}

	@Override
	public void deleteCommand(Long commandId) {
		commandRepository.delete(commandId);
	}

	@Override
	public Command removeEntryToCommand(Long commandId, Long entryId) {
		Command command = commandRepository.findById(commandId);
		CommandEntry entry = commandEntryRepository.findById(entryId);
		command.removeEntry(entry);
		commandRepository.save(command);
		commandEntryRepository.delete(entry);
		return command;
	}

	@Override
	public List<Sale> findAllSales() {
		return saleRepository.findAll();
	}

	@Override
	public Sale createEmptySale(User user, Command command) {
		Sale sale = new Sale(user, command, new Date());
		saleRepository.save(sale);
		for (CommandEntry entry : command.getEntries()) {
			SaleEntry saleEntry = new SaleEntry(sale, entry.getProduct(), entry.getQuantity());
			saleEntryRepository.save(saleEntry);
			sale.addEntry(saleEntry);
		}
		return saleRepository.save(sale);
	}

	@Override
	public Sale findSaleById(Long saleId) {
		return saleRepository.findById(saleId);
	}

	@Override
	public void closeSale(Sale sale, boolean isDirecteSale) {
		sale = saleRepository.findById(sale.getId());
		sale.setState(CommandState.Closed);
		sale.setSaleNumber(generateSaleNumer());
		saleRepository.save(sale);
		Command command = commandRepository.findById(sale.getCommand().getId());
		command.setState(CommandState.DELIVERED);
		if (isDirecteSale) {
			command.setCommandNumber(generateCommandNumer());
		}
		commandRepository.save(command);

	}

	@Override
	public Sale removeEntryToSale(long saleId, long entryId) {
		Sale sale = saleRepository.findById(saleId);
		SaleEntry entry = saleEntryRepository.findById(entryId);
		sale.removeEntry(entry);
		saleRepository.save(sale);
		saleEntryRepository.delete(entry);
		return sale;

	}

	@Override
	public void deleteSale(Long saleId, boolean isDirectSale) {
		Sale sale = saleRepository.findById(saleId);
		Command command = commandRepository.findById(sale.getCommand().getId());
		boolean isOpened = sale.isOpened();
		saleRepository.delete(saleId);
		if (isOpened && isDirectSale) {
			commandRepository.delete(command);
		}

	}

	@Override
	public void saveSaleEntry(SaleEntry entry, Sale sale) {
		SaleEntry existingEntry = saleEntryRepository.findByProductAndSale(entry.getProduct(), sale);
		if (existingEntry != null) {
			existingEntry.setQuantity(existingEntry.getQuantity() + entry.getQuantity());
			entry = saleEntryRepository.save(existingEntry);
		} else {
			entry = saleEntryRepository.save(entry);
		}
		sale.addEntry(entry);
		saleRepository.save(sale);
	}

	@Override
	public CommandEntry findCommandEntry(Command command, Product product) {
		return commandEntryRepository.findByProductAndCommand(product, command);
	}

	@Override
	public Sale removeEntryToDirectSale(long saleId, long saleEntryId) {
		SaleEntry saleEntry = saleEntryRepository.findById(saleEntryId);
		Sale sale = removeEntryToSale(saleId, saleEntryId);

		Command command = commandRepository.findById(sale.getCommandId());
		CommandEntry commandEntry = commandEntryRepository.findByProductAndCommand(saleEntry.getProduct(), command);
		CommandEntry entry = commandEntryRepository.findById(commandEntry.getId());
		command.removeEntry(entry);
		commandRepository.save(command);
		commandEntryRepository.delete(entry);
		return sale;

	}

	@Override
	public void executeCommand(Command command, Client client) {
		command.setClient(client);
		command.execute();
		command.setCommandNumber(generateCommandNumer());
		updateCommand(command);
	}

	private String generateCommandNumer() {
		SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
		String dateAsString = dfm.format(new Date());
		String[] splitedDate = dateAsString.split("-");
		String year = splitedDate[0].substring(2);
		String commandNumber = "CM" + year + "002";

		List<Counter> counters = counterRepository.findAll();
		Counter counter = null;
		if (counters.isEmpty()) {
			counter = new Counter(0L, 0L);
			counter = counterRepository.save(counter);
		} else {
			counter = counters.get(0);
		}
		counter.incrementCommandCounter();
		String counterAsString = Long.toString(counter.getCommandCounter());
		switch (counterAsString.length()) {
		case 1:
			commandNumber = commandNumber + "0000" + counterAsString;
			break;
		case 2:
			commandNumber = commandNumber + "000" + counterAsString;
			break;
		case 3:
			commandNumber = commandNumber + "00" + counterAsString;
			break;
		case 4:
			commandNumber = commandNumber + "0" + counterAsString;
			break;

		case 5:
			commandNumber = commandNumber + counterAsString;
			break;
		default:
			throw new GenerateOutOfBoundOperationNumberException();
		}
		counterRepository.save(counter);
		return commandNumber;
	}

	private String generateSaleNumer() {
		SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
		String dateAsString = dfm.format(new Date());
		String[] splitedDate = dateAsString.split("-");
		String year = splitedDate[0].substring(2);
		String saleNumber = "SA" + year + "002";

		List<Counter> counters = counterRepository.findAll();
		Counter counter = null;
		if (counters.isEmpty()) {
			counter = new Counter(0L, 0L);
			counter = counterRepository.save(counter);
		} else {
			counter = counters.get(0);
		}
		counter.incrementSaleCounter();
		String counterAsString = Long.toString(counter.getSaleCounter());
		switch (counterAsString.length()) {
		case 1:
			saleNumber = saleNumber + "0000" + counterAsString;
			break;
		case 2:
			saleNumber = saleNumber + "000" + counterAsString;
			break;
		case 3:
			saleNumber = saleNumber + "00" + counterAsString;
			break;
		case 4:
			saleNumber = saleNumber + "0" + counterAsString;
			break;

		case 5:
			saleNumber = saleNumber + counterAsString;
			break;
		default:
			throw new GenerateOutOfBoundOperationNumberException();
		}
		counterRepository.save(counter);
		return saleNumber;
	}
}
