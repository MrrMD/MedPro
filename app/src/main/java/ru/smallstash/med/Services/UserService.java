package ru.smallstash.med.Services;

import java.util.ArrayList;
import java.util.List;

import ru.smallstash.med.UserDAO.UserDAO;
import ru.smallstash.med.entites.Employee;
import ru.smallstash.med.entites.User;

public class UserService {

    private UserDAO userDAO = new UserDAO();
    private User user;

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

    public List<Employee> getEmployeeByPost(String post){
        return userDAO.getEmployeeByPost(post);
    }

    public List<Employee> getEmployeeByHospital(String hosital){
        return userDAO.getEmployeeByHospital(hosital);
    }

    public List<String> getEmployeeByHospitalAndPost(String hospital, String post){
        List<Employee> employeesByHospital = getEmployeeByHospital(hospital);
        List<String> list = new ArrayList<>();
        if(employeesByHospital == null){
            return null;
        };
        for (Employee employee:employeesByHospital) {
            if(employee.getPost().equals(post)){
                list.add(employee.getFullName());
            }
        }
        return list;
    }

    public List<String> getDoctorsDaysByFullName(String fullname){
        return userDAO.getDoctorsDaysByFullName(fullname);
    }

    public List<String> getDoctorsTimesFullName(String fullname) {
        return userDAO.getDoctorsTimesFullName(fullname);
    }

    public boolean isAdmin(String email){
        user = userDAO.getUserByEmail(email);
        return user.isAdmin();
    }

    public boolean isEmployee(String email){
        user = userDAO.getUserByEmail(email);
        return user.isEmployee();
    }

    public boolean isEmailExist(String email){
        user = userDAO.getUserByEmail(email);
        if(user == null) {
            return false;
        }
        return true;
    }

}
