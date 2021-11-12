package spec_01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemRepository {
		
	private static ItemRepository itemRepository;
	private Connection connection;
	
	private ItemRepository() {

	}
	
	public static ItemRepository getInstance() {
		if (itemRepository == null)	{
			itemRepository = new ItemRepository();
		}
		return itemRepository;
	}
	
	public List<Item> selectAllItems() {
		connection = HikariDatabaseConnection.get();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from items order by value");
			List<Item> items = new ArrayList<Item>();
			while (resultSet.next()) {
				Item item = new Item();
				item.setId(resultSet.getString(1));
				item.setValue(resultSet.getString(2));
				items.add(item);
			}
			connection.close();
			return items;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int insertItem(Item item) {
		connection = HikariDatabaseConnection.get();
		item.setId(UUID.randomUUID().toString().replace("-", "").substring(0, 16));
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("insert into items values (?, ?)");
			preparedStatement.setString(1, item.getId());
			preparedStatement.setString(2, item.getValue());
			preparedStatement.executeUpdate();
			connection.close();
			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
