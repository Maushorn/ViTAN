package application;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.nio.file.Paths;

/**This dialog allows setting the authentification data for connecting with a account.
 *
 */
public class AuthenticationDialog extends Dialog {

    private static final int LABEL_WIDTH = 160;
    private static final String INVALID_AUTHENTICATION = "Keine g\u00FCltigen Authentifizierungsdaten vorhanden.";

    public AuthenticationDialog(){

        this.setTitle("Authentifizierung");
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

        AuthenticationInfo info = loadAuthenticationInfo();

        TextField consumerKeyTxt = new TextField();
        TextField consumerSecretTxt = new TextField();
        TextField accessTokenTxt = new TextField();
        TextField accessTokenSecretTxt = new TextField();
        if(info != null){
            consumerKeyTxt.setText(info.getCONSUMER_KEY());
            consumerSecretTxt.setText(info.getCONSUMER_SECRET());
            accessTokenTxt.setText(info.getACCESS_TOKEN());
            accessTokenSecretTxt.setText(info.getACCESS_TOKEN_SECRET());
        }else{
            consumerKeyTxt.setPromptText(INVALID_AUTHENTICATION);
            consumerSecretTxt.setPromptText(INVALID_AUTHENTICATION);
            accessTokenTxt.setPromptText(INVALID_AUTHENTICATION);
            accessTokenSecretTxt.setPromptText(INVALID_AUTHENTICATION);
        }

        consumerKeyTxt.setPrefWidth(500);
        consumerSecretTxt.setPrefWidth(500);
        accessTokenTxt.setPrefWidth(500);
        accessTokenSecretTxt.setPrefWidth(500);

        consumerKeyHBox.getChildren().addAll(consumerKeyLabel, consumerKeyTxt);
        consumerSecretHBox.getChildren().addAll(consumerSecretLabel, consumerSecretTxt);
        accessTokenHBox.getChildren().addAll(accessTokenLabel, accessTokenTxt);
        accessTokenSecretHbox.getChildren().addAll(accessTokenSecretLabel, accessTokenSecretTxt);

        Button saveBtn = new Button("\u00DCbernehmen");
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
        this.getDialogPane().setPrefWidth(700);
        this.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
    }

    /**Loads the necessary information for acting in behalf of a Twitter-Account from a serialized file.
     *
     * @return
     */
    private AuthenticationInfo loadAuthenticationInfo() {
        FileInputStream fis;
        ObjectInputStream ois;
        AuthenticationInfo info = null;
        try {
            fis = new FileInputStream(".\\ConfigData.ser");
            ois = new ObjectInputStream(fis);
            info = (AuthenticationInfo)ois.readObject();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }
        return info;
    }

}
