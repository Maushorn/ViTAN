package application;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import metainformation.ItemInfo;
import metainformation.PositionInfo;
import service.DatabaseService;
import textadventure.Player;

public class PlayerInfoDialog extends Dialog {

    public PlayerInfoDialog(){

        this.setTitle("Spielerdaten");
        this.getDialogPane().setPrefWidth(500);

        TableView<PositionInfo> locationTable = new TableView<>(DatabaseService.getPositionData());
        TableColumn<PositionInfo, Long> userIdCol = new TableColumn<>("UserID");
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        TableColumn<PositionInfo, String> positionCol = new TableColumn<>("Position");
        positionCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        TableColumn<PositionInfo, String> adventureCol = new TableColumn<>("Adventure");
        adventureCol.setCellValueFactory(new PropertyValueFactory<>("adventure"));
        locationTable.getColumns().addAll(userIdCol, positionCol, adventureCol);

        TableView<ItemInfo> itemTable = new TableView<>(DatabaseService.getItemData());
        TableColumn<ItemInfo, Long> userIdCol2 = new TableColumn<>("UserID");
        userIdCol2.setCellValueFactory((new PropertyValueFactory<>("userId")));
        TableColumn<ItemInfo, String> itemCol = new TableColumn<>("Item");
        itemCol.setCellValueFactory(new PropertyValueFactory<>("item"));
        TableColumn<ItemInfo, String> adventureCol2 = new TableColumn<>("Adventure");
        adventureCol2.setCellValueFactory(new PropertyValueFactory<>("adventure"));
        itemTable.getColumns().addAll(userIdCol2, itemCol, adventureCol2);

        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(new Label("Position:"), locationTable, new Label("Inventar"), itemTable);

        this.getDialogPane().setContent(vBox);
        this.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
    }

}
