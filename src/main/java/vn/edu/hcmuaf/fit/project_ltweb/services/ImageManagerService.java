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

    public int totalImages() {
        return FileUtil.totalFiles(uploadPath);
    }

    public List<ImageFile> getPagedImages(int offset, int pageSize) {
        List<ImageFile> images = new ArrayList<>();
        List<File> files = FileUtil.getPagedFiles(uploadPath, offset, pageSize);
        if (files == null) {
            return images;
        }
        for(File file : files){
            ImageFile image = new ImageFile();
            image.setName(file.getName());
            image.setUrl("/assets/images/" + file.getName());
            image.setSize(file.length());

            images.add(image);
        }

        return images;
    }
    public List<ImageFile> getPagedSearchImages(int offset, int pageSize, String search) {
        List<ImageFile> images = new ArrayList<>();
        List<File> files = FileUtil.getPagedSearchFiles(uploadPath, offset, pageSize, search);
        if (files == null) {
            return images;
        }
        for(File file : files){
            ImageFile image = new ImageFile();
            image.setName(file.getName());
            image.setUrl("/assets/images/" + file.getName());
            image.setSize(file.length());

            images.add(image);
        }
        return  images;
    }
    public int totalSearchImages(String search){
        return FileUtil.totalSearchFiles(uploadPath, search);
    }
    public ImageFile getImage(String name) {
        ImageFile image = new ImageFile();
        File f = FileUtil.getFile(uploadPath, name);
        if(f.exists()){
            image.setName(f.getName());
            image.setUrl("/assets/images/" + f.getName());
            image.setSize(f.length());
            return image;
        }
        return null;
    }
    public int totalImagesSearch(String search){
        return FileUtil.totalSearchFiles(uploadPath, search);
    }

    public void deleteImage(String name){
        FileUtil.deleteFile(uploadPath +"/"+ name);
    }
    public void renameImage(String name, String newName){
        FileUtil.renameFile(uploadPath +"/"+ name, newName);
    }

}
