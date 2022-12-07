package ru.smallstash.med.entites;

import java.util.ArrayList;
import java.util.List;

public class Employee extends User{

    private String post;
    private List<String> days;
    private List<String> receptionHours;
    private String hospital;
    private List<Order> orders = new ArrayList<>();

    public Employee(String name, String surname, String thirdName, String phoneNumber, String email,
                    String password, String post, List<String> days, List<String> receptionHours, String hospital) {
        super(name, surname, thirdName, phoneNumber, email, password);
        this.post = post;
        this.days = new ArrayList<>(days);
        this.receptionHours = new ArrayList<>(receptionHours);
        this.hospital = hospital;
        System.out.println(this);
    }

    public void newOrder(Order order){
        this.orders.add(order);
        System.out.println("Order added for Emp");
    }

    public String getFullName(){
        return getSurname() + " " + getName() + " " + getThirdName();
    }

    public String getPost() {
        return this.post;
    }

    public List<String> getDays() {
        return this.days;
    }

    public List<String> getReceptionHours() {
        if(this.receptionHours.isEmpty()){
            System.out.println("ПУСТО1");
        }
        return this.receptionHours;
    }

    public String getHospital() {
        return this.hospital;
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
