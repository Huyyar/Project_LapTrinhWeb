package vn.edu.hcmuaf.fit.project_ltweb.dao;

import vn.edu.hcmuaf.fit.project_ltweb.model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
    public List<Contact> findAll() {
        List<Contact> list = new ArrayList<>();

        String sql = "SELECT * FROM contacts ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Contact c = new Contact();
                c.setId(rs.getInt("id"));
                c.setFullName(rs.getString("full_name"));
                c.setEmail(rs.getString("email"));
                c.setMessage(rs.getString("message"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                
                // Handle is_replied safely in case column doesn't exist
                try {
                    c.setIs_replied(rs.getBoolean("is_replied"));
                } catch (Exception e) {
                    c.setIs_replied(false); // default to false if column doesn't exist
                }

                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public Contact findById(int id) {

        String sql = "SELECT * FROM contacts WHERE id = ?";
        Contact c = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Contact();
                c.setId(rs.getInt("id"));
                c.setFullName(rs.getString("full_name"));
                c.setEmail(rs.getString("email"));
                c.setMessage(rs.getString("message"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                
                // Handle is_replied safely in case column doesn't exist
                try {
                    c.setIs_replied(rs.getBoolean("is_replied"));
                } catch (Exception e) {
                    c.setIs_replied(false); // default to false if column doesn't exist
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }
    public void deleteById(int id) {
        String sql = "DELETE FROM contacts WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate(); // ← DÒNG QUYẾT ĐỊNH XÓA DB

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void markAsReplied(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            
            // First, try to add column if it doesn't exist
            try {
                String alterSql = "ALTER TABLE contacts ADD COLUMN is_replied BOOLEAN DEFAULT 0";
                PreparedStatement alterPs = conn.prepareStatement(alterSql);
                alterPs.executeUpdate();
                System.out.println("Column is_replied added to contacts table");
            } catch (Exception e) {
                // Column might already exist, continue
                System.out.println("Column is_replied might already exist: " + e.getMessage());
            }
            
            // Now update the contact
            String sql = "UPDATE contacts SET is_replied = 1 WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Contact ID " + id + " marked as replied");
            
        } catch (Exception e) {
            System.err.println("Error in markAsReplied: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
