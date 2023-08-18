package com.enterpriseapplications.authenticationspring.controllers.view;

import com.enterpriseapplications.authenticationspring.dto.RegisterUser;
import com.enterpriseapplications.authenticationspring.service.interfaces.LocalUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

@Controller
@AllArgsConstructor
public class LoginController  {

    private final LocalUserService userService;



    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signIn")
    public String registration(Model model){
        model.addAttribute("registration", new RegisterUser());
        return "signin";
    }

    @RequestMapping(value = "/signInUser", method = RequestMethod.POST)
    public String redirectPostToPost(@ModelAttribute("registration") @Valid RegisterUser registration, BindingResult bindingResult,Model model) {


        if(bindingResult.hasErrors())
            return "signin";

        try{
            this.userService.insertUser(registration);
        }
        catch (Exception exception)
        {
            model.addAttribute("userAlreadyRegistered",true);
            return "signin";
        }

        return "redirect:/login?signIn";
    }

}
