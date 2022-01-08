package Controller;

import db.DbConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import model.ItemDetails;
import model.Order;
import tm.ListTM;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CashierMainFormController {
    public Label lblDate;
    public Label lblTime;
    public ComboBox<String> cmbIcode;
    public ComboBox<String> cmbCid;
    public TextField txtName;
    public TextField txtTittle;
    public TextField txtCity;
    public TextField txtQtyOnHand;
    public TextField txtUnitPrice;
    public TextField txtDicount;
    public TableView<ListTM> tblList;
    public TableColumn<Object, Object> colCode;
    public TableColumn<Object, Object> colDesc;
    public TableColumn<Object, Object> ColUnitPrice;
    public TableColumn<Object, Object> colDiscount;
    public TableColumn<Object, Object> ColDelete;
    public TableColumn<Object, Object> colQuantity;
    public TableColumn<Object, Object> ColTotal;
    public TextField txtDesc;
    public TextField txtQty;
    public Label txtTtl;
    public Label lblOrderId;
    public Button lblLogOUT;

    public void initialize(){

        colCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        ColUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        ColTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
        ColDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadDateAndTime();
        setOrderId();

        try {
            loadCustomerIds();
            loadItemCodes();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbCid.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            try {
                setCustomerData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } );
        cmbIcode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setOrderId() {
        try {
            lblOrderId.setText(new OrderController().getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemData(String newValue) throws SQLException, ClassNotFoundException {
        Item i=new ItemController().getCustomer(newValue);
        if(i==null){
            new Alert(Alert.AlertType.WARNING, "Empty Customer data");
        }else{
            txtDesc.setText(i.getDescription());
            txtUnitPrice.setText(i.getUnitPrice());
            txtQtyOnHand.setText(i.getQtyOnHand());
            txtDicount.setText(i.getDiscount());
        }
    }

    private void setCustomerData(String ID) throws SQLException, ClassNotFoundException {
        Customer c1=new CustomerController().getCustomer(ID);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Customer data");
        } else {
            txtName.setText(c1.getName());
            txtTittle.setText(c1.getTittle());
            txtCity.setText(c1.getCity());
        }
    }

    private void loadDateAndTime() {
        Date date=new Date();
        SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(d.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }
    public void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> ids=new CustomerController().getCustomerIds();
        cmbCid.getItems().addAll(ids);

    }
    public void loadItemCodes() throws SQLException, ClassNotFoundException {
        ArrayList<String> ItemCodes=new ItemController().getItemCodes();
        cmbIcode.getItems().addAll(ItemCodes);

    }


        ObservableList<ListTM> tmObservableList= FXCollections.observableArrayList();
    public void addToListOnAction(ActionEvent event) {
        if(txtQty.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please select Amount to purchase").show();
            return;
        }

        String description=txtDesc.getText();
        int qtyOnHnd=Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice=Double.parseDouble(txtUnitPrice.getText());
        double discountOfOne=Double.parseDouble(txtDicount.getText());
        int  QtyOfCustomer=Integer.parseInt(txtQty.getText());
        double total=(unitPrice-discountOfOne)*QtyOfCustomer;
        double discount=discountOfOne*QtyOfCustomer;

        if(qtyOnHnd<QtyOfCustomer){
            new Alert(Alert.AlertType.ERROR,"Not Enough Quantity for order").show();
            return;
        }

        Button btn= new Button("Delete");

        ListTM listTM=new ListTM(cmbIcode.getValue(),description,unitPrice,discount,QtyOfCustomer,total,btn);

        int rowNumber=ifExists(listTM);

        if (rowNumber==-1){
            tmObservableList.add(listTM);
        }else{
            ListTM TEMP=tmObservableList.get(rowNumber);
            ListTM newTm=new ListTM(TEMP.getItemCode(),TEMP.getDescription(),unitPrice,TEMP.getDiscount()+discount,
                    TEMP.getQuantity()+QtyOfCustomer,TEMP.getTotal()+total,btn);
            if(qtyOnHnd<TEMP.getQuantity()+QtyOfCustomer){
                new Alert(Alert.AlertType.ERROR,"Not Enough Quantity for order").show();
                return;
            }
            tmObservableList.remove(rowNumber);
            tmObservableList.add(newTm);
        }

        for (ListTM tm:tmObservableList) {
            btn.setOnAction((e)->{
                tmObservableList.remove(tm);
                calculateCost();
            });

        }

        tblList.setItems(tmObservableList);
        calculateCost();
        clear();
    }
    private int ifExists(ListTM tm){
        for (int i = 0; i <tmObservableList.size();i++) {
            if(tm.getItemCode().equals(tmObservableList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }
    void calculateCost(){
        double ttl=0;
        for (ListTM  tm:tmObservableList) {
            ttl+=tm.getTotal();
        }
        txtTtl.setText(ttl+" /=");
    }
    void clear(){
        txtDesc.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
        txtDicount.clear();
        txtQty.clear();
    }

    public void PlaceOrderOnAction(ActionEvent event) {
        double discount = 0;
        ArrayList<ItemDetails> itemDetails=new ArrayList<>();
        double ttl=0;
        
        for (ListTM tm:tmObservableList) {
            ttl+=tm.getTotal();
            discount=tm.getDiscount();
            itemDetails.add(new ItemDetails(tm.getItemCode(),tm.getUnitPrice(),tm.getQuantity(),discount));
        }
        
        Order order=new Order(lblOrderId.getText(),lblDate.getText(),cmbCid.getValue(),ttl,String.valueOf(discount),itemDetails);

        if (new OrderController().placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION,"Successful").show();
            setOrderId();
            tblList.getItems().clear();
            cmbCid.getSelectionModel().clearSelection();
            cmbIcode.getSelectionModel().clearSelection();
            txtName.clear();
            txtTittle.clear();
            txtCity.clear();

        }else{
            new Alert(Alert.AlertType.ERROR,"Try again").show();
        }
    }


    public void logOutOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/logInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) lblLogOUT.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
