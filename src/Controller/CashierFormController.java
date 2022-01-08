package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CashierFormController {
    public Button backBtn;

    public void addNewCustomerOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/addNewCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) backBtn.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void manageOrdersOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/CashierMainForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) backBtn.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void backBtnOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/loginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) backBtn.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
