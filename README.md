# jdbc.properties
src/main/resources/config/jdbc.properties > jdbc 설정 파일.
src/test/java/com/mvc/template/JTest.java > 파일 참고해서 jdbc 설정 값 암호화.

# Dao
src/main/java/com/mvc/template/was/Dao > 여기 생성되는 Dao 파일들은 자동으로 첫글자 소문자로 myBatis Aliase 생성됨.
src/main/resources/mapper > 여기 생성되는 xml 파일들은 자동으로 myBatis Mapper 추가됨. 

# Sample Local Oracle Table Scheme
  CREATE TABLE "SCOTT"."TEST_USER" 
   (	"ID" VARCHAR2(20 BYTE), 
	"NAME" VARCHAR2(20 BYTE), 
	"PASSWORD" VARCHAR2(20 BYTE), 
	"AGE" NUMBER, 
	"EMAIL" VARCHAR2(20 BYTE), 
	"PHONE" VARCHAR2(20 BYTE), 
	"CREATEDATE" DATE
   )
