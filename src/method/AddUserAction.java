package method;

import Biz.UserBiz;
import Entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Created by 47 on 2016/6/11.
 */

public class AddUserAction extends ActionSupport {
    private String content;
    private UserBiz userBiz;

    public UserBiz getUserBiz() {
        return userBiz;
    }
    public void setUserBiz(UserBiz userBiz) {
        this.userBiz = userBiz;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String execute() throws Exception{
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameters = context.getParameters();
        Map<String, Object> hSession = context.getSession();
        JSONObject json = new JSONObject();
        if (parameters.get("username") == null ||
                parameters.get("coin") == null ||
                parameters.get("password")==null||
                parameters.get("mobile") == null ||
                parameters.get("email") == null ||
                parameters.get("role") == null ){
            json.put("status","error");
            json.put("warning","信息不完整！");
            content = json.toString();
            return SUCCESS;
        }
        if (((String[])parameters.get("username"))[0] == "" ||
                ((String[])parameters.get("coin"))[0] == "" ||
                ((String[])parameters.get("password"))[0] == ""||
        ((String[])parameters.get("mobile"))[0] == "" ||
                ((String[])parameters.get("email"))[0] == "" ||
                ((String[])parameters.get("role"))[0] == "" ){
            json.put("status","error");
            json.put("warning","信息不完整！");
            content = json.toString();
            return SUCCESS;
        }
        String username = ((String[])parameters.get("username"))[0];
        String mobile = ((String[])parameters.get("mobile"))[0];
        String email = ((String[])parameters.get("email"))[0];
        String password=((String[])parameters.get("password"))[0];
        int role = Integer.parseInt(((String[])parameters.get("role"))[0]);
        int coin = Integer.parseInt(((String[])parameters.get("coin"))[0]);
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setCoin(coin);
        user.setRole(role);
        userBiz.addUser(user);
        json.put("status", "success");
        json.put("message", "添加成功!");
        content = json.toString();
        return SUCCESS;
    }
}
