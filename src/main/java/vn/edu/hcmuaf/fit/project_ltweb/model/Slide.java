package vn.edu.hcmuaf.fit.project_ltweb.model;

public class Slide {
    private long id;
    private String imageUrl;
    private String title;
    private String description;
    private boolean active ;
    private int priority;


    public Slide(String imageUrl, String title, String description, boolean active, int priority) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.active = active;
        this.priority = priority;
    }

    public Slide() {
    }

    public Slide(long id, String imageUrl, String title, String description, boolean active, int priority) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.active = active;
        this.priority = priority;

    }

    public Slide(int priority, boolean active, String description, String title, String imageUrl) {
        this.priority = priority;
        this.active = active;
        this.description = description;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
