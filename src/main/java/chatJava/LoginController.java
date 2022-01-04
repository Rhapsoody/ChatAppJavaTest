package chatJava;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    /*------------------------------------------------------------
    * Binding the form elements with the class
    * -----------------------------------------------------------*/
    @FXML
    private TextField usernameFieldL;

    @FXML
    private PasswordField passwordFieldL;

    @FXML
    private Button loginBtn;

    @FXML
    private Button homeFromLogin;

    @FXML
    private Button quitFromLogin;

    @FXML
    private Button signFromLogin;

    /*------------------------------------------------------------
     * Preparing variables to change the scene after login
     * -----------------------------------------------------------*/
    private Stage stage;
    private Scene scene;
    private static Parent root;

    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {
        Window windowOwner = loginBtn.getScene().getWindow();

        System.out.println(usernameFieldL.getText());
        System.out.println(passwordFieldL.getText());

        String usernameL = usernameFieldL.getText();
        String passwordL = passwordFieldL.getText();

        if (usernameL.isEmpty()){
            displayAlert(Alert.AlertType.ERROR, windowOwner, "Input Error", "Please enter a username !");
            return;
        }

        if (passwordL.isEmpty()){
            displayAlert(Alert.AlertType.ERROR, windowOwner, "Input Error", "Please enter a password !");
            return;
        }

        /*----------------------------------------------------
        * HERE WE SHOULD WRITE A METHOD TO VERIFY IF THE FIELDS
        * ARE FILLED WITH EXISTING USERNAME ANS PASSWORD
        * --------------------------------------------------*/

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chat-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    /*-----------------------------------------------------------
     *Method for displaying an alert message if a field is not
     * filled correctly
     *------------------------------------------------------------*/
    private static void displayAlert(Alert.AlertType alertType, Window windowOwner, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(windowOwner);
        alert.show();
    }

    @FXML
    public void loginToHome(ActionEvent event) throws IOException{
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/home.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Chat | Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loginToSign(ActionEvent event) throws IOException{
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/signup_form.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void quitFromLogin(ActionEvent event){
        Platform.exit();
    }
}
