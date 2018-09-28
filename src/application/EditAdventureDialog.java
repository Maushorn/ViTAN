package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import textadventure.AdventureMap;

public class EditAdventureDialog extends Dialog<ButtonType> {

	private static final int BUTTON_SPACING;
	private static final int BUTTON_SIZE;
	
	private AdventureMap map;
	private HBox mapColumns;
	
	static {
		BUTTON_SPACING = 10;
		BUTTON_SIZE = 100;
	}
	
	public EditAdventureDialog(AdventureMap map) {
		this.map = map;
		this.mapColumns = new HBox();
		this.mapColumns.setPadding(new Insets(BUTTON_SPACING));
		this.mapColumns.setSpacing(BUTTON_SPACING);
		
		this.setTitle("Adventure bearbeiten");
		updateButtons();
		
		this.getDialogPane().setContent(mapColumns);
		this.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
	}
	
	private void updateButtons() {
		for(int i = 0; i < map.getMap().size(); ++i) {
			VBox column = new VBox();
			column.setSpacing(10);
			for(int j = 0; j < map.getMap().get(i).size(); ++j){
				Button button = new Button(map.getRoomAt(i, j).getName());
				button.setPrefWidth(BUTTON_SIZE);
				button.setPrefHeight(BUTTON_SIZE);
				column.getChildren().add(button);
			}
			mapColumns.getChildren().add(column);
		}
	}
	
}
