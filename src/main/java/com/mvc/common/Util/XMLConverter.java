package com.mvc.common.Util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

/// 사용안함.
public class XMLConverter {
  private Marshaller marshaller;
  private Unmarshaller unmarshaller;

  public Marshaller getMarshaller() {
    return marshaller;
  }

  public void setMarshaller(Marshaller marshaller) {
    this.marshaller = marshaller;
  }

  public Unmarshaller getUnmarshaller() {
    return unmarshaller;
  }

  public void setUnmarshaller(Unmarshaller unmarshaller) {
    this.unmarshaller = unmarshaller;
  }

  public String convertFromObjectToXML(Object object) throws IOException {
    StringWriter sw = new StringWriter();
    
    try {
      getMarshaller().marshal(object, (Result) sw);
    } finally {
      if (sw != null) {
        sw.close();
      }
    }
    
    return sw.toString();
  }

  public Object convertFromXMLToObject(String xmlString) throws IOException {
    StringReader sr = new StringReader(xmlString);
    Object resultObj = null;
    
    try {
      resultObj = getUnmarshaller().unmarshal((Source) sr);
    } finally {
      if (sr != null) {
        sr.close();
      }
    }
    
    return resultObj;
  }
}
