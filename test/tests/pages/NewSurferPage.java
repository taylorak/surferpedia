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
public class NewSurferPage extends FluentPage {
  private String url;
  
  /**
   * Create the LoginPage.
   * @param webDriver The driver.
   * @param port The port.
   */
  public NewSurferPage(WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "/new";
  }
  
  @Override
  public String getUrl() {
    return this.url;
  }
  
  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("New (surferpedia)");
  }
  
  /**
   * Navigate to new surfer page.
   */
  public void goTonewSurferPage() {
    find("#admindropdown").click();
    find("#newSurfer").click();
  }
  
  /**
   * Fills in the new surfer form and adds a new surfer to the database.
   */
  public void addNewSurfer() {
    fill("#name").with("Evan Komiyama");
    fill("#home").with("Hawaii");
    fill("#carouselUrl").with("http://www.toaheadphones.com/athletes/maliamanuel/gallery/MaliaManuel_MLD3702_DEF%20VERSIE2.jpg");
    fill("#bioUrl").with("http://waxedradio.com/wp-content/uploads/2013/04/154.jpg");
    fill("#bio").with("testing");
    fill("#slug").with("evankomiyama");
    find("#type").click();
    find("#Male").click();
    find("#Regular").click();
    find("#submit").click();
  }
  
}
