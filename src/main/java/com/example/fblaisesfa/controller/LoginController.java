package com.example.fblaisesfa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.fblaisesfa.model.Role;
import com.example.fblaisesfa.model.User;
import com.example.fblaisesfa.service.AdministrationService;

@Controller
public class LoginController {

	@Autowired
	private AdministrationService administrationService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		Role role = administrationService.findRoleByName("ADMIN");
		if (role == null) {
			role = new Role("ADMIN");
			administrationService.saveRole(role);
		}
		User user1 = administrationService.findUserByUserName("admin");
		if (user1 == null) {
			user1 = new User("blaise_feukjouo@yahoo.fr", "admin", "Feukouo", "Blaise", "admin", true);
			administrationService.saveUser(user1);
		}

		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String createNewUser(@ModelAttribute("user") User user, Model model) {
		User userExists = administrationService.findUserByUserName(user.getUserName());
		String viewToReturn;
		Long userId;
		if (userExists != null) {
			model.addAttribute("userNameError", "There is already a user registered with the user name provided");
			viewToReturn = "registration";
		} else {
			userId = administrationService.saveUser(user);
			model.addAttribute("successMessage", "User has been registered successfully");
			model.addAttribute("user", new User());
			viewToReturn = "redirect:/registration/" + userId;
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userConnected = administrationService.findUserByUserName(auth.getName());
		model.addAttribute("userName", userConnected.getFirstName() + " " + userConnected.getLastName() + " ("
				+ userConnected.getUserName() + ")");
		return viewToReturn;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userConnected = administrationService.findUserByUserName(auth.getName());
		modelAndView.addObject("currentUser", userConnected);
		modelAndView.addObject("userName", userConnected.getFirstName() + " " + userConnected.getLastName() + " ("
				+ userConnected.getUserName() + ")");
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@GetMapping(value = { "/access-denied" })
	public String accessDenied() {
		return "error/AccessDenied";
	}

}
