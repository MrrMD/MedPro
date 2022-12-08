package ru.smallstash.med.entites;

public class Admin extends User{

    private String privateHospital;

    public Admin(String email, String password, String hospital){
        super(email, password);
        this.privateHospital = hospital;
    }

    public String getPrivateHospital() {
        return privateHospital;
    }
}
