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
        try {
            Part part = request.getPart("file");
            String name = request.getParameter("name");

            service.uploadImage(part, name);

            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(new String[]{name}));
        } catch (Exception e) {
            response.sendError(500, e.getMessage());
        }
    }
}