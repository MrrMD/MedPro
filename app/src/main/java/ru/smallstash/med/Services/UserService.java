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

    public void createNewUser(String email, String password, String hospital){
        userDAO.createNewUser(email,password, hospital);
    }

    public void createNewUser(String name, String surname, String thirdName, String phoneNumber, String email,
                              String password, String post, List<String> days, List<String> receptionHours, String hospital){
        userDAO.createNewUser(name,surname,thirdName,phoneNumber,email,password, post, days, receptionHours, hospital);
    }

    public boolean userSignInValidation(String login, String password){
        return userDAO.userSignInValidation(login, password);
    }

    public boolean isAdmin(String email){
        User user = userDAO.getUserByEmail(email);
        return user.isAdmin();
    }

}
