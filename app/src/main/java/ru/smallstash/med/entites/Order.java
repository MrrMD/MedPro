package ru.smallstash.med.entites;

public class Order {

    private Patient patient;
    private Employee doctor;
    private String day;
    private String time;
    private Boolean isComplite = false;

    public Order(Patient patient, Employee doctor, String day, String time) {
        this.patient = patient;
        this.doctor = doctor;
        this.day = day;
        this.time = time;
        isComplite = false;
    }

    public Patient getPatient() {
        return patient;
    }

    public Employee getDoctor() {
        return doctor;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public Boolean getComplite() {
        return isComplite;
    }

    @Override
    public String toString() {
        return "Order{" +
                "patient=" + patient +
                ", doctor=" + doctor +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", isComplite=" + isComplite +
                '}';
    }
}
