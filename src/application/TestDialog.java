package application;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import textadventure.AdventureMap;

public class TestDialog extends Dialog {

	private final int WIDTH = 400;
	
	
	
	public TestDialog(AdventureMap map) {
		
		this.setTitle("Test Window");
		TextArea txtOutput = new TextArea();
		txtOutput.setEditable(false);
		txtOutput.setMinHeight(WIDTH);
		txtOutput.setMinWidth(WIDTH);
		txtOutput.setWrapText(true);
		TextField txtInput = new TextField();
		txtInput.setPromptText("Befehl eingeben und mit Enter bestätigen.");
		txtInput.setMinWidth(WIDTH);
		txtInput.setOnAction(e -> {
			txtOutput.appendText("\n" + txtInput.getText());
			txtInput.clear();
		});
		
		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(txtOutput, txtInput);
		
		
		this.getDialogPane().setContent(vBox);
		this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
		
	}
	
}
