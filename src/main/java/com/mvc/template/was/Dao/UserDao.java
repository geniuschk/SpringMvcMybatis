package com.mvc.template.was.Dao;

import java.sql.Timestamp;

import com.google.gson.annotations.Expose;

public class UserDao extends Base {  
  public UserDao(){}

  @Expose
  private String id = null;
  @Expose
  private Integer age = null; 
  @Expose
  private String password = null;
  @Expose
  private String email = null;
  @Expose
  private String name = null;
  @Expose
  private String phone = null;
  @Expose
  private Timestamp createDate = null;
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public Integer getAge() {
    return age;
  }
  public void setAge(Integer age) {
    this.age = age;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public Timestamp getCreateDate() {
    return createDate;
  }
  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }
  @Override
  public boolean isValid() {
    // TODO Auto-generated method stub
    return false;
  }  
}
