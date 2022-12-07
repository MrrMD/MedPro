package ru.smallstash.med.controllers;

import android.annotation.SuppressLint;

import java.util.List;

import ru.smallstash.med.Services.UserService;
import ru.smallstash.med.entites.Employee;

public class UserController {
    private UserService userService = new UserService();

    public void createNewUser(String name, String surname, String thirdName, String phoneNumber, String email, String password){
        userService.createNewUser(name, surname, thirdName, phoneNumber, email,password);
    }

    public void createNewUser(String email, String password, String hospital){
        userService.createNewUser(email,password, hospital);
    }
    @SuppressLint("NewApi")
    public void createNewUser(String name, String surname, String thirdName, String phoneNumber, String email,
                              String password, String post, List<String> days, List<String> receptionHours, String hospital){
        System.out.println(name);
        days.forEach(System.out::println);
        userService.createNewUser(name, surname, thirdName, phoneNumber, email,password, post, days, receptionHours, hospital);
    }

    public boolean userSignInValidation(String login, String password){
        return userService.userSignInValidation(login,password);
    }

    public boolean userEmailExistCheck(String email){
        return userService.isEmailExist(email);
    }

    public List<Employee> getEmployeeByPost(String post){
       return userService.getEmployeeByPost(post);
    }

    public List<String> getEmployeeByHospitalAndPost(String hospital, String post){
        return userService.getEmployeeByHospitalAndPost(hospital, post);
    }

    public List<String> getDoctorsDaysByFullName(String fullname){
        return userService.getDoctorsDaysByFullName(fullname);
    }

    public List<String> getDoctorsTimesByFullname(String fullname){
        return userService.getDoctorsTimesFullName(fullname);
    }

    public boolean isAdmin(String email){
       return userService.isAdmin(email);
    }

    public boolean isEmployee(String email){
        return userService.isEmployee(email);
    }


}
