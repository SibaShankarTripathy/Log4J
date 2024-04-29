package com.sst.dbconnectionlog4j;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

public class DBConnection {
	public static Connection getDBConnection(Logger logger) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("url","username","password");
			if(connection!=null) {
				logger.info("com.sst.dbconnectionlog4j.DBConnection.class: Database connected successfully");
				return connection;
			} else {
				connection = null;
			}
		} catch (Exception e) {
			logger.error("com.sst.dbconnectionlog4j.DBConnection.class:"+e);
		}
		return connection;
	}
}
