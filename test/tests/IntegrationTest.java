package tests;

import org.junit.Test;
import play.test.TestBrowser;
import play.libs.F.Callback;
import tests.pages.LoginPage;
import tests.pages.NewSurferPage;
import tests.pages.SurferListPage;
import tests.pages.SurferPage;
//import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.FIREFOX;
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
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        browser.goTo("http://localhost:3333");
        assertThat(browser.pageSource()).contains("Home");
      }
    });
  }
  
  /**
   * Check to see login page logs the user in and user can logout.
   */
  @Test
  public void testLogin() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        LoginPage loginpage = new LoginPage(browser.getDriver(), PORT);
        browser.goTo(loginpage);
        loginpage.isAt();
        loginpage.login();
       assertThat(browser.pageSource()).contains("Home");
       loginpage.logout();
      }
    });
  }
  
  /**
   * Check to see that a page of surfers is given.
   */
  @Test
  public void testSurferList() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        SurferListPage surferspage = new SurferListPage(browser.getDriver(), PORT);
        browser.goTo(surferspage);
        surferspage.isAt();
        assertThat(browser.pageSource()).contains("Surfer List");
      }
    });
  }
  
  /**
   * Check to see if a surfer field can be updated.
   */
  @Test
  public void testSurferUpdate() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        LoginPage loginpage = new LoginPage(browser.getDriver(), PORT);
        browser.goTo(loginpage);
        loginpage.isAt();
        loginpage.login();
        SurferPage surferspage = new SurferPage(browser.getDriver(), PORT);
        browser.goTo(surferspage);
        surferspage.isAt();
        surferspage.updateButton();
       surferspage.updateSurfer();
       assertThat(browser.pageSource()).contains("Home");
      }
    });
  }
  
  /**
   * Check to see if a surfer can be deleted.
   */
  @Test
  public void testSurferDelete() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) throws InterruptedException {
        LoginPage loginpage = new LoginPage(browser.getDriver(), PORT);
        browser.goTo(loginpage);
        loginpage.isAt();
        loginpage.login();
        SurferPage surferspage = new SurferPage(browser.getDriver(), PORT);
        browser.goTo(surferspage);
        surferspage.isAt();
        surferspage.delete();
      }
    });
  }
  
  /**
   * Check to see if a new surfer can be added.
   */
  @Test
  public void testNewSurfer() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) throws InterruptedException {
        LoginPage loginpage = new LoginPage(browser.getDriver(), PORT);
        browser.goTo(loginpage);
        loginpage.isAt();
        loginpage.login();
        NewSurferPage newSurferPage = new NewSurferPage(browser.getDriver(), PORT);
        newSurferPage.goTonewSurferPage();
        newSurferPage.goTonewSurferPage();
        newSurferPage.isAt();
        assertThat(browser.pageSource()).contains("New");
        newSurferPage.addNewSurfer();
        
      }
    });
  }
  
  /**
   * Check the search bar function. Uses Jake Marshall as an example search.
   */
  @Test
  public void testSearch() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) throws InterruptedException {
         browser.goTo("http://localhost:3333");
         browser.fill("#name").with("Jake Marshall");
         browser.find("#searchSubmit").click();
         assertThat(browser.pageSource()).contains("Jake Marshall");
      }
    });
  }
  
  /**
   * 
   */
  @Test
  public void testGetUpdateList() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) throws InterruptedException {
        LoginPage loginpage = new LoginPage(browser.getDriver(), PORT);
        browser.goTo(loginpage);
        loginpage.isAt();
        loginpage.login();
        browser.find("#admindropdown").click();
        browser.find("#update").click();
        assertThat(browser.pageSource()).contains("Update");
        
      }
    });
  }

}
