package vn.edu.hcmuaf.fit.project_ltweb.controller.admin.imageManager;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.project_ltweb.services.ImageManagerService;
import java.io.IOException;

@WebServlet("/admin/upload-image")
@MultipartConfig(maxFileSize = 1024 * 1024 * 20)
public class UploadImage extends HttpServlet {
    private ImageManagerService service;

    @Override
    public void init() throws ServletException {
        this.service = new ImageManagerService(getServletContext().getRealPath("/"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Thiết lập trả về JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Part part = request.getPart("file");
            String name = request.getParameter("name");

            // Lưu file và lấy tên thực tế (đã xử lý trùng abc(1).jpg)
            String savedName = service.uploadImage(part, name);

            // Tạo một Map hoặc Object để Gson biến thành JSON {}
            java.util.Map<String, String> data = new java.util.HashMap<>();
            data.put("fileName", savedName);

            response.getWriter().write(new com.google.gson.Gson().toJson(data));
            response.getWriter().flush();
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }}