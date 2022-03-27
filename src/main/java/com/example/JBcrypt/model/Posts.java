package com.example.JBcrypt.model;

import javax.persistence.*;

@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String text;

    @ManyToOne
    SiteUser postsByUser;

    public Posts()
    {
    }

    public Posts(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SiteUser getPostsByUser() {
        return postsByUser;
    }

    public void setPostsByUser(SiteUser postsByUser) {
        this.postsByUser = postsByUser;
    }

}
