package ru.smallstash.med.controllers;

import java.util.List;

import ru.smallstash.med.Services.UserService;

public class UserController {
    UserService userService = new UserService();

    public void createNewUser(String name, String surname, String thirdName, String phoneNumber, String email, String password){
        userService.createNewUser(name, surname, thirdName, phoneNumber, email,password);
    }

    public void createNewUser(String email, String password, String hospital){
        userService.createNewUser(email,password, hospital);
    }
    public void createNewUser(String name, String surname, String thirdName, String phoneNumber, String email,
                              String password, String post, List<String> days, List<String> receptionHours, String hospital){
        userService.createNewUser(name, surname, thirdName, phoneNumber, email,password, post, days, receptionHours, hospital);
    }

    public boolean userSignInValidation(String login, String password){
        return userService.userSignInValidation(login,password);
    }

    public boolean isAdmin(String email){
       return userService.isAdmin(email);
    }

}
