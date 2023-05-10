package org.example;

public class CustomerManager {
    // Dependency Injection

    private Logger[] loggers;

    public CustomerManager(Logger[] loggers) {
        this.loggers = loggers;
    }

    // loosely / tightly coupled
    public void add(){
        System.out.println("Müşteri eklendi");
        for(Logger logger:loggers){
            logger.log("test");
        }

    }
}
