package method;

import Biz.BookBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;

import Entity.Book;
import java.util.Map;

/**
 * Created by 47 on 2016/6/11.
 */
public class BookDetailAction extends ActionSupport {
    private BookBiz bookBiz;
    private String content;

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

    public String execute() throws Exception{
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameters = context.getParameters();
        Map<String, Object> hSession = context.getSession();
        JSONObject json = new JSONObject();
        if (parameters.get("bookId") == null){
            json.put("status", "Error");
            json.put("message", "参数为空！");
            content = json.toString();
            return SUCCESS;
        }
        int bookId = Integer.parseInt(((String[])parameters.get("bookId"))[0]);
        Book book=bookBiz.getBook(bookId);
        json.put("status", "Success");
        json.put("message", "添加成功！");
        json.put("bookname",book.getName());
        json.put("author",book.getAuthor());
        json.put("publisher",book.getPublisher());
        json.put("price",book.getPrice());
        json.put("stock",book.getStock());
        json.put("type",book.getType());
        setContent(json.toString());
        return SUCCESS;
    }
}
