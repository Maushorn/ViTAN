package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import textadventure.Room;

public class EditRoomDialog extends Dialog {

	public EditRoomDialog(Room room) {
		
		if(room.getName().equals("<leer>"))
			this.setTitle("leeren Raum bearbeiten");
		else
			this.setTitle("Raum \"" + room.getName() + "\" bearbeiten");
		
		VBox vBox = new VBox(10);
		TextField txtName = new TextField(room.getName());
		txtName.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				setTitle("Raum \"" + newValue + "\" bearbeiten");
				room.setName(newValue);
			}

			
		});
		TextField txtDescription = new TextField(room.getDescription());
		vBox.getChildren().addAll(new Label("Name"),txtName, new Label("Raumbeschreibung"), txtDescription);
		
		this.getDialogPane().setContent(vBox);
		this.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
		
	}
	
}
