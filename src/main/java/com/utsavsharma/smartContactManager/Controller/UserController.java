package com.utsavsharma.smartContactManager.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.utsavsharma.smartContactManager.Entity.Contact;
import com.utsavsharma.smartContactManager.Entity.User;
import com.utsavsharma.smartContactManager.form.ContactForm;
import com.utsavsharma.smartContactManager.services.ContactService;
import com.utsavsharma.smartContactManager.services.imageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/user", method = { RequestMethod.GET, RequestMethod.POST })
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // private final UserService UserService;
    private final ContactService contactService;
    private final imageService imageService;

    public UserController(ContactService contactService, imageService imageService) {

        // this.UserService = UserService;
        this.contactService = contactService;
        this.imageService = imageService;
    }

    @PostMapping(value = "/addContactForm")
    public String addContactForm(@ModelAttribute("contactForm") ContactForm contactForm,
            Authentication authentication, RedirectAttributes redirectAttributes) {
        // model.addAttribute("ContactForm", contactForm);
        // if (result.hasErrors()) {
        // return "user/addcontact";
        // }
        System.out.println(contactForm);
        // System.out.println(name);
        System.out.println("this is add contact running");

        // String username = LoggedInUserDetails.getEmailOfLoggedInUser(authentication);
        // User user = UserService.getUserByEmail(username);

        // System.out.println(user);

        try {
            Contact contact;
            String imageUrl;

            if (contactForm.getContactImage().isEmpty()) {
                contact = Contact.builder().Name(contactForm.getName())
                        .email(contactForm.getEmail())
                        .address(contactForm.getAddress())
                        .phoneNumber(contactForm.getPhoneNumber())
                        .discription(contactForm.getDescription())

                        .favorite(contactForm.isFavorite())
                        .webSiteStringLink(contactForm.getWebsite())
                        .linkedInLink(contactForm.getLinkedInLink())
                        .build();
                contactService.saveContact(contact);
                redirectAttributes.addFlashAttribute("message", "Contact added successfully");
                return "redirect:/user/addcontact";

            } else {
                imageUrl = imageService.UploadImage(contactForm.getContactImage());

                contact = Contact.builder().Name(contactForm.getName())
                        .email(contactForm.getEmail())
                        .address(contactForm.getAddress())
                        .phoneNumber(contactForm.getPhoneNumber())
                        .discription(contactForm.getDescription())
                        .favorite(contactForm.isFavorite())
                        .picture(imageUrl)
                        .webSiteStringLink(contactForm.getWebsite())
                        .linkedInLink(contactForm.getLinkedInLink())
                        .build();

                contactService.saveContact(contact);
                redirectAttributes.addFlashAttribute("message", "Contact added successfully");
                return "redirect:/user/addcontact";

            }

        } catch (Exception e) {
            logger.error("Exception : ", e);
            redirectAttributes.addFlashAttribute("message1", "Something went wrong");
            return "redirect:/user/addcontact";

        }
        // return "redirect:/user/addcontact";
    }

    @GetMapping("/add")
    public String showAddContactForm(Model model) {
        // ContactForm contactForm = new ContactForm();

        // model.addAttribute("contactForm", contactForm);
        return "user/addcontact";
    }

    @RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String profileUpdate(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response) {
        logger.info("this is update Controller running");
        String CurrentUrl = request.getRequestURL().toString();
        System.out.println(CurrentUrl);
        System.out.println(user);

        return "forward:/";
    }

}
