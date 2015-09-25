package com.jasokan.logger.dao;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

@Repository("mongoConnector")
public class MongoConnectorImpl implements MongoConnector {

	@Override
	public int create(Map<String, String> createDetails) {
		try {

			Date date = new Date();
			MongoClient mongo = new MongoClient("localhost", 27000);
			DB db = mongo.getDB("test");

			DBCollection table = db.getCollection("user");
			BasicDBObject document = new BasicDBObject();
			Iterator<Entry<String, String>> mapContentsIterator = createDetails.entrySet().iterator();

			while (mapContentsIterator.hasNext()) {
				Map.Entry<String, String> pair = (Map.Entry<String, String>) mapContentsIterator
						.next();
				document.put(pair.getKey(), pair.getValue());
				System.out.println(pair.getKey() + " = " + pair.getValue());
				mapContentsIterator.remove(); // avoids a ConcurrentModificationException
			}
			table.insert(document);

			mongo.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Map<String, String> updateDetails) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int read(Map<String, String> readDetails) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Map<String, String> deleteDetails) {
		// TODO Auto-generated method stub
		return 0;
	}

}
