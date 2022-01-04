package chatJava;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegisterController {

    @FXML
    private TextField usernameFieldR;

    @FXML
    private PasswordField passwordFieldR;

    @FXML
    private Button signUpBtn;

    @FXML
    private Button signUpToHome;

    @FXML
    private Button quitFromRegister;



    /*------------------------------------------------------------
     * Preparing variables to change the scene after login
     * -----------------------------------------------------------*/
    private Stage stage;
    private Scene scene;
    private static Parent root;

    /*--------------------------------------------------------
    Register Method to collect data from the register form
    --------------------------------------------------------*/
    @FXML
    public void register(ActionEvent event) throws SQLException {

        Window windowOwner = signUpBtn.getScene().getWindow();



        /*--------------------------------------------------------
        *Retrieve the content of the fields
        --------------------------------------------------------*/
        System.out.println(usernameFieldR.getText());
        System.out.println(passwordFieldR.getText());

        String usernameR = usernameFieldR.getText();
        String passwordR = passwordFieldR.getText();


        /*if the username field is empty => error displayed*/
        if (usernameR.isEmpty()){
            displayAlert(Alert.AlertType.ERROR, windowOwner, "Input Error", "Please enter a username !");
            return;
        }

        /*if the password field is empty => error displayed*/
        if (passwordR.isEmpty()){
            displayAlert(Alert.AlertType.ERROR, windowOwner, "Input Error", "Please enter a password !");
            return;
        }

        /*
        * IN THIS PART THE DATA RETRIEVED FROM THE FIELDS MUST BE INSERTED IN THE DATABASE
        * */


        displayAlert(AlertType.CONFIRMATION, windowOwner, "Registration Successful", "Welcome" + usernameR);

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
    public void signUpToHome(ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/home.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Chat | Sign Up");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void signUpToLog(ActionEvent event) throws IOException{
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/login_form.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void quitFromRegister(ActionEvent event){
        Platform.exit();
    }
}
