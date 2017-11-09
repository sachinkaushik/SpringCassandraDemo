package com.spring.couchbase.SpringCassandraDemo;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration{

	@Override
	protected String getKeyspaceName() {
		return "sachin";
	}
	
}
