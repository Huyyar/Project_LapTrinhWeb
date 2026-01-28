package vn.edu.hcmuaf.fit.project_ltweb.utils;

import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static String getSrcPath(String root) {
        Path current = Paths.get(root);
        while (current != null) {
            if (current.getFileName() != null && current.getFileName().toString().equalsIgnoreCase("target")) {
                Path projectRoot = current.getParent();
                if (projectRoot != null) {
                    return projectRoot.resolve("src").resolve("main").resolve("webapp")
                            .resolve("assets").resolve("images").toAbsolutePath().toString();
                }
            }
            current = current.getParent();
        }
        throw new IllegalStateException("Không tìm thấy thư mục 'target' trong: " + root);
    }

    public static String getDeployPath(String root) {
        return Paths.get(root, "assets", "images").toAbsolutePath().toString();
    }

    public static void saveDualFile(Part part, String name, String srcPath, String deployPath) throws IOException {
        // Đảm bảo các thư mục tồn tại
        new File(srcPath).mkdirs();
        new File(deployPath).mkdirs();

        // 1. Ghi vào SRC
        String srcFilePath = srcPath + File.separator + name;
        part.write(srcFilePath);

        // 2. Copy sang Deploy
        File srcFile = new File(srcFilePath);
        File deployFile = new File(deployPath + File.separator + name);
        Files.copy(srcFile.toPath(), deployFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void deleteFile(String path) {
        File f = new File(path);
        if (f.exists()) f.delete();
    }

    public static void renameFile(String path, String newName) {
        File f = new File(path);
        if (f.exists()) {
            File newFile = new File(f.getParent(), newName);
            f.renameTo(newFile);
        }
    }

    public static List<File> getPagedSearchFiles(String path, int offset, int pageSize, String search) {
        List<File> result = new ArrayList<>();
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null) return result;

        for (File file : files) {
            if (file.getName().toLowerCase().contains(search.toLowerCase())) {
                result.add(file);
            }
        }
        int end = Math.min(offset + pageSize, result.size());
        return (offset >= result.size()) ? new ArrayList<>() : result.subList(offset, end);
    }

    public static int totalSearchFiles(String path, String search) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null) return 0;
        int count = 0;
        for (File f : files) {
            if (f.getName().toLowerCase().contains(search.toLowerCase())) count++;
        }
        return count;
    }
    public static String checkName(String path, String img) {
        String nameOnly = img;
        String extension = "";
        int lastDotIndex = img.lastIndexOf(".");

        if (lastDotIndex != -1) {
            nameOnly = img.substring(0, lastDotIndex);
            extension = img.substring(lastDotIndex);
        }
        File dir = new File(path);
        if (!dir.exists()) return img;
        String finalName = img;
        int count = 1;
        File checkFile = new File(dir, finalName);

        while (checkFile.exists()) {
            finalName = nameOnly + "(" + count + ")" + extension;
            checkFile = new File(dir, finalName);
            count++;
        }

        return finalName;
    }
}