package textadventure;

import java.util.ArrayList;

public class AdventureMap {

	ArrayList<ArrayList<Room>> rows;
	Room start;
	
	/**Initializes a Map with 3 times 3 Rooms, where the start-room is in the middle.
	 * 
	 * @param start
	 */
	public AdventureMap(Room start) {
		this.start = start;
		rows = new ArrayList<>();
		rows.add(new ArrayList<>());
		rows.add(new ArrayList<>());
		rows.add(new ArrayList<>());
		for(int i = 0; i <= 2; ++i) {
		rows.get(0).add(null);
		rows.get(2).add(null);
		}
		rows.get(1).add(null);
		rows.get(1).add(start);
		rows.get(1).add(null);
		System.out.println(rows.get(1).get(0));
	}

	public ArrayList<ArrayList<Room>> getRows() {
		return rows;
	}

	public Room getStart() {
		return start;
	}

	public void setStart(Room start) {
		this.start = start;
	}
	
	
	
}
