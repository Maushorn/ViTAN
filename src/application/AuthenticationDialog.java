package application;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

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

        Button saveBtn = new Button("Übernehmen");
        saveBtn.setOnAction(e -> {
            AuthenticationInfo authInfo = new AuthenticationInfo(
                    consumerKeyTxt.getText(),
                    consumerSecretTxt.getText(),
                    accessTokenTxt.getText(),
                    accessTokenSecretTxt.getText());
            FileOutputStream fos;
            ObjectOutputStream os;
            try {
                fos = new FileOutputStream(".\\ConfigData.ser");
                os = new ObjectOutputStream(fos);
                os.writeObject(authInfo);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        vBox.getChildren().addAll(
                consumerKeyHBox,
                consumerSecretHBox,
                accessTokenHBox,
                accessTokenSecretHbox,
                saveBtn
        );

        this.getDialogPane().setContent(vBox);
        this.setOnCloseRequest(e -> {

        });
        this.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
    }

}
