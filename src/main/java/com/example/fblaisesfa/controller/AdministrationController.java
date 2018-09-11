package com.example.fblaisesfa.controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.fblaisesfa.model.ProductFamily;
import com.example.fblaisesfa.model.Role;
import com.example.fblaisesfa.model.Sector;
import com.example.fblaisesfa.model.SfaUiObject;
import com.example.fblaisesfa.model.User;
import com.example.fblaisesfa.service.AdministrationService;

@Controller
public class AdministrationController {
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

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public String getUserList(Model model) {
		model.addAttribute("user", new User());
		List<User> listUsers = administrationService.findAllUsers();
		model.addAttribute("listUsers", listUsers);
		SfaUiObject forDeleteAction = new SfaUiObject();
		model.addAttribute("forDeleteAction", forDeleteAction);
		getTheUserConnected(model);
		return "admin/users/UsersList";
	}

	@RequestMapping(value = "/admin/user.add.htm", method = RequestMethod.GET)
	public String getUserAddPage(Model model) {
		model.addAttribute("user", new User());
		getTheUserConnected(model);
		return "admin/users/UserAdd";
	}

	// For add and update person both
	@RequestMapping(value = "/admin/user/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, Model model) {
		User userExists = administrationService.findUserByUserName(user.getUserName());
		String viewToReturn;
		Long userId;
		if (userExists != null) {
			model.addAttribute("userNameError", "There is already a user registered with the user name provided");
			viewToReturn = "admin/users/UserAdd";
		} else {
			userId = administrationService.saveUser(user);
			model.addAttribute("successMessage", "User has been registered successfully");
			model.addAttribute("user", new User());
			viewToReturn = "redirect:/admin/user.view.htm/" + userId;
		}
		getTheUserConnected(model);
		return viewToReturn;
	}

	@RequestMapping("/admin/user.edit.htm/{id}")
	public String getEditUserPage(@PathVariable("id") Long id, Model model) {
		User user = this.administrationService.findUserById(id);
		model.addAttribute("user", user);
		getTheUserConnected(model);
		return "admin/users/UserEdit";
	}

	@RequestMapping(value = "/admin/user/edit", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		String viewToReturn;
		Long userId;
		if (user.getId() == null) {
			bindingResult.rejectValue("id", "error.user", "The user you are trying to update does not exist");
		}
		if (bindingResult.hasErrors()) {
			viewToReturn = "admin/users/UserEdit";
		} else {
			userId = administrationService.updateUser(user);
			model.addAttribute("successMessage", "User has been updated successfully");
			model.addAttribute("user", new User());
			viewToReturn = "redirect:/admin/user.view.htm/" + userId;

		}
		getTheUserConnected(model);
		return viewToReturn;

	}

	@RequestMapping("/admin/user.remove")
	public String deleteUser(Model model, @ModelAttribute("forDeleteAction") SfaUiObject forDeleteAction) {
		Long id = forDeleteAction.getId();
		User user = administrationService.findUserById(id);
		user.setRoles(new HashSet<Role>());
		administrationService.saveUser(user);
		this.administrationService.deleteUser(id);
		return "redirect:/admin/users";
	}

	@RequestMapping("/admin/user.view.htm/{id}")
	public String viewUser(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", this.administrationService.findUserById(id));
		getTheUserConnected(model);
		return "admin/users/UserDetails";
	}

	// -------------Sectors-----------
	@RequestMapping(value = "/admin/sectors", method = RequestMethod.GET)
	public String getSectorList(Model model) {
		model.addAttribute("sector", new Sector());
		List<Sector> listSectors = this.administrationService.findAllSectors();
		model.addAttribute("listSectors", listSectors);
		SfaUiObject forDeleteAction = new SfaUiObject();
		model.addAttribute("forDeleteAction", forDeleteAction);
		getTheUserConnected(model);
		return "admin/sectors/SectorsList";
	}

	@RequestMapping(value = "/admin/sector.add.htm", method = RequestMethod.GET)
	public String getSectorAddPage(Model model) {
		model.addAttribute("sector", new Sector());
		getTheUserConnected(model);
		return "admin/sectors/SectorAdd";
	}

	// For add and update sector both
	@RequestMapping(value = "/admin/sector/add", method = RequestMethod.POST)
	public String addSector(@ModelAttribute("sector") Sector p) {
		Long sectorId;
		if (p.getId() == null) {
			// new person, add it
			sectorId = this.administrationService.saveSector(p);
		} else {
			// existing person, call update
			sectorId = this.administrationService.updateSector(p);
		}

		return "redirect:/admin/sector.view.htm/" + sectorId;

	}

	@RequestMapping(value = "/admin/sector.remove")
	public String deleteSector(Model model, @ModelAttribute("forDeleteAction") SfaUiObject forDeleteAction) {
		this.administrationService.deleteSector(forDeleteAction.getId());
		return "redirect:/admin/sectors";
	}

	@RequestMapping("/admin/sector.edit.htm/{id}")
	public String getEditSectorPage(@PathVariable("id") Long id, Model model) {
		Sector sector = this.administrationService.findSectorById(id);
		model.addAttribute("sector", sector);
		getTheUserConnected(model);
		return "admin/sectors/SectorEdit";
	}

	@RequestMapping("/admin/sector.view.htm/{id}")
	public String viewSector(@PathVariable("id") Long id, Model model) {
		model.addAttribute("sector", this.administrationService.findSectorById(id));
		getTheUserConnected(model);
		return "admin/sectors/SectorDetails";
	}

	// ------------product families------------
	@RequestMapping(value = "/admin/families", method = RequestMethod.GET)
	public String getFamilyList(Model model) {
		model.addAttribute("family", new ProductFamily());
		List<ProductFamily> listFamilies = this.administrationService.findAllProductFamilies();
		model.addAttribute("listFamilies", listFamilies);
		SfaUiObject forDeleteAction = new SfaUiObject();
		model.addAttribute("forDeleteAction", forDeleteAction);
		getTheUserConnected(model);
		return "admin/family/FamiliesList";
	}

	@RequestMapping(value = "/admin/family.add.htm", method = RequestMethod.GET)
	public String getFamilyAddPage(Model model) {
		model.addAttribute("family", new ProductFamily());
		getTheUserConnected(model);
		return "admin/family/FamilyAdd";
	}

	// For add and update person both
	@RequestMapping(value = "/admin/family/add", method = RequestMethod.POST)
	public String addFamily(@ModelAttribute("family") ProductFamily p) {
		Long familyId;
		if (p.getId() == null) {
			// new person, add it
			familyId = this.administrationService.saveProductFamily(p);
		} else {
			// existing person, call update
			familyId = this.administrationService.updateProductFamily(p);
		}

		return "redirect:/admin/family.view.htm/" + familyId;

	}

	@RequestMapping(value = "/admin/family.remove")
	public String deleteFamily(Model model, @ModelAttribute("forDeleteAction") SfaUiObject forDeleteAction) {
		this.administrationService.deleteProductFamily(forDeleteAction.getId());
		return "redirect:/admin/families";

	}

	@RequestMapping("/admin/family.edit.htm/{id}")
	public String getEditFamilyPage(@PathVariable("id") Long id, Model model) {
		ProductFamily family = this.administrationService.findProductFamilyById(id);
		model.addAttribute("family", family);
		getTheUserConnected(model);
		return "admin/family/FamilyEdit";
	}

	@RequestMapping("/admin/family.view.htm/{id}")
	public String viewFamily(@PathVariable("id") Long id, Model model) {
		model.addAttribute("family", this.administrationService.findProductFamilyById(id));
		getTheUserConnected(model);
		return "admin/family/FamilyDetails";
	}

	private void getTheUserConnected(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userConnected = administrationService.findUserByUserName(auth.getName());
		model.addAttribute("currentUser", userConnected);
		model.addAttribute("userName", userConnected.getFirstName() + " " + userConnected.getLastName() + " ("
				+ userConnected.getUserName() + ")");
	}

}