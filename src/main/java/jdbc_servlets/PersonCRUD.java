package jdbc_servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonCRUD {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servket", "root","root");
		return connection;
	}
	
	public int signUp(Person person) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PERSON VALUES (?,?,?,?,?,?,?,?)");
		preparedStatement.setInt(1, person.getId());
		preparedStatement.setString(2, person.getFirst_name());
		preparedStatement.setString(3, person.getLast_name());
		preparedStatement.setInt(4, person.getAge());
		preparedStatement.setLong(5, person.getPhone());
		preparedStatement.setString(6, person.getAddress());
		preparedStatement.setString(7, person.getEmail());
		preparedStatement.setString(8, person.getPassword());
		
		int count = preparedStatement.executeUpdate();
		connection.close();
		return count;
		
	}
	
	public int updatePerson(Person person) throws ClassNotFoundException, SQLException
	{
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PERSON SET FIRST_NAME=?,LAST_NAME=?, AGE=?,PHONE=?,ADDRESS=?,EMAIL=?,PASSWORD=?");
		preparedStatement.setString(1, person.getFirst_name());
		preparedStatement.setString(2, person.getLast_name());
		preparedStatement.setInt(3, person.getAge());
		preparedStatement.setLong(4, person.getPhone());
		preparedStatement.setString(5, person.getAddress());
		preparedStatement.setString(6, person.getEmail());
		preparedStatement.setString(7, person.getPassword());
		
		int count = preparedStatement.executeUpdate();
		connection.close();
		return count;
		
	}
	
	public String getPerson(String email) throws ClassNotFoundException, SQLException
	{
		Connection connection= getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PERSON WHERE EMAIL=?");
		preparedStatement.setString(1, email);
		
		ResultSet resultset = preparedStatement.executeQuery();
		String password = null;
		while (resultset.next()) 
		{
			password = resultset.getString("password");	
		}
		connection.close();
		return password;
	}
}
