package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.AdminPageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.model.ImageFile;
import vn.edu.hcmuaf.fit.project_ltweb.services.ImageManagerService;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(name = "AdminImageManagerServlet", value = "/admin/image-manager")
public class AdminImageManageServlet extends HttpServlet {
    private ImageManagerService service;

    @Override
    public void init() throws ServletException {
        // Đây mới là nơi an toàn để lấy RealPath
        String root = getServletContext().getRealPath("/");
        this.service = new ImageManagerService(root);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminPageInfo info = new AdminPageInfo();
        info.setName("image-manager");
        info.setTitle("Admin - Image Manager");
        info.setContent("/WEB-INF/views/admin_pages/image_manager.jsp");
        info.setCss(new String[]{
                "admin/admin.css",
        });
        info.setJs(new String[]{
                "admin/upload.js"
        });
        request.setAttribute("info", info);
        List<ImageFile> images = service.getImages();
        request.setAttribute("images", images);
        request.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}