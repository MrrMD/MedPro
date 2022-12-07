package ru.smallstash.med.Services;

import ru.smallstash.med.UserDAO.OrdersDAO;
import ru.smallstash.med.entites.Employee;
import ru.smallstash.med.entites.Patient;

public class OrdersService {
    OrdersDAO ordersDAO = new OrdersDAO();

    public void newOrder(Patient patient, Employee doctor, String day, String time){
        ordersDAO.newOrder(patient, doctor, day, time);
    }

}
