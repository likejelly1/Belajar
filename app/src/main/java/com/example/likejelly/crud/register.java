package com.example.likejelly.crud;

public class register {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String waktuDaftar;

    public register(String username, String email, String password, String phoneNumber, String waktuDaftar) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.waktuDaftar = waktuDaftar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWaktuDaftar() {
        return waktuDaftar;
    }

    public void setWaktuDaftar(String waktuDaftar) {
        this.waktuDaftar = waktuDaftar;
    }
}
