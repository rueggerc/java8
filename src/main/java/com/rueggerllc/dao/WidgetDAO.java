package com.rueggerllc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class WidgetDAO {
	
	private static final Logger logger = Logger.getLogger(WidgetDAO.class);
	
	
	public int getMaxWidgetID() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		int max = 0;
		try {
			Properties properties = new Properties();
			properties.setProperty("user", "hadoop");
			properties.setProperty("password", "dakota");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://captain:3306/hadoopdb", properties);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select max(id) from widgets");
			while (resultSet.next()) {
				max = resultSet.getInt(1);
				logger.info("MAX=" + max);
			}
		} catch (Exception e) {
			logger.error("ERROR", e);
		} finally {
			close(resultSet);
			close(statement);
			close(connection);
		}
		return max;
	}
	
	public void insertWidgets(int numberOfWidgets, int startID) {

	}
	
	private void close(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
		}
	}
	private void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (Exception e) {
		}
	}
	private void close(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (Exception e) {
		}
	}

}
