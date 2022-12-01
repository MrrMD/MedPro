package ru.smallstash.med.UserDAO;

import java.util.ArrayList;
import java.util.List;

import ru.smallstash.med.entites.Admin;
import ru.smallstash.med.entites.Employee;
import ru.smallstash.med.entites.Patient;
import ru.smallstash.med.entites.User;

public class UserDAO {

    private List<Patient> patientList = new ArrayList<>();
    private List<Admin> adminList = new ArrayList<>();
    private List<Employee> epmloyeeList = new ArrayList<>();

    public void createNewUser(String name, String surname, String thirdName,
                              String phoneNumber, String email, String password){
        patientList.add(new Patient(name,surname,thirdName,phoneNumber,email,password));
        System.out.println("User was created");
        System.out.println(patientList.get(0));
    }

    public void createNewUser(String email, String password, String hospital){
        this.adminList.add(new Admin(email, password, hospital));
        System.out.println("Admin was created");
    }

    public void createNewUser(String name, String surname, String thirdName,
                              String phoneNumber, String email, String password, String post, List<String> days, List<String> receptionHours, String hospital){
        epmloyeeList.add(new Employee(name,surname,thirdName,phoneNumber,email,password, post, days, receptionHours, hospital));
        System.out.println("User was created");
        System.out.println(patientList.get(0));
    }

    public User getUserByEmail(String email){
        for (Patient patient:patientList) {
            if(patient.getEmail().equals(email)){
                return patient;
            }
        }
        for (Admin admin:adminList) {
            if(admin.getEmail().equals(email)){
                return admin;
            }
        }
        return null;
    }

    public boolean userSignInValidation(String email, String password){
        for (Patient patient:patientList) {
            if(patient.getEmail().equals(email) && patient.getPassword().equals(password)){
                System.out.println("ok");
                return true;
            }
        }
        for (Admin admin:adminList) {
            if(admin.getEmail().equals(email) && admin.getPassword().equals(password)){
                System.out.println("ok");
                return true;
            }
        }
        return false;
    }
}
