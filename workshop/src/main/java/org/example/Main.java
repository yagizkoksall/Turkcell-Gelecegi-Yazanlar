package org.example;
public class Main {

    public static void main(String[] args) {

        EmployeeManager manager = new EmployeeManager(new EmailNotification(),
                new Notification[]{new EmailNotification(),new SmsNotification()});

        Employee[] employees = {new Engineer(1,"Yağız","Köksal","Software")
        ,new Executive(2,"Aleyna","Ertok","Software")
        ,new Manager(3,"Serhat","Pala",5000)};

        Engineer engineer = new Engineer(4, "Murat", "Doğan", "Software");
        manager.addCustomerMultiplyNotification(engineer);
        System.out.println("*******************************");
        manager.addCustomerSingleNotification(engineer);
        System.out.println("*******************************");
        manager.addMultiplyCustomerMultiplyNotification(employees);
        System.out.println("*******************************");
        manager.addMultiplyCustomerSingleNotification(employees);
    }
}
