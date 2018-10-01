package textadventure;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class AdventureMap {

	private SimpleStringProperty name;
	private ArrayList<ArrayList<Room>> map;
	private Room start;
	
	/**Initializes a Map with 3 times 3 Rooms, where the start-room is in the middle.
	 * 
	 * @param start
	 */
	public AdventureMap(String name, Room start) {
		this.name = new SimpleStringProperty(name);
		this.start = start;
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
		this.start = new Room("Start");
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

	public AdventureMap(AdventureMap original) {
		name = new SimpleStringProperty(original.getName());
		map = new ArrayList<>();
		for(int i = 0; i < original.getMap().size(); ++i) {
			map.add(new ArrayList<>());
			ArrayList<Room> originalColumn = original.getMap().get(i);
			for(int j = 0; j < originalColumn.size(); ++j)
				map.get(i).add(new Room(originalColumn.get(j)));
		}
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
	
	public Room getRoomAt(int x, int y) {
		return map.get(x).get(y);
	}
	
	public ArrayList<ArrayList<Room>> getMap() {
		return map;
	}

	public Room getStart() {
		return start;
	}

	public void setStart(Room start) {
		this.start = start;
	}
	
	
	
}
