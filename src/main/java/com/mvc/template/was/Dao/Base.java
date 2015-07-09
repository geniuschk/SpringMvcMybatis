package com.mvc.template.was.Dao;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * 기본 클래스.
 *
 * @author author
 * @see    BigExample
 */
abstract public class Base {
  private static Gson _gson = null;
  private static Gson _gsonExpose = null;
  
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
    return (String) toXml(this, this.getClass());
  }
  
  private Object toXml(Object source, Class<?> type) throws IOException {
    String result;
    StringWriter sw = new StringWriter();
    try {    
        Marshaller jaxbMarshaller = getMarshaller(type);
        jaxbMarshaller.marshal(source, sw);
        
        result = sw.toString();
        
    } catch (JAXBException e) {
        throw new RuntimeException(e);
    }
  
    return result;
  }
  
  public Object fromXml(String xml) throws IOException {
    return fromXml(xml, this.getClass());
  }
  
  private Object fromXml(String xmlString, Class<?> type) throws IOException{
    StringReader sr = new StringReader(xmlString);
    Object resultObj = null;
    
    try {
      Unmarshaller jaxbUnMarshaller = getUnMarshaller(type);            
      resultObj = jaxbUnMarshaller.unmarshal(sr);
    } catch (JAXBException e) {
      e.printStackTrace();
    } finally {
      if (sr != null) {
        sr.close();
      }
    }
    
    return resultObj;
  }
  
  private JAXBContext getJAXBContext( Class<?> type ) throws JAXBException {
    return JAXBContext.newInstance(type);   
  }
  
  private Marshaller getMarshaller( Class<?> type ) throws JAXBException {  
    JAXBContext jaxbContext = getJAXBContext(type);
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);    
    
    return jaxbMarshaller;    
  }
  
  private Unmarshaller getUnMarshaller( Class<?> type ) throws JAXBException {
    JAXBContext jaxbContext = getJAXBContext(type);
    Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
    
    return jaxbUnMarshaller;      
  }
}
