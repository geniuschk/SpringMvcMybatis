package com.mvc.common.Util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtil{
  private static final Integer authExpireDay = 7;
  
  private static final String fileName = "config.properties";
  
  private static PropertyUtil propertyUtil = new PropertyUtil();
  private static Properties propObj = null;
  private static File propFile = null;
  private static FileInputStream propStream = null;
  private static long propLastModified = 0L;
  
  private  PropertyUtil() {
  }
  
  public synchronized String getProperty(String key) {
    StringBuffer log = new StringBuffer();
    long lastModified = propFile.lastModified();
    if (propLastModified < lastModified) {
      propLastModified = propFile.lastModified();
      
      try {
        propStream = new FileInputStream(propFile);
        propObj.load(propStream);
        propStream.close();
      } catch (Exception e) {
        CommonUtil.exceptionLogging( e, log.append("key : ").append(key).append(", propLastModified : ").append(propLastModified).append(", lastModified : ").append(lastModified) );
      } finally {
        log = null;
      }
    }
    
    return propObj.getProperty(key);
  }
  
  public static synchronized PropertyUtil getInstance(String fileName) {
    StringBuffer log = new StringBuffer();
    String path = PropertyUtil.class.getClassLoader().getResource(fileName).getPath();
    if (propObj == null){
      propObj = new Properties();
      try {
        propFile = new File(path);
        propLastModified = propFile.lastModified();
        propStream = new FileInputStream(propFile);
        propObj.load(propStream);
      } catch (Exception e) {
        CommonUtil.exceptionLogging(e, log.append("fileName : ").append(fileName).append(", propLastModified : ").append(propLastModified));
      } finally {
        log = null;
        if (propStream != null) try{propStream.close();}catch(Exception e){
          CommonUtil.exceptionLogging(e, null);
        };
      }
    }
    
    return propertyUtil;
  }
  
  public static Integer getAuthExpireDay() {
    Integer expire = authExpireDay;
    
    return expire;
  }
  
  public static String getDBConfig() {
    return PropertyUtil.getInstance(PropertyUtil.fileName).getProperty("db.config");
  }
  
  public static String[] getBlockIpList() {
    String ips = PropertyUtil.getInstance(PropertyUtil.fileName).getProperty("block.IpList");
    if(ips != null && ips.length() > 0) {
      String[] ipList = ips.split(",");
      return ipList;
    }
    return null;
  }
  
  public static String[] getBlockIdList() {
    String ids = PropertyUtil.getInstance(PropertyUtil.fileName).getProperty("block.UserIdList");
    if(ids != null && ids.length() > 0) {
      String[] idList = ids.split(",");
      return idList;
    }
    return null;
  }
  
  public static String fileUploadNormalLimit() {
    return PropertyUtil.getInstance(PropertyUtil.fileName).getProperty("fileupload.normalFileLimit");
  }
  
  public static String fileUploadImageLimit() {
    return PropertyUtil.getInstance(PropertyUtil.fileName).getProperty("fileupload.imageFileLimit");
  }
  
  public static String fileUploadNormalLimitString() {
    return PropertyUtil.getInstance(PropertyUtil.fileName).getProperty("fileupload.normalFileLimitString");
  }
  
  public static String fileUploadImageLimitString() {
    return PropertyUtil.getInstance(PropertyUtil.fileName).getProperty("fileupload.imageFileLimitString");
  }
  
  public static String[] fileExecutableExt() {
    String fileExt = PropertyUtil.getInstance(PropertyUtil.fileName).getProperty("file.executableExt");
    if(fileExt != null && fileExt.length() > 0) {
      String[] extList = fileExt.split(",");
      return extList;
    }
    return null;    
  }
  
  public static String[] fileImageExt() {
    String fileExt = PropertyUtil.getInstance(PropertyUtil.fileName).getProperty("file.imageExt");
    if(fileExt != null && fileExt.length() > 0) {
      String[] extList = fileExt.split(",");
      return extList;
    }
    return null;    
  }
  
  public static String[] mimeTypeBlackList() {
    String fileExt = PropertyUtil.getInstance(PropertyUtil.fileName).getProperty("file.mimeTypeBlackList");
    if(fileExt != null && fileExt.length() > 0) {
      String[] extList = fileExt.split(",");
      return extList;
    }
    return null;    
  }
  
  public static int getCookieMaxAge() {
    return Integer.parseInt(PropertyUtil.getInstance(PropertyUtil.fileName).getProperty("cookie.maxAge"));    
  }
}
