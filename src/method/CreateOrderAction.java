package method;

import Biz.OrderBiz;
import Entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 47 on 2016/6/10.
 */
public class CreateOrderAction extends ActionSupport {
    private String content;
    OrderBiz orderBiz;

    public OrderBiz getOrderBiz() {
        return orderBiz;
    }
    public void setOrderBiz(OrderBiz orderBiz) {
        this.orderBiz = orderBiz;
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
        User user = (User)hSession.get("user");
        if (hSession.get("shopCart") == null){
            json.put("status", "Empty");
            json.put("message", "购物车为空！");
            content = json.toString();
            return SUCCESS;
        }
        HashMap shopCart = (HashMap)hSession.get("shopCart");
        if (shopCart.size() == 0){
            json.put("status", "Empty");
            json.put("message", "购物车为空！");
            content = json.toString();
            return SUCCESS;
        }
        orderBiz.createOrderFromShopCart(shopCart, user, json);
        content = json.toString();
        shopCart.clear();
        return SUCCESS;
    }
}