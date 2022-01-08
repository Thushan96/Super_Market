package Controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.OrderDetail;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MostMovableItemFormController {
    public TableColumn<Object, Object> ColItemCode;
    public TableColumn<Object, Object> ColSoldQuantity;
    public TableColumn<Object, Object> ColrevenueEarned;
    public TableColumn<Object, Object> ColDiscount;
    public TableView<OrderDetail> tblItem;
    public Button btnBack;
    ArrayList<OrderDetail> orderDetail=new ArrayList<>();
    int index=0;

    public void initialize() throws SQLException, ClassNotFoundException {
        ColItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        ColSoldQuantity.setCellValueFactory(new PropertyValueFactory<>("OrderQty"));
        ColrevenueEarned.setCellValueFactory(new PropertyValueFactory<>("Price"));
        ColDiscount.setCellValueFactory(new PropertyValueFactory<>("Discount"));

        getData();

    }

    ObservableList<OrderDetail> tmObservableList= FXCollections.observableArrayList();
    public void getData() throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM OrderDetail").executeQuery();
        while (rst.next()){
            orderDetail.add(new OrderDetail(rst.getString(1),rst.getString(2),
                    rst.getInt(3),rst.getInt(4),rst.getDouble(5)));
        }



        for (OrderDetail dtl:orderDetail) {

            if(isExists(dtl)==-1){
                tmObservableList.add(dtl);
            }else{
                index=isExists(dtl);
                OrderDetail temp=tmObservableList.get(index);
                OrderDetail newTm=new OrderDetail(temp.getOrderId(),temp.getItemCode(),temp.getOrderQty()+dtl.getOrderQty(),
                        temp.getPrice()*temp.getOrderQty(),temp.getDiscount()+dtl.getDiscount());

                tmObservableList.remove(index);
                tmObservableList.add(newTm);

                tmObservableList.sort(new Comparator<OrderDetail>() {
                    @Override
                    public int compare(OrderDetail o1, OrderDetail o2) {
                        return Integer.compare(o2.getOrderQty(), o1.getOrderQty());
                    }
                });

            }
        }
        tblItem.setItems(tmObservableList);

    }



    private int isExists(OrderDetail dtl) {

        for (int i = 0; i <tmObservableList.size();i++) {
            if(dtl.getItemCode().equals(tmObservableList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }


    public void BackOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/AdminReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
