package tests;

import org.junit.Test;
import play.test.TestBrowser;
import play.libs.F.Callback;
import tests.pages.LoginPage;
import tests.pages.SurferListPage;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.testServer;
import static play.test.Helpers.running;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Integration tests running on an instance of the application.
 */
public class IntegrationTest {
  /** The port number for the integration test. */
  private static final int PORT = 3333;

  /**
   * Check to see that the two pages can be displayed.
   */
  @Test
  public void test() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        browser.goTo("http://localhost:3333");
        assertThat(browser.pageSource()).contains("Home");;
      }
    });
  }
  
  /**
   * Check to see login page logs the user in.
   */
  @Test
  public void testLogin() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        //browser.goTo("http://localhost:" + PORT + "/login");
        LoginPage loginpage = new LoginPage(browser.getDriver(), PORT);
        browser.goTo(loginpage);
        loginpage.isAt();
        loginpage.login();
        assertThat(browser.pageSource()).contains("Home");
        SurferListPage surferspage = new SurferListPage(browser.getDriver(), PORT);
        browser.goTo(surferspage);
        surferspage.isAt();
        loginpage.logout();
        assertThat(browser.pageSource()).contains("Home");
        

      }
    });
  }
  
  /**
   * Check to see that a page of surfers is given.
   */
  @Test
  public void testSurferList() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        //browser.goTo("http://localhost:" + PORT + "/surfers");
        SurferListPage surferspage = new SurferListPage(browser.getDriver(), PORT);
        browser.goTo(surferspage);
        surferspage.isAt();
      }
    });
  }

}
