package com.jasokan.logger.dao;

import java.util.Map;

public interface MongoConnector {

	public int create(Map<String, String> createDetails);

	public int update(Map<String, String> updateDetails);

	public int read(Map<String, String> readDetails);

	public int delete(Map<String, String> deleteDetails);

}
