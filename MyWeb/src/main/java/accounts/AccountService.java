package accounts;

import dbServices.DBService;
import dbServices.DBServiceImpl;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AccountService {
    private final DBService dbService;

    public AccountService() {
        dbService = DBServiceImpl.getDBService();
        dbService.createUsersTable();
    }

    public void addNewUser(UserProfile userProfile) {
        try (Session session = dbService.getSession()) {
            session.save(userProfile);
        }
    }

    public UserProfile getUserByLogin(String login) {
        Session session = dbService.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserProfile> criteria = builder.createQuery(UserProfile.class);
        Root<UserProfile> root = criteria.from(UserProfile.class);
        criteria.select(root).where(builder.equal(root.get("login"), login));
        Query<UserProfile> query = session.createQuery(criteria);
        return query.getResultList().isEmpty() ? null : query.getSingleResult();
    }

    //    public UserProfile getUserBySessionId(String sessionId) {
    //        return sessionIdToProfile.get(sessionId);
    //    }

    //    public void addSession(String sessionId, UserProfile userProfile) {
    //        sessionIdToProfile.put(sessionId, userProfile);
    //    }

    //    public void deleteSession(String sessionId) {
    //        sessionIdToProfile.remove(sessionId);
    //    }
}
