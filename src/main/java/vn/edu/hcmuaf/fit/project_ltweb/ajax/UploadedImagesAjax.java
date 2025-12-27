package vn.edu.hcmuaf.fit.project_ltweb.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.hcmuaf.fit.project_ltweb.model.ImageFile;
import vn.edu.hcmuaf.fit.project_ltweb.services.ImageManagerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/ajax/uploaded-images")
@MultipartConfig
public class UploadedImagesAjax extends HttpServlet {
    private ImageManagerService service;

    @Override
    public void init() throws ServletException {
        String root = getServletContext().getRealPath("/");
        this.service = new ImageManagerService(root);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws
            ServletException, IOException {
        List<ImageFile> images = new ArrayList<>();
        String[] names = request.getParameterValues("names");
        if (names != null) {
            for (String name : names) {
                ImageFile image = service.getImage(name);
                images.add(image);

            }
        }
        request.setAttribute("uploadedImages", images);
        System.out.println("Tổng số lượng ảnh hợp lệ gửi sang JSP: " + images.size());
        request.getRequestDispatcher("/WEB-INF/views/partials/uploaded_list.jsp")
                .forward(request, response);
    }
}

