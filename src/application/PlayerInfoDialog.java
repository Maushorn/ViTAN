package application;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import metainformation.PositionInfo;
import textadventure.Player;

public class PlayerInfoDialog extends Dialog {

    public PlayerInfoDialog(){

        this.setTitle("Spielerdaten");

        TableView<PositionInfo> playerDataTable = new TableView<>();
        TableColumn<PositionInfo, Long> userIdCol = new TableColumn<>("UserID");


        this.getDialogPane().setContent(playerDataTable);
        this.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
    }

}
