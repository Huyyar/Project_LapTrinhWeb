package vn.edu.hcmuaf.fit.project_ltweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmuaf.fit.project_ltweb.model.Slide;

public class SlideDao {
    
    
    public List<Slide> getAllSlides() {
        List<Slide> slides = new ArrayList<>();
        String sql = "SELECT * FROM slides ORDER BY priority ASC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Slide slide = new Slide();
                slide.setId(rs.getLong("id"));
                slide.setImageUrl(rs.getString("image_url"));
                slide.setTitle(rs.getString("title"));
                slide.setDescription(rs.getString("description"));
                slide.setActive(rs.getBoolean("active"));
                slide.setPriority(rs.getInt("priority"));
                slides.add(slide);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slides;
    }
    

    public List<Slide> getActiveSlides() {
        List<Slide> slides = new ArrayList<>();
        String sql = "SELECT * FROM slides WHERE active = 1 ORDER BY priority ASC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Slide slide = new Slide();
                slide.setId(rs.getLong("id"));
                slide.setImageUrl(rs.getString("image_url"));
                slide.setTitle(rs.getString("title"));
                slide.setDescription(rs.getString("description"));
                slide.setActive(rs.getBoolean("active"));
                slide.setPriority(rs.getInt("priority"));
                slides.add(slide);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slides;
    }

    public long addSlide(Slide slide) {
        String sql = "INSERT INTO slides(image_url, title, description, active, priority) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, slide.getImageUrl());
            ps.setString(2, slide.getTitle());
            ps.setString(3, slide.getDescription());
            ps.setBoolean(4, slide.isActive());
            ps.setInt(5, slide.getPriority());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    

    public boolean updateSlide(Slide slide) {
        String sql = "UPDATE slides SET image_url = ?, title = ?, description = ?, active = ?, priority = ? WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, slide.getImageUrl());
            ps.setString(2, slide.getTitle());
            ps.setString(3, slide.getDescription());
            ps.setBoolean(4, slide.isActive());
            ps.setInt(5, slide.getPriority());
            ps.setLong(6, slide.getId());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Delete slide
     */
    public boolean deleteSlide(long slideId) {
        String sql = "DELETE FROM slides WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, slideId);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    

    public Slide getSlideById(long id) {
        String sql = "SELECT * FROM slides WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Slide slide = new Slide();
                slide.setId(rs.getLong("id"));
                slide.setImageUrl(rs.getString("image_url"));
                slide.setTitle(rs.getString("title"));
                slide.setDescription(rs.getString("description"));
                slide.setActive(rs.getBoolean("active"));
                slide.setPriority(rs.getInt("priority"));
                return slide;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Get total number of slides
     */
    public int getTotalSlides() {
        String sql = "SELECT COUNT(*) FROM slides";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    /**
     * Get paginated slides
     */
    public List<Slide> getPagedSlides(int offset, int limit) {
        List<Slide> slides = new ArrayList<>();
        String sql = "SELECT * FROM slides ORDER BY priority ASC LIMIT ? OFFSET ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Slide slide = new Slide();
                slide.setId(rs.getLong("id"));
                slide.setImageUrl(rs.getString("image_url"));
                slide.setTitle(rs.getString("title"));
                slide.setDescription(rs.getString("description"));
                slide.setActive(rs.getBoolean("active"));
                slide.setPriority(rs.getInt("priority"));
                slides.add(slide);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slides;
    }
}
