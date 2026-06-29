package com.lombok.lombok.controller;

import com.lombok.lombok.dao.UserDao;
import com.lombok.lombok.dto.CustomerDto;
import com.lombok.lombok.entity.Customer;
import com.lombok.lombok.entity.User;
import com.lombok.lombok.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserDao userDao;
    private CustomerService customerService;

    @Autowired
    public AdminController(UserDao userDao, CustomerService customerService) {
        this.userDao = userDao;
        this.customerService = customerService;
    }

    //string trimmer editor removes starting and trailing white spaces
    //this function pre-processes all controller request
    //so this implementation pre-processes all Strings
    @InitBinder
    public void removeWhiteSpacesFromString(WebDataBinder webDataBinder){
        //null in constructor -> if a string of all white spaces -> return null
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/dashboard")
    public String getAdminProfile(Authentication authentication, Model model){
        String username = authentication.getName();
        User user =userDao.findUserByUsername(username);
        model.addAttribute("user", user);
        return "admin/admin-dashboard";
    }

//    @GetMapping("/customers")
//    public String getAllCustomers(Model model){
//        model.addAttribute("user", );
//        return "admin-dashboard";
//    }

    @PostMapping("/processFormToAddCustomer")
    public String processFormToAddCustomer(@Valid @ModelAttribute("customer")CustomerDto customerDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "customer/AddCustomerForm";
        }else{
            customerService.addCustomer(customerDto);
            model.addAttribute("role", "Customer");
            model.addAttribute("action", " added");
            model.addAttribute("goBackLink", "/admin/dashboard");
            return "ConfirmationPage";
        }
    }
    @GetMapping("/showFormToAddCustomer")
    public String showFormToAddCustomer(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer/AddCustomerForm";
    }
}
