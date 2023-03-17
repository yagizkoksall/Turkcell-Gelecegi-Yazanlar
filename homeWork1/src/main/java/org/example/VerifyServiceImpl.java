package org.example;

import java.util.Date;

public class VerifyServiceImpl implements VerifyService{

    @Override
    public boolean verify(String natId, String name, String surname, Date birthDate) {
        System.out.println("User verified");
        return true;

    }

}
