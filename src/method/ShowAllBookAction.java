package method;

import Biz.BookBiz;
import Entity.Book;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

/**
 * Created by 47 on 2016/6/9.
 */
public class ShowAllBookAction extends ActionSupport{
    private Book[] bookArray;
    private BookBiz bookBiz;

    public BookBiz getBookBiz() {
        return bookBiz;
    }

    public void setBookBiz(BookBiz bookBiz) {
        this.bookBiz = bookBiz;
    }

    public Book[] getBookArray() {
        return bookArray;
    }

    public void setBookArray(Book[] bookArray) {
        this.bookArray = bookArray;
    }

    public String execute() throws Exception{
        ActionContext context = ActionContext.getContext();
        Map<String, Object> parameters = context.getParameters();
        bookArray = bookBiz.getAllBook();
        return SUCCESS;
    }
}
