package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LogInFormController {
    public TextField txtUsername;
    public TextField txtPassword;
    public Button btnLogin;


    public void loginButtonAction(ActionEvent event) throws IOException {
        if(txtUsername.getText().equalsIgnoreCase("admin") && txtPassword.getText().equalsIgnoreCase("1234")) {
            URL resource = getClass().getResource("../View/AdminMainForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) btnLogin.getScene().getWindow();
            window.setScene(new Scene(load));
        }else if(txtUsername.getText().equalsIgnoreCase("user") && txtPassword.getText().equalsIgnoreCase("1234")){
            URL resource = getClass().getResource("../View/CashierForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) btnLogin.getScene().getWindow();
            window.setScene(new Scene(load));
        }else{
            new Alert(Alert.AlertType.ERROR,"Incorrect Username Or Password.Try Again").show();
        }
    }
}
