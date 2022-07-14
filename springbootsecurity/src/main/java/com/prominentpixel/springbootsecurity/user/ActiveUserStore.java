package com.prominentpixel.springbootsecurity.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActiveUserStore {

    private List<String> users;

    public ActiveUserStore() {
        this.users = new ArrayList<>();
    }

    public List<String> getUsers() {
        return this.users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
