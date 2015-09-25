package com.jasokan.logger.dao;

import java.util.HashMap;

public interface CassandraConnector {
	
	public int create(HashMap<String, String> createDetails);
	
	public int update(HashMap<String, String> updateDetails);
	
	public int read(HashMap<String, String> readDetails);
	
	public int delete(HashMap<String, String> deleteDetails);

}
