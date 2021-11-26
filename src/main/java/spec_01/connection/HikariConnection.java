package spec_01.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class HikariConnection {

	private static HikariDataSource dataSource = new HikariDataSource();

	static {
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/standard?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
	}

	public static Connection get() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
