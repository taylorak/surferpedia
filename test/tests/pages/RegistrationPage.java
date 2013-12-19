package tests.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;
import play.Play;
// Although Eclipse marks the following two methods as deprecated, 
// the no-arg versions of the methods used here are not deprecated.  (as of May, 2013).
import static org.fluentlenium.core.filter.FilterConstructor.withText; 
import static org.fluentlenium.core.filter.FilterConstructor.withId;  
import static org.fest.assertions.Assertions.assertThat;

/**
 * Illustration of the Page Object Pattern in Fluentlenium.  
 * @author Taylor Kennedy
 */
public class RegistrationPage extends FluentPage {
  private String url;
  
  /**
   * Create the LoginPage.
   * @param webDriver The driver.
   * @param port The port.
   */
  public RegistrationPage(WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/register";
  }
  
  @Override
  public String getUrl() {
    return this.url;
  }
  
  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Registration (surferpedia)");
  }
  
  /**
   * Set the form to the passed values, then submit it.
   * @param name The form name data.
   * @param gender The form gender value. 
   */
  public void register() {
    fill("#first").with("Jane");
    fill("#last").with("Doe");
    fill("#email").with("jane@example.com");
    fill("#password").with("password");
    fill("#confirmPassword").with("password");
    find("#submitRegister").click();
  }
  
  
}
