package org.example;

public class EmployeeManager {

    private Notification notification;
    private Notification[] notifications;
    public EmployeeManager(Notification notification,Notification[] notifications) {
        this.notification = notification;
        this.notifications = notifications;
    }

    public void addCustomerSingleNotification(Employee employee){
        System.out.println(employee.getFirstName() + " çalışanı eklendi.");

        notification.sendNotify();

    }

    public void addCustomerMultiplyNotification(Employee employee){
        System.out.println(employee.getFirstName() + " çalışanı eklendi.");

        for(Notification notification : notifications){
            notification.sendNotify();
        }
    }

    public void addMultiplyCustomerSingleNotification(Employee[] employees){
        for(Employee employee : employees){
            addCustomerSingleNotification(employee);
        }

    }

    public void addMultiplyCustomerMultiplyNotification(Employee[] employees){
        for(Employee employee: employees){
            addCustomerMultiplyNotification(employee);
        }
    }
}
