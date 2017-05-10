package method;

import Biz.UserBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Created by 47 on 2016/6/10.
 */
public class ModifyUserAction extends ActionSupport {
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

    public String execute() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameters = context.getParameters();
        Map<String, Object> hSession = context.getSession();
        JSONObject json = new JSONObject();
        if (parameters.get("userId") == null){
            json.put("status", "Error");
            json.put("status", "错误的参数!");
            content = json.toString();
            return SUCCESS;
        }
        if (parameters.get("role") != null){
            if (((String[])parameters.get("role"))[0] != ""){
                int temp = Integer.parseInt(((String[])parameters.get("role"))[0]);
                if (temp !=1&&temp!=2){
                    parameters.remove("role");
                }
            }
        }
        userBiz.ModifyUser(parameters);
        json.put("status", "Success");
        json.put("message", "更改成功！");
        content = json.toString();
        return SUCCESS;
    }

}
