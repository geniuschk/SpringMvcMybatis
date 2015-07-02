package com.mvc.common.Util;

import org.apache.log4j.Logger;

public class CommonUtil {
  final public static boolean isLoggable = true;
  
  public static void exceptionLogging(Exception e, StringBuffer log) {
    Logger logger = Logger.getLogger(CommonUtil.class.getName() + ".exceptionLogging");
    StackTraceElement[] ste = e.getStackTrace();
    String className = ste[0].getClassName();
    String methodName = ste[0].getClassName();
    int lineNumber = ste[0].getLineNumber();
    String fileName = ste[0].getFileName();
    
    logger.fatal("\n# # # # # Exception : " + e.getMessage() + "\nClassName : " + className + ", MethodName : " + methodName + " "
          + fileName + " " + lineNumber + " line\n" + log);
    logger = null;
  }

  public static String cutStr(String str,int cutByte) {
    byte [] strByte = str.getBytes();
    if( strByte.length < cutByte )
      return str;
    int cnt = 0;
    for( int i = 0; i < cutByte; i++ ) {
      if( strByte[i] < 0 )
        cnt++;
    }

    String r_str;
    if(cnt%2==0) r_str = new String(strByte, 0, cutByte );
    else r_str = new String(strByte, 0, cutByte + 1 );

    return r_str;
  }
  
  private static char toHex(int nibble) {
        return hexDigit[(nibble & 0xF)];
    }
  
    private static char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String escape(String str) {
        // Modeled after the code in java.util.Properties.save()
      String retVal = "";
        StringBuffer buf = new StringBuffer();
        int len = str.length();
        char ch;
        for (int i = 0; i < len; i++) {
            ch = str.charAt(i);
            switch (ch) {
            case '\\':
                buf.append("\\\\");
                break;
            /* mybatis ���� \�� �߰�����.
            case '\t':
                buf.append("\\t");
                break;
            case '\n':
                buf.append("\\n");
                break;
            case '\r':
                buf.append("\\r");
                break;*/
            default:
                if (ch >= ' ' && ch <= 127) {
                    buf.append(ch);
                } else {
                    buf.append('\\');
                    buf.append(toHex((ch >> 12) & 0xF));
                    buf.append(toHex((ch >> 8) & 0xF));
                    buf.append(toHex((ch >> 4) & 0xF));
                    buf.append(toHex((ch >> 0) & 0xF));
                }
            }
        }
        retVal = buf.toString();
        buf = null;
        return retVal;
    }
    
    public static Boolean isMine(String cookieUid, String uid) {
      Boolean retVal = false;
      if(cookieUid!=null && cookieUid.equals(uid)) retVal = true;
      
      return retVal;
    }
    
    public static Boolean isNull(CharSequence str) {
      return str==null || str.length()==0;
    }
  
    public static int getStringCount(char[] c){
    int len=c.length;
    int totalCount=0;
    int korCount=0;
    int notKorCount=0;
      
    for(int i=0;i<len;i++){
      int ch=c[i];
      if(ch<=0x7f){
        notKorCount++;
        totalCount++;
      //}else if(ch<=0x7ff){
        //count+=2;
      }else{
        korCount++;
        totalCount+=2;
      }
    }
    System.out.println("korCount : " + korCount + ", notKorCount : " + notKorCount);
    return totalCount;
  }
    
    public static int getUTF8Count(char[] c){
    int len=c.length;
    int count=0;
      
    for(int i=0;i<len;i++){
      int ch=c[i];
      if(ch<=0x7f){
        count++;
      }else if(ch<=0x7ff){
        count+=2;
      }else{
        count+=3;
      }
    }
    return count;
  }
}
