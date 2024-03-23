package JavaLaba7.Service;

import JavaLaba7.DataSets.UserProfileDataSet;
import JavaLaba7.Dao.UsersDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static JavaLaba7.Service.MyAppLifecycleListener.sessionFactory;

public class DBService {
    //Сервис, что следит за регистрацией пользователей

    public static void addNewUser(UserProfileDataSet userProfileDataSet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UsersDAO.addUser(session,userProfileDataSet);
        transaction.commit();
        session.close();
    }

    public static UserProfileDataSet getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        UserProfileDataSet result =  UsersDAO.getUserByLogin(session, login);
        session.close();

        return result;
    }


}
