package com.mvc.template.was.Dao;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.google.gson.annotations.Expose;
import com.mvc.common.Util.DateAdapter;

@XmlRootElement(name="USER")
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
  private Timestamp createdate = null;
  
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
  @XmlJavaTypeAdapter(DateAdapter.class)
  public Timestamp getCreatedate() {
    return createdate;
  }
  public void setCreatedate(Timestamp createdate) {
    this.createdate = createdate;
  }
  @Override
  public boolean isValid() {
    // TODO Auto-generated method stub
    return false;
  }  
}
