package application;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AuthenticationDialog extends Dialog {

    public static final int LABEL_WIDTH = 160;

    public AuthenticationDialog(){

        HBox consumerKeyHBox = new HBox();
        HBox consumerSecretHBox = new HBox();
        HBox accessTokenHBox = new HBox();
        HBox accessTokenSecretHbox = new HBox();
        VBox vBox = new VBox(10);

        Label consumerKeyLabel = new Label("Consumer Key:");
        consumerKeyLabel.setPrefWidth(LABEL_WIDTH);
        Label consumerSecretLabel = new Label("Consumer Secret:");
        consumerSecretLabel.setPrefWidth(LABEL_WIDTH);
        Label accessTokenLabel = new Label("Access Token:");
        accessTokenLabel.setPrefWidth(LABEL_WIDTH);
        Label accessTokenSecretLabel = new Label("Access Token Secret:");
        accessTokenSecretLabel.setPrefWidth(LABEL_WIDTH);

        TextField consumerKeyTxt = new TextField();
        TextField consumerSecretTxt = new TextField();
        TextField accessTokenTxt = new TextField();
        TextField accessTokenSecretTxt = new TextField();

        consumerKeyHBox.getChildren().addAll(consumerKeyLabel, consumerKeyTxt);
        consumerSecretHBox.getChildren().addAll(consumerSecretLabel, consumerSecretTxt);
        accessTokenHBox.getChildren().addAll(accessTokenLabel, accessTokenTxt);
        accessTokenSecretHbox.getChildren().addAll(accessTokenSecretLabel, accessTokenSecretTxt);
        vBox.getChildren().addAll(
                consumerKeyHBox,
                consumerSecretHBox,
                accessTokenHBox,
                accessTokenSecretHbox
        );


        this.getDialogPane().setContent(vBox);
        this.setOnCloseRequest(e -> {

        });
        this.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
    }

}
