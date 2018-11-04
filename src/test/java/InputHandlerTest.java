package test.java;

import org.junit.jupiter.api.Test;
import textadventure.AdventureMap;
import textadventure.InputHandler;
import textadventure.Player;
import textadventure.Room;

public class InputHandlerTest {

    //TODO: sinvollen Test einbauen
    @Test
    void testInputHandler(){
        AdventureMap map = new AdventureMap("Abenteuer", new Room("TestRaum"));
        Player player1 = new Player(map);
        InputHandler handler = new InputHandler(new AdventureMap("TestAdventure", new Room("Testraum")), player1);
        System.out.println("Test positive");

        

    }

}
