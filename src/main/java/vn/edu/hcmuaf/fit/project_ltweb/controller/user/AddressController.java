package vn.edu.hcmuaf.fit.project_ltweb.controller.user;


import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.hcmuaf.fit.project_ltweb.model.Address;
import vn.edu.hcmuaf.fit.project_ltweb.model.PageInfo;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.services.AddressService;

@WebServlet(name = "UserAddress", value = "/address")
public class AddressController extends HttpServlet {
    private AddressService service = new AddressService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                forwardToAddressPage(request, response);
                break;
            case "delete":
                handleDelete(request, response);
                break;
            case "setDefault":
                handleSetDefault(request, response);
                break;
            default:
                forwardToAddressPage(request, response);
        }

    }

    private void forwardToAddressPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PageInfo info = new PageInfo();
        info.setName("Profile");
        info.setTitle("User - Profile");
        info.setContent("/WEB-INF/views/layouts/userProfile_layout.jsp");
        info.setCss(new String[]{
                "user_profile.css", "userPage.css", "user/user_address_modal.css"
        });
        info.setJs(new String[]{
                "user/user_address.js"
        });
        User u = (User) request.getSession().getAttribute("auth");
        if (u == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        List<Address> addressList = service.getByUserId(u.getId());
        request.setAttribute("addressGet", addressList);

        request.setAttribute("info", info);
        request.setAttribute("userContent", "/WEB-INF/views/userpages/user_address.jsp");
        request.getRequestDispatcher("/WEB-INF/views/layouts/layout.jsp").forward(request, response);
    }


    private void handleDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("auth");
        if (u == null) {
            try {
                response.sendRedirect("login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        boolean success = service.deleteAddress(id, u.getId());
        if (success) {
            session.setAttribute("message", "Address deleted successfully.");
        } else {
            session.setAttribute("error", "Failed to delete address.");
        }
        try {
            response.sendRedirect("address?action=list");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleAdd(HttpServletRequest request, HttpServletResponse response) {
        User u = (User) request.getSession().getAttribute("auth");
        HttpSession session = request.getSession();
        
        if (u == null) {
            try {
                response.sendRedirect("login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try {
            int userId = u.getId();
            String recipientName = request.getParameter("recipientName");
            String recipientPhone = request.getParameter("recipientPhone");
            String province = request.getParameter("province");
            String district = request.getParameter("district");
            String ward = request.getParameter("ward");
            String provinceCode = request.getParameter("provinceCode");
            String districtCode = request.getParameter("districtCode");
            String wardCode = request.getParameter("wardCode");
            String addressDetail = request.getParameter("addressDetail");
            boolean defaultAddress = "on".equals(request.getParameter("defaultAddress"));
            
            Address newAddress = new Address(userId, recipientName, recipientPhone,
                    province, district, ward,
                    provinceCode, districtCode, wardCode,
                    addressDetail, defaultAddress
            );
            
            service.addAddress(newAddress);
            session.setAttribute("message", "Thêm địa chỉ thành công!");
            response.sendRedirect("address?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            try {
                response.sendRedirect("address?action=list");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private void handleSetDefault(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("auth");
        if (u == null) {
            try {
                response.sendRedirect("login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        service.setDefaultAddress(id, u.getId());
        session.setAttribute("message", "Đã đặt làm địa chỉ mặc định!");
        try {
            response.sendRedirect("address?action=list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) {
        User u = (User) request.getSession().getAttribute("auth");
        HttpSession session = request.getSession();
        
        if (u == null) {
            try {
                response.sendRedirect("login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        
        try {
            int addressId = Integer.parseInt(request.getParameter("addressId"));
            
            // Lấy địa chỉ hiện tại từ database
            Address currentAddress = service.getById(addressId);
            
            // Kiểm tra quyền sở hữu
            if (currentAddress == null || currentAddress.getUserId() != u.getId()) {
                session.setAttribute("error", "Không có quyền sửa địa chỉ này!");
                response.sendRedirect("address?action=list");
                return;
            }
            
            String recipientName = request.getParameter("recipientName");
            String recipientPhone = request.getParameter("recipientPhone");
            String province = request.getParameter("province");
            String district = request.getParameter("district");
            String ward = request.getParameter("ward");
            String provinceCode = request.getParameter("provinceCode");
            String districtCode = request.getParameter("districtCode");
            String wardCode = request.getParameter("wardCode");
            String addressDetail = request.getParameter("addressDetail");
            
            // Xử lý defaultAddress: 
            // - Nếu checkbox được check → set làm mặc định
            // - Nếu không check → giữ nguyên giá trị cũ (tránh mất thuộc tính mặc định)
            boolean isCheckboxChecked = "on".equals(request.getParameter("defaultAddress"));
            boolean defaultAddress = isCheckboxChecked || currentAddress.isDefaultAddress();
            
            Address updatedAddress = new Address(addressId, u.getId(), recipientName, recipientPhone,
                    province, district, ward,
                    provinceCode, districtCode, wardCode,
                    addressDetail, defaultAddress
            );
            
            service.updateAddress(updatedAddress);
            
            // Nếu user check "mặc định", cập nhật các địa chỉ khác thành không mặc định
            if (isCheckboxChecked && !currentAddress.isDefaultAddress()) {
                service.setDefaultAddress(addressId, u.getId());
            }
            
            session.setAttribute("message", "Cập nhật địa chỉ thành công!");
            response.sendRedirect("address?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            try {
                response.sendRedirect("address?action=list");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";
        switch (action) {
            case "add":
                handleAdd(request, response);
                break;
            case "update":
                handleUpdate(request, response);
                break;
            default:
                forwardToAddressPage(request, response);
        }
    }
}


