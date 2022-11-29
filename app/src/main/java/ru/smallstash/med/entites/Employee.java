package ru.smallstash.med.entites;

public class Employee extends User{

    private Post post;

    public Employee(String name, String surname, String thirdName, String phoneNumber, String email, String password, Post post) {
        super(name, surname, thirdName, phoneNumber, email, password);
        this.post = post;
    }
}
