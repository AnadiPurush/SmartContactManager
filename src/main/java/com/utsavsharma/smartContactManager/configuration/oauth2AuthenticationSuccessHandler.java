package com.utsavsharma.smartContactManager.configuration;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.utsavsharma.smartContactManager.Entity.Providers;
import com.utsavsharma.smartContactManager.Entity.User;
import com.utsavsharma.smartContactManager.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @file oauth2AuthenticationSuccessHandler.java
 *       Author: Utsav Sharma
 *       Date: 27-08-2024
 *       Time: 16:28:33
 */

@Component

public class oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserRepo userRepo;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public oauth2AuthenticationSuccessHandler(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

        // // user.getAttributes().forEach((key, value) -> {
        // // logger.info(key + " : " + value);

        // });
        if (authentication instanceof OAuth2AuthenticationToken oauth2User) {

            String authorizedClientRegistrationId = oauth2User.getAuthorizedClientRegistrationId();
            if (authorizedClientRegistrationId != null) {
                logger.info(authorizedClientRegistrationId);
                User user = createUser();

                switch (authorizedClientRegistrationId.toLowerCase()) {
                    case "google" -> {

                        Object emailObject = oAuth2User.getAttribute("email");
                        String email = emailObject instanceof String emailstr && !emailstr.isBlank() ? emailstr : null;
                        user.setEmail(email);
                        /*  
                         * 
                         */

                        user.setProfilePic(
                                Optional.ofNullable(oAuth2User.getAttribute("picture"))
                                        .map(Object::toString)
                                        .orElse(null));

                        user.setName(Optional.ofNullable(oAuth2User.getAttribute("name")).map(Object::toString)
                                .orElse("null"));
                        user.setPhoneNumber(Optional.ofNullable(oAuth2User.getAttribute("phone")).map(Object::toString)
                                .orElse("null"));
                        user.setProviderUserID(Optional.ofNullable(oAuth2User.getAttribute("sub")).map(Object::toString)
                                .orElse("null"));

                        user.setProviders(Providers.GOOGLE);
                        user.setAbout("This Account is created by Google");

                        if (userRepo.findByEmail(user.getEmail()).isEmpty()) {
                            userRepo.save(user);
                            response.sendRedirect("/home");

                        }
                        response.sendRedirect("/home");

                    }
                    case "github" -> {
                        user.setEmail(Optional.ofNullable(oAuth2User.getAttribute("email")).map(Object::toString)
                                .orElseGet(() -> Optional.ofNullable(oAuth2User.getAttribute("login"))
                                        .map(Object::toString).orElse(null)));

                        user.setProfilePic(Optional.ofNullable(oAuth2User.getAttribute("avatar_url"))
                                .map(Object::toString).orElse(null));

                        user.setName(Optional.ofNullable(oAuth2User.getAttribute("login")).map(Object::toString)
                                .orElse(null));

                        user.setProviderUserID(Optional.ofNullable(oAuth2User.getAttribute("id")).map(Object::toString)
                                .orElseGet(() -> Optional.ofNullable(oAuth2User.getAttribute("sub"))
                                        .map(Object::toString).orElse(null)));

                        user.setProviders(Providers.GITHUB);

                        user.setAbout("This Account is created by GitHub");
                        if (userRepo.findByEmail(user.getEmail()).isEmpty()) {
                            userRepo.save(user);
                            response.sendRedirect("/user/profile");

                        }
                        response.sendRedirect("/user/profile");

                    }
                    default -> {
                        response.sendRedirect("/login");

                        logger.info("Not an authorized client");
                    }

                }

            } else {

                response.sendRedirect("/login");
                logger.warn(" OAuth2AuthenticationToken is null");
            }

        } else {
            response.sendRedirect("/login");

            logger.warn("Not an OAuth2AuthenticationToken");
        }

    }

    private User createUser() {
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setEnabled(true);
        user.setPhoneVerified(true);
        user.isCredentialsNonExpired();
        return user;
    }
}
