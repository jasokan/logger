package com.jasokan.logger.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jasokan.logger.dao.CassandraConnector;
import com.jasokan.logger.dao.MongoConnector;
import com.jasokan.logger.model.LogDetail;

@Controller("rewardsController")
public class LogsController {

	private CassandraConnector cassandraConnector;

	private MongoConnector mongoConnector;

	public MongoConnector getMongoConnector() {
		return mongoConnector;
	}

	@Autowired
	public void setMongoConnector(MongoConnector mongoConnector) {
		this.mongoConnector = mongoConnector;
	}

	public CassandraConnector getCassandraConnector() {
		return cassandraConnector;
	}

	@Autowired
	public void setCassandraConnector(CassandraConnector cassandraConnector) {
		this.cassandraConnector = cassandraConnector;
	}


	@RequestMapping(value = "/postLogs", method = RequestMethod.POST)
	public @ResponseBody String postLogs(
			HttpServletRequest request,
            HttpServletResponse response) {
		
		String jsonString = request.getParameter("json");
		System.out.println(jsonString);
		
		Gson gson = new Gson();
		LogDetail lDetail = gson.fromJson(jsonString, LogDetail.class);
		
		System.out.println("------------------------");
		System.out.println(lDetail.toString());
		System.out.println("------------------------");
		
		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		try {

			// convert JSON string to Map
			map = mapper.readValue(jsonString.trim(),
					new TypeReference<HashMap<String, String>>() {
					});

			System.out.println(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mongoConnector.create(map); 
		
		return jsonString;
		// return null;
	}

	@RequestMapping(value = "/rewardsProcessing", method = RequestMethod.GET)
	public @ResponseBody String rewardsProcessing(
			@RequestParam("rewardsMap") String rewardsMap) {
		System.out.println(rewardsMap);

		Gson jsonConverter = new Gson();

		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		try {

			// convert JSON string to Map
			map = mapper.readValue(rewardsMap.trim(),
					new TypeReference<HashMap<String, String>>() {
					});

			System.out.println(map);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// String operationKey = map.get("Operation");

		/*
		 * map.remove("Operation");
		 * 
		 * if (operationKey.equalsIgnoreCase("CREATE_ACCOUNT")) { return
		 * rewardsConnector.createAccount(jsonConverter.toJson(map));
		 * 
		 * } else if (operationKey.equalsIgnoreCase("GET_ACCOUNT_INFO")) {
		 * return rewardsConnector.getAccountInformation(map);
		 * 
		 * } else if (operationKey.equalsIgnoreCase("FUND_ACCOUNT")) {
		 * 
		 * 
		 * customer:value, account_identifier:value, amount:value,
		 * clientIp:value, creditCardNumber:value creditCardExpiration:value,
		 * creditCardSecurity:value, f_name:value, l_name:value, address:value,
		 * city:value, state:value, country:value, zip:value
		 * 
		 * 
		 * 
		 * Map<String, Object> fundAccount = new HashMap<String, Object>();
		 * Map<String, Object> creditCardDetails = new HashMap<String,
		 * Object>(); Map<String, Object> billingDetails = new HashMap<String,
		 * Object>();
		 * 
		 * fundAccount.put("customer", map.get("customer"));
		 * fundAccount.put("account_identifier", map.get("account_identifier"));
		 * fundAccount.put("amount", map.get("amount"));
		 * 
		 * 
		 * creditCardDetails.put("number", map.get("creditCardNumber"));
		 * creditCardDetails.put("expiration", map.get("creditCardExpiration"));
		 * creditCardDetails.put("security_code",
		 * map.get("creditCardSecurity"));
		 * 
		 * billingDetails.put("f_name", map.get("f_name"));
		 * billingDetails.put("l_name", map.get("l_name"));
		 * billingDetails.put("address", map.get("address"));
		 * billingDetails.put("city", map.get("city"));
		 * billingDetails.put("state", map.get("state"));
		 * billingDetails.put("country", map.get("country"));
		 * billingDetails.put("zip", map.get("zip"));
		 * 
		 * fundAccount.put("credit_card", creditCardDetails);
		 * fundAccount.put("billing_address", billingDetails);
		 * 
		 * 
		 * { "customer" : "CompanyA", "account_identifier": "123456", "amount" :
		 * 100000, "client_ip" : "127.0.0.1", "credit_card" : { "number" :
		 * "4111111111111111", "expiration" : "02/20", "security_code" : "123",
		 * "billing_address": { "f_name" : "John", "l_name" : "Doe", "address":
		 * "123 Sesame St, Apt 1", "city" : "Smallville", "state" : "WA",
		 * "country": "USA", "zip" : "11111", "email" : "test@example.com" } } }
		 * 
		 * return
		 * rewardsConnector.fundAccount(jsonConverter.toJson(fundAccount));
		 * 
		 * } else if (operationKey.equalsIgnoreCase("LIST_REWARDS")) { return
		 * rewardsConnector.listRewards();
		 * 
		 * } else if (operationKey.equalsIgnoreCase("PLACE_ORDER")) {
		 * 
		 * Map<String, Object> placeOrderDetails = new HashMap<String,
		 * Object>(); Map<String, Object> recipientDetails = new HashMap<String,
		 * Object>(); placeOrderDetails.put("customer", map.get("customer"));
		 * placeOrderDetails.put("account_identifier",
		 * map.get("account_identifier")); placeOrderDetails.put("reward_from",
		 * map.get("reward_from")); placeOrderDetails.put("reward_subject",
		 * map.get("reward_subject")); placeOrderDetails.put("sku",
		 * map.get("sku")); placeOrderDetails.put("amount", map.get("amount"));
		 * placeOrderDetails.put("reward_message", map.get("reward_message"));
		 * 
		 * recipientDetails.put("name", map.get("recipientName"));
		 * recipientDetails.put("email", map.get("recipientEmail"));
		 * 
		 * placeOrderDetails.put("recipient", recipientDetails);
		 * 
		 * 
		 * 
		 * return
		 * rewardsConnector.placeOrder(jsonConverter.toJson(placeOrderDetails));
		 * 
		 * 
		 * { "customer" : "CompanyA", "account_identifier": "123456",
		 * "recipient" : { "name" : "John Doe", "email": "john.doe@example.com"
		 * }, "sku" : "TNGO-E-V-STD", "amount" : 1000, "reward_message" :
		 * "Thank you for participating in the XYZ survey.", "reward_subject" :
		 * "XYZ Survey, thank you...", "reward_from" : "Jon Survey Doe" }
		 * 
		 * 
		 * } else if (operationKey.equalsIgnoreCase("GET_ORDER_INFO")) { return
		 * rewardsConnector.getOrderInformation(jsonConverter .toJson(map));
		 * 
		 * orders/123-12345678-12
		 * 
		 * 
		 * } else if (operationKey.equalsIgnoreCase("GET_ORDER_HISTORY")) {
		 * return rewardsConnector.orderHistory(getUrl(map));
		 * 
		 * > GET /raas/v1/orders?start_date=2013-03-01T00:00:00-08:00&end_date
		 * =2013-04-01T00:00:00-08:00&offset=0
		 * &limit=2&account_identifier=12345678&customer=CompanyA
		 * 
		 * 
		 * }
		 */

		// cassandraConnector.create(new HashMap<String, String>());
		mongoConnector.create(new HashMap<String, String>());
		return null;

	}

	public String getUrl(Map<String, String> requestMap) {
		StringBuilder sb = new StringBuilder();

		List<String> listOfParams = new ArrayList<String>();
		for (String param : requestMap.keySet()) {
			listOfParams.add(param + "=" + encodeString(requestMap.get(param)));
		}

		if (!listOfParams.isEmpty()) {
			String query = StringUtils.join(listOfParams, "&");
			sb.append("?");
			sb.append(query);
		}

		return sb.toString();
	}

	public static String encodeString(String name) throws NullPointerException {
		String tmp = null;

		if (name == null)
			return null;

		try {
			tmp = java.net.URLEncoder.encode(name);
		} catch (Exception e) {
		}

		if (tmp == null)
			throw new NullPointerException();

		return tmp;
	}

}