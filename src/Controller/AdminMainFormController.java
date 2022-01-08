package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminMainFormController {

    public Button btnManage;
    public Button btnReports;
    public Button btnBack;

    public void MangeItemsOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/AdminItemsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnManage.getScene().getWindow();
        window.setScene(new Scene(load));

    }

    public void ReportsOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/AdminReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnManage.getScene().getWindow();
        window.setScene(new Scene(load));

    }

    public void BackOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/logInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnManage.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
