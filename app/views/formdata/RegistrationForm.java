package views.formdata;

import javax.persistence.Id;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

public class RegistrationForm {
  
  @Id
  @Email(message="Enter a valid email.")
  @Required(message="Email is required.")
  public String email;
  
  @Required(message="Enter first name.")
  @MaxLength(value=20, message="First name is too long.")
  public String first;
  
  @Required(message="Enter last name.")
  public String last;
  
  @Required(message="Password is required.")
  @MinLength(value=6, message="Password must be at least 6 characters long.")
  public String password;
  
  @Required(message="Confirmation password is required.")
  @MinLength(value=6, message="Password must be at least 6 characters long.")
  public String confirmPassword;
  
  public String validate() {
    if(!password.equals(confirmPassword)) {
        return "Password mismatch";
    }
    return null;
  }
}
