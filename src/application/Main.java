package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import service.DatabaseService;
import textadventure.AdventureMap;
import textadventure.Item;
import textadventure.Room;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {

	static ArrayList<AdventureMap> adventures;
	private AdventureMap selectedAdventure;
	private ObservableList<String> adventureNameList;
	private ArrayList<String> adventureNames;
	private ListView<String> adventureList;

	@Override
	public void start(Stage primaryStage) {
		try {

			adventures = new ArrayList<>();
			adventureNames = new ArrayList<>();			
			loadAdventures();

			// BorderPane root = new BorderPane();
			VBox root = new VBox(10);
			root.setPadding(new Insets(20));
			Label lbl1 = new Label("Adventure auswählen");
			adventureList = new ListView<>();
			updateAdventureList();
			Button btnNewAdventure = new Button("Neu");
			btnNewAdventure.setOnAction(e -> {
				EditAdventureDialog ed = new EditAdventureDialog(new AdventureMap("Neues Adventure"), adventures);
				ed.showAndWait();
				updateAdventureList();
			});
			Button btnEditAdventure = new Button("Bearbeiten...");
			btnEditAdventure.setDisable(true);
			btnEditAdventure.setOnAction(e -> {
				EditAdventureDialog ed = new EditAdventureDialog(selectedAdventure, adventures);
				ed.showAndWait();
				updateAdventureList();
			});

			// TODO: implement removing of serialized files
			Button btnDeleteAdventure = new Button("Löschen");
			btnDeleteAdventure.setOnAction(e -> {
				adventures.remove(selectedAdventure);
				updateAdventureList();
				deleteAdventure(selectedAdventure);
			});

			Button btnStartTest = new Button("Test-Modus starten...");
			btnStartTest.setOnAction(e -> {
				TestDialog td = new TestDialog(new AdventureMap(selectedAdventure));
				td.showAndWait();
			});

			Button btnStartOnline = new Button("Online-Modus starten...");
			// TODO: implement!
			btnStartOnline.setOnAction(e -> {
				OnlineDialog od = new OnlineDialog(selectedAdventure);
				od.showAndWait();
			});

			adventureList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					for (AdventureMap am : adventures) {
						if (am.getName().equals(newValue))
							selectedAdventure = am;
						btnEditAdventure.setDisable(false);
					}
				}
			});

			primaryStage.setOnCloseRequest(e -> {
				saveAdventures();
			});

			VBox vbEditButtons = new VBox(10);
			vbEditButtons.getChildren().addAll(btnNewAdventure, btnEditAdventure, btnDeleteAdventure);
			HBox hbAdventure = new HBox(10);
			hbAdventure.getChildren().addAll(adventureList, vbEditButtons);
			root.getChildren().addAll(lbl1, hbAdventure, btnStartTest, btnStartOnline);
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateAdventureList() {
		ArrayList<String> adventureNames = new ArrayList<>();
		for (AdventureMap am : adventures)
			adventureNames.add(am.getName());
		adventureNameList = FXCollections.observableArrayList(adventureNames);
		adventureList.setItems(adventureNameList);
	}

	public static void saveAdventures() {
		ObjectOutputStream oos;
		FileOutputStream fos;
		for (AdventureMap am : adventures) {
			try {
				fos = new FileOutputStream(".\\Adventures\\" + am.getName() + ".ser");
				oos = new ObjectOutputStream(fos);
				am.prepareForSerialization();
				oos.writeObject(am);
				oos.close();
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**Deletes specific AdventureMap that are serialized in Adventures folder.
	 * 
	 * @param am
	 */
	public static void deleteAdventure(AdventureMap am) {
		try {
			Files.deleteIfExists(Paths.get(".\\Adventures\\" + am.getName() + ".ser"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**Loads all saved AdventureMaps from .ser-file, deserializes them and puts them into adventures-List.
	 * 
	 */
	public static void loadAdventures() {
		FileInputStream fis;
		ObjectInputStream ois;
		Path dir;
		DirectoryStream<Path> ds;
		try {
			dir = Paths.get(".\\Adventures");
			ds = Files.newDirectoryStream(dir, "*.ser");
			for (Path p : ds) {
				// System.out.println(p.getFileName());
				fis = new FileInputStream(".\\Adventures\\" + p.getFileName());
				ois = new ObjectInputStream(fis);
				AdventureMap am = (AdventureMap) ois.readObject();
				am.initAfterDeserialization();
				adventures.add(am);
				ois.close();
				fis.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
