package ru.smallstash.med.controllers;

import ru.smallstash.med.Services.OrdersService;
import ru.smallstash.med.entites.Employee;
import ru.smallstash.med.entites.Patient;

public class OrdersController {
    private OrdersService ordersService = new OrdersService();

    public void newOrder(Patient patient, Employee doctor, String day, String time){
        ordersService.newOrder(patient, doctor, day, time);
    }

}
