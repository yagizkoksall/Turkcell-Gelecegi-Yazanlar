package org.example;

public class SmsLogger extends Logger{
    @Override
    public void log() {
        System.out.println("Sms iletildi.");
    }
}
