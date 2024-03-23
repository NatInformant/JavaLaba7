package JavaLaba7.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler<T> {
    T handle(ResultSet result) throws SQLException;
}
