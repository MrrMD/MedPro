package ru.smallstash.med.entites;

public class Admin extends User{

    private String privateHospital;

    public Admin(String name, String surname, String thirdName, String phoneNumber, String email, String password, String hospital) {
        super(name, surname, thirdName, phoneNumber, email, password);
    }

    public Admin(String email, String password, String hospital){
        super(email, password);
        this.privateHospital = hospital;
    }
}
