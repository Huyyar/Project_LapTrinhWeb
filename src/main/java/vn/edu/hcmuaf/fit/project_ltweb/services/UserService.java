package vn.edu.hcmuaf.fit.project_ltweb.services;

import vn.edu.hcmuaf.fit.project_ltweb.dao.UserDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.utils.HashUtil;

import java.util.UUID;

public class UserService {
    UserDao dao = new UserDao();
    HashUtil hashUtil = new HashUtil();


    public boolean registerUser(User u) {
        if (dao.isUserExists(u.getEmail())) {
            return false;
        }
        // 1. Tạo Token ngẫu nhiên
        String token = UUID.randomUUID().toString();
        u.setVerificationToken(token);

        String hashedPassword = hashUtil.passwordHash(u.getPassword());
        u.setPassword(hashedPassword);
        int res = dao.insertUser(u);

       return res > 0 ;

    }
    public boolean verifyAccount(String token) {
        return dao.activateUser(token);
    }

    public User getUserByEmail(String email) {
        return dao.getUserByEmail(email);
    }

    public User login(String email, String password) {
        String hashedPassword = hashUtil.passwordHash(password);
        User user = dao.getUserByEmail(email);
        if (user.isIs_active() == true) {

            if (user != null && user.getPassword().equals(hashedPassword)) {
                return user;
            } else {
                System.out.println("Login failed for email: " + email);
                return null;
            }
        } else {
            System.out.println("Account is deactivated for email: " + email);
            return null;
        }
    }

    public User updateUserProfile(User u) {
        if (dao.updatePUser(u)) {
            return dao.getUserById(u.getId());
        }
        System.out.println("Update failed for user id: " + u.getId());
        return null;
    }

    public boolean updateUserPassword(String newPassword, User u) {
        String passwordHash = hashUtil.passwordHash(newPassword);
        if (dao.updatePassword(passwordHash, u)) {
            u.setPassword(passwordHash);
            return true;
        }
        return false;
    }
}
