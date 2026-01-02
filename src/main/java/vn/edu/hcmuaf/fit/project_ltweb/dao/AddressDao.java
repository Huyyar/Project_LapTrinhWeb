package vn.edu.hcmuaf.fit.project_ltweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmuaf.fit.project_ltweb.model.Address;

public class AddressDao {
    public List<Address> getByUserId(int userId) {
        List<Address> addresses = new ArrayList<>();
        String query = "SELECT * FROM addresses WHERE user_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Address ad = new Address();
                ad.setId(rs.getInt("id"));
                ad.setUserId(rs.getInt("user_id"));
                ad.setRecipientName(rs.getString("recipient_name"));
                ad.setRecipientPhone(rs.getString("recipient_phone"));
                ad.setProvince(rs.getString("province"));
                ad.setDistrict(rs.getString("district"));
                ad.setWard(rs.getString("ward"));
                ad.setProvinceCode(rs.getString("province_code"));
                ad.setDistrictCode(rs.getString("district_code"));
                ad.setWardCode(rs.getString("ward_code"));
                ad.setAddressDetail(rs.getString("address_detail"));
                ad.setDefaultAddress(rs.getBoolean("is_default"));

                addresses.add(ad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }
public void addAddress(Address address) {
    String query = "INSERT INTO addresses (user_id, recipient_name, recipient_phone, province, district, ward, province_code, district_code, ward_code, address_detail, is_default) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

        statement.setInt(1, address.getUserId());
        statement.setString(2, address.getRecipientName());
        statement.setString(3, address.getRecipientPhone());
        statement.setString(4, address.getProvince());
        statement.setString(5, address.getDistrict());
        statement.setString(6, address.getWard());
        statement.setString(7, address.getProvinceCode());
        statement.setString(8, address.getDistrictCode());
        statement.setString(9, address.getWardCode());
        statement.setString(10, address.getAddressDetail());
        statement.setBoolean(11, address.isDefaultAddress());

        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public void updateAddress(Address ad) throws SQLException {
        String query = "UPDATE addresses SET recipient_name = ?, recipient_phone = ?, province = ?, district = ?, ward = ?, province_code = ?, district_code = ?, ward_code = ?, address_detail = ?, is_default = ? WHERE id = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, ad.getRecipientName());
        statement.setString(2, ad.getRecipientPhone());
        statement.setString(3, ad.getProvince());
        statement.setString(4, ad.getDistrict());
        statement.setString(5, ad.getWard());
        statement.setString(6, ad.getProvinceCode());
        statement.setString(7, ad.getDistrictCode());
        statement.setString(8, ad.getWardCode());
        statement.setString(9, ad.getAddressDetail());
        statement.setBoolean(10, ad.isDefaultAddress());
        statement.setInt(11, ad.getId());
        statement.executeUpdate();


}
    public boolean deleteAddress(int addressId , int userId) {
        String query = "DELETE FROM addresses WHERE id = ? AND user_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, addressId);
            statement.setInt(2, userId);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false ;
    }
    public void setDefault(int addressId, int userId) throws SQLException {
        String query = "UPDATE addresses SET is_default = CASE WHEN id = ? THEN TRUE ELSE FALSE END WHERE user_id = ?";
        Connection conn = DBConnection.getConnection()  ;
        PreparedStatement statement= conn.prepareStatement(query);
        statement.setInt(1, addressId);
        statement.setInt(2, userId);
        statement.executeUpdate();
    }

    public Address getById(int addressId) {
        Address address = null;
        String query = "SELECT * FROM addresses WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, addressId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                address = new Address();
                address.setId(rs.getInt("id"));
                address.setUserId(rs.getInt("user_id"));
                address.setRecipientName(rs.getString("recipient_name"));
                address.setRecipientPhone(rs.getString("recipient_phone"));
                address.setProvince(rs.getString("province"));
                address.setDistrict(rs.getString("district"));
                address.setWard(rs.getString("ward"));
                address.setProvinceCode(rs.getString("province_code"));
                address.setDistrictCode(rs.getString("district_code"));
                address.setWardCode(rs.getString("ward_code"));
                address.setAddressDetail(rs.getString("address_detail"));
                address.setDefaultAddress(rs.getBoolean("is_default"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }
}
