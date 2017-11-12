package com.hp.technicalfest;

import java.math.BigInteger;

/**
 * Created by hp on 14-10-2017.
 */
public class StudentModal {
    String usn,firstname,lastname,email,password,contact;

    public StudentModal(String usn, String firstname, String lastname, String email, String password, String contact) {
        this.usn = usn;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.contact = contact;
    }

    public StudentModal() {
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
