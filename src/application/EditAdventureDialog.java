package application;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;import javafx.collections.MapChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import textadventure.AdventureMap;
import textadventure.Room;

public class EditAdventureDialog extends Dialog<ButtonType> {

	private static final int BUTTON_SPACING;
	private static final int BUTTON_SIZE;
	
	private AdventureMap tempMap;
	private HBox mapColumns;
	
	static {
		BUTTON_SPACING = 10;
		BUTTON_SIZE = 100;
	}
	
	public EditAdventureDialog(AdventureMap map, ArrayList<AdventureMap> adventures) {
		
		VBox vBox = new VBox(BUTTON_SPACING);
		this.tempMap = new AdventureMap(map);
		this.mapColumns = new HBox();
		this.mapColumns.setPadding(new Insets(BUTTON_SPACING));
		this.mapColumns.setSpacing(BUTTON_SPACING);
		this.setTitle("Adventure bearbeiten");
		TextField txtAdventureName = new TextField(map.getName());
		updateButtons();
		ScrollPane scrollPane = new ScrollPane(mapColumns);
		Button btnSave = new Button("Speichern");
		
		btnSave.setOnAction(e -> {
			tempMap.setName(txtAdventureName.getText());
			adventures.remove(map);
			adventures.add(tempMap);
			close();
			});
		Button btnEnd = new Button("Beenden");
		
		vBox.getChildren().addAll(txtAdventureName, scrollPane, btnSave);
		this.setResizable(true);
		this.setWidth(500);
		this.setHeight(500);
		this.getDialogPane().setContent(vBox);
		
		this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
		
	}
	
	private void updateButtons() {
		mapColumns.getChildren().clear();
		for(int i = 0; i < tempMap.getMap().size(); ++i) {
			int columnsIndex = i;
			VBox column = new VBox();
			column.setSpacing(10);
			for(int j = 0; j < tempMap.getMap().get(i).size(); ++j){
				int rowIndex = j;
				Room room = tempMap.getRoomAt(i, j);
				Button button = new Button(room.getName());
				button.setPrefWidth(BUTTON_SIZE);
				button.setPrefHeight(BUTTON_SIZE);
				button.setOnAction(e -> {
					EditRoomDialog ed = new EditRoomDialog(room);
					ed.showAndWait();
					button.setText(room.getName());
					tempMap.setRoomAt(columnsIndex, rowIndex, room);
					updateButtons();
				});
				if(!room.getName().equals("<leer>"))
					button.setTextFill(Color.BLUE);
				column.getChildren().add(button);
			}
			mapColumns.getChildren().add(column);
		}
	}
	
}
