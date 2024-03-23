package JavaLaba7.Service;

import JavaLaba7.Model.Executor;
import JavaLaba7.Model.UserProfile;

public class AccountService {
    //Сервис, что следит за регистрацией пользователей

    public static void addNewUser(UserProfile userProfile) {
        Executor.execUpdate("insert into users(login, password, email) values ('" + userProfile.getLogin()
                + "', '" + userProfile.getPass() + "', '" + userProfile.getEmail() + "')");
    }

    public static UserProfile getUserByLogin(String login) {
        return Executor.execQuery("select * from users u where u.login = '" + login + "'",
                result -> {
                    if (!result.next()){
                        return null;
                    }

                    return new UserProfile(
                            result.getString("login"),
                            result.getString("password"),
                            result.getString("email"));

                });
    }


}
