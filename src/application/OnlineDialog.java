package application;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import service.AuthenticationException;
import service.OnlineThread;
import textadventure.AdventureMap;

/**
 * @deprecated This is now implemented in the Main-Menu.
 */
public class OnlineDialog extends Dialog {

	public OnlineDialog(AdventureMap am) throws AuthenticationException {
		this.setTitle("Online-Modus");
		
		
		VBox vBox = new VBox(20);
		Label lblText = new Label("Mit Twitter verbunden.");
		vBox.getChildren().add(lblText);

		OnlineThread online = new OnlineThread(am);
		Thread thread = new Thread(online);
		thread.setDaemon(true);
		thread.start();
		this.setOnCloseRequest(e -> online.setEndLoop(true));
		this.getDialogPane().setContent(vBox);
		this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
	}
	
}
