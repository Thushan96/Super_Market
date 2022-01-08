package Controller;

import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerService {
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public List<String> getCustomerIds() throws SQLException, ClassNotFoundException;
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Customer> getAllCustomers();

}
