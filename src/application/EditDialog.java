package application;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class EditDialog extends Dialog<ButtonType> {

	public EditDialog() {
		this.setTitle("Adventure bearbeiten");
		
		this.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
	}
	
}
