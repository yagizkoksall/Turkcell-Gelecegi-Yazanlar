package org.example;

public class DatabaseLogger implements Logger{
    @Override
    public void log(String message) {
        System.out.println(message + " veritabanına loglandı.");
    }
}
