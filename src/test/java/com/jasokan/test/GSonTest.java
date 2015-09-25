package com.jasokan.test;

import java.sql.Timestamp;
import java.util.Date;

import com.google.gson.Gson;
import com.jasokan.logger.model.LogDetail;

public class GSonTest {

	public static void main(String[] args) {

		Gson gson = new Gson();
		
		/*Map<String, String> insideMap = new HashMap<String, String>();

		Map<String, Object> checkMap = new HashMap<String, Object>();
		
		checkMap.put("customer", "companyA");
		checkMap.put("account_identifier", "123456");
		insideMap.put("name" , "John Doe");
		insideMap.put("email", "john.doe@example.com");
		checkMap.put("recipient", insideMap);
		checkMap.put("sku"              , "TNGO-E-V-STD");
		checkMap.put("amount", 1000);
		checkMap.put("reward_message"    , "Thank you for participating in the XYZ survey.");*/
		
		
		SimpleJSON sj = new SimpleJSON();
		
		sj.setAge(20);
		sj.setAddress1("asdfasdfasdfasdfadwfaaaaaa");
		sj.setAddress2("asdfasdf98097808970987980");
		sj.setAddress3("dasfsdfsdaxzvxzcvxzcv");
		sj.setEmployee(false);
		sj.setFloatVal(30.2323232f);
		
		String sjString = gson.toJson(sj);
		
		LogDetail lDet = new LogDetail();
		lDet.setLogTimestamp(new Timestamp(new Date().getTime()));
		lDet.setAction("Action");
		lDet.setActionJson(sjString);
		lDet.setSellerId(1);
		lDet.setSkuId("SomeSKUID");
		
		System.out.println(sjString);
		
		
		
		
		
		
		
		System.out.println(gson.toJson(lDet));

	}

}
