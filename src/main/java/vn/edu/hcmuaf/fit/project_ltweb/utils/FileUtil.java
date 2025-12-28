package vn.edu.hcmuaf.fit.project_ltweb.utils;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static String getUploadPath(String root) {
        return Paths.get(root).getParent().getParent().getParent()
                .resolve("src/main/webapp/assets/images")
                .toFile().getAbsolutePath();
    }
    public static void deleteFile(String path){
        File f =  new File(path);
        if(f.exists()){
            f.delete();
        }
    }
    public static void renameFile(String path, String newName){
        File f =  new File(path);
        if(f.exists()){
            String parentPath = f.getParent();
            File newFile = new File(parentPath, newName);
            f.renameTo(newFile);
        }
    }
    public static int totalFiles(String path){
        File dir = new File(path);
        File[] files = dir.listFiles();
        return files == null ? 0 : files.length;
    }
    public static int totalSearchFiles(String path,String search){
        File dir = new File(path);
        File[] files = dir.listFiles();
        if(files == null){
            return 0;
        }
        int count = 0;
        for(File file : files){
            if(file.getName().toLowerCase().contains(search.toLowerCase())){
                count++;
            }
        }
        return count;
    }
    public static List<File> getPagedFiles(String path, int offset, int pageSize){
        List<File> result =  new ArrayList<File>();
        File dir = new File(path);
        File[] files = dir.listFiles();
        if(files == null){
            return null;
        }
        int end = Math.min(offset + pageSize, files.length);
        for (int i = offset; i < end; i++) {
            result.add(files[i]);
        }
        return result;
    }
    public static List<File> getPagedSearchFiles(String path, int offset, int pageSize, String search){
        List<File> result =  new ArrayList<File>();

        File dir = new File(path);
        File[] files = dir.listFiles();
        if(files == null){
            return null;
        }

        for(File  file : files){
            if(file.getName().toLowerCase().contains(search.toLowerCase())){
                result.add(file);
            }
        }
        int end = Math.min(offset + pageSize, result.size());
        return result.subList(offset, end);
    }
    public static File getFile(String path, String name){
        File dir = new File(path);
        File[] files = dir.listFiles();
        if(files == null){
            return null;
        }
        for(File file : files){
            if(file.getName().equals(name)){
                return file;
            }
        }
        return null;
    }
}
