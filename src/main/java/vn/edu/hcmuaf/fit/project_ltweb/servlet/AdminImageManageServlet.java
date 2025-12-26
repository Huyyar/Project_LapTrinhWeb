package vn.edu.hcmuaf.fit.project_ltweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.AdminPageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.model.ImageFile;
import vn.edu.hcmuaf.fit.project_ltweb.model.PageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.services.ImageManagerService;
import vn.edu.hcmuaf.fit.project_ltweb.services.ProductService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminImageManagerServlet", value = "/admin/image-manager")
public class AdminImageManageServlet extends HttpServlet {
    private ImageManagerService service;
    private static final int PAGE_SIZE = 5;

    @Override
    public void init() throws ServletException {
        String root = getServletContext().getRealPath("/");
        this.service = new ImageManagerService(root);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageInfo info = new PageInfo();
        info.setName("image-manager");
        info.setTitle("Admin - Image Manager");
        info.setContent("/WEB-INF/views/admin_pages/image_manager.jsp");
        info.setCss(new String[]{
                "pagination.css",
                "admin/image_manager.css",
        });
        info.setJs(new String[]{
                "admin/image_manager.js",
                "admin/upload_image.js",
                "admin/copy_url.js"
        });
        request.setAttribute("info", info);

        int totalImage, totalPage;
        String search = request.getParameter("search");
        if(search == null){
            search = "";
        }
        request.setAttribute("search", search);
        totalImage = service.totalSearchImages(search);
        request.setAttribute("totalImage",  totalImage);
        totalPage = (int) Math.ceil((double) totalImage / PAGE_SIZE);
        request.setAttribute("totalPage", totalPage);
        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                currentPage = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                currentPage = 1;
            }
        }
        if (currentPage > totalPage || currentPage < 1) {
            currentPage = 1;
        }
        request.setAttribute("currentPage", currentPage);
        int offset = (currentPage - 1) * PAGE_SIZE;
        List<ImageFile> images = service.getPagedSearchImages(offset,PAGE_SIZE, search);
        request.setAttribute("images", images);

        request.getRequestDispatcher("/WEB-INF/views/layouts/admin_layout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}