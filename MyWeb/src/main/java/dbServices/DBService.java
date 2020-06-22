package dbServices;

import org.hibernate.Session;

public interface DBService {

    Session getSession();
    void createUsersTable();
}
