package com.anil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DataSourceProperties {
	private final String host;
	private final String port;
	private final String db;
	private final String username;
	private final String password;
	
	public DataSourceProperties() {
		this.host=System.getenv("RDS_HOSTNAME");
		this.port=System.getenv("RDS_PORT");
		this.db=System.getenv("RDS_DB");
		this.username=System.getenv("RDS_USERNAME");
		this.password=System.getenv("RDS_PASSWORD");
		
//		System.out.println("DataSourceProperties - "+db.toString());
	}

	@Override
	public String toString() {
		return "DataSourceProperties [host=" + host + ", port=" + port + ", db=" + db + ", username=" + username
				+ ", password=" + password + "]";
	}
	
	
	
}
