package com.mvc.template;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;

public class JTest {

  @Test
  public void test() {
    StandardPBEStringEncryptor AES = new StandardPBEStringEncryptor();
    AES.setPassword("geniuschk");

    System.out.println(AES.encrypt("jdbc:oracle:thin:@localhost:1521:orcl"));
    System.out.println(AES.encrypt("scott"));
    System.out.println(AES.encrypt("1234"));
  }

}
