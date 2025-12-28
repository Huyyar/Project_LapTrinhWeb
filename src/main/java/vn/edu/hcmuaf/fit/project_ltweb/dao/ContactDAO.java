package vn.edu.hcmuaf.fit.project_ltweb.dao;

import vn.edu.hcmuaf.fit.project_ltweb.model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ContactDAO {

    public void insert(Contact c) {

        String sql = "INSERT INTO contacts(full_name, email, message) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getFullName());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getMessage());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
