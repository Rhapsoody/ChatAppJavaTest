package chatJava;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import network.Client;
import network.Server;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;



public class ClientController implements Initializable {

    @FXML
    private Button send_messageBtn;

    @FXML
    private TextField txtField_message;

    @FXML
    private ScrollPane message_scrollPane;

    @FXML
    private Button quitFromChat;

    @FXML
    private Pane message_pane;

    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try{
            client = new Client(new Socket("localhost", 1234));
        }catch(IOException e){
            e.printStackTrace();
        }
        message_pane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number refreshedValue) {
                message_scrollPane.setVvalue((Double) refreshedValue );
            }
        });

        /*Méthode pour recevoir des messages du serveur
        et les bind au Pane*/

        send_messageBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // retrieve the message written in the textfield
                String messageToSend = txtField_message.getText();
                /*if the textfield contains a message, we have to define the box
                in which it'll be seen on the screen*/
                if (!messageToSend.isEmpty()){
                    //creating the box for the message =
                    HBox hbox = new HBox();
                    hbox.setAlignment(Pos.CENTER_RIGHT);
                    hbox.setPadding(new Insets(5,5,5,10));

                    Text textSent = new Text(messageToSend);
                    TextFlow textFlow = new TextFlow(textSent);
                    //just styling the box for the message
                    textFlow.setStyle("-fx-background-radius: 15px" +
                            "-fx-color: rgb(255,255,255)" +
                            "-fx-background-color:  #a73ba8");

                    textFlow.setPadding(new Insets(5,10,5,10));
                    textSent.setFill(Color.color(0.934,0.945,0.996));

                    hbox.getChildren().add(textFlow);
                    message_pane.getChildren().add(hbox);

                    /*-----------------------------------------------------
                     *Here a method allowing to
                     *send message from client should be called
                     * client.sendMessageToServer(messageToSend) .......
                     * -----------------------------------------------------*/

                    txtField_message.clear();

                }
}
