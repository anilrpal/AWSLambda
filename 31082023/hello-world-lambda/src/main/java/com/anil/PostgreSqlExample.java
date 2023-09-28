package com.anil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgreSqlExample {

	private final DataSourceProperties db;
	
	public PostgreSqlExample() {
		db=new DataSourceProperties();
		System.out.println("PostgreSqlExample - "+db.toString());
	}
	
	public PostgreSqlExample(DataSourceProperties db){
		this.db=db;
		System.out.println("PostgreSqlExample - "+db.toString());
	}
	
	public List<String> handleRequest(){
		
		List<String>listOfName=new ArrayList<>();
		
		
		String jdbcUrl="";
		String username="";
		String password="";
		
//		jdbcUrl="jdbc:postgresql://anil-db-1.ca1lhmlbxgt8.ap-south-1.rds.amazonaws.com:5432/postgres";
//		username="postgres";
//		password="postgres";
		
		jdbcUrl=db.getHost()+db.getPort()+db.getDb();
		username=db.getUsername();
		password=db.getPassword();
		
		System.out.println("JDBC URL : "+jdbcUrl);
		System.out.println("USERNAME : "+username);
		System.out.println("PASSWORD : "+password);
				
		
		try(Connection connection= DriverManager.getConnection(jdbcUrl, username, password)){
			
			if(!connection.isValid(0)) {
				System.out.println("Unable to connect "+jdbcUrl);
				System.exit(0);
			}
			
			PreparedStatement statement=connection.prepareStatement("select * from company");
			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {
				String name=rs.getString("name");
				System.out.println("Name : "+name);
				listOfName.add(name);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage()+" "+e.getSQLState());
			System.out.println(e.getErrorCode()+" "+e.getLocalizedMessage());
			throw new RuntimeException();
		}
		return listOfName;
	}
}
