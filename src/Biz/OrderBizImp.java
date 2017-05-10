package Biz;

import Dao.BookDao;
import Dao.OrderDao;
import Entity.Order;
import Entity.User;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 47 on 2016/6/6.
 */
public class OrderBizImp implements OrderBiz {
    OrderDao orderDao;
    BookDao bookDao;
    public BookDao getBookDao() {
        return bookDao;
    }
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    public OrderDao getOrderDao() {
        return orderDao;
    }
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderBizImp(){}
    public boolean createOrderFromShopCart(HashMap shopCart, User user, JSONObject json){
       return orderDao.createOrderFromShopCart(shopCart,user,json);
    }
    public Order[] getOrderByDate(Date startDate, Date endDate){
        return orderDao.getOrderByDate(startDate, endDate);
    }

    public List getOrderByUser(User user){
        return orderDao.getOrderByUser(user);
    }

    public Map getTypeByUser(User user){
        return orderDao.getTypeByUser(user.getId());
    }

    public Map<String, Integer> getTypeByDate(Date start,Date end){
        return orderDao.getTypeByDate(start,end);
    }

    public Map<String, Integer> getBookByDate(Date start,Date end){
        return  orderDao.getBookByDate(start, end);
    }
}
