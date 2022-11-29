package ru.smallstash.med.UserDAO;

import java.util.ArrayList;
import java.util.List;
import ru.smallstash.med.entites.Patient;

public class UserDAO {

    private List<Patient> patientList = new ArrayList<>();

    public void createNewUser(String name, String surname, String thirdName,
                              String phoneNumber, String email, String password){
        patientList.add(new Patient(name,surname,thirdName,phoneNumber,email,password));
        System.out.println("User was created");
        System.out.println(patientList.get(0));
    }

    public Patient getPatientByEmail(String email){
        for (Patient patient:patientList) {
            if(patient.getEmail().equals(email)){
                return patient;
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
        return false;
    }
}
