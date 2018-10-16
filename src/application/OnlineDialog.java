package application;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import service.OnlineThread;

public class OnlineDialog extends Dialog {

	public OnlineDialog() {
		this.setTitle("Online-Modus");
		
		
		VBox vBox = new VBox(20);
		Label lblText = new Label("Connected to Twitter.");
		vBox.getChildren().add(lblText);
		
		OnlineThread online = new OnlineThread();
		Thread thread = new Thread(online);
		thread.setDaemon(true);
		thread.start();
		this.setOnCloseRequest(e -> online.setEndLoop(true));
		this.getDialogPane().setContent(vBox);
		this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
	}
	
}
