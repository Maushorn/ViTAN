package application;
	
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			BorderPane root = new BorderPane();
			VBox root = new VBox(10);
			root.setPadding(new Insets(20));
			
			Label lbl1 = new Label("Adventure auswählen");
			ListView<String> adventureList = new ListView<>();
			ObservableList<String> adventures = FXCollections.observableArrayList(
					"Adventure1",
					"Adventure2"
					);
			adventureList.setItems(adventures);
			
		
			
			Button btnNewAdventure = new Button("neues Adventure anlegen...");
			Button btnEditAdventure = new Button("ausgewähltes Adventure bearbeiten...");
			btnEditAdventure.setDisable(true);
			Button btnStartTest = new Button("Test-Modus starten...");
			Button btnStartOnline = new Button("Online-Modus starten...");
			
			adventureList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

					btnEditAdventure.setDisable(false);
					
				}
				
			});
			
			root.getChildren().addAll(lbl1, adventureList, btnNewAdventure, btnEditAdventure,btnStartTest, btnStartOnline);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
