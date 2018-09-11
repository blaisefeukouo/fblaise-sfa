package com.example.fblaisesfa.controller;

import org.springframework.stereotype.Controller;

@Controller
public class OperationController_Old {
//	@Autowired
//	private StructureService structureService;
//	@Autowired
//	private AdministrationService administrationService;
//	@Autowired
//	private OperationService operationService;
//
//	@RequestMapping(value = "/commands", method = RequestMethod.GET)
//	public String listCommands(Model model) {
//		model.addAttribute("command", new Command());
//		List<Command> listCommands = this.operationService.findAllCommands();
//		model.addAttribute("listCommands", listCommands);
//		getTheUserConnected(model);
//		return "command/CommandsList";
//	}
//
//	private User getTheUserConnected(Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User userConnected = administrationService.findUserByUserName(auth.getName());
//		model.addAttribute("currentUser", userConnected);
//		model.addAttribute("userName", userConnected.getFirstName() + " " + userConnected.getLastName() + " ("
//				+ userConnected.getUserName() + ")");
//		return userConnected;
//	}
//
//	@RequestMapping("/command.view.htm/{id}")
//	public String viewCommand(@PathVariable("id") Long id, Model model) {
//		model.addAttribute("command", this.operationService.findCommandById(id));
//		getTheUserConnected(model);
//		return "command/CommandDetails";
//	}
//
//	@RequestMapping(value = "/command.add.htm", method = RequestMethod.GET)
//	public String getCommandAddPage(Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User userConnected = administrationService.findUserByUserName(auth.getName());
//		Command command = operationService.createEmptyCommand(userConnected);
//		UiOperationEntry uiCommandEntry = new UiOperationEntry(command);
//		model.addAttribute("command", command);
//		model.addAttribute("commandEntry", uiCommandEntry);
//		List<Client> listClients = structureService.findAllClients();
//		model.addAttribute("listClients", listClients);
//		List<Product> listProducts = operationService.findAllProducts();
//		model.addAttribute("listProducts", listProducts);
//		model.addAttribute("listEntries", command.getEntries());
//		getTheUserConnected(model);
//		return "command/CommandAdd";
//	}
//
//	@RequestMapping(value = "/commandentry/add/{id}", method = RequestMethod.POST)
//	public String addCommandEntry(@ModelAttribute("commandEntry") UiOperationEntry uiEntry,
//			@PathVariable("id") Long commandId, Model model) {
//		Command command = operationService.findCommandById(commandId);
//		int quantity = uiEntry.getQuantity();
//		if (quantity <= 0) {
//			model.addAttribute("quantiityError", "Invalid quantity");
//		} else {
//			CommandEntry entry = new CommandEntry(command, uiEntry.getProduct(), quantity);
//			operationService.saveCommandEntry(entry, command);
//
//			command = operationService.saveCommand(command);
//		}
//
//		model.addAttribute("command", command);
//		model.addAttribute("commandEntry", new UiOperationEntry(command, uiEntry.getClient()));
//		List<Client> listClients = structureService.findAllClients();
//		model.addAttribute("listClients", listClients);
//		List<Product> listProducts = operationService.findAllProducts();
//		model.addAttribute("listProducts", listProducts);
//		model.addAttribute("listEntries", command.getEntries());
//		getTheUserConnected(model);
//		return "command/CommandAdd";
//
//	}
//
//	@RequestMapping(value = "/entry.remove", method = RequestMethod.GET)
//	public String removeCommandEntry(@RequestParam("commandId") long commandId, @RequestParam("entryId") long entryId,
//			Model model) {
//		Command command = operationService.removeEntryToCommand(commandId, entryId);
//		model.addAttribute("command", command);
//		model.addAttribute("commandEntry", new UiOperationEntry(command));
//		List<Client> listClients = structureService.findAllClients();
//		model.addAttribute("listClients", listClients);
//		List<Product> listProducts = operationService.findAllProducts();
//		model.addAttribute("listProducts", listProducts);
//		model.addAttribute("listEntries", command.getEntries());
//		getTheUserConnected(model);
//		return "command/CommandAdd";
//	}
//
//	@RequestMapping(value = "/command/save")
//	public String saveCommand(@RequestParam("commandId") Long commandId, @RequestParam("clientId") Long clientId,
//			@ModelAttribute("commandEntry") UiOperationEntry uiEntry, @ModelAttribute("command") Command command,
//			Model model) {
//
//		Client client = structureService.findClientById(command.getClientId());
//		command = operationService.findCommandById(commandId);
//		List<CommandEntry> entries = command.getEntries();
//		if (entries.isEmpty()) {
//			model.addAttribute("emptyEntriesError", "Invalid quantity");
//			model.addAttribute("command", command);
//			model.addAttribute("commandEntry", new UiOperationEntry(command, uiEntry.getClient()));
//			List<Client> listClients = structureService.findAllClients();
//			model.addAttribute("listClients", listClients);
//			List<Product> listProducts = operationService.findAllProducts();
//			model.addAttribute("listProducts", listProducts);
//			model.addAttribute("listEntries", command.getEntries());
//			getTheUserConnected(model);
//			return "command/CommandAdd";
//		}
//		command.setClient(client);
//		command.execute();
//		operationService.updateCommand(command);
//		getTheUserConnected(model);
//		return "redirect:/commands";
//
//	}
//
//	@RequestMapping(value = "/command.details.htm", method = RequestMethod.GET)
//	public String getCommandDetailsPage(Model model,
//			@RequestParam(value = "commandId", required = false) Long commandId) {
//
//		Command command = operationService.findCommandById(commandId);
//		UiOperationEntry uiCommandEntry = new UiOperationEntry(command);
//		uiCommandEntry.setClient(command.getClient());
//		model.addAttribute("command", command);
//		model.addAttribute("commandEntry", uiCommandEntry);
//		List<Client> listClients = structureService.findAllClients();
//		model.addAttribute("listClients", listClients);
//		List<Product> listProducts = operationService.findAllProducts();
//		model.addAttribute("listProducts", listProducts);
//		model.addAttribute("listEntries", command.getEntries());
//		getTheUserConnected(model);
//		return "command/CommandDetails";
//	}
//
//	@RequestMapping(value = "/command/cancel")
//	public String cancelCommand(@RequestParam("commandId") long commandId) {
//		Command command = operationService.findCommandById(commandId);
//		if (command.isOpened()) {
//			operationService.deleteCommand(commandId);
//		}
//		return "redirect:/commands";
//
//	}
//
//	@RequestMapping(value = "/command.remove.htm")
//	public String deleteCommand(Model model, @RequestParam(value = "commandId", required = false) Long commandId) {
//		operationService.deleteCommand(commandId);
//		return "redirect:/commands";
//
//	}
//
//	@RequestMapping(value = "/sales", method = RequestMethod.GET)
//	public String listSales(Model model) {
//		model.addAttribute("sale", new Sale());
//		List<Sale> listSales = this.operationService.findAllSales();
//		model.addAttribute("listSales", listSales);
//		getTheUserConnected(model);
//		return "sale/SalesList";
//	}
//
//	@RequestMapping(value = "command.validate.htm", method = RequestMethod.POST)
//	public String getValidateCommadPage(Model model, @RequestParam(value = "commandId") Long commandId) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User userConnected = administrationService.findUserByUserName(auth.getName());
//
//		Command command = operationService.findCommandById(commandId);
//		Sale sale = operationService.createEmptySale(userConnected, command);
//		UiOperationEntry uiSaleEntry = new UiOperationEntry(sale);
//		model.addAttribute("sale", sale);
//		model.addAttribute("saleEntry", uiSaleEntry);
//		List<Product> listProducts = operationService.findAllProducts();
//		model.addAttribute("listProducts", listProducts);
//		model.addAttribute("listEntries", sale.getEntries());
//		getTheUserConnected(model);
//		return "sale/SaleAdd";
//	}
//
//	@RequestMapping(value = "/sale.add.htm", method = RequestMethod.GET)
//	public String getSaleAddPage(Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User userConnected = administrationService.findUserByUserName(auth.getName());
//
//		Command command = operationService.createEmptyCommand(userConnected);
//		Sale sale = operationService.createEmptySale(userConnected, command);
//		UiOperationEntry uiSaleEntry = new UiOperationEntry(sale);
//		model.addAttribute("sale", sale);
//		model.addAttribute("saleEntry", uiSaleEntry);
//		List<Product> listProducts = operationService.findAllProducts();
//		List<Client> listClients = structureService.findAllClients();
//		model.addAttribute("listProducts", listProducts);
//		model.addAttribute("listClients", listClients);
//		model.addAttribute("listEntries", sale.getEntries());
//		getTheUserConnected(model);
//		return "sale/DirectSaleAdd";
//	}
//
//	@RequestMapping(value = "/sale/cancel")
//	public String cancelSale(@RequestParam("saleId") long saleId) {
//		Sale sale = operationService.findSaleById(saleId);
//		Command command = operationService.findCommandById(sale.getCommandId());
//		if (sale.isOpened()) {
//			operationService.deleteSale(saleId);
//		}
//		return "redirect:/command.details.htm?commandId=" + command.getId();
//
//	}
//
//	@RequestMapping(value = "/directsale/cancel")
//	public String cancelDirectSale(@RequestParam("saleId") long saleId) {
//		Sale sale = operationService.findSaleById(saleId);
//		Command command = operationService.findCommandById(sale.getCommandId());
//		if (sale.isOpened()) {
//			operationService.deleteSale(saleId);
//			operationService.deleteCommand(command.getId());
//		}
//		return "redirect:/sales";
//
//	}
//
//	@RequestMapping(value = "/saleentry/add/{id}", method = RequestMethod.POST)
//	public String addSaleEntry(@ModelAttribute("saleEntry") UiOperationEntry uiEntry, @PathVariable("id") Long saleId,
//			Model model) {
//		Sale sale = operationService.findSaleById(saleId);
//		int quantity = uiEntry.getQuantity();
//		if (quantity <= 0) {
//			model.addAttribute("quantiityError", "Invalid quantity");
//		} else {
//			SaleEntry entry = new SaleEntry(sale, uiEntry.getProduct(), quantity);
//			operationService.saveSaleEntry(entry, sale);
//		}
//
//		model.addAttribute("sale", sale);
//		model.addAttribute("saleEntry", new UiOperationEntry(sale));
//		List<Product> listProducts = operationService.findAllProducts();
//		model.addAttribute("listProducts", listProducts);
//		model.addAttribute("listEntries", sale.getEntries());
//		getTheUserConnected(model);
//		return "sale/SaleAdd";
//
//	}
//
//	@RequestMapping(value = "/directsale/saleentry/add/{id}", method = RequestMethod.POST)
//	public String addSaleEntryToDirectSale(@ModelAttribute("saleEntry") UiOperationEntry uiEntry,
//			@PathVariable("id") Long saleId, Model model) {
//		Sale sale = operationService.findSaleById(saleId);
//		Command command = operationService.findCommandById(sale.getCommandId());
//
//		int quantity = uiEntry.getQuantity();
//		if (quantity <= 0) {
//			model.addAttribute("quantiityError", "Invalid quantity");
//		} else {
//			CommandEntry commandEntry = new CommandEntry(command, uiEntry.getProduct(), quantity);
//			operationService.saveCommandEntry(commandEntry, command);
//			operationService.saveCommand(command);
//			SaleEntry entry = new SaleEntry(sale, uiEntry.getProduct(), quantity);
//			operationService.saveSaleEntry(entry, sale);
//		}
//		getTheUserConnected(model);
//		model.addAttribute("sale", sale);
//		model.addAttribute("saleEntry", new UiOperationEntry(sale));
//		List<Product> listProducts = operationService.findAllProducts();
//		model.addAttribute("listProducts", listProducts);
//		model.addAttribute("listEntries", sale.getEntries());
//		return "sale/DirectSaleAdd";
//
//	}
//
//	@RequestMapping(value = "/saleentry.remove", method = RequestMethod.GET)
//	public String removeSaleEntry(@RequestParam("saleId") long saleId, @RequestParam("entryId") long entryId,
//			Model model) {
//		Sale sale = operationService.removeEntryToSale(saleId, entryId);
//		model.addAttribute("sale", sale);
//		model.addAttribute("commandEntry", new UiOperationEntry(sale));
//		List<Client> listClients = structureService.findAllClients();
//		model.addAttribute("listClients", listClients);
//		List<Product> listProducts = operationService.findAllProducts();
//		model.addAttribute("listProducts", listProducts);
//		model.addAttribute("listEntries", sale.getEntries());
//		getTheUserConnected(model);
//		return "sale/SaleAdd";
//	}
//
//	@RequestMapping(value = "/directsale/saleentry.remove", method = RequestMethod.GET)
//	public String removeSaleEntryToDirectSale(@RequestParam("saleId") long saleId,
//			@RequestParam("entryId") long entryId, Model model) {
//		Sale sale = operationService.removeEntryToDirectSale(saleId, entryId);
//		getTheUserConnected(model);
//		model.addAttribute("sale", sale);
//		model.addAttribute("commandEntry", new UiOperationEntry(sale));
//		List<Client> listClients = structureService.findAllClients();
//		model.addAttribute("listClients", listClients);
//		List<Product> listProducts = operationService.findAllProducts();
//		model.addAttribute("listProducts", listProducts);
//		model.addAttribute("listEntries", sale.getEntries());
//		return "sale/DirectSaleAdd";
//	}
//
//	@RequestMapping(value = "/sale/save")
//	public String saveSale(@RequestParam("saleId") Long saleId, @ModelAttribute("sale") Sale sale, Model model) {
//
//		sale = operationService.findSaleById(saleId);
//		List<SaleEntry> entries = sale.getEntries();
//		if (entries.isEmpty()) {
//			model.addAttribute("emptyEntriesError", "Invalid entries");
//			model.addAttribute("sale", sale);
//			model.addAttribute("saleEntry", new UiOperationEntry(sale));
//			List<Product> listProducts = operationService.findAllProducts();
//			model.addAttribute("listProducts", listProducts);
//			model.addAttribute("listEntries", sale.getEntries());
//			getTheUserConnected(model);
//			return "sale/SaleAdd";
//		}
//		operationService.closeSale(sale);
//		getTheUserConnected(model);
//		return "redirect:/commands";
//
//	}
//
//	@RequestMapping(value = "/directsale/save")
//	public String saveDirectSale(@RequestParam("saleId") Long saleId, @RequestParam("clientId") Long clientId,
//			@ModelAttribute("sale") Sale sale, Model model) {
//
//		sale = operationService.findSaleById(saleId);
//		List<SaleEntry> entries = sale.getEntries();
//		if (entries.isEmpty()) {
//			model.addAttribute("emptyEntriesError", "Invalid entries");
//			model.addAttribute("sale", sale);
//			model.addAttribute("saleEntry", new UiOperationEntry(sale));
//			List<Product> listProducts = operationService.findAllProducts();
//			model.addAttribute("listProducts", listProducts);
//			model.addAttribute("listEntries", sale.getEntries());
//			getTheUserConnected(model);
//			return "sale/DirectSaleAdd";
//		}
//		Client client = structureService.findClientById(clientId);
//		Command command = sale.getCommand();
//		command.setClient(client);
//		operationService.updateCommand(command);
//		operationService.closeSale(sale);
//		getTheUserConnected(model);
//		return "redirect:/sales";
//
//	}
//
//	@RequestMapping(value = "/sale.remove.htm")
//	public String deleteSale(Model model, @RequestParam(value = "saleId", required = false) Long saleId) {
//		operationService.deleteSale(saleId);
//		return "redirect:/sales";
//
//	}
}