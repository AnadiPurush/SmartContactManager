package com.utsavsharma.smartContactManager.helper;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class LoggedInUserDetails {
    static String userName;

    public static String getEmailOfLoggedInUser(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken authenticationToken) {
            OAuth2User user = (OAuth2User) authentication.getPrincipal();
            var clientId = authenticationToken.getAuthorizedClientRegistrationId();
            switch (clientId.toLowerCase()) {
                case "google" -> {
                    userName = Optional.ofNullable(user.getAttribute("email")).map(Object::toString).orElse(null);

                }
                case "github" -> {
                    userName = Optional.ofNullable(user.getAttribute("email"))
                            .map(Object::toString)
                            .orElseGet(() -> Optional.ofNullable(user.getAttribute("login"))
                                    .map(Object::toString)
                                    .orElse(null));

                }
                default -> {
                    return authentication.getName();

                }
            }

        }

        return userName;
    }
}