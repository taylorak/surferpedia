package test;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;
import models.Surfer;
import org.junit.Test;
import controllers.Secured;
import play.libs.F.Callback;
import play.mvc.Content;
import play.test.TestBrowser;

public class SurferTest {

  /** The port number for the integration test. */
  private static final int PORT = 3333;
  
  @Test
  public void checkHomePage() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
    public void invoke(TestBrowser browser) {
    browser.goTo("http://localhost:3333");
    assertThat(browser.pageSource()).contains("Home");
    }
    });
  }
  
  @Test
  public void checkLoginPage() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
      browser.goTo("http://localhost:3333/login");
      assertThat(browser.pageSource()).contains("Home");
      }
      });
  }

  
}
