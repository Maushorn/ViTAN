package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import metainformation.ItemInfo;
import metainformation.PositionInfo;
import textadventure.AdventureMap;
import textadventure.Item;
import textadventure.Player;
import textadventure.Room;
import twitter4j.DirectMessage;

public class DatabaseService {

	public static final String DBlocation = ".\\DB";
	public static final String connString = "jdbc:derby:" + DBlocation + ";create=true";
	
	
	/**Initializes Database.
	 * 
	 */
	public static void initialize() {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	/**Initializes all tables.
	 *
	 */
	public static void createTables() {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			
			String createMessagesTable = "CREATE TABLE Messages("
					+ "MessageID BIGINT,"
					+ "UserID BIGINT,"
					+ "Adventure VARCHAR(200),"
					+ "PRIMARY KEY(MessageID))";
			stmt.executeUpdate(createMessagesTable);
			System.out.println("Messages-Table created");
			
			String  createLocationTable = "CREATE TABLE Location("
					+ "UserID BIGINT,"
					+ "Room VARCHAR(200),"
					+ "Adventure VARCHAR(200))";
			stmt.executeUpdate(createLocationTable);
			System.out.println("Location-Table created");
			
			String createInventoryTable = "CREATE TABLE Inventory("
					+ "UserID BIGINT,"
					+ "Item VARCHAR(200),"
					+ "Adventure VARCHAR(200))";
			stmt.executeUpdate(createInventoryTable);
			System.out.println("Inventory-Table created");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	/**Drops all tables.
	 *
	 */
	public static void dropAllTables() {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			
			String dropMessagesTable = "DROP TABLE Messages";
			stmt.executeUpdate(dropMessagesTable);
			System.out.println("Messages-Table dropped");
			
			String  dropLocationTable = "DROP TABLE Location";
			stmt.executeUpdate(dropLocationTable);
			System.out.println("Location-Table dropped");
			
			String dropInventoryTable = "DROP TABLE Inventory";
			stmt.executeUpdate(dropInventoryTable);
			System.out.println("Inventory-Table dropped");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}

	/**Checks if a specific MessageID is already in the DataBase.
	 *
	 * @param dm
	 * @return
	 */
	public static Boolean isMessageInDatabase(DirectMessage dm) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			String searchMessage = "SELECT MessageID FROM Messages WHERE MessageID = " + dm.getId();
			
			ResultSet result = stmt.executeQuery(searchMessage);
			if(result.next())
				return true;
			else return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**Adds message to database.
	 *
	 * @param dm
	 * @param am
	 */
	public static void insertMessage(DirectMessage dm, AdventureMap am) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			String insert = "INSERT INTO Messages VALUES(?,?,?)";
			prepStmt = conn.prepareStatement(insert);
			prepStmt.setLong(1, dm.getId());
			prepStmt.setLong(2, dm.getSenderId());
			prepStmt.setString(3, am.getName());
			prepStmt.executeUpdate();
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(prepStmt!=null)
					prepStmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**Checks if a player has already played this Adventure.
	 *
	 * @param userID
	 * @param am
	 * @return
	 */
	public static Boolean isPlayerOnAdventure(long userID, AdventureMap am) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			String searchMessage = "SELECT * FROM Location WHERE UserID = " + userID + " AND Adventure = '" + am.getName() + "'";
			ResultSet result = stmt.executeQuery(searchMessage);
			if(result.next())
				return true;
			else return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**Adds new player to a Adventure.
	 *
	 * @param userID
	 * @param am
	 */
	public static void putPlayerOnAdventure(long userID, AdventureMap am) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			String insert = "INSERT INTO Location VALUES (?,?,?)";
			prepStmt = conn.prepareStatement(insert);
			prepStmt.setLong(1, userID);
			prepStmt.setString(2, am.getStart().getName());
			prepStmt.setString(3, am.getName());
			prepStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(prepStmt!=null)
					prepStmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**Loads the items a player has collected in a specific adventure.
	 *
	 * @param userId
	 * @param am
	 * @return
	 */
	public static HashSet<Item> loadInventory(long userId, AdventureMap am) {
		Connection conn = null;
		Statement stmt = null;
		HashSet<Item> inventory = new HashSet<>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			String select = "SELECT * FROM Inventory WHERE UserId=" + userId + " AND Adventure='" + am.getName() + "'";
			ResultSet result = stmt.executeQuery(select);
			while(result.next()) {
				for(Item item : am.getAllItems()) {
					if(item.getName().equalsIgnoreCase(result.getString("Item"))) {
						//TODO: remove Item from AdventureMap
						inventory.add(item);
						for(Room room : am.getAllRooms()){
							if(room.getItems().contains(item))
								room.getItems().remove(item);
						}

					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return inventory;
	}

	/**Removes all items from a players inventory for a specific adventure.
	 *
	 * @param userId
	 * @param am
	 */
	public static void resetInventory(long userId, AdventureMap am){

		Connection conn = null;
		Statement stmt = null;
		HashSet<Item> inventory = new HashSet<>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			String delete = "DELETE FROM Inventory WHERE UserId=" + userId + " AND Adventure='" + am.getName() + "'";
			stmt.executeUpdate(delete);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//TODO: finish
	public static void resetLocation(long userId, AdventureMap am){
		Connection conn = null;
		Statement stmt = null;
		HashSet<Item> inventory = new HashSet<>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			String delete = "DELETE FROM Location WHERE UserId=" + userId + " AND Adventure='" + am.getName() + "'";
			stmt.executeUpdate(delete);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**CAUTION: Removes all items from all inventories.
	 *
	 */
	public static void resetAllInventories() {
		Connection conn = null;
		Statement stmt = null;
		HashSet<Item> inventory = new HashSet<>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			String delete = "DELETE FROM Inventory";
			stmt.executeUpdate(delete);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public static void saveGame(long userID, Player player, AdventureMap am) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			
			//save intenvory
			for(Item item : player.getItems()) {
				String select = "SELECT * FROM Inventory WHERE Item='" + item.getName() + "' AND UserID=" + userID;
				ResultSet result = stmt.executeQuery(select);
				if(!result.next()) {
					String insert = "INSERT INTO Inventory VALUES(?,?,?)";
					PreparedStatement prepStmt = conn.prepareStatement(insert);
					prepStmt.setLong(1, userID);
					prepStmt.setString(2, item.getName());
					prepStmt.setString(3, am.getName());
					prepStmt.executeUpdate();
				}
			}
			//Update room
			String update = "UPDATE Location SET Room='" + player.getPosition().getName() + "' WHERE "
					+ "UserID=" + userID + " AND Adventure='" + am.getName() + "'";
			stmt.executeUpdate(update);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static Room loadRoom(long userID, AdventureMap am) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			String select = "SELECT Room FROM Location WHERE UserID =" + userID + " AND Adventure='" + am.getName() + "'";
			ResultSet result = stmt.executeQuery(select);
			String roomName = "";
			while(result.next()) {
				roomName = result.getString(1);
			}
			for(Room r : am.getAllRooms())
				if(r.getName().equals(roomName))
					return r;
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static ObservableList<PositionInfo> getPositionData(){
		Connection conn = null;
		Statement stmt = null;
		ObservableList<PositionInfo> data = null;
		try{
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();

			String getLocationData = "SELECT * FROM Location";
			ResultSet result = stmt.executeQuery(getLocationData);
			List<PositionInfo> locationList = new ArrayList<>();
			while(result.next()){
				PositionInfo tempPositionInfo = new PositionInfo();
				tempPositionInfo.setUserId(result.getLong("UserID"));
				tempPositionInfo.setLocation(result.getString("Room"));
				tempPositionInfo.setAdventure(result.getString("Adventure"));
				locationList.add(tempPositionInfo);
			}
			data = FXCollections.observableList(locationList);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static ObservableList<ItemInfo> getItemData() {
		Connection conn = null;
		Statement stmt = null;
		ObservableList<ItemInfo> data = null;
		try{
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();

			String getItemData = "SELECT * FROM Inventory";
			ResultSet result = stmt.executeQuery(getItemData);
			List<ItemInfo> inventoryList = new ArrayList<>();
			while(result.next()){
				ItemInfo tempItemInfo = new ItemInfo();
				tempItemInfo.setUserId(result.getLong("UserID"));
				tempItemInfo.setAdventure(result.getString("Adventure"));
				tempItemInfo.setItem(result.getString("Item"));
				inventoryList.add(tempItemInfo);
			}
			data = FXCollections.observableList(inventoryList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static MetaInformation getMetaInformation() {
		//TODO: Implement!!!
		return null;
	}


}
