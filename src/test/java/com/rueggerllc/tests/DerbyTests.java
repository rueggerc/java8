package com.rueggerllc.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.rueggerllc.beans.Foo;
import com.rueggerllc.dao.WidgetDAO;

public class DerbyTests {

	private static Logger logger = Logger.getLogger(DerbyTests.class);
	
	
	
	@BeforeClass
	public static void setupClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setupTest() throws Exception {
	}

	@After
	public void tearDownTest() throws Exception {
	}
	
	@Test
	// @Ignore
	public void testInsertPageViews() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Properties properties = new Properties();
			properties.setProperty("user", "chris");
			properties.setProperty("password", "dakota");
			
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			connection = DriverManager.getConnection("jdbc:derby://captain:1527/rueggerllc", properties);

			String insertStatement = 
				"insert into chris.pageviews " +
			    "(username, url) " +
			    "values " +
			    "(?, ?)";
			preparedStatement = connection.prepareStatement(insertStatement);
			for (int i = 0; i < 5; i++) {
				preparedStatement.setString(1,"username"+i);
				preparedStatement.setString(2,"./page"+i);
				preparedStatement.executeUpdate();
				logger.info("Inserted");
				
			}
			connection.commit();

		} catch (Exception e) {
			logger.error("ERROR", e);
		} finally {
			close(preparedStatement);
			close(connection);
		}

	}
	
	@Test
	@Ignore
	public void testSelectPageViews() {
		logger.info("SELECT PAGE VIEW BEGIN");
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Properties properties = new Properties();
			properties.setProperty("user", "chris");
			properties.setProperty("password", "dakota");
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			connection = DriverManager.getConnection("jdbc:derby://captain:1527/rueggerllc", properties);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from pageviews");
			while (resultSet.next()) {
				String username = resultSet.getString("username");
				logger.info("username=" + username);
			}
		} catch (Exception e) {
			logger.error("ERROR", e);
		} finally {
			close(resultSet);
			close(statement);
			close(connection);
		}

	}

	@Test
	@Ignore
	public void testInsertWidgets() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Properties properties = new Properties();
			properties.setProperty("user", "hadoop");
			properties.setProperty("password", "dakota");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://captain:3306/hadoopdb", properties);
			
			String insertStatement = 
				"insert into widgets " +
			    "(id, widget_name, price, design_date, version, design_comment) " +
			    "values " +
			    "(null, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(insertStatement);
			insertWidgets(preparedStatement);
			
			for (int i = 0; i < 1000; i++) {
				insertWidget(i, preparedStatement);
			}

		} catch (Exception e) {
			logger.error("ERROR", e);
		} finally {
			close(preparedStatement);
			close(connection);
		}

	}
	
	private void insertWidgets(PreparedStatement preparedStatement) throws Exception {
		preparedStatement.setString(1,"sprocket");
		preparedStatement.setDouble(2, new Double(0.25));
		preparedStatement.setDate(3, getNow());
		preparedStatement.setInt(4, new Integer(1));
		preparedStatement.setString(5, "Connects two gizmos");
		preparedStatement.executeUpdate();		
		
		preparedStatement.setString(1,"gizmo");
		preparedStatement.setDouble(2, new Double(4.00));
		preparedStatement.setDate(3, getNow());
		preparedStatement.setInt(4, new Integer(4));
		preparedStatement.setString(5, "some widget");
		preparedStatement.executeUpdate();		
		
		preparedStatement.setString(1,"gadget");
		preparedStatement.setDouble(2, new Double(99.99));
		preparedStatement.setDate(3, getNow());
		preparedStatement.setInt(4, new Integer(13));
		preparedStatement.setString(5, "Flagship Product");
		preparedStatement.executeUpdate();		
	}
	
	private void insertWidget(int i, PreparedStatement preparedStatement) throws Exception {
		logger.info("Insert");
		preparedStatement.setString(1,"Widget#" + i);
		preparedStatement.setDouble(2, new Double(311.55+i));
		preparedStatement.setDate(3, getNow());
		preparedStatement.setInt(4, new Integer(42+i));
		preparedStatement.setString(5, "Widget Comment: " + i);
		preparedStatement.executeUpdate();
	}
	
	
	
	private static java.sql.Date getNow() {
		long now = Calendar.getInstance().getTime().getTime();
		// long uDate = new java.util.Date().getTime();
		java.sql.Date date = new java.sql.Date(now);
		return date;
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
