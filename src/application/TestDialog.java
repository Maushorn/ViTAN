package application;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import textadventure.AdventureMap;
import textadventure.InputHandler;
import textadventure.Player;

public class TestDialog extends Dialog {

	private final int WIDTH = 400;
	private final int HEIGHT = 400;
	
	
	
	public TestDialog(AdventureMap map) {
		Player player = new Player(map);
		player.setPosition(map.getStart());
		InputHandler handler = new InputHandler(map, player);
		this.setTitle("Test Window");
		TextArea txtOutput = new TextArea();
		txtOutput.setEditable(false);
		txtOutput.setMinHeight(HEIGHT);
		txtOutput.setMinWidth(WIDTH);
		txtOutput.setWrapText(true);
		TextField txtInput = new TextField();
		txtInput.setPromptText("Befehl eingeben und mit Enter bestätigen.");
		txtInput.setMinWidth(WIDTH);
		txtInput.setOnAction(e -> {
			txtOutput.appendText("Spieler: " + txtInput.getText() + "\n");
			txtOutput.appendText("ViTan: " + handler.processInput(txtInput.getText()) + "\n");
			txtInput.clear();
		});
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(txtOutput, txtInput);
		
		
		
		this.getDialogPane().setContent(vBox);
		this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
		
	}
	
}
