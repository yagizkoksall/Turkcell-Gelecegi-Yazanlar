package org.example;

import java.util.Date;

public interface VerifyService {
     boolean verify(String natId, String name, String surname, Date birthDate);
}
