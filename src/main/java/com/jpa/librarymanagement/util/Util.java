package com.jpa.librarymanagement.util;

import com.jpa.librarymanagement.model.BookList;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class Util {

    public List<BookList> getRentalsByUser(EntityManager entityManager, int user_id) {
        String queryStr = "select book_id, book_name, author_name from Book where book_id in (select book_id from Rental where user_id = ?1)";
        try {
            Query query = entityManager.createNativeQuery(queryStr);
            query.setParameter(1, user_id);

            List<Object[]> objectList = query.getResultList();

            List<BookList> result = new ArrayList<>();
            for (Object[] row : objectList) {
                result.add(new BookList(row));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

//    public int getBookIdbyRental(EntityManager entityManager, int rent_id) {
//        String queryStr = "SELECT r.book_id,r.user_id FROM Rental r WHERE r.rent_id = ?1";
//        try {
//            Query query = entityManager.createNativeQuery(queryStr);
//            query.setParameter(1, rent_id);
//            BookId b = new BookId((Object[]) query.getSingleResult());
//
//            return b.getBook_id();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
}
