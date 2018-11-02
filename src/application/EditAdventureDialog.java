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
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import textadventure.AdventureMap;
import textadventure.InteractiveObject;
import textadventure.Item;
import textadventure.Room;

/**This class allows creating and editing a Text-Adventure through a GUI.
 *
 */
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
    private Button selectedButton;

	static {
		BUTTON_SPACING = 10;
		BUTTON_SIZE = 80;
	}

    TextField txtItemName;
	TextArea txtItemDescription;
    TextField txtIObjectName;
    TextArea txtIObjectDescription;
    TextField txtObjectKeyName;
    TextField txtRewardName;
    TextArea txtRewardDescription;


	public EditAdventureDialog(AdventureMap map, ArrayList<AdventureMap> adventures) {

		selectedRoom = map.getStart();
		txtRoomName = new TextField();
		txtRoomDescription = new TextArea();

        txtItemName = new TextField();
        txtItemDescription = new TextArea();
        txtIObjectName = new TextField();
        txtIObjectDescription = new TextArea();
        txtObjectKeyName = new TextField();
        txtRewardName = new TextField();
        txtRewardDescription = new TextArea();

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


		Button btnApplyItem = new Button("�ndern");
		btnApplyItem.setOnAction(e -> {
			if(selectedItem == null){
				selectedItem = new Item(txtItemName.getText(), txtItemDescription.getText());
				selectedRoom.getItems().add(selectedItem);
			}else {
				selectedItem.setName(txtItemName.getText());
				selectedItem.setDescription(txtItemDescription.getText());
			}
			cBoxItems.setItems(FXCollections.observableArrayList(selectedRoom.getItemNames()));

		});
		Button btnAddNewItem = new Button("Neu hinzuf�gen");
		btnAddNewItem.setOnAction(e -> {
			selectedItem = new Item(txtItemName.getText(), txtItemDescription.getText());
			selectedRoom.getItems().add(selectedItem);
			cBoxItems.setItems(FXCollections.observableArrayList(selectedRoom.getItemNames()));
		});
		Button btnRemoveItem = new Button("L�schen");
		btnRemoveItem.setOnAction(e -> {
			if(selectedItem != null)
				selectedRoom.getItems().remove(selectedItem);
			cBoxItems.setItems(FXCollections.observableArrayList(selectedRoom.getItemNames()));

		});
		HBox hBoxItemEditButtons = new HBox(BUTTON_SPACING);
		hBoxItemEditButtons.getChildren().addAll(btnApplyItem, btnAddNewItem, btnRemoveItem);

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
		txtObjectKeyName.setPromptText("Name des Schl�ssels");
		txtRewardName.setPromptText("Name der Belohnung");
		txtRewardDescription.setPromptText("Beschreibung der Belohnung");
		Button btnApplyIObject = new Button("�ndern");
		btnApplyIObject.setOnAction(e -> {
			if(selectedIObject == null)
				selectedIObject = new InteractiveObject(txtIObjectName.getText());
			else selectedIObject.setName(txtIObjectName.getText());
			selectedIObject.setDescription(txtIObjectDescription.getText());
			selectedIObject.setKeyItem(txtObjectKeyName.getText());
			selectedIObject.setReward(new Item(txtRewardName.getText(), txtRewardDescription.getText()));
			cBoxIObjects.setItems(FXCollections.observableArrayList(selectedRoom.getIObjectNames()));
		});
		Button btnAddNewIObject = new Button("Neu hinzuf�gen");
		btnAddNewIObject.setOnAction(e -> {
			selectedIObject = new InteractiveObject(txtIObjectName.getText());
			selectedIObject.setDescription(txtIObjectDescription.getText());
			selectedIObject.setKeyItem(txtObjectKeyName.getText());
			selectedIObject.setReward(new Item(txtRewardName.getText(), txtRewardDescription.getText()));
			selectedRoom.getInteractiveObjects().add(selectedIObject);
			cBoxIObjects.setItems(FXCollections.observableArrayList(selectedRoom.getIObjectNames()));
		});
		Button btnRemoveIObject = new Button("L�schen");
		btnRemoveIObject.setOnAction(e -> {
			if(selectedIObject != null)
				selectedRoom.getInteractiveObjects().remove(selectedIObject);
			cBoxIObjects.setItems(FXCollections.observableArrayList(selectedRoom.getIObjectNames()));

		});
		HBox hBoxIObjectEditButtons = new HBox(BUTTON_SPACING);
		hBoxIObjectEditButtons.getChildren().addAll(btnApplyIObject, btnAddNewIObject, btnRemoveIObject);
		cBoxIObjects.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (selectedRoom.getIObjectWithName(newValue) != null) {
					selectedIObject = selectedRoom.getIObjectWithName(newValue);
					txtIObjectName.setText(selectedIObject.getName());
					txtIObjectDescription.setText(selectedIObject.getDescription());
					txtObjectKeyName.setText(selectedIObject.getKeyItem());
					if(selectedIObject.getReward() != null) {
						txtRewardName.setText(selectedIObject.getReward().getName());
						txtRewardDescription.setText(selectedIObject.getReward().getDescription());
						}
					else {
						txtRewardName.setText("");
						txtRewardDescription.setText("");
					}

				}
			}
		});

		VBox vBox = new VBox(BUTTON_SPACING);
		vBox.getChildren().addAll(
				cBoxIObjects,
				new Label("Name:"),
				txtIObjectName,
				new Label("Beschreibung:"),
				txtIObjectDescription,
				new Label("Schl�ssel-Item:"),
				txtObjectKeyName,
				new Label("Verstecktes Item:"),
				txtRewardName,
				txtRewardDescription,
				hBoxIObjectEditButtons);
		TitledPane paneIObject = new TitledPane("Interagierbare Objekte", vBox);
		Accordion acc = new Accordion(paneItem, paneIObject);

		VBox vBoxRight = new VBox(BUTTON_SPACING);
		vBoxRight.setPrefWidth(400);
		vBoxRight.getChildren().addAll(lblRoomName, txtRoomName, lblRoomDescription, txtRoomDescription, acc);

		HBox hBox = new HBox(BUTTON_SPACING);
		hBox.getChildren().addAll(vBoxLeft, vBoxRight);
		this.setResizable(true);
		this.setWidth(1300);
		this.setHeight(800);
		this.getDialogPane().setContent(hBox);
		this.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

	}

	/**This method updates the GUI and shall be called avery time an element of an AdventureMap(e.g. Adventure) is changed.
	 * For example when a Room is added this method adds a row or column to the grid of rooms.
	 */
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
					button.setText(room.getName());
					tempMap.setRoomAt(columnsIndex, rowIndex, room);
					txtItemName.setText("");
					txtItemDescription.setText("");
					txtIObjectName.setText("");
					txtIObjectDescription.setText("");
					txtObjectKeyName.setText("");
					txtRewardName.setText("");
					txtRewardDescription.setText("");
					updateGUI();
				});

				if(room == selectedRoom)
				    button.setEffect(new DropShadow(15., Color.BLUE));

				if (!room.getName().equals("<leer>"))
					button.setTextFill(Color.BLUE);
				column.getChildren().add(button);
			}
			mapColumns.getChildren().add(column);
		}
	}

}
