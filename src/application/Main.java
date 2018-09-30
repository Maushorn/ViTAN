package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import textadventure.AdventureMap;
import textadventure.Room;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	
	private ArrayList<AdventureMap> adventures;
	private AdventureMap selectedAdventure;
	
	@Override
	public void start(Stage primaryStage) {
		try {

			//TODO: placeholder AdventureMap
			AdventureMap testMap = new AdventureMap("TestAdventure");
			adventures = new ArrayList<>();
			adventures.add(testMap);
			adventures.add(new AdventureMap("Test 2"));
			
//			BorderPane root = new BorderPane();
			VBox root = new VBox(10);
			root.setPadding(new Insets(20));
			Label lbl1 = new Label("Adventure auswählen");
			ListView<String> adventureList = new ListView<>();
			ObservableList<String> adventureNames = FXCollections.observableArrayList(testMap.getName());
			adventureList.setItems(adventureNames);
			
			Button btnNewAdventure = new Button("neues Adventure anlegen...");
			btnNewAdventure.setOnAction(e -> {
				EditAdventureDialog ed = new EditAdventureDialog(new AdventureMap("Neues Adventure"), adventures);
				ed.showAndWait();
			});
			Button btnEditAdventure = new Button("ausgewähltes Adventure bearbeiten...");
			btnEditAdventure.setDisable(true);
			btnEditAdventure.setOnAction(e -> {
				EditAdventureDialog ed = new EditAdventureDialog(selectedAdventure, adventures);
				ed.showAndWait();
			});
			
			Button btnStartTest = new Button("Test-Modus starten...");
			btnStartTest.setOnAction(e -> {
				//TODO: implement!
			});
			
			Button btnStartOnline = new Button("Online-Modus starten...");
			//TODO: implement!
	
			adventureList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					for(AdventureMap am : adventures) {
						if(am.getName().equals(newValue))
							selectedAdventure = am;
						btnEditAdventure.setDisable(false);
					}
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
