package accounts;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserProfile implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    public UserProfile(String login, String pass, String email) {
        setLogin(login);
        setPassword(pass);
        setEmail(email);
    }

    public UserProfile(String login) {
        setLogin(login);
        setPassword(login);
        setEmail(login);
    }

    public UserProfile() {}

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {this.login = login;}

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {this.password = password;}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {this.email = email;}

}
