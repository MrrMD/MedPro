package ru.smallstash.med.UserDAO;

import ru.smallstash.med.entites.Employee;
import ru.smallstash.med.entites.Order;
import ru.smallstash.med.entites.Patient;

public class OrdersDAO {

    private Order order;

    public void newOrder(Patient patient, Employee doctor, String day, String time){
        order =  new Order(patient, doctor, day, time);
        doctor.newOrder(order);
        patient.newOrder(order);
    }
}
