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
import tm.CustomerWiseTm;

import javax.jws.Oneway;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class CustomerWiseFormController {
    public Button btnBack;
    public TableView<CustomerWiseTm> tblCustomer;
    public TableColumn ColName;
    public TableColumn ColId;
    public TableColumn ColIncome;

    ArrayList<CustomerWiseTm> customerWiseTmArrayList=new ArrayList<>();
    ArrayList<CustomerWiseTm> arrayList2=new ArrayList<>();
    ObservableList<CustomerWiseTm> ObList= FXCollections.observableArrayList();

    public void initialize(){

        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColId.setCellValueFactory(new PropertyValueFactory<>("CustId"));
        ColIncome.setCellValueFactory(new PropertyValueFactory<>("cost"));

        try {
            loadData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    ArrayList<CustomerWiseTm> arrayListReturned=null;
    int index;
    int secondindex;

    private void loadData() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT CustId,Cost FROM Orders").executeQuery();
        while (rst.next()){
            customerWiseTmArrayList.add(new CustomerWiseTm(rst.getString(1),rst.getDouble(2)));
        }
         arrayListReturned=getName();

        for (CustomerWiseTm tm:customerWiseTmArrayList) {
            if(ifExists(tm)==-1){

            }else{
                index=ifExists(tm);
                tm.setName(arrayListReturned.get(index).getName());
            }
        }
        load();


    }

    private void load(){
        for (CustomerWiseTm temp:customerWiseTmArrayList) {
            if(ifExists2(temp)==-1){
                ObList.add(temp);
            }else{
                secondindex=ifExists2(temp);
                CustomerWiseTm teWiseTm=ObList.get(secondindex);
                CustomerWiseTm newTM= new CustomerWiseTm(temp.getName(),temp.getCustId(),temp.getCost()+teWiseTm.getCost());
                ObList.remove(index);
                ObList.add(newTM);
            }
            ObList.sort(new Comparator<CustomerWiseTm>() {
                @Override
                public int compare(CustomerWiseTm o1, CustomerWiseTm o2) {
                    return Double.compare(o2.getCost(),o1.getCost());
                }
            });
        }
        tblCustomer.setItems(ObList);

    }

    private int ifExists2(CustomerWiseTm temp) {
        for (int i = 0; i <ObList.size() ; i++) {
            if(temp.getCustId().equals(ObList.get(i).getCustId())){
                return i;
            }
        }
        return -1;
    }


    private int ifExists(CustomerWiseTm tm) {
        for (int i = 0; i <arrayListReturned.size() ; i++) {
            if(tm.getCustId().equals(arrayListReturned.get(i).getCustId())){
                return i;
            }
        }
        return -1;
    }

    public ArrayList<CustomerWiseTm> getName() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance().getConnection().prepareStatement("SELECT CustId,CustName FROM Customer").executeQuery();
        while (resultSet.next()){
            arrayList2.add(new CustomerWiseTm(resultSet.getString(1),resultSet.getString(2)));
        }

        return arrayList2;
    }

    public void BackOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("../View/AdminReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) btnBack.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
