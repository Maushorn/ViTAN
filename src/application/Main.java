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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.AuthenticationException;
import service.DatabaseService;
import service.OnlineThread;
import service.TwitterService;
import textadventure.AdventureMap;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {

	static ArrayList<AdventureMap> adventures;
	private AdventureMap selectedAdventure;
	private ObservableList<String> adventureNameList;
	private ArrayList<String> adventureNames;
	private ListView<String> adventureList;
	private Boolean online;
	private Label lblOnline;
	private OnlineThread onlineThread;

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.getIcons().add(new Image(Paths.get(".\\Resources\\Icon.png").toUri().toString()));
//			uncomment this if you want to delete all inventories
//			DatabaseService.resetAllInventories();
			onlineThread = null;
			adventures = new ArrayList<>();
			adventureNames = new ArrayList<>();			
			loadAdventures();

			MenuBar menuBar = new MenuBar();
			Menu edit = new Menu("_Edit");
			MenuItem authMI = new MenuItem("Authentifizierung bearbeiten...");
			edit.getItems().addAll(authMI);
			Menu info  = new Menu("_Info");
			MenuItem playerInfoMI = new MenuItem("Spielerdaten...");
			MenuItem helpMI = new MenuItem("Hilfe...");
			info.getItems().addAll(playerInfoMI, helpMI);
			menuBar.getMenus().addAll(edit, info);

			authMI.setOnAction(e -> {
				AuthenticationDialog ad = new AuthenticationDialog();
				ad.showAndWait();
			});

			playerInfoMI.setOnAction(e -> {
				PlayerInfoDialog pid = new PlayerInfoDialog();
				pid.showAndWait();
			});

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
			btnStartTest.setDisable(true);

			Button btnStartOnline = new Button("Online-Modus starten");
			online = false;
			btnStartOnline.setOnAction(e -> {
//				OnlineDialog od = new OnlineDialog(selectedAdventure);
//				od.showAndWait();
				if(!online){

					try {
						onlineThread = new OnlineThread(selectedAdventure);

					Thread thread = new Thread(onlineThread);
					thread.setDaemon(true);
					thread.start();
					online = true;
					lblOnline.setText("online");
					lblOnline.setTextFill(Color.GREEN);
					btnStartOnline.setText("Online-Modus beenden");
					} catch (AuthenticationException e1) {
						e1.printStackTrace();
						Alert alert = new Alert(Alert.AlertType.ERROR, e1.getMessage());
						alert.showAndWait();
					}
				}else{
					if(onlineThread != null)
						onlineThread.setEndLoop(true);
					lblOnline.setText("offline");
					lblOnline.setTextFill(Color.RED);
					online = false;
					btnStartOnline.setText("Online-Modus starten");
				}
			});
			btnStartOnline.setDisable(true);
			lblOnline = new Label("offline");
			lblOnline.setPadding(new Insets(5));
			lblOnline.setTextFill(Color.RED);
			HBox hBoxOnline = new HBox(20);
			hBoxOnline.getChildren().addAll(btnStartOnline, lblOnline);

			adventureList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					for (AdventureMap am : adventures) {
						if (am.getName().equals(newValue))
							selectedAdventure = am;
						btnEditAdventure.setDisable(false);
						btnStartTest.setDisable(false);
						btnStartOnline.setDisable(false);
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
			root.getChildren().addAll(
					lbl1,
					hbAdventure,
					btnStartTest,
					hBoxOnline
			);
			VBox parent = new VBox();
			parent.getChildren().addAll(menuBar, root);
			Scene scene = new Scene(parent, 400, 400);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**This updates the list of AdventureMaps and should be called every time an AdventureMap is created or deleted.
	 *
	 */
	private void updateAdventureList() {
		adventureNames = new ArrayList<>();
		for (AdventureMap am : adventures)
			adventureNames.add(am.getName());
		adventureNameList = FXCollections.observableArrayList(adventureNames);
		adventureList.setItems(adventureNameList);
	}

	/**Serializes all AdventureMaps and saves them.
	 *
	 */
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
