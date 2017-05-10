package Biz;

import Dao.UserDao;
import Dao.UserDaoImp;
import Entity.User;
import Entity.UserProfile;
import com.mongodb.gridfs.GridFSDBFile;
import net.sf.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by 47 on 2016/6/4.
 */
public class UserBizImp implements UserBiz {
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public void UserBizImpl(){
        userDao = new UserDaoImp();
    }

    public User getUserByUsernameAndPassword(String username, String password){
        User user = userDao.getUserByUsername(username);
        if (user == null){
            return null;
        }
        if (user.getPassword().equals(password)){
            return user;
        }else{
            return null;
        }
    }

    public boolean isAvailable(String username){
        if (userDao.getUserByUsername(username) == null){
            return true;
        }else{
            return false;
        }
    }

    public void ModifyUser(Map<String, Object> parameters){
        int userId = Integer.parseInt(((String[])parameters.get("userId"))[0]);
        User user = userDao.getUserById(userId);
        if (parameters.get("username") != null){
            user.setUsername(((String[])parameters.get("username"))[0]);
        }
        if (parameters.get("coin") != null){
            user.setCoin(Integer.parseInt(((String[])parameters.get("coin"))[0]));
        }
        if (parameters.get("mobile") != null){
            user.setMobile(((String[])parameters.get("mobile"))[0]);
        }
        if(parameters.get("email")!=null){
            user.setEmail(((String[])parameters.get("email"))[0]);
        }
        if (parameters.get("password") != null){
            user.setPassword(((String[])parameters.get("password"))[0]);
        }
        if(parameters.get("role")!=null){
            user.setRole(Integer.parseInt(((String[])parameters.get("role"))[0]));
        }
        userDao.updateUser(user);
    }

    public boolean addUser(User user){
        if (isAvailable(user.getUsername())){
            userDao.addUser(user);
            return true;
        }else{
            return false;
        }
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void removeUser(int userId){
        userDao.removeUser(userId);
    }

    //获取所有用户和其统计信息
    public List getAllUser(){
        return userDao.getAllUser();
    }

    public boolean changePassword(User user, String originalPassword, String newPassword){
        //不从数据库中重新取出密码
        if (!user.getPassword().equals(originalPassword))
            return false;
        user.setPassword(newPassword);
        userDao.updateUser(user);
        return true;
    }

    public void addUserProfileItem(User user, String key, String value){
        UserProfile userProfile = userDao.getUserProfileByMysqlId(user.getId());
        if (userProfile == null){
            userProfile = new UserProfile();
            userProfile.setMysqlId(user.getId());
            userDao.addUserProfile(userProfile);
        }
        String profile = userProfile.getProfile();
        JSONObject json;
        if (profile == null)
            json = new JSONObject();
        else
            json = JSONObject.fromObject(profile);
        json.put(key, value);
        userProfile.setProfile(json.toString());
        userDao.updateUserProfile(userProfile);
    }
    public UserProfile getUserProfileByMysqlId(long mysqlId){
        return userDao.getUserProfileByMysqlId(mysqlId);
    }
    public void removeUserProfileItem(User user, String key){
        UserProfile userProfile = userDao.getUserProfileByMysqlId(user.getId());
        if (userProfile == null){
            return;
        }
        String profile = userProfile.getProfile();
        JSONObject json;
        if (profile == null)
            return;
        else
            json = JSONObject.fromObject(profile);
        json.remove(key);
        userProfile.setProfile(json.toString());
        userDao.updateUserProfile(userProfile);
    }

    public void addUserPhoto(File userPhoto, User user) {
        userDao.updateUserPhoto(userPhoto,user.getUsername());
    }

    public GridFSDBFile getUserPhoto(User user) {
        return userDao.getUserPhoto(user.getUsername());
    }
}
