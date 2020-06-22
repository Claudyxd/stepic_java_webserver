package accounts;

public class AccountServerImpl implements AccountServer {
    private int usersCount;
    private int usersLimit;

    public AccountServerImpl(int limit) {
        usersCount = 0;
        usersLimit = limit;
    }

    public int getUsers() {
        return usersCount;
    }

    public int getUsersLimit() {
        return usersLimit;
    }

    public void setUsersLimit(int limit) {
        usersLimit = limit;
    }

    public void addNewUser() {
        usersCount += 1;
    }

    public void removeUser() {
        usersCount -= 1;
    }

}
