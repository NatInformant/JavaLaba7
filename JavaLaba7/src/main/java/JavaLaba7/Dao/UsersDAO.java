package JavaLaba7.Dao;

import JavaLaba7.DataSets.UserProfileDataSet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsersDAO {
    //объект отвечающий за получение информации из БД
    public static void addUser(Session session, UserProfileDataSet user) {
        session.save(user);
    }

    public static UserProfileDataSet getUserByLogin(Session session, String login) {
        Criteria criteria = session.createCriteria(UserProfileDataSet.class);
        return (UserProfileDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }
}
