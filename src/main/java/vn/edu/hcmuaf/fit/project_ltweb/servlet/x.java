package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.ImageFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/admin/file-manager")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class x extends HttpServlet {

    private String getUploadPath() {
        // Lấy đường dẫn đến thư mục 'target/classes' hoặc 'target/project-name'
        String root = getServletContext().getRealPath("/");

        // Dùng Paths để nhảy ngược ra 2 cấp và đi vào thư mục mong muốn
        Path path = Paths.get(root).getParent().getParent()
                .resolve("src/main/webapp/assets/images");

        File dir = path.toFile();
        if (!dir.exists()) dir.mkdirs();

        return dir.getAbsolutePath();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String uploadPath = getUploadPath();
        String searchTerm = request.getParameter("search");

        if ("delete".equals(action)) {
            String fileName = request.getParameter("name");
            if (fileName != null) new File(uploadPath + File.separator + fileName).delete();
        } else if ("rename".equals(action)) {
            String oldName = request.getParameter("oldName");
            String newName = request.getParameter("newName");
            if (oldName != null && newName != null) {
                new File(uploadPath + File.separator + oldName).renameTo(new File(uploadPath + File.separator + newName));
            }
        }

        File dir = new File(uploadPath);
        File[] rawFiles = Optional.ofNullable(dir.listFiles()).orElse(new File[0]);

        // Chuyển đổi sang List<ImageFile>
        List<ImageFile> imageFiles = Arrays.stream(rawFiles)
                .filter(File::isFile)
                .map(f -> new ImageFile(
                        f.getName(),
                        request.getContextPath() + "/assets/images/" + f.getName(),
                        f.length()))
                .collect(Collectors.toList());

        // Lọc tìm kiếm nếu có
        if (searchTerm != null && !searchTerm.isEmpty()) {
            imageFiles = imageFiles.stream()
                    .filter(img -> img.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                    .collect(Collectors.toList());
        }

        request.setAttribute("files", imageFiles);
        request.getRequestDispatcher("/WEB-INF/views/admin_pages/x.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Part filePart = request.getPart("file");
            if (filePart != null) {
                String fileName = filePart.getSubmittedFileName();
                filePart.write(getUploadPath() + File.separator + fileName);
                response.setContentType("application/json");
                response.getWriter().write("{\"status\":\"success\"}");
            }
        } catch (Exception e) {
            response.sendError(500, e.getMessage());
        }
    }
}