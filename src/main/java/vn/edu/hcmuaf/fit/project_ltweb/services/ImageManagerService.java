package vn.edu.hcmuaf.fit.project_ltweb.services;

import vn.edu.hcmuaf.fit.project_ltweb.model.ImageFile;
import vn.edu.hcmuaf.fit.project_ltweb.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageManagerService {
    private String uploadPath;

    public ImageManagerService(String root) {
        this.uploadPath = FileUtil.getUploadPath(root);
    }
    public List<ImageFile> getImages() {
        List<ImageFile> images = new ArrayList<>();
        File dir = new File(uploadPath);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                ImageFile image = new  ImageFile();
                image.setName(f.getName());
                image.setUrl("/assets/images/" + f.getName());
                image.setSize(f.length());
                images.add(image);
            }
        }
        return images;
    }

    public String getUploadPath() {
        return uploadPath;
    }
}
