package com.enterpriseapplications.authenticationspring.controllers.view;

import com.enterpriseapplications.authenticationspring.dto.RegisterUser;
import com.enterpriseapplications.authenticationspring.service.interfaces.LocalUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String registration(){
        return "signin";
    }

    @PostMapping("/signInUser")
    public String redirectPostToPost(HttpServletRequest httpRequest, @Valid RegisterUser registerUser, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "redirect:/signIn?invalid";

        try{
            this.userService.insertUser(registerUser);

        }
        catch (Exception exception)
        {
            return "redirect:/signIn?error";
        }


        return "redirect:/login?signIn";
    }

}
