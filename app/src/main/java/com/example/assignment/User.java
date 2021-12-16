package com.example.assignment;

public class User {
    private String Contactname,phone,email;

    public User() {

    }

    public String getContactname() {
        return Contactname;
    }

    public void setContactname(String contactname) {
        Contactname = contactname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
