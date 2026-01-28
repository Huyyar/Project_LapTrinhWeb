package vn.edu.hcmuaf.fit.project_ltweb.services;

import java.util.List;
import java.util.UUID;

import vn.edu.hcmuaf.fit.project_ltweb.dao.UserDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.utils.HashUtil;

public class UserService {
    UserDao dao = new UserDao();
    HashUtil hashUtil = new HashUtil();


    public boolean registerUser(User u) {
        if (dao.isUserExists(u.getEmail())) {
            return false;
        }
        // 1. Tạo mã OTP 6 số ngẫu nhiên thay vì UUID
        String otp = String.valueOf((int)((Math.random() * (999999 - 100000)) + 100000));
        u.setVerificationToken(otp);

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

    public User getUserById(int id) {
        return dao.getUserById(id);
    }

    public User login(String email, String password) {
        User user = dao.getUserByEmail(email);
        
        // Check if user exists
        if (user == null) {
            System.out.println("User not found for email: " + email);
            return null;
        }
        
        // Check if user account is active
        if (!user.isIs_active()) {
            System.out.println("Account is locked for email: " + email);
            user.setPassword("ACCOUNT_LOCKED"); // Mark as locked
            return user;
        }
        
        String hashedPassword = hashUtil.passwordHash(password);
        if (user.getPassword().equals(hashedPassword)) {
            return user;
        } else {
            System.out.println("Login failed for email: " + email);
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

    // Get all users with pagination
    public List<User> getAllUsers(int offset, int limit) {
        return dao.getAllUsers(offset, limit);
    }

    // Get total number of users
    public int getTotalUsers() {
        return dao.getTotalUsers();
    }

    // Search users
    public List<User> searchUsers(String search, int offset, int limit) {
        return dao.searchUsers(search, offset, limit);
    }

    // Get total search users
    public int getTotalSearchUsers(String search) {
        return dao.getTotalSearchUsers(search);
    }

    // Lock/Unlock user
    public boolean lockUser(int userId, boolean isLock) {
        return dao.lockUser(userId, isLock);
    }

    // Delete user
    public boolean deleteUser(int userId) {
        return dao.deleteUser(userId);
    }

    // Update user password by admin
    public boolean changeUserPassword(int userId, String newPassword) {
        String passwordHash = hashUtil.passwordHash(newPassword);
        return dao.updateUserPasswordByAdmin(userId, passwordHash);
    }

    // Cập nhật OTP mới cho user 
    public boolean updateOtp(String email, String newOtp) {
        return dao.updateOtp(email, newOtp);
    }
}
