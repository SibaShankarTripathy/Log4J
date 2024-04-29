package com.sst.dbconnectionlog4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class DataRetrieve {
	private static Logger logger = Logger.getLogger(DataRetrieve.class);
	PreparedStatement prepareStatement = null;
	ResultSet resultSet = null;
	static {
		/* For Logger Setup */
		//Other Layouts HTMLLayout htmlLayout = new HTMLLayout(); 
		//XMLLayout xmlLayout = new XMLLayout(); 
		//PatternLayout patternLayout = new PatternLayout();
		SimpleLayout simplelayout = new SimpleLayout();
		ConsoleAppender appender = new ConsoleAppender(simplelayout);
		logger.addAppender(appender);
		logger.setLevel(Level.DEBUG);
		logger.debug("com.sst.dbconnectionlog4j.DataRetrieve.class: All setup done for logger");
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
				logger.error("com.sst.dbconnectionlog4j.DataRetrieve.getRecords(): DB Connection fa");
		} catch (Exception e) {
			logger.fatal("com.sst.dbconnectionlog4j.DataRetrieve.getRecords() Fatal Error: "+e);
		}
	}
	public static void main(String[] args) {
		DataRetrieve dbretrieve = new DataRetrieve();
		dbretrieve.getRecords();
	}
}
