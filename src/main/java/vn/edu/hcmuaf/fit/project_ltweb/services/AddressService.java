package vn.edu.hcmuaf.fit.project_ltweb.services;

import java.sql.SQLException;
import java.util.List;

import vn.edu.hcmuaf.fit.project_ltweb.dao.AddressDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.Address;

public class AddressService {
    private AddressDao dao  = new AddressDao();
    public List<Address> getByUserId(int userID) {
        return dao.getByUserId(userID) ;
    }
    public void addAddress(Address address) {
        dao.addAddress(address);
    }
    public void updateAddress(Address ad) throws Exception {
        dao.updateAddress(ad);
    }
    public boolean deleteAddress(int addressID , int userID) {
        return dao.deleteAddress(addressID,userID) ;
    }
    public void setDefaultAddress(int addressID ,int userID) {
        try {
            dao.setDefault(addressID,userID) ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Address getById(int addressId) {
        return dao.getById(addressId);
    }
    
    
    public Address getDefaultAddress(int userId) {
        return dao.getDefaultAddress(userId);
    }
}
