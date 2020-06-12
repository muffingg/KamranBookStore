package com.fdmgroup.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Admin")
public class Admin extends User {

    public Admin() {
    }

    public Admin(String userEmail, String firstName, String password, String lastName, String address, String phoneNumber) {
        super(userEmail, password, firstName, lastName, address, phoneNumber);
    }

    public void setEmailAddress() {
        setUserEmail(getFirstName().toLowerCase() + "." + getLastName().toLowerCase() + "@books4u.com");
    }

}
