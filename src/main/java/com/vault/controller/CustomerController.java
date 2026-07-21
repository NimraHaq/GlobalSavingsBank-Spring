package com.vault.controller;


import com.vault.dto.CardDto;
import com.vault.dto.UserDto;
import com.vault.service.CardService;
import com.vault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    UserService userService;
    CardService cardService;

    @Autowired
    public CustomerController(UserService userService, CardService cardService){
        this.userService = userService;
        this.cardService = cardService;
    }

    @GetMapping("/dashboard")
    public String showCustomerDashboard(Authentication authentication, Model model){
        String username = authentication.getName();
        UserDto user =userService.findUserByUsername(username);
        model.addAttribute("user", user);
        return "customer/customer-dashboard";
    }

    @GetMapping("/showCustomerCards")
    public String showCustomerCards(@RequestParam("id") int id, Model model){
        System.out.println("CH id is : " + id);
        List<CardDto> cards = cardService.getCardsByChId(id);
        model.addAttribute("cardsList", cards);
        return "ShowCards";
    }
}
