package org.example;

public class Main {
    public static void main(String[] args) {
        CustomerManager manager = new CustomerManager();
        manager.add(new SmsLogger());
    }
}