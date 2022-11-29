package ru.smallstash.med.Services;

import java.util.List;

import ru.smallstash.med.UserDAO.UserDAO;
import ru.smallstash.med.entites.User;

public class UserService {

    UserDAO userDAO = new UserDAO();

    public void createNewUser(String name, String surname, String thirdName,
                              String phoneNumber, String email, String password){
        userDAO.createNewUser(name,surname,thirdName,phoneNumber,email,password);
    }

    public boolean userSignInValidation(String login, String password){
        return userDAO.userSignInValidation(login, password);
    }
}
