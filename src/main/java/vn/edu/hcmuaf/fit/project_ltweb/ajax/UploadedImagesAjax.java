package vn.edu.hcmuaf.fit.project_ltweb.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import vn.edu.hcmuaf.fit.project_ltweb.model.ImageFile;
import vn.edu.hcmuaf.fit.project_ltweb.services.ImageManagerService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/ajax/uploaded-images")
public class UploadedImagesAjax extends HttpServlet {
    private ImageManagerService service;

    @Override
    public void init() throws ServletException {
        this.service = new ImageManagerService(getServletContext().getRealPath("/"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ImageFile> images = new ArrayList<>();
        String[] names = request.getParameterValues("names");

        if (names != null) {
            for (String name : names) {
                ImageFile img = service.getImage(name);
                if (img != null) images.add(img);
            }
        }

        request.setAttribute("uploadedImages", images);
        request.getRequestDispatcher("/WEB-INF/views/partials/uploaded_list.jsp").forward(request, response);
    }
}