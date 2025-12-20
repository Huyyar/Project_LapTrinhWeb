package vn.edu.hcmuaf.fit.project_ltweb.model;
import java.sql.Timestamp;

public class OrderItem {
    private int id;
    private int order_id;
    private int product_id;
    private String product_name_snapshot;
    private String product_image_snapshot;
    private double unit_price_snapshot;
    private int quantity;
    private double line_total;
    private Timestamp created_at;

    public OrderItem() {}

   
}