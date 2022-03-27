package com.example.JBcrypt.controller;


import com.example.JBcrypt.model.SiteUser;
import com.example.JBcrypt.repository.SiteUserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
    @Autowired
    SiteUserRepository siteUserRepository;

    @GetMapping("/")
    public String getLoginPage(){
        return "index.html";
    }
    @PostMapping("/login")
    public RedirectView login(String username, String password){
        SiteUser loggingInUser = siteUserRepository.findByUsername(username);
        if (loggingInUser == null){
            return new RedirectView("/");
        }

        boolean isPasswordCorrect = BCrypt.checkpw(password, loggingInUser.getPassword());

        if (!isPasswordCorrect)
        {
            return new RedirectView("/");
        }
        else
        {
            return new RedirectView("/home/" + username);
        }

    }

    @GetMapping("/signup")
    public String getSignupPage(){
        return "signup.html";
    }
    @PostMapping("/signup")
    public RedirectView signup(String username, String password){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        SiteUser newUser = new SiteUser(username, hashedPassword);
        siteUserRepository.save(newUser);

        return new RedirectView("/");
    }


}
