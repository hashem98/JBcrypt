package com.example.JBcrypt.controller;


import com.example.JBcrypt.model.Posts;
import com.example.JBcrypt.model.SiteUser;
import com.example.JBcrypt.repository.PostsRepository;
import com.example.JBcrypt.repository.SiteUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


import java.util.List;


@Controller
public class HomeController {
    @Autowired
    SiteUserRepository siteUserRepository;
    @Autowired
    PostsRepository postsRepository;



    @GetMapping("/home/{username}")
    public String getHomepage(@PathVariable String username, Model m) {
        SiteUser siteUserPage = siteUserRepository.findByUsername(username);
        m.addAttribute("username", username.toUpperCase());
        m.addAttribute("siteUser", siteUserPage);
        m.addAttribute("posts", siteUserPage.getPostsOfThisUser());
        List<Posts> posts = postsRepository.findAll();
        m.addAttribute("posts", posts);
        return "home";
    }

    @PostMapping("/add-post")
    public RedirectView addPost(long siteUserId, String text) {
        SiteUser postsByUser = siteUserRepository.getById(siteUserId);
        Posts postsToAdd = new Posts(text);
        postsToAdd.setPostsByUser(postsByUser);
        postsRepository.save(postsToAdd);
        String username = postsByUser.getUsername();

        return new RedirectView("/home/" + username);

    }
}