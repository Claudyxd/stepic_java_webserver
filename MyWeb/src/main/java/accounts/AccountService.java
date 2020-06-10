package accounts;

import dbServices.DBService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AccountService {
    private final DBService dbService;

    public AccountService() {
        dbService = new DBService();
        dbService.createUsersTable();
    }

    public long addNewUser(UserProfile userProfile) {
        try (Session session = dbService.getSession()) {
            return (Long) session.save(userProfile);
        }
    }

    public UserProfile getUserByLogin(String login) throws HibernateException {
        try (Session session = dbService.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserProfile> criteria = builder.createQuery(UserProfile.class);
            Root<UserProfile> root = criteria.from(UserProfile.class);
            criteria.select(root).where(builder.equal(root.get("login"), login));
            Query<UserProfile> query = session.createQuery(criteria);
            return query.getResultList().size() == 0 ? null : query.getSingleResult();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
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
