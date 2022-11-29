package ru.smallstash.med.entites;

public class User {

    private String name;
    private String surname;
    private String thirdName;
    private String phoneNumber;
    private String email;
    private String password;


    public User(String name, String surname, String thirdName, String phoneNumber, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.thirdName = thirdName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getThirdName() {
        return thirdName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", thirdName='" + thirdName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
