package models;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.AssertFalse;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

/**
 * A simple representation of a user. 
 * @author Philip Johnson
 */
@Entity
public class User extends Model {

  private static final long serialVersionUID = 514181764033756576L;
  
  @Id
  private String email;
  
  private String first;
  
  private String last;
  
  private String password;
  
  private boolean admin;
  
  /**
   * Creates a new UserInfo instance.
   * @param name The name.
   * @param email The email.
   * @param password The password.
   */
  public User(String first, String last, String email, String password) {
    this.first = first;
    this.last = last;
    this.email = email;
    this.password = password;
  }
  
  /**
   * The EBean ORM finder method for database queries on LastTimeStamp.
   * @return The finder method for products.
   */  
  public static Finder<String,User> find = new Finder<String,User>(
      String.class, User.class
    ); 
  
  
  /**
   * Defines the admin account if values are non-null.
   * @param name Their name.
   * @param email Their email.
   * @param password Their password. 
   */
  public static void defineAdmin(String first, String last, String email, String password) {
    if(email != null && password != null) {
      User user = new User(first, last, email, password);
      user.setAdmin(true);
      user.save();
    }
  }
  
  /**
   * Returns true if there is an admin.
   * @return
   */
  public static boolean adminDefined() {
    User user = find.where().eq("admin", true).findUnique();
    return user != null;
  }
  
  /**
   * Adds the specified user to the UserInfoDB.
   * @param name Their name.
   * @param email Their email.
   * @param password Their password. 
   */
  public static void addUser(String first, String last, String email, String password) {
    User user = new User(first, last, email, password);
    user.save();
  }
  
  /**
   * Returns the UserInfo associated with the email, or null if not found.
   * @param email The email.
   * @return The UserInfo.
   */
  public static User getUser(String email) {
    return find.where().eq("email", email).findUnique();
  }

  /**
   * Returns true if email and password are valid credentials.
   * @param email The email. 
   * @param password The password. 
   * @return True if email is a valid user email and password is valid for that email.
   */
  public static boolean authenticate(String email, String password) {
        return find.where()
            .eq("email", email)
            .eq("password", password)
            .findUnique() != null;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }
  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }
  
  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }
  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
  
  /**
   * @return the admin
   */
  public boolean isAdmin() {
    return admin;
  }

  /**
   * @param admin the admin to set
   */
  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  /**
   * @return the first
   */
  public String getFirst() {
    return first;
  }

  /**
   * @param first the first to set
   */
  public void setFirst(String first) {
    this.first = first;
  }

  /**
   * @return the last
   */
  public String getLast() {
    return last;
  }

  /**
   * @param last the last to set
   */
  public void setLast(String last) {
    this.last = last;
  }

}