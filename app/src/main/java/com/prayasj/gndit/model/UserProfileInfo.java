package com.prayasj.gndit.model;


public class UserProfileInfo {
  private String name;

  private String gender;

  private String address;

  private String contact;

  private long dob;

  public UserProfileInfo(String name, String gender, String address, String contact, long dob) {
    this.name = name;
    this.gender = gender;
    this.address = address;
    this.contact = contact;
    this.dob = dob;
  }

  public String getName() {
    return name;
  }

  public String getGender() {
    return gender;
  }

  public String getAddress() {
    return address;
  }

  public String getContact() {
    return contact;
  }

  public long getDob() {
    return dob;
  }
}
