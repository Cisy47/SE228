package Biz;

import Dao.BookDao;
import Entity.Book;
import Entity.ShopCartItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 47 on 2016/6/9.
 */
public class BookBizImp implements BookBiz {
    BookDao bookDao;
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public boolean addBook(Book book){
         return bookDao.addBook(book);
    }

    public Book[] getAllBook(){
        return bookDao.getAllBook();
    }

    public ArrayList getBookDetail(HashMap hashMap){
        Iterator iter = hashMap.entrySet().iterator();
        ArrayList arrayList = new ArrayList();
        while (iter.hasNext()){
            ShopCartItem item = new ShopCartItem();
            Map.Entry entry = (Map.Entry)iter.next();
            item.book = bookDao.getBookById((Integer) entry.getKey());
            item.bookNum = (Integer) entry.getValue();
            arrayList.add(item);
        }
        return arrayList;
    }

    public Book[] searchBook(String content){
        return bookDao.searchBook(content);
    }

    public void modifyBook(Map<String, Object> parameters) {
        int bookId = Integer.parseInt(((String[]) parameters.get("bookId"))[0]);
        Book book = bookDao.getBookById(bookId);
        String str;
        if((str=((String[])parameters.get("type"))[0])!=null){
            book.setType(str);
        }
        if((str=((String[])parameters.get("price"))[0])!=null){
            book.setPrice(Integer.parseInt(str));
        }
        if((str=((String[])parameters.get("stock"))[0])!=null) {
            book.setStock(Integer.parseInt(str));
        }
        bookDao.updateBook(book);
    }

    public void removeBook(int bookId){
        bookDao.removeBook(bookId);
    }

    public Book getBook(int bookId) {
        return bookDao.getBookById(bookId);
    }
}
