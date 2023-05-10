package org.example;

public class SmsNotification implements Notification{
    @Override
    public void sendNotify() {
        System.out.println("Sms bildirimi yollandı.");
    }
}
