package com.utsavsharma.smartContactManager.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.utsavsharma.smartContactManager.Entity.User;
import com.utsavsharma.smartContactManager.helper.LoggedInUserDetails;
import com.utsavsharma.smartContactManager.services.UserService;

import jakarta.servlet.http.HttpSession;

@SessionAttributes("loggedInUser")
@ControllerAdvice
public class RootController {
    // private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;
    private String username;

    public RootController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void addLoggedInUserDetails(Model model, Authentication authentication, HttpSession session) {
        if (authentication == null) {
            return;

        }
        System.out.println("this is root controller");
        String user_name = LoggedInUserDetails.getEmailOfLoggedInUser(authentication);
        // if (session.getAttribute("loggedInUser1") != null) {
        // System.out.println("Session contains logged in user");

        // return;
        // }

        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
            User user = userService.getUserByEmail(username);
            model.addAttribute("loggedInUser", user);
            // session.setAttribute("loggedInUser1", user);

        } else {
            User user = userService.getUserByEmail(user_name);
            // logger.info(user.getUsername());
            // logger.info(user.getName());
            // logger.info(user.getProfilePic());
            model.addAttribute("loggedInUser", user);
        }
    }

}
