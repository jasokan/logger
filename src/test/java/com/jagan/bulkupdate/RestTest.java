package com.jagan.bulkupdate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;

public class RestTest {

	public String RestPutClient(String url, int newValue) {
		// example url : http://localhost:9898/data/1d3n71f13r.json
		CloseableHttpClient httpClient = HttpClients.createDefault();
		StringBuilder result = new StringBuilder();
		try {
			HttpPut putRequest = new HttpPut(url);
			// putRequest.addHeader("Content-Type", "application/json");
			// putRequest.addHeader("Accept", "application/json");
			JSONObject keyArg = new JSONObject();
			keyArg.put("stock", newValue);
			StringEntity input;
			try {

				input = new StringEntity(keyArg.toString());
				input.setContentType("application/json");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "1";
			}
			putRequest.setEntity(input);
			HttpResponse response = httpClient.execute(putRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));
			String output;
			while ((output = br.readLine()) != null) {
				result.append(output);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	public static void main(String[] args) {

		RestTest rTest = new RestTest();
		rTest.RestPutClient(
				"http://52.10.75.145/api/skus/1?auth_token=authtoken1",
				101);
	}

}
