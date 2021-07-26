package com.time.to.shop.crm.controller;

import com.time.to.shop.crm.model.db.User;
import com.time.to.shop.crm.model.dto.LoginDTO;
import com.time.to.shop.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute(name = "loginDTO") @Valid LoginDTO loginDTO, Model model) {
        if (userService.isUserPresentInDB(loginDTO)) {
            System.out.println("logged in");
            return "main";
        } else {
            model.addAttribute("invalidCredentials", true);
            return "login";
        }
    }
}
