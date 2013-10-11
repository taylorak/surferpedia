package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.Index;
import views.html.Stephanie;
import views.html.LairdHamilton;
import views.html.BethanyHamilton;
import views.html.JakeMarshall;
import views.html.KoloheAndino;
import views.html.StephanieGilmore;


/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {

  /**
   * Returns the home page. 
   * @return The resulting home page. 
   */
  public static Result index() {
    return ok(Index.render("Welcome to the home page."));
  }
  
  /**
   * Returns Laird Hamilton, a simple example of a second page to illustrate navigation.
   * @return Laird Hamilton.
   */
  public static Result laird() {
    return ok(LairdHamilton.render("Laird Hamilton"));
    
  }
  
  /**
   * Returns Bethany Hamilton, a simple example of a second page to illustrate navigation.
   * @return Bethany Hamilton.
   */
  public static Result bethany() {
    return ok(BethanyHamilton.render("Bethany Hamilton"));
    
  }
  
  /**
   * Returns Jake Marshall, a simple example of a second page to illustrate navigation.
   * @return Jake Marshall.
   */
  public static Result jake() {
    return ok(JakeMarshall.render("Jake Marshall"));
    
  }
  
  /**
   * Returns Kolohe Andino, a simple example of a second page to illustrate navigation.
   * @return Kolohe Andino.
   */
  public static Result koloheandino() {
    return ok(KolohAandino.render("Kolohe Andino"));
    
  }
  
  /**
   * Returns the Stephanie Gilmore page
   * @return Stephanie Gilmore
   */
  public static Result stephanie() {
    return ok(StephanieGilmore.render("Stephanie Gilmore"));
  }
}
