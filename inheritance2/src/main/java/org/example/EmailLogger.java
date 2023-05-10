package org.example;

public class EmailLogger extends Logger {

    @Override
    public void log() {
        System.out.println("Eposta gönderildi");
    }

}
