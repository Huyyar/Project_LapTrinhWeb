package vn.edu.hcmuaf.fit.project_ltweb.utils;

import java.io.File;
import java.nio.file.Paths;

public class FileUtil {
    public static String getUploadPath(String root) {
        return Paths.get(root).getParent().getParent()
                .resolve("src/main/webapp/assets/images")
                .toFile().getAbsolutePath();
    }
    public static void deleteImage(String path){
        File f =  new File(path);
        if(f.exists()){
            f.delete();
        }
    }
}
