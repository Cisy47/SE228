package method;

import Biz.BookBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Created by 47 on 2016/6/11.
 */
public class ModifyBookAction extends ActionSupport {
    private String content;
    private BookBiz bookBiz;

    public BookBiz getBookBiz() {
        return bookBiz;
    }
    public void setBookBiz(BookBiz bookBiz) {
        this.bookBiz = bookBiz;
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
        if (parameters.get("bookId") == null){
            json.put("status", "Error");
            json.put("status", "错误的参数!");
            content = json.toString();
            return SUCCESS;
        }
        bookBiz.modifyBook(parameters);
        json.put("status", "Success");
        json.put("message", "更改成功！");
        content = json.toString();

        return SUCCESS;
    }

}
