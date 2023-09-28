package com.anil;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostgresqlExampleTest {
	@org.junit.jupiter.api.Test
	void test() {
		
		DataSourceProperties dsp=new DataSourceProperties("jdbc:postgresql://anil-db-1.ca1lhmlbxgt8.ap-south-1.rds.amazonaws.com:", "5432/", "postgres", "postgres", "postgres");
		PostgreSqlExample pst=new PostgreSqlExample(dsp);
		
		assertEquals(5, pst.handleRequest().size());
	}
}
