package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import metainformation.ItemInfo;
import metainformation.PositionInfo;
import service.DatabaseService;

/**This shows tables of Player information, that are used to save the current state of each individual player.
 *
 */
public class PlayerInfoDialog extends Dialog {


    public PlayerInfoDialog(){
        this.setTitle("Spielerdaten");
        this.getDialogPane().setPrefWidth(500);

        ObservableList<PositionInfo> positionData = DatabaseService.getPositionData();
        TableView<PositionInfo> locationTable = new TableView<>(positionData);
        locationTable.setPrefHeight(300);
        TableColumn<PositionInfo, Long> userIdCol = new TableColumn<>("UserID");
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        TableColumn<PositionInfo, String> positionCol = new TableColumn<>("Position");
        positionCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        TableColumn<PositionInfo, String> adventureCol = new TableColumn<>("Adventure");
        adventureCol.setCellValueFactory(new PropertyValueFactory<>("adventure"));
        locationTable.getColumns().addAll(userIdCol, positionCol, adventureCol);

        Button deleteLocationEntryButton = new Button("Markierten Eintrag entfernen");
        deleteLocationEntryButton.setOnAction(e -> {
            PositionInfo positionInfo = locationTable.getSelectionModel().getSelectedItem();
            DatabaseService.deletePositionEntry(positionInfo);
            positionData.remove(positionInfo);
        });

        ObservableList<ItemInfo> itemData = DatabaseService.getItemData();
        TableView<ItemInfo> itemTable = new TableView<>(itemData);
        itemTable.setPrefHeight(300);
        TableColumn<ItemInfo, Long> userIdCol2 = new TableColumn<>("UserID");
        userIdCol2.setCellValueFactory((new PropertyValueFactory<>("userId")));
        TableColumn<ItemInfo, String> itemCol = new TableColumn<>("Item");
        itemCol.setCellValueFactory(new PropertyValueFactory<>("item"));
        TableColumn<ItemInfo, String> adventureCol2 = new TableColumn<>("Adventure");
        adventureCol2.setCellValueFactory(new PropertyValueFactory<>("adventure"));
        itemTable.getColumns().addAll(userIdCol2, itemCol, adventureCol2);

        Button deleteItemEntryButton = new Button("Markierten Eintrag entfernen");
        deleteItemEntryButton.setOnAction(e -> {
            ItemInfo itemInfo = itemTable.getSelectionModel().getSelectedItem();
            DatabaseService.deleteItemEntry(itemInfo);
            itemData.remove(itemInfo);
        });


        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(
                new Label("Position:"),
                locationTable,
                deleteLocationEntryButton,
                new Label("Inventar:"),
                itemTable,
                deleteItemEntryButton
        );

        this.getDialogPane().setContent(vBox);
        this.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
    }

}
