package ru.smallstash.med.entites;

import java.util.ArrayList;
import java.util.List;

public class Patient extends User{

    private List<Order> orders = new ArrayList<>();


    public Patient(String name, String surname, String thirdName, String phoneNumber, String email, String password) {
        super(name, surname, thirdName, phoneNumber, email, password);
    }

    public void newOrder(Order order){
        this.orders.add(order);
        System.out.println("Order Added for patient");
    }

    public List<Order> getOrders() {
        return orders;
    }
}
