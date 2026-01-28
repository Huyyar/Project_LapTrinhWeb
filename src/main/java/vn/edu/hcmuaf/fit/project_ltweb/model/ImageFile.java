package vn.edu.hcmuaf.fit.project_ltweb.model;

import vn.edu.hcmuaf.fit.project_ltweb.utils.AppContextListener;

public class ImageFile {
    private String name;
    private String url;
    private long size; // Dùng long cho khớp với file.length()

    public ImageFile(String name, String url, long size) {
        this.name = name;
        this.url = url;
        this.size = size;
    }

    public ImageFile() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl(){
        return url;
    }
    public String getFullPath() {
        String cp = AppContextListener.contextPath;
        return cp + url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}