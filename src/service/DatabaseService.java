package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import textadventure.AdventureMap;
import textadventure.Item;
import textadventure.Player;
import textadventure.Room;
import twitter4j.DirectMessage;

public class DatabaseService {

	public static final String DBlocation = "C:\\Users\\janoc_000\\Desktop\\Projekt\\DB";
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
	
	public static void dropTables() {
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
			prepStmt.executeUpdate(insert);
		
			
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
	
	public static Boolean isPlayerOnAdventure(long UserID, AdventureMap am) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			String searchMessage = "SELECT * FROM Location WHERE UserID = " + UserID + " AND Adventure = '" + am.getName() + "'";
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
	
	public static void putPlayerOnAdventure(long UserID, AdventureMap am) {
		Connection conn = null;
		PreparedStatement prepStmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			String insert = "INSERT INTO Location VALUES (?,?,?)";
			prepStmt = conn.prepareStatement(insert);
			prepStmt.setLong(1, UserID);
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
	
	public static HashSet<Item> loadInventory(long UserId, AdventureMap am) {
		Connection conn = null;
		Statement stmt = null;
		HashSet<Item> inventory = new HashSet<>();
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			String select = "SELECT * FROM Inventory WHERE UserId=" + UserId + " AND Adventure='" + am.getName() + "'";
			ResultSet result = stmt.executeQuery(select);
			while(result.next()) {
				for(Item item : am.getAllItems()) {
					if(item.getName().equalsIgnoreCase(result.getString("Item"))) {
						//TODO: remove Item from AdventureMap
						inventory.add(item);
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
	
	//TODO: implement!
	public static void saveGame(long USerID, Player player, AdventureMap am) {
		//has Player already played the Adventure?
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(connString);
			stmt = conn.createStatement();
			String searchMessage = "";
			
			
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
	
	public static Player loadRoom(int UserID, AdventureMap am) {
		//TODO: Implement!!!
		return null;
	}
	
	public static MetaInformation getMetaInformation() {
		//TODO: Implement!!!
		return null;
	}
	
}
