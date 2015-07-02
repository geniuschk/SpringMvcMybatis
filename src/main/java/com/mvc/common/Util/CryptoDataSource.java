package com.mvc.common.Util;

import org.apache.commons.dbcp.BasicDataSource;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class CryptoDataSource extends BasicDataSource {
  StandardPBEStringEncryptor AES = new StandardPBEStringEncryptor();

  @Override
  public synchronized void setUrl(String url) {
    // TODO Auto-generated method stub
    AES.setPassword("geniuschk");
    super.setUrl(AES.decrypt(url));
  }
  
  @Override
  public void setPassword(String password) {
    // TODO Auto-generated method stub
    super.setPassword(AES.decrypt(password));
  }

  @Override
  public void setUsername(String username) {
    // TODO Auto-generated method stub
    super.setUsername(AES.decrypt(username));
  }
  
  
}
