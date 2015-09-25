package com.jasokan.logger.dao;

import java.util.HashMap;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;

@Repository("cassandraConnector")
public class CassandraConnectorImpl implements CassandraConnector {

	@Override
	public int create(HashMap<String, String> createDetails) {

		Cluster cluster = null;

		cluster = Cluster.builder().addContactPoint("localhost").build();
		// .addContactPoints(InetAddress.getLocalHost()).build();

		Session session = cluster.connect("mykeyspace");

		CassandraOperations cassandraOps = new CassandraTemplate(session);

		Insert insert = QueryBuilder.insertInto("users");
		insert.setConsistencyLevel(ConsistencyLevel.ONE);
		insert.value("user_id", 1100);
		insert.value("fname", "Jagannathan");
		insert.value("lname", "Asokan");

		cassandraOps.execute(insert);

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(HashMap<String, String> updateDetails) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int read(HashMap<String, String> readDetails) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(HashMap<String, String> deleteDetails) {
		// TODO Auto-generated method stub
		return 0;
	}

}
