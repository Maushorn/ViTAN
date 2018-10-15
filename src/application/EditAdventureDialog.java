package application;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import textadventure.AdventureMap;
import textadventure.InteractiveObject;
import textadventure.Item;
import textadventure.Room;

public class EditAdventureDialog extends Dialog<ButtonType> {

	private static final int BUTTON_SPACING;
	private static final int BUTTON_SIZE;

	private AdventureMap tempMap;
	private HBox mapColumns;
	private Room selectedRoom;
	private Item selectedItem;
	private InteractiveObject selectedIObject;

	private TextField txtRoomName;
	private TextArea txtRoomDescription;
	private ComboBox<String> cBoxItems;
	private ComboBox<String> cBoxIObjects;

	static {
		BUTTON_SPACING = 10;
		BUTTON_SIZE = 80;
	}

	public EditAdventureDialog(AdventureMap map, ArrayList<AdventureMap> adventures) {

		selectedRoom = map.getStart();
		txtRoomName = new TextField();
		txtRoomDescription = new TextArea();

		// map
		VBox vBoxLeft = new VBox(BUTTON_SPACING);
		this.tempMap = new AdventureMap(map);
		this.mapColumns = new HBox();
		this.mapColumns.setPadding(new Insets(BUTTON_SPACING));
		this.mapColumns.setSpacing(BUTTON_SPACING);
		this.setTitle("Adventure bearbeiten");
		Label lblTitle = new Label("Titel des Abenteuers:");
		TextField txtAdventureName = new TextField(map.getName());
		updateGUI();
		ScrollPane scrollPane = new ScrollPane(mapColumns);
		scrollPane.setPrefSize(600, 750);
		Button btnSave = new Button("Speichern");
		btnSave.setOnAction(e -> {
			tempMap.setName(txtAdventureName.getText());
			adventures.remove(map);
			adventures.add(tempMap);
			Main.saveAdventures();
			if (!map.getName().equals(tempMap.getName()))
				Main.deleteAdventure(map);
			close();
		});
		vBoxLeft.getChildren().addAll(lblTitle, txtAdventureName, scrollPane, btnSave);

		// RoomEdit
		Label lblRoomName = new Label("Name:");
		txtRoomName.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				selectedRoom.setName(newValue);
				updateGUI();
			}
		});
		Label lblRoomDescription = new Label("Beschreibung");
		txtRoomDescription.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				selectedRoom.setDescription(newValue);
				updateGUI();
			}
		});
		// EditItems
		cBoxItems = new ComboBox<>();
		TextField txtItemName = new TextField();

		// txtItemName.textProperty().addListener(new ChangeListener<String>() {
		// @Override
		// public void changed(ObservableValue<? extends String> observable, String
		// oldValue, String newValue) {
		// if(oldValue != null && oldValue != "")
		// selectedRoom.getItemWithName(oldValue).setName(newValue);
		//
		// cBoxItems.setItems(FXCollections.observableArrayList(selectedRoom.getItemNames()));
		// }
		// });
		TextArea txtItemDescription = new TextArea();
		// txtItemDescription.textProperty().addListener(new ChangeListener<String>() {
		// @Override
		// public void changed(ObservableValue<? extends String> observable, String
		// oldValue, String newValue) {
		// // TODO Auto-generated method stub
		// }
		// });
		Button btnApplyItem = new Button("Änderungen übernehmen");
		btnApplyItem.setOnAction(e -> {
			// TODO: implement error message when no existing item is selected
			selectedItem.setName(txtItemName.getText());
			selectedItem.setDescription(txtItemDescription.getText());
			cBoxItems.setItems(FXCollections.observableArrayList(selectedRoom.getItemNames()));

		});
		Button btnAddNewItem = new Button("Item hinzufügen");
		btnAddNewItem.setOnAction(e -> {
			selectedItem = new Item(txtItemName.getText(), txtItemDescription.getText());
			selectedRoom.getItems().add(selectedItem);
			cBoxItems.setItems(FXCollections.observableArrayList(selectedRoom.getItemNames()));
		});
		HBox hBoxItemEditButtons = new HBox(BUTTON_SPACING);
		hBoxItemEditButtons.getChildren().addAll(btnApplyItem, btnAddNewItem);

		VBox vBoxItem = new VBox(BUTTON_SPACING);
		vBoxItem.getChildren().addAll(cBoxItems, new Label("Name:"), txtItemName, new Label("Beschreibung:"),
				txtItemDescription, hBoxItemEditButtons);
		TitledPane paneItem = new TitledPane("Items", vBoxItem);
		cBoxItems.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (selectedRoom.getItemWithName(newValue) != null) {
					selectedItem = selectedRoom.getItemWithName(newValue);
					txtItemName.setText(selectedItem.getName());
					txtItemDescription.setText(selectedItem.getDescription());
				}
			}
		});

		// EditIObjects
		cBoxIObjects = new ComboBox<>();
		TextField txtIObjectName = new TextField();
		// TODO: implement logic!
		TextArea txtIObjectDescription = new TextArea();
		TextField txtObjectKeyName = new TextField();
		Button btnApplyIObject = new Button("Änderungen übernehmen");
		btnApplyIObject.setOnAction(e -> {
			// TODO: error message when no iObject is selected
			selectedIObject.setName(txtIObjectName.getText());
			selectedIObject.setDescription(txtIObjectDescription.getText());
			selectedIObject.setKeyItem(txtObjectKeyName.getText());
			cBoxIObjects.setItems(FXCollections.observableArrayList(selectedRoom.getIObjectNames()));
		});
		Button btnAddNewIObject = new Button("Objekt hinzufügen");
		btnAddNewIObject.setOnAction(e -> {
			// TODO: error message when no iObject is selected
			selectedIObject = new InteractiveObject(txtIObjectName.getText());
			selectedIObject.setDescription(txtIObjectDescription.getText());
			selectedIObject.setKeyItem(txtObjectKeyName.getText());
			selectedRoom.getInteractiveObjects().add(selectedIObject);
			cBoxIObjects.setItems(FXCollections.observableArrayList(selectedRoom.getIObjectNames()));
		});
		HBox hBoxIObjectEditButtons = new HBox(BUTTON_SPACING);
		hBoxIObjectEditButtons.getChildren().addAll(btnApplyIObject, btnAddNewIObject);
		cBoxIObjects.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (selectedRoom.getIObjectWithName(newValue) != null) {
					selectedIObject = selectedRoom.getIObjectWithName(newValue);
					txtIObjectName.setText(selectedIObject.getName());
					txtIObjectDescription.setText(selectedIObject.getDescription());
					txtObjectKeyName.setText(selectedIObject.getKeyItem());
				}
			}
		});

		VBox vBox = new VBox(BUTTON_SPACING);
		vBox.getChildren().addAll(cBoxIObjects, new Label("Name:"), txtIObjectName, new Label("Beschreibung"),
				txtIObjectDescription, new Label("Schlüssel-Item:"), txtObjectKeyName, hBoxIObjectEditButtons);
		TitledPane paneIObject = new TitledPane("Interagierbare Objekte", vBox);
		Accordion acc = new Accordion(paneItem, paneIObject);

		VBox vBoxRight = new VBox(BUTTON_SPACING);
		vBoxRight.setPrefWidth(250);
		vBoxRight.getChildren().addAll(lblRoomName, txtRoomName, lblRoomDescription, txtRoomDescription, acc);

		HBox hBox = new HBox(BUTTON_SPACING);
		hBox.getChildren().addAll(vBoxLeft, vBoxRight);
		this.setResizable(true);
		this.setWidth(1200);
		this.setHeight(800);
		this.getDialogPane().setContent(hBox);

		this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

	}

	private void updateGUI() {
		mapColumns.getChildren().clear();
		for (int i = 0; i < tempMap.getMap().size(); ++i) {
			int columnsIndex = i;
			VBox column = new VBox();
			column.setSpacing(10);
			for (int j = 0; j < tempMap.getMap().get(i).size(); ++j) {
				int rowIndex = j;
				Room room = tempMap.getRoomAt(i, j);
				Button button = new Button(room.getName());
				button.setPrefWidth(BUTTON_SIZE);
				button.setPrefHeight(BUTTON_SIZE);
				button.setOnAction(e -> {
					selectedRoom = room;
					txtRoomName.setText(room.getName());
					txtRoomDescription.setText(room.getDescription());
					cBoxItems.setItems(FXCollections.observableArrayList(selectedRoom.getItemNames()));
					cBoxIObjects.setItems(FXCollections.observableArrayList(selectedRoom.getIObjectNames()));
					// TODO: Test
					// for(String s : selectedRoom.getItemNames())
					// System.out.println(s);
					// System.out.println(selectedRoom.getItemNames().size());

					// EditRoomDialog ed = new EditRoomDialog(room);
					// ed.showAndWait();
					button.setText(room.getName());
					tempMap.setRoomAt(columnsIndex, rowIndex, room);
					updateGUI();
				});
				if (!room.getName().equals("<leer>"))
					button.setTextFill(Color.BLUE);
				column.getChildren().add(button);
			}
			mapColumns.getChildren().add(column);
		}
	}

}
