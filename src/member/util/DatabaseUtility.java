package member.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtility {
	public static void close(PreparedStatement preparedStatement, Connection connection){
		if(preparedStatement!=null){
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection){
		if(resultSet!=null){
			try {
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("SQLException 발생");
			}
		}
		if(preparedStatement!=null){
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				System.out.println("SQLException 발생");
			}

		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("SQLException 발생");
			}
		}
	}
}
