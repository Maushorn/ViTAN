package application;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class EditAdventureDialog extends Dialog<ButtonType> {

	public EditAdventureDialog() {
		this.setTitle("Adventure bearbeiten");
		
		this.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
	}
	
}
