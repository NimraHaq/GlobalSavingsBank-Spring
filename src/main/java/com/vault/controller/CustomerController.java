package com.vault.controller;


import com.vault.dto.CardDto;
import com.vault.dto.TransactionDto;
import com.vault.dto.UserDto;
import com.vault.service.CardService;
import com.vault.service.CustomerService;
import com.vault.service.UserService;
import com.vault.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.scanner.Constant;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    UserService userService;
    CustomerService customerService;

    @Autowired
    public CustomerController(UserService userService, CustomerService customerService){
        this.userService = userService;
        this.customerService = customerService;
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
        List<CardDto> cards = customerService.getCardByChId(id);
        cards.stream().filter(c -> c.getCategory().equalsIgnoreCase(Constants.CARD_CATEGORY_DEBIT)).map(c -> {
            c.setCategory("DEBIT");
            return c;
            }).toList();
        model.addAttribute("cardsList", cards);
        return "customer/ShowCards";
    }

    @GetMapping("/showCardTransactions")
    public String showCardTransactions(@RequestParam("cardNo") long cardNo, Model model){
        List<TransactionDto> transactions = new ArrayList<>();
        model.addAttribute("transactionsList", transactions);
        return "customer/ShowTransactions";
    }
}
