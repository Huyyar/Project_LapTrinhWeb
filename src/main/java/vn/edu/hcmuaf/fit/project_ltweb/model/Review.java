package vn.edu.hcmuaf.fit.project_ltweb.model;
import java.sql.Timestamp;

public class Review {
    private int id;
    private int user_id;
    private int product_id;
    private String status;
    private String comment;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Review() {}
    
   
}