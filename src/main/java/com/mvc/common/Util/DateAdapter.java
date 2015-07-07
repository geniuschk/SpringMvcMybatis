package com.mvc.common.Util;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {

  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Override
  public String marshal(Date v) throws Exception {
      return dateFormat.format(v);
  }

  @Override
  public Date unmarshal(String v) throws Exception {
      return (Date) dateFormat.parse(v);
  }

}