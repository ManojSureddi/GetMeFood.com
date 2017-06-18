package com.gmf.ws.helper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UtilityMethods {
public static void stripHTML(String input) throws ParseException{
	SimpleDateFormat formater= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	Date d1=formater.parse("2014-11-12 11:10:55.846");
	Date d2=new Date();
	long duration=d2.getTime()-d1.getTime();
	long diff=TimeUnit.MILLISECONDS.toHours(duration);
	System.out.println(diff+" "+(new Timestamp(d2.getTime())));
}
public static  void main(String args[]){
try {
	stripHTML("asdas");
} catch (ParseException e) {
	//TODO Auto-generated catch block
	e.printStackTrace();
}
}
}
