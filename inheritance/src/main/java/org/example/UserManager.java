package org.example;

public class UserManager {
    // CRUD operations
    public void add(User user) {
        System.out.println(user.getFirstName() + " sisteme eklendi");
    }

    public void addMultiple(User[] users){
        for(User user:users){
            add(user);
        }
    }
}
