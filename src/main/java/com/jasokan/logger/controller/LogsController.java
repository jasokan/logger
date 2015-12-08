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