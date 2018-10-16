package textadventure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import javafx.beans.property.SimpleStringProperty;

public class AdventureMap implements Serializable{

	static final long serialVersionUID = 555183l;
	
	private String serializableName;
	transient private SimpleStringProperty name;
	private ArrayList<ArrayList<Room>> map;
	private Room start;
	
	/**Initializes a Map with 3 times 3 Rooms, where the start-room is in the middle.
	 * 
	 * @param start
	 */
	public AdventureMap(String name, Room start) {
		this.name = new SimpleStringProperty(name);
		this.start = start;
		this.start.setStartRoom(true);
		map = new ArrayList<>();
		map.add(new ArrayList<>());
		map.add(new ArrayList<>());
		map.add(new ArrayList<>());
		for(int i = 0; i <= 2; ++i) {
			map.get(0).add(new Room());
			map.get(2).add(new Room());
		}
		map.get(1).add(new Room());
		map.get(1).add(start);
		map.get(1).add(new Room());
	}
	
	public AdventureMap(String name) {
		this.name = new SimpleStringProperty(name);
		this.start = new Room("Start", true);
		map = new ArrayList<>();
		map.add(new ArrayList<>());
		map.add(new ArrayList<>());
		map.add(new ArrayList<>());
		for(int i = 0; i <= 2; ++i) {
			map.get(0).add(new Room());
			map.get(2).add(new Room());
		}
		map.get(1).add(new Room());
		map.get(1).add(start);
		map.get(1).add(new Room());
	}

	/**Copy-Constructor
	 * 
	 * @param original
	 */
	public AdventureMap(AdventureMap original) {
		name = new SimpleStringProperty(original.getName());
		map = new ArrayList<>();
		for(int i = 0; i < original.getMap().size(); ++i) {
			map.add(new ArrayList<>());
			ArrayList<Room> originalColumn = original.getMap().get(i);
			for(int j = 0; j < originalColumn.size(); ++j) {
				Room r = new Room(originalColumn.get(j));
				if(original.isStart(originalColumn.get(j))) {
					r.setStartRoom(true);
					this.setStart(r);
				}
				map.get(i).add(r);
				
					
			}
		}
//		start = new Room(original.getStart());
	}

	public SimpleStringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}
	
	public String getName() {
		return name.get();
	}

	public void setRoomAt(int x, int y, Room room) {
		if(this.getRoomAt(x, y).isStartRoom())
			room.setStartRoom(true);
		map.get(x).set(y, room);
		ArrayList<Room> newColumn = new ArrayList<Room>();
		for(int i = 0; i < map.get(0).size(); ++i)
			newColumn.add(new Room());
		if(x == 0) {
			map.add(x, newColumn);
			if(y == 0) {
				for(ArrayList<Room> column : map)
					column.add(0, new Room());
			}
			else if(y == map.get(0).size()-1) {
				for(ArrayList<Room> column : map)
					column.add(new Room());
			}
		}else if(x == map.size()-1) {
			map.add(x+1, newColumn);
			if(y == 0) {
				for(ArrayList<Room> column : map)
					column.add(0, new Room());
			}
			else if(y == map.get(0).size()-1) {
				for(ArrayList<Room> column : map)
					column.add(map.get(0).size()-1, new Room());
			}
		}else {
			if(y == 0)
				for(ArrayList<Room> column : map)
					column.add(0, new Room());
			if(y == map.get(0).size()-1)
				for(ArrayList<Room> column : map)
					column.add(new Room());
			
		}
	}
	
	/**Get coordinates of specified room within the AdventureMap.
	 * Returns null if no matching room is found.
	 * 
	 * @param room
	 * @return
	 */
	public Coord getCoordinates(Room room) {
		for(int i = 0; i < map.size(); ++i)
			for(int j = 0; j < map.get(i).size(); ++j)
				if(map.get(i).get(j) == room)
					return new Coord(i, j);
		return null;
		
	}
	
	//TODO: What if Room does not exist?
	public Room getRoomAt(int x, int y) {
		if((map.size() <= x) || (map.get(x).size() <= y))
			return null;
		else return map.get(x).get(y);
	}
	
	public ArrayList<Room> getAllRooms() {
		ArrayList<Room> roomList = new ArrayList<>();
		for(int i = 0; i < map.size(); ++i)
			for(int j = 0; j < map.get(i).size(); j++) {
				roomList.add(map.get(i).get(j));
			}
		return roomList;
	}
	
	public HashSet<Item> getAllItems(){
		HashSet<Item> items = new HashSet<>();
		for(Room r : getAllRooms()) {
			for(Item item : r.getItems())
				items.add(item);
		}
		return items;
	}
	
	public ArrayList<ArrayList<Room>> getMap() {
		return map;
	}

	private Boolean isStart(Room r) {
		return r == start;
	}
	
	public Room getStart() {
		return start;
	}

	public void setStart(Room start) {
		this.start = start;
	}
	
	public void prepareForSerialization() {
		serializableName = name.get();
	}
	
	public void initAfterDeserialization() {
		name = new SimpleStringProperty(serializableName);
	}
	
}
