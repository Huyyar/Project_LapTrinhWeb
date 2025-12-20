package vn.edu.hcmuaf.fit.project_ltweb.model;
import java.sql.Timestamp;

public class Coupon {
    private int id;
    private String code;
    private String description;
    private double discount_value;
    private int max_usage;
    private int usage_count;
    private Timestamp start_date;
    private Timestamp end_date;
    private boolean is_active;
    private Timestamp created_at;

    public Coupon() {}

  
}