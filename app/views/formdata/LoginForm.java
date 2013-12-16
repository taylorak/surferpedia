package views.formdata;

import play.data.validation.Constraints.Required;
import models.User;

/**
 * Backing class for the login form.
 */
public class LoginForm {

  /** The submitted email. */
  @Required
  public String email;
  /** The submitted password. */
  @Required
  public String password;

  /** Required for form instantiation. */
  public LoginForm() {
  }

  /**
   * Validates Form<LoginFormData>.
   * Called automatically in the controller by bindFromRequest().
   * Checks to see that email and password are valid credentials.
   * @return Null if valid, or a List[ValidationError] if problems found.
   */
  public String validate() {
    if(!User.authenticate(email, password)) {
        return "Invalid email or password";
    }
    return null;
  }

}
