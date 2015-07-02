package com.mvc.common.Util;

import org.apache.commons.dbcp.BasicDataSource;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class CryptoDataSource extends BasicDataSource {
  StandardPBEStringEncryptor PBE = new StandardPBEStringEncryptor();

  @Override
  public synchronized void setUrl(String url) {
    // TODO Auto-generated method stub
    PBE.setPassword("geniuschk");
    super.setUrl(PBE.decrypt(url));
  }
  
  @Override
  public void setPassword(String password) {
    // TODO Auto-generated method stub
    super.setPassword(PBE.decrypt(password));
  }

  @Override
  public void setUsername(String username) {
    // TODO Auto-generated method stub
    super.setUsername(PBE.decrypt(username));
  }
  
  
}
