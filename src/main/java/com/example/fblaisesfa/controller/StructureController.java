package com.example.fblaisesfa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.fblaisesfa.model.Client;
import com.example.fblaisesfa.model.Product;
import com.example.fblaisesfa.model.ProductFamily;
import com.example.fblaisesfa.model.Sector;
import com.example.fblaisesfa.model.SfaUiObject;
import com.example.fblaisesfa.model.User;
import com.example.fblaisesfa.service.AdministrationService;
import com.example.fblaisesfa.service.StructureService;

@Controller
public class StructureController {
	@Autowired
	private StructureService structureService;
	@Autowired
	private AdministrationService administrationService;
	//
	// @Autowired(required = true)
	// @Qualifier(value = "administrationService")
	// public void setUserService(AdministrationService us) {
	// this.administrationService = us;
	// }
	//
	// @RequestMapping(value = "/login", method = RequestMethod.GET)
	// public String gotoLogin(Model model) {
	// return "Login";
	// }
	//
	// @RequestMapping(value = "/home", method = RequestMethod.GET)
	// public String gotoHome(Model model) {
	// return "home";
	// }

	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public String getClientList(Model model) {
		model.addAttribute("client", new Client());
		List<Client> listClients = this.structureService.findAllClients();
		model.addAttribute("listClients", listClients);
		SfaUiObject forDeleteAction = new SfaUiObject();
		model.addAttribute("forDeleteAction", forDeleteAction);
		getTheUserConnected(model);
		return "client/ClientsList";
	}

	@RequestMapping(value = "/client.add.htm", method = RequestMethod.GET)
	public String getClientAddPage(Model model) {
		model.addAttribute("client", new Client());
		List<Sector> listSectors = administrationService.findAllSectors();
		model.addAttribute("listSectors", listSectors);
		getTheUserConnected(model);
		return "client/ClientAdd";
	}

	// For add and update client both
	@RequestMapping(value = "/client/add", method = RequestMethod.POST)
	public String addClient(@ModelAttribute("client") Client p) {
		Long clientId;
		if (p.getId() == null) {
			// new person, add it
			clientId = this.structureService.saveClient(p);
		} else {
			// existing person, call update
			clientId = this.structureService.updateClient(p);
		}

		return "redirect:/client.view.htm/" + clientId;

	}

	// @RequestMapping("/client.remove/{id}")
	// public String removeClient(@PathVariable("id") Long id) {
	// this.structureService.deleteClient(id);
	// return "redirect:/clients";
	// }

	@RequestMapping(value = "/client.remove")
	public String deleteClient(Model model, @ModelAttribute("forDeleteAction") SfaUiObject forDeleteAction) {
		this.structureService.deleteClient(forDeleteAction.getId());
		return "redirect:/clients";

	}

	@RequestMapping("/client.edit.htm/{id}")
	public String editClient(@PathVariable("id") Long clientId, Model model) {
		model.addAttribute("client", structureService.findClientById(clientId));
		List<Sector> listSectors = administrationService.findAllSectors();
		model.addAttribute("listSectors", listSectors);
		getTheUserConnected(model);
		return "client/ClientEdit";
	}

	@RequestMapping("/client.view.htm/{id}")
	public String viewClient(@PathVariable("id") Long id, Model model) {
		model.addAttribute("client", this.structureService.findClientById(id));
		getTheUserConnected(model);
		return "client/ClientDetails";
	}

	// -----------Product--------------
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String getProductList(Model model) {
		model.addAttribute("product", new Product());
		List<Product> listProducts = this.structureService.findAllProducts();
		model.addAttribute("listProducts", listProducts);
		SfaUiObject forDeleteAction = new SfaUiObject();
		model.addAttribute("forDeleteAction", forDeleteAction);
		getTheUserConnected(model);
		return "product/ProductsList";
	}

	@RequestMapping(value = "/product.add.htm", method = RequestMethod.GET)
	public String getProductAddPage(Model model) {
		model.addAttribute("product", new Product());
		List<ProductFamily> listFamilies = administrationService.findAllProductFamilies();
		model.addAttribute("listFamilies", listFamilies);
		getTheUserConnected(model);
		return "product/ProductAdd";
	}

	// For add and update Product both
	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product p) {
		Long productId;
		if (p.getId() == null) {
			// new person, add it
			productId = this.structureService.saveProduct(p);
		} else {
			// existing person, call update
			productId = this.structureService.updateProduct(p);
		}

		return "redirect:/product.view.htm/" + productId;

	}

//	@RequestMapping("/product.remove/{id}")
//	public String removeProduct(@PathVariable("id") Long id) {
//		this.structureService.deleteProduct(id);
//		return "redirect:/products";
//	}

	@RequestMapping(value = "/product.remove")
	public String deleteProduct(Model model, @ModelAttribute("forDeleteAction") SfaUiObject forDeleteAction) {
		this.structureService.deleteProduct(forDeleteAction.getId());
		return "redirect:/products";

	}

	@RequestMapping("/product.edit.htm/{id}")
	public String editProduct(@PathVariable("id") Long productId, Model model) {
		model.addAttribute("product", structureService.findProductById(productId));
		List<ProductFamily> listFamilies = administrationService.findAllProductFamilies();
		model.addAttribute("listFamilies", listFamilies);
		getTheUserConnected(model);
		return "product/ProductEdit";
	}

	@RequestMapping("/product.view.htm/{id}")
	public String viewProduct(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", this.structureService.findProductById(id));
		getTheUserConnected(model);
		return "product/ProductDetails";
	}

	private void getTheUserConnected(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userConnected = administrationService.findUserByUserName(auth.getName());
		model.addAttribute("currentUser", userConnected);
		model.addAttribute("userName", userConnected.getFirstName() + " " + userConnected.getLastName() + " ("
				+ userConnected.getUserName() + ")");
	}
}