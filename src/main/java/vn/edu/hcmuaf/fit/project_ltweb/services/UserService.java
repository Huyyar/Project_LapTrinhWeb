package vn.edu.hcmuaf.fit.project_ltweb.services;

import vn.edu.hcmuaf.fit.project_ltweb.dao.UserDao;
import vn.edu.hcmuaf.fit.project_ltweb.model.User;
import vn.edu.hcmuaf.fit.project_ltweb.untils.HashUtil;

public class UserService {
    UserDao dao = new UserDao();
    HashUtil hashUtil = new HashUtil();
    public boolean registerUser(User u) {
        if(dao.isUserExists(u.getEmail())) {
            return false;
        }
        String hashedPassword = hashUtil.passwordHash(u.getPassword());
        u.setPassword(hashedPassword);
        int res = dao.insertUser(u);

        if(res>0){
            return true;
        }
        return false;

    }
    public User getUserByEmail(String email){
        return dao.getUserByEmail(email);
    }

    public User login(String email , String password){
        String hashedPassword = hashUtil.passwordHash(password);
        User user = dao.getUserByEmail(email);
        if(user.isIs_active() == true){

        if(user != null && user.getPassword().equals(hashedPassword)){
            return user;
        }else {
            System.out.println("Login failed for email: " + email);
            return null;
        }
        }else {
            System.out.println("Account is deactivated for email: " + email);
            return null;
        }
    }
    public User updateUserProfile(User u){
        if(dao.updateUser(u)){
            return dao.getUserById(u.getId());
        }
        System.out.println("Update failed for user id: " + u.getId());
        return null;
    }

}
