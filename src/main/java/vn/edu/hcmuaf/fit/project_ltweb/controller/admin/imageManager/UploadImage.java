package vn.edu.hcmuaf.fit.project_ltweb.controller.admin.imageManager;

import com.google.gson.Gson;
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

@WebServlet("/admin/upload-image")
// Cấu hình để nhận dữ liệu multipart (file upload)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 20,      // 20MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class UploadImage extends HttpServlet {
    private ImageManagerService service;

    @Override
    public void init() throws ServletException {
        String root = getServletContext().getRealPath("/");
        this.service = new ImageManagerService(root);
            }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Part part = request.getPart("file");
            String name = request.getParameter("name");
            part.write(service.getUploadPath() + File.separator + name);

            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = new Gson().toJson(new String[]{name});
            response.getWriter().write(json);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}