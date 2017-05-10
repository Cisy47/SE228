package method;

import Biz.OrderBiz;
import Entity.Order;
import Entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by 47 on 2016/6/9.
 */
public class ShowOrderAction extends ActionSupport {
    private String content;
    List<Order> orderList;
    private OrderBiz orderBiz;

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public List<Order> getOrderList() {
        return orderList;
    }
    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public OrderBiz getOrderBiz() {
        return orderBiz;
    }

    public void setOrderBiz(OrderBiz orderBiz) {
        this.orderBiz = orderBiz;
    }

    public String execute() throws Exception{
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameters = context.getParameters();
        Map<String, Object> hSession = context.getSession();
        JSONObject json = new JSONObject();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        User user = (User)hSession.get("user");
        orderList=orderBiz.getOrderByUser(user);
        if (orderList == null){
            content = "当前无订单!";
            return "finish";
        }
        if (orderList.size() == 0){
            content = "当前无订单!";
            return "finish";
        }
        Order[] orderArray = new Order[orderList.size()];
        orderList.toArray(orderArray);
        Arrays.sort(orderArray);
        orderList = Arrays.asList(orderArray);
        return "continue";
    }

}
