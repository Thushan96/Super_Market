package Controller;

import db.DbConnection;
import model.ItemDetails;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {


    public boolean placeOrder(Order order){
        Connection con=null;
        try {
            con=DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm = con.prepareStatement("INSERT INTO Orders VALUES(?,?,?,?)");
            stm.setObject(1,order.getOrderId());
            stm.setObject(2,order.getOrderDate());
            stm.setObject(3,order.getCustId());
            stm.setObject(4,order.getCost());

            if (stm.executeUpdate()>0){

                if(SaveOrderDetail(order.getOrderId(),order.getItems(),order.getDiscount())){
                    con.commit();
                    return true;
                }else{
                    con.rollback();
                    return false;
                }

            }else{
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;

    }
    public boolean SaveOrderDetail(String orderId, ArrayList<ItemDetails> itemDetails,String discount) throws SQLException, ClassNotFoundException {
        for (ItemDetails temp:itemDetails) {
            PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement
                    ("INSERT INTO OrderDetail VALUES (?,?,?,?,?)");
            stm.setObject(1,orderId);
            stm.setObject(2,temp.getItemCode());
            stm.setObject(3,temp.getQtyForSell());
            stm.setObject(4,temp.getUnitPrice());
            stm.setObject(5,temp.getDiscount());
            if(stm.executeUpdate()>0){
                if (updateQty(temp.getItemCode(),temp.getQtyForSell())){

                }else{
                    return false;
                }


                }else{
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE Item SET QtyOnHand=(QtyOnHand-" + qty + ") WHERE ItemCode='" + itemCode + "'");


        return stm.executeUpdate()>0;
    }

    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT OrderId FROM Orders ORDER BY OrderId DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<9){
                return "O-00"+tempId;
            }else if(tempId<99){
                return "O-0"+tempId;
            }else{
                return "O-"+tempId;
            }

        }else{
            return "O-001";
        }
    }
}
