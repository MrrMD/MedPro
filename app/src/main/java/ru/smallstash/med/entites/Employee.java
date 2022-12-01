package ru.smallstash.med.entites;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User{

    private String post;
    private List<String> days;
    private List<String> receptionHours;
    private String hospital;


    public Employee(String name, String surname, String thirdName, String phoneNumber, String email,
                    String password, String post, List<String> days, List<String> receptionHours, String hospital) {
        super(name, surname, thirdName, phoneNumber, email, password);
        this.post = post;
        this.days = days;
        this.receptionHours = receptionHours;
        this.hospital = hospital;
    }
}
