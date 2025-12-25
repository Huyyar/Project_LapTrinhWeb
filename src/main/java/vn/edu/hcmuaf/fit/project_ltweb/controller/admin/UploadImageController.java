package vn.edu.hcmuaf.fit.project_ltweb.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.edu.hcmuaf.fit.project_ltweb.services.ImageManagerService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(name = "UploadImageController", value = "/admin/upload-handler")
// Cấu hình để nhận dữ liệu multipart (file upload)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class UploadImageController extends HttpServlet {
    private ImageManagerService service;
    private String deploymentPath;

    @Override
    public void init() throws ServletException {
        // Đây mới là nơi an toàn để lấy RealPath
        String root = getServletContext().getRealPath("/");
        this.service = new ImageManagerService(root);
        this.deploymentPath = getServletContext().getRealPath("/assets/images");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. Lấy file từ Uppy gửi lên (fieldName: 'file')
            Part filePart = request.getPart("file");
            if (filePart == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Không tìm thấy file");
                return;
            }
            String name = request.getParameter("name");

            String uploadPath = service.getUploadPath();

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            // 5. Ghi file xuống ổ cứng
            filePart.write(uploadPath + File.separator + name);
            filePart.write(deploymentPath + File.separator + name);

            // 6. Phản hồi cho Uppy (200 OK)
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Thành công: " + name);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}