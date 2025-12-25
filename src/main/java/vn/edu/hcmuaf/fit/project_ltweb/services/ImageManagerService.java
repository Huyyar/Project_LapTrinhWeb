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
    public String getUploadPath() {
        return uploadPath;
    }
    public List<ImageFile> getImages(int offset, int pageSize) {
        List<ImageFile> images = new ArrayList<>();
        File dir = new File(uploadPath);
        File[] files = dir.listFiles();

        if (files == null || files.length == 0) {
            return images;
        }
        int end = Math.min(offset + pageSize, files.length);
        for (int i = offset; i < end; i++) {
            File f = files[i];

            ImageFile image = new ImageFile();
            image.setName(f.getName());
            image.setUrl("/assets/images/" + f.getName());
            image.setSize(f.length());

            images.add(image);
        }
        return images;
    }
    public int totalImages() {
        File dir = new File(uploadPath);
        File[] files = dir.listFiles();
        return files == null ? 0 : files.length;
    }

    public ImageFile getImage(String name) {
        ImageFile image = new  ImageFile();
        File dir = new File(uploadPath);
        File[] files = dir.listFiles();
        for(File file : files){
            if(file.getName().equals(name)){
                image.setName(file.getName());
                image.setUrl("/assets/images/" + file.getName());
                image.setSize(file.length());
                return image;
            }
        }
        return null;
    }
}
