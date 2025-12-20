package vn.edu.hcmuaf.fit.project_ltweb.model;

public class ProductImage {
    private int id; 
    private int product_id;
    private String image_url; 
    private boolean is_default; 

   
    public ProductImage() {
    }

    
    public ProductImage(int id, int product_id, String image_url, boolean is_default) {
        this.id = id;
        this.product_id = product_id;
        this.image_url = image_url;
        this.is_default = is_default;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isIs_default() {
        return is_default;
    }

    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", image_url='" + image_url + '\'' +
                ", is_default=" + is_default +
                '}';
    }
}