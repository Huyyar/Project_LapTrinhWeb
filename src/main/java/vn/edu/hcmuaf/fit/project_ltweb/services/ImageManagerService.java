package vn.edu.hcmuaf.fit.project_ltweb.services;

import jakarta.servlet.http.Part;
import vn.edu.hcmuaf.fit.project_ltweb.model.ImageFile;
import vn.edu.hcmuaf.fit.project_ltweb.utils.FileUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageManagerService {
    private final String srcPath;
    private final String deployPath;

    public ImageManagerService(String root) {
        this.srcPath = FileUtil.getSrcPath(root);
        this.deployPath = FileUtil.getDeployPath(root);
    }

    public String uploadImage(Part part, String name) throws IOException {
        String finalName = FileUtil.checkName(srcPath, name);
        FileUtil.saveDualFile(part, finalName, srcPath, deployPath);
        return finalName;
    }

    public List<ImageFile> getPagedSearchImages(int offset, int pageSize, String search) {
        List<ImageFile> images = new ArrayList<>();
        List<File> files = FileUtil.getPagedSearchFiles(deployPath, offset, pageSize, search);
        for (File f : files) {
            images.add(mapToFileModel(f));
        }
        return images;
    }

    public ImageFile getImage(String name) {
        File f = new File(deployPath, name);
        return f.exists() ? mapToFileModel(f) : null;
    }

    public void deleteImage(String name) {
        FileUtil.deleteFile(srcPath + File.separator + name);
        FileUtil.deleteFile(deployPath + File.separator + name);
    }

    public void renameImage(String name, String newName) {
        FileUtil.renameFile(srcPath + File.separator + name, newName);
        FileUtil.renameFile(deployPath + File.separator + name, newName);
    }

    public int totalSearchImages(String search) {
        return FileUtil.totalSearchFiles(deployPath, search);
    }

    private ImageFile mapToFileModel(File f) {
        ImageFile img = new ImageFile();
        img.setName(f.getName());
        img.setUrl("/assets/images/" + f.getName());
        img.setSize(f.length());
        return img;
    }
}