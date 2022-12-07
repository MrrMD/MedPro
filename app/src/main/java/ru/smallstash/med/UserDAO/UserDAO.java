package ru.smallstash.med.UserDAO;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import ru.smallstash.med.entites.Admin;
import ru.smallstash.med.entites.Employee;
import ru.smallstash.med.entites.Patient;
import ru.smallstash.med.entites.User;

public class UserDAO {

    private List<Patient> patientList = new ArrayList<>();
    private List<Admin> adminList = new ArrayList<>();
    private List<Employee> epmloyeeList = new ArrayList<Employee>();

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

    @SuppressLint("NewApi")
    public void createNewUser(String name, String surname, String thirdName,
                              String phoneNumber, String email, String password, String post, List<String> days, List<String> receptionHours, String hospital){
        epmloyeeList.add(new Employee(name,surname,thirdName,phoneNumber,email,password, post, days, receptionHours, hospital));
        return;
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
        for (Employee employee:epmloyeeList) {
            if(employee.getEmail().equals(email)){
                return employee;
            }
        }
        return null;
    }

    @SuppressLint("NewApi")
    public List<String> getDoctorsDaysByFullName(String fullname){
        for (Employee employee:epmloyeeList) {
            if (employee.getFullName().equals(fullname)) {
                System.out.println("dao");
                employee.getDays().forEach(System.out::println);
                return employee.getDays();
            }
        }
        return null;
    }
    public List<String> getDoctorsTimesFullName(String fullname){
        for (Employee employee:epmloyeeList) {
            if (employee.getFullName().equals(fullname)) {
                return employee.getReceptionHours();
            }
        }
        return null;
    }

    public List<Employee> getEmployeeByPost(String post){
        List<Employee> list = new ArrayList<>();
        for (Employee employee:epmloyeeList) {
            if(employee.getPost().equals(post)){
                list.add(employee);
            }
        }
        return list.isEmpty() ? null : list;
    }

    public List<Employee> getEmployeeByHospital(String hosital){
        List<Employee> list = new ArrayList<>();
        for (Employee employee:epmloyeeList) {
            if(employee.getHospital().equals(hosital)){
                list.add(employee);
            }
        }
        return list.isEmpty() ? null : list;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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
        for (Employee employee:epmloyeeList) {
            if(employee.getEmail().equals(email) && employee.getPassword().equals(password)){
                System.out.println("ok");
                return true;
            }
        }
        return false;
    }

}
