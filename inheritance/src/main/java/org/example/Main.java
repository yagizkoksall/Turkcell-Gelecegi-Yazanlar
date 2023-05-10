package org.example;

public class Main {
    public static void main(String[] args) {
        UserManager manager = new UserManager();
        Instructor instructor = new Instructor(1, "Engin", "Demiroğ",
                "kodlamaio");
        Applicant applicant = new Applicant(2, "Burak", "Kalaycı",
                "123123");
        Employee employee = new Employee(3, "Yağız", "Köksal",
                30000);




//        manager.add(instructor);
//        manager.add(applicant);
//        manager.add(employee);

        User[] users = {instructor, applicant, employee};
        manager.addMultiple(users);


    }
}