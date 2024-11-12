package com.utsavsharma.smartContactManager.Controller;

import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.utsavsharma.smartContactManager.Entity.Contact;
import com.utsavsharma.smartContactManager.Entity.Providers;
import com.utsavsharma.smartContactManager.Entity.User;
import com.utsavsharma.smartContactManager.configuration.AppConfig;
import com.utsavsharma.smartContactManager.form.UserForm;
import com.utsavsharma.smartContactManager.helper.LoggedInUserDetails;
import com.utsavsharma.smartContactManager.services.ContactService;
import com.utsavsharma.smartContactManager.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * HomeController.java Created by utsav on 28-Jul-2024 at 3:30:48 pm.
 */
@Controller
public class HomeController {

	private final UserService userService;
	private final AppConfig appConfig;
	private final SecurityContextLogoutHandler logoutHandler;
	private final ContactService contactService;
	private static User user;
	public ArrayList<Contact> contacts;

	public HomeController(UserService userService, AppConfig appConfig, ContactService contactService) {
		this.userService = userService;
		this.appConfig = appConfig;
		this.logoutHandler = new SecurityContextLogoutHandler();
		this.contactService = contactService;
	}

	// get login method to forward request login page
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String getLogin() {

		return "login";

	}

	@GetMapping(path = "/signup")
	public String getSignUp() {
		System.out.println("proccessing sign up form");
		return "signup";

	}

	@PostMapping(path = "/do-signup")
	public String postSignUp(@ModelAttribute UserForm userForm, RedirectAttributes redirectAttributes) {
		System.out.println(userForm);
		// String password = userForm.getPassword();
		// String confirm_password = userForm.getConfirmpassword();
		// if (password != null && password.equals(confirm_password)) {

		System.out.println("processing registration");

		/*
		 * if password matches than we will save the user in the database
		 * 
		 */
		try {

			user = User.builder()
					.Name(userForm.getName())
					.email(userForm.getEmail())
					.phoneNumber(userForm.getPhone())
					.Password(userForm.getPassword())
					.ProfilePic(appConfig.getDefaultProfilePictureUrl())
					.enabled(true)
					.providers(Providers.SELF)
					.about(userForm.getAbout())
					.build();
			System.out.println(user.getUserId());
			userService.saveUser(user);
			redirectAttributes.addFlashAttribute("message", "Registration successful! Please login.");

			return "redirect:/login";

		} catch (Exception e) {
			System.out.println("something went wrong while getting data from UserForm and setting it to user ");
			System.out.println("Exception in post SignUp" + e.getMessage());

			redirectAttributes.addFlashAttribute("message", "We are sorry !! Try Again");

		}

		// }
		// else {

		// redirectAttributes.addFlashAttribute("message", "Passwords does not match!");

		// return "redirect:/signup";
		// }
		return "redirect:/signup";
	}

	@GetMapping({ "/", "/home" })

	public String homeView() {
		return "index";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@RequestMapping(value = "/logout", method = { RequestMethod.POST, RequestMethod.GET })
	public String Logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		System.out.println("running coustom logout");

		this.logoutHandler.logout(request, response, authentication);

		redirectAttributes.addFlashAttribute("message", "Logout successful!");
		return "redirect:/login";
	}

	@RequestMapping("/services")
	public String showServicesPage() {
		System.out.println("showing services page");
		// logger.debug("this is service page");
		return "services"; // Thymeleaf template in /templates/user/services.html
	}

	@RequestMapping(path = "/contact", method = { RequestMethod.GET, RequestMethod.POST })

	public String contact(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
			@RequestParam(value = "direction", defaultValue = "asc") String direction,
			Model model,
			Authentication authentication) {
		System.out.println("this is contact controller");

		if (authentication != null) {
			System.out.println("this is contact controller");

			String userName = LoggedInUserDetails.getEmailOfLoggedInUser(authentication);
			user = userService.getUserByEmail(userName);
			contacts = contactService.findAll();
			model.addAttribute("pageContact", contacts);
			contacts.forEach(System.out::println);
		} else {
			String userName2 = LoggedInUserDetails.getEmailOfLoggedInUser(authentication);

			user = userService.getUserByEmail(userName2);
			contacts = contactService.findAll();
			contacts.forEach(System.out::println);
		}

		return "contact";
	}

}
