package com.example.service.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Months;

import com.example.web.model.response.Response;

public class Util {
	
    public static String getSalt(){
    	return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    public static String getAutoGeneratedUserPassword(){
        return RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomNumeric(3);
    }
    
    public static boolean isEmpty(Integer[] arr){
    	if(arr != null && arr.length > 0){
    		return false;
    	}
    	return true;
    }
    
    public static Date getDate(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.YEAR, year);
        Date date = calendar.getTime();
        return date;
    }
    
    public static int getMaximumDays(int month){
    	Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
    	return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    public static double round(int scale, double value){
    	BigDecimal bd = new BigDecimal(value);
 	    bd = bd.setScale(2, RoundingMode.HALF_UP);
 	    return bd.doubleValue();
    }
    
    public static BigDecimal getAmount(BigDecimal amount, int mon, int year){
    	Calendar now = Calendar.getInstance();
    	int month = now.get(Calendar.MONTH);
    	int day = now.get(Calendar.DAY_OF_MONTH);
    	int noOfDays = getMaximumDays(month);
    	int noOfMonths = monthsDifference(mon, year);
    	double d = (double)day/noOfDays;
    	BigDecimal monthUnits = BigDecimal.valueOf(round(2, round(2, d) + noOfMonths));
		return amount.multiply(monthUnits).setScale(2, RoundingMode.HALF_UP);
    }
    
    public static int monthsDifference(int month, int year){
    	LocalDate valDate = new LocalDate(getDate(month, year));
    	DateTime validityDate = new DateTime().withDate(valDate);
    	Months d1 = Months.monthsBetween( new DateTime(), validityDate);
    	return d1.getMonths();
    }
    
    public static double distBetweenPoints(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (float) (earthRadius * c);

        return dist;
    }
    
    public static Response getResponseInstance(){
    	return Response.getResponseInstance();
    }
    
    public static Time onlyTime(long timeInMilis){
    	return new Time(timeInMilis);
    }
    
    public static String getOTP(){
    	return UUID.randomUUID().toString().substring(0,6);
    }
    
    public static Date parseStringToDate(String date) throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	return sdf.parse(date);
    }
    
    public static String parseDateToString(Date date) throws ParseException{
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	return sdf.format(date);
    }
    
    public static LatLong getLatLong(String sourceLatLong, String destLatLong){
    	LatLong ll = new LatLong();
    	
		ll.setSourceLat(Double.parseDouble(sourceLatLong.split(",")[0]));
		ll.setSourceLong(Double.parseDouble(sourceLatLong.split(",")[1]));
		
		ll.setDestLat(Double.parseDouble(destLatLong.split(",")[0]));
		ll.setDestLong(Double.parseDouble(destLatLong.split(",")[1]));
    	
    	return ll;
    }
    
}
