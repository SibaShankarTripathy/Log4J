package com.sst.log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;

public class RetrieveData {
	private static Logger logger = Logger.getLogger(RetrieveData.class);
//	private static Logger logger1 = LogManager.getLogger(RetrieveData.class.getName());
	PreparedStatement prepareStatement = null;
	ResultSet resultSet = null;
	static {
		try {
			PropertyConfigurator.configure("src/com/sst/common/log4j.properties");
			logger.info("LoggerSetup ready");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.fatal("Loger creation error"+e);
		}
	}

	private void getRecords() {
		String query = "SELECT * FROM SHSTAFF";
		Connection connection = DBConnection.getDBConnection(logger);
		try {
			if (connection != null) {
				prepareStatement = connection.prepareStatement(query);
				resultSet = prepareStatement.executeQuery();
				if (resultSet != null) {
					logger.debug("com.sst.dbconnectionlog4j.DataRetrieve.getRecords(): ResultSet contains data.");
					while (resultSet.next()) {
						System.out.println("Staff ID: " + resultSet.getInt(1));
						System.out.println("Staff Name: " + resultSet.getString(2));
						System.out.println("Staff Address: " + resultSet.getString(3));
					}
				} else
					logger.error("com.sst.dbconnectionlog4j.DataRetrieve.getRecords(): Result set contain no data");
			} else
				logger.error("com.sst.dbconnectionlog4j.DataRetrieve.getRecords(): DB Connection fail");
		} catch (Exception e) {
			logger.fatal("com.sst.dbconnectionlog4j.DataRetrieve.getRecords() Fatal Error: "+e);
		}
	}
	public static void main(String[] args) {
		 new RetrieveData().getRecords();
	}
}
