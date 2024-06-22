package com.example.edu_admin.model;

public class RegisterMobile {

    String registrationNo,mobile;

    public RegisterMobile() {
    }

    public RegisterMobile(String registrationNo, String mobile) {
        this.registrationNo = registrationNo;
        this.mobile = mobile;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
