package com.lombok.lombok.controller;

import com.lombok.lombok.dto.UserDto;
import com.lombok.lombok.service.CustomerService;
import com.lombok.lombok.service.UserService;
import com.lombok.lombok.utils.Constants;
import com.lombok.lombok.utils.Utils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private CustomerService customerService;

    @Autowired
    public AdminController(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    //instead of using StringTrimmerEditor, use @NotBlank annotion
    //string trimmer editor removes starting and trailing white spaces
    //this function pre-processes all controller request
    //so this implementation pre-processes all Strings
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        //null in constructor -> if a string of all white spaces -> return null
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/dashboard")
    public String showAdminDashboard(Authentication authentication, Model model){
        String username = authentication.getName();
        UserDto user =userService.findUserByUsername(username);
        model.addAttribute("user", user);
        return "admin/admin-dashboard";
    }

//    @GetMapping("/customers")
//    public String getAllCustomers(Model model){
//        model.addAttribute("user", );
//        return "admin-dashboard";
//    }

    @GetMapping("/showFormToAddCustomer")
    public String showFormToAddCustomer(Model model){

        UserDto user = new UserDto();
        model.addAttribute("user", user);
        model.addAttribute("role", Constants.CUSTOMER_ROLE);
        model.addAttribute("isAdmin", false);

        return "user/AddUserForm";
    }

    @PostMapping("/processFormToAddCustomer")
    public String processFormToAddCustomer(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "user/AddUserForm";
        }else{
            customerService.addCustomer(userDto);
            model.addAttribute("confirmationMsg", "Customer added successfully.");
            model.addAttribute("goBackLink", "/admin/dashboard");
            return "ConfirmationPage";
        }
    }


    @GetMapping("/showFormToAddAdmin")
    public String showFormToAddAdmin(Model model){

        UserDto user = new UserDto();
        model.addAttribute("user", user);
        model.addAttribute("role", Constants.ADMIN_ROLE);
        model.addAttribute("isAdmin", true);
        return "user/AddUserForm";
    }

    @PostMapping("/processFormToAddAdmin")
    public String processFormToAddAdmin(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "user/AddUserForm";
        }else{
            userService.addAdmin(user);
            model.addAttribute("confirmationMsg", "Admin added successfully.");
            model.addAttribute("goBackLink", "/admin/dashboard");
            return "ConfirmationPage";
        }
    }


    @GetMapping("/showFormToDeleteUser")
    public String showFormToDeleteUser(Model model){
        return "user/DeleteUser";
    }

    @PostMapping("/processFormToDeleteUser")
    public String processFormToDeleteUser(@RequestParam("username") String username, @RequestParam("cnic") String cnic, Model model,
                                          Authentication authentication){
        if(!(Utils.isNullOrEmptyString(username) || Utils.isNullOrEmptyString(cnic))){
            if(username.equals(authentication.getName())){
                return "redirect:/admin/showFormToDeleteUser?loggedInUserCannotDeleted";
            }
            //cannot delete the login user.
            UserDto user = userService.deleteUserByUsernameAndCnic(username, cnic);
            if(Objects.nonNull(user)){
                model.addAttribute("confirmationMsg", "User deleted successfully.");
                model.addAttribute("goBackLink", "/admin/dashboard");
                return "ConfirmationPage";
            }else{
                return "redirect:/admin/showFormToDeleteUser?notfound";
            }
        }
        return "redirect:/admin/showFormToDeleteUser?incompleteData";
    }
}
