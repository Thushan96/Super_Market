package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminItemsFormController {

    public Button btnNewItem;
    public Button btnModify;
    public Button btnRemoveItems;
    public Button btnBack;

    public void newItemOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/NewItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void ModifyItemOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/ModifyItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnModify.getScene().getWindow();
        window.setScene(new Scene(load));

    }

    public void removeItemsOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/RemoveItems.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnRemoveItems.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void BackOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/AdminMainForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(load));
    }


}
