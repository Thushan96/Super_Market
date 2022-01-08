package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminReportFormController {
    public Button btnItemStatus;
    public Button btnleastMovable;
    public Button btnleastMv;
    public Button btnCwise;
    public Button btnBack;
    public Button btnDailyI;
    public Button btnMonthly;
    public Button btnYearly;

    public void ItemStatusOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/MostMovableItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnItemStatus.getScene().getWindow();
        window.setScene(new Scene(load));
    }


    public void leastMvOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/lowestMovableForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnleastMv.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void CustomerWiseIncomeOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/CustomerWiseForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnCwise.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void BackOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/AdminMainForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void DailyIncomeOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/DailyIncomeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnDailyI.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void MonthlyIncomeOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/MonthlyIncomeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnMonthly.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void YearlyIncomeOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/YeralyIncomeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnYearly.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
