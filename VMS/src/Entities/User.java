package Entities;

import Utils.Constants;

public class User {

    private String userId;
    private String IDType;
    private String personalIdentificationNo;
    private Long phone;
    private String name;
    private Integer age;

    private volatile static int n = 0;

    private synchronized String nextNum() {
        n++;
        return n + "";
    }

    public User(String IDType, String personalIdentificationNo, Long phone, String name, Integer age) {
        this.userId = Constants.USER_SEQUENCE + nextNum();
        this.IDType = IDType;
        this.personalIdentificationNo = personalIdentificationNo;
        this.phone = phone;
        this.name = name;
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIDType() {
        return IDType;
    }

    public void setIDType(String IDType) {
        this.IDType = IDType;
    }

    public String getPersonalIdentificationNo() {
        return personalIdentificationNo;
    }

    public void setPersonalIdentificationNo(String personalIdentificationNo) {
        this.personalIdentificationNo = personalIdentificationNo;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static int getN() {
        return n;
    }

    public static void setN(int n) {
        User.n = n;
    }
}
