package method;

import Biz.BookBiz;
import Entity.ShopCartItem;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 47 on 2016/6/9.
 */
public class ShowCartAction extends ActionSupport {
    ArrayList<ShopCartItem> arrayList;
    private String content;
    private BookBiz bookBiz;

    public ArrayList<ShopCartItem> getArrayList() {
        return arrayList;
    }
    public void setArrayList(ArrayList<ShopCartItem> arrayList) {
        this.arrayList = arrayList;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public BookBiz getBookBiz() {
        return bookBiz;
    }
    public void setBookBiz(BookBiz bookBiz) {
        this.bookBiz = bookBiz;
    }

    public String execute() throws Exception{
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameters = context.getParameters();
        Map<String, Object> hSession = context.getSession();
        JSONObject json = new JSONObject();
        HashMap<Integer, Integer> hashMap;
        if (hSession.get("shopCart") == null ){
            content = "购物车为空";
            return "finish";
        }else{
            hashMap = (HashMap)hSession.get("shopCart");
        }
        if (hashMap.size() == 0){
            content = "购物车为空";
            return "finish";
        }
        arrayList = bookBiz.getBookDetail(hashMap);
        return "continue";
    }
}
