package Controller;

import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemService {
    public boolean saveItem(Item c) throws SQLException, ClassNotFoundException;
    public boolean updateItem(Item i) throws SQLException, ClassNotFoundException;
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    public Item getCustomer(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getItemCodes() throws SQLException, ClassNotFoundException;
    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException;

}
