package com.mvc.template.was.Dao;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvc.common.Util.XMLConverter;

abstract public class Base {
  private static Gson _gson = null;
  private static Gson _gsonExpose = null;
  private static XMLConverter xmlConverter;
  
  static {
    _gsonExpose = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss.S Z")
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
    _gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss.S Z")
                .create();
  }
  
  public String toJSON(boolean isExpose) {
    Gson gson = null;
    if (isExpose) {
      gson = _gsonExpose;
    } else {
      gson = _gson;
    }
    return gson.toJson(this);
  }
  
  static public String toJSON(Object obj, boolean isExpose) {
    Gson gson = null;
    if (isExpose) {
      gson = _gsonExpose;
    } else {
      gson = _gson;
    }
    
    return gson.toJson(obj);
  }
  
  static public Object fromJSON(String json, Class<?> classofType) {
    return _gson.fromJson(json, classofType);
  }
  
  static public Object fromJSON(String json, Type classofType) {
    return _gson.fromJson(json, classofType);
  }
  
  static public Object fromJSON2(String json, Type classofType) {
    Gson gson = new GsonBuilder()
                  .setDateFormat("yyyy-MM-dd HH:mm:ss.S Z")
                  .serializeNulls()
                  .create();
    return gson.fromJson(json, classofType);
  }
  
  public abstract  boolean isValid();
  
  @Override
  public String toString(){
    return toJSON(true);
  }
  
  
  
  

  
  public String toXml() throws IOException {
    return (String) toXml(this);
  }
  
  public Object toXml(Object obj) throws IOException {
    return getXmlConverter().convertFromObjectToXML(obj);    
  }
  
  public XMLConverter getXmlConverter() {
    if (xmlConverter!=null) {
      return xmlConverter;
    } else {
      xmlConverter = new XMLConverter();
      return xmlConverter;
    }
  }
}
