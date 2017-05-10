package Dao;

import Entity.Book;
import method.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;

import java.util.List;

/**
 * Created by 47 on 2016/6/4.
 */
public class BookDaoImp implements BookDao{
    public boolean addBook(Book book) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        return true;
    }

    public Book[] getAllBook() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.getTransaction();
        boolean b=tx.isActive();
        if(!b){
            tx.begin();
        }
        Query query = session.createQuery("from Book");
        List list = query.list();
        Book[] bookArray = new Book[list.size()];
        list.toArray(bookArray);
        tx.commit();
        return bookArray;

    }

    public Book[] searchBook(String content) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "from Book where name like ? or publisher like ? or author like ?";
        Query query = session.createQuery(hql);
        query.setParameter(0, '%' + content + '%', StandardBasicTypes.STRING);
        query.setParameter(1, '%' + content + '%', StandardBasicTypes.STRING);
        query.setParameter(2, '%' + content + '%', StandardBasicTypes.STRING);
        List list = query.list();
        Book[] bookArray = new Book[list.size()];
        list.toArray(bookArray);
        session.getTransaction().commit();
        return bookArray;
    }

    public void removeBook(int bookId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Book book = (Book) session.get(Book.class, bookId);
        if (session.get(Book.class,bookId) == null) {
            return;
        }
        session.delete(book);
        session.getTransaction().commit();
    }

    public Book getBookById(int bookId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "from Book where id=?";
        org.hibernate.Query query = session.createQuery(hql);
        query.setParameter(0, bookId, StandardBasicTypes.INTEGER);
        List<Book> books = query.list();
        session.getTransaction().commit();
        if (books != null && books.size() != 0) {
            return books.get(0);
        } else {
            return null;
        }
    }

    public void updateBook(Book book) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(book);
        session.getTransaction().commit();
    }
}
