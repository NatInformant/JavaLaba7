package JavaLaba7.DataSets;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "users")
public class UserProfileDataSet implements Serializable {
    //Моделька пользователя, специально подготовленная для работы hibernate
    private static final long serialVersionUID = -8706689714326132798L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "login", unique = true, updatable = false, nullable = false)
    private String login;
    @Column(name = "password", updatable = false, nullable = false)
    private String pass;
    @Column(name = "email", updatable = false, nullable = false)
    private String email;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UserProfileDataSet() {
    }
    public UserProfileDataSet(String login, String pass, String email) {
        this.login = login;
        this.pass = pass;
        this.email = email;
    }


    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }
}
