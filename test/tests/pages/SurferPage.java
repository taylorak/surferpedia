package tests.pages;

import models.Surfer;
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
public class SurferPage extends FluentPage {
  private String url;
  
  /**
   * Create the LoginPage.
   * @param webDriver The driver.
   * @param port The port.
   */
  public SurferPage(WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/surfer/" + Surfer.find.findList().get(Surfer.find.findList().size()-1).getSlug();
  }
  
  @Override
  public String getUrl() {
    return this.url;
  }
  
  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("Surfer (surferpedia)");
  }
  
  /**
   * Moves to the surfer update page.
   * @param name The form name data.
   * @param gender The form gender value. 
   */
  public void updateButton() {
   find("#edit").click();
  }
  
  /**
   * Tests the surfer update function using the bio as the changed field.
   * @param name The form name data.
   * @param gender The form gender value. 
   */
  public void updateSurfer() {
    fill("#bio").with("Testing Update");
    find("#submit").click();
  }
  
  /**
   * Deletes a surfer from the database.
   * @param name The form name data.
   * @param gender The form gender value. 
   */
  public void delete() {
    find("#delete").click();
  }
  
}
