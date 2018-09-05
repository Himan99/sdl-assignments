package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public abstract class MySqlAccess {
	
	public Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;

	  final private String host = "localhost";
	  final private String user = "te3346";
	  final private String passwd = "te3346";
	  
	  public void readDataBase(String query ) {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://" + host + "/te3346db?"
	              + "user=" + user + "&password=" + passwd );

	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      resultSet = statement
		          .executeQuery(query);
//          .executeQuery("select * from feedback.comments");
	      writeResultSet(resultSet);

	      // PreparedStatements can use variables and are more efficient
	      
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      close();
	    }

	  }
	  
//	  void preparedStatmentInsert(ArrayList<Object> params,String query){
//		  
//		  try {
//		  Class.forName("com.mysql.jdbc.Driver");
//	      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/te3346db?"
//	    		  										+ "user=" + user + "&password=" + passwd );
//		  preparedStatement = connect.prepareStatement(query);
////		          .prepareStatement("insert into  student values (?,?,?,?,?,?,?)");
//		      preparedStatement.setString(1, (String)params.get(0));
//		      preparedStatement.setInt(2, (int)params.get(1));
//		      preparedStatement.setString(3, (String)params.get(2));
//		      preparedStatement.setInt(4, (int)params.get(3));
//		      preparedStatement.setInt(5, (int)params.get(4));
//		      preparedStatement.setString(6, (String)params.get(5));
//		      preparedStatement.setString(7, (String)params.get(6));
//		      preparedStatement.executeUpdate();
//		  } catch (Exception e) {
//		      e.printStackTrace();
//		    } finally {
//		      close();
//		    }
//	  }
	  
	  public PreparedStatement getPreparedStatement(String query) {
		  try {
			  Class.forName("com.mysql.jdbc.Driver");
		      connect = DriverManager.getConnection("jdbc:mysql://" + host + "/te3346db?"
		    		  										+ "user=" + user + "&password=" + passwd );
			  preparedStatement = connect.prepareStatement(query);
			  } catch (Exception e) {
			      e.printStackTrace();
			  }
		return preparedStatement;
	  }
	  
	  public void executePreparedStatement(PreparedStatement ps) {
		  try {
			  preparedStatement=ps;
			  preparedStatement.executeUpdate();
		  }catch (Exception e) {
		      e.printStackTrace();
		  } finally {
		      close();
		  }
	  }
	  public void deletequery(String query) throws ClassNotFoundException, SQLException{
		  Class.forName("com.mysql.jdbc.Driver");
	      
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://" + host + "/te3346db?"
	              + "user=" + user + "&password=" + passwd );

	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      statement.executeUpdate(query);
	  }
	  private void close() {
		    try {
		      if (resultSet != null) {
		        resultSet.close();
		      }

		      if (statement != null) {
		        statement.close();
		      }

		      if (connect != null) {
		        connect.close();
		      }
		    } catch (Exception e) {

		    }
	  }

//	abstract void writeMetaData(ResultSet resultSet2);

	abstract void writeResultSet(ResultSet resultSet2) throws Exception;

}
/*

preparedStatement = connect
	          .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
	      // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
	      // Parameters start with 1
	      preparedStatement.setString(1, "Test");
	      preparedStatement.setString(2, "TestEmail");
	      preparedStatement.setString(3, "TestWebpage");
	      preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
	      preparedStatement.setString(5, "TestSummary");
	      preparedStatement.setString(6, "TestComment");
	      preparedStatement.executeUpdate();

	      preparedStatement = connect
	          .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
	      resultSet = preparedStatement.executeQuery();
	      writeResultSet(resultSet);

	      // Remove again the insert comment
	      preparedStatement = connect
	      .prepareStatement("delete from feedback.comments where myuser= ? ; ");
	      preparedStatement.setString(1, "Test");
	      preparedStatement.executeUpdate();
	      
	      resultSet = statement
	      .executeQuery("select * from feedback.comments");
	      writeMetaData(resultSet);
*/