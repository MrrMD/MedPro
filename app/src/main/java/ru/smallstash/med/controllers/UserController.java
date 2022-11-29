package ru.smallstash.med.controllers;

import ru.smallstash.med.Services.UserService;

public class UserController {
    UserService userService = new UserService();

    public void createNewUser(String name, String surname, String thirdName, String phoneNumber, String email, String password){
        userService.createNewUser(name, surname, thirdName, phoneNumber, email,password);
    }

    public boolean userSignInValidation(String login, String password){
        return userService.userSignInValidation(login,password);
    }

}
