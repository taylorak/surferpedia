package controllers;

import java.util.List;
import java.util.Map;
import models.Surfer;
//import models.SurferDB;
import models.UpdateDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.FootstyleTypes;
import views.formdata.LoginFormData;
import views.formdata.SurferFormData;
import views.formdata.SurferTypes;
import views.html.Index;
import views.html.Login;
import views.html.ManageSurfer;
import views.html.ShowSurfer;
import views.html.UpdateSurfer;
import views.html.SurferList;



/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {

  /**
   * Returns the home page. 
   * @return The resulting home page. 
   */
  public static Result index() {
    return ok(Index.render("Home", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), Surfer.getSurfers()));
  }
  
  /**
   * Creates a new surfer's page.
   * @return The resulting home page. 
   */
  @Security.Authenticated(Secured.class)
  public static Result newSurfer() {
    SurferFormData data = new SurferFormData();
    Form<SurferFormData> formData = Form.form(SurferFormData.class).fill(data);
    Map<String, Boolean> surferTypeMap = SurferTypes.getTypes(data.type);
    List<String> footStyleMap = FootstyleTypes.getFootStyleTypes();
    return ok(ManageSurfer.render("Manage", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData, surferTypeMap, footStyleMap, Surfer.getSurfers(), false));
  }
  
  /**
   * Gets an existing surfer's page.
   * @return The resulting home page. 
   */
  public static Result getSurfer(String slug) {
    return ok(ShowSurfer.render("Surfer", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), Surfer.getSurfer(slug), Surfer.getSurfers()));
  }
  
  /**
   * Gets all surfers. 
   * @return The resulting home page. 
   */
  public static Result getSurfers() {
    return ok(SurferList.render("Surfer List", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), Surfer.getSurfers()));
  }
  
  /**
   * Deletes a surfer's page. 
   * @return The resulting home page. 
   */
  @Security.Authenticated(Secured.class)
  public static Result deleteSurfer(String slug) {
    UpdateDB.addUpdate("delete", Surfer.getSurfer(slug).getName());
    Surfer.deleteSurfer(slug);
    return ok(Index.render("Home", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), Surfer.getSurfers()));
  }
  
  /**
   * Edit a surfer's page. 
   * @return The resulting home page. 
   */
  @Security.Authenticated(Secured.class)
  public static Result manageSurfer(String slug) {
    UpdateDB.addUpdate("edit", Surfer.getSurfer(slug).getName());
    SurferFormData data = new SurferFormData(Surfer.getSurfer(slug));
    Form<SurferFormData> formData = Form.form(SurferFormData.class).fill(data);
    Map<String, Boolean> surferTypeMap = SurferTypes.getTypes(Surfer.getSurfer(slug).getType());
  //  Map<String, Boolean> footStyleMap = FootstyleTypes.getFootStyleTypes(SurferDB.getSurfer(slug).getFootStyle());
    List<String> footStyleMap = FootstyleTypes.getFootStyleTypes();
    return ok(ManageSurfer.render("Manage",Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData, surferTypeMap, footStyleMap, Surfer.getSurfers(), true));
  }
  
  /**
   * Post a surfer's page. 
   * @return The resulting home page. 
   */
  public static Result postSurfer() {
    Form<SurferFormData> formData = Form.form(SurferFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      Map<String, Boolean> typeMap = SurferTypes.getTypes();
      List<String> footStyleMap = FootstyleTypes.getFootStyleTypes();
      return badRequest(ManageSurfer.render("Manage", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData, typeMap, footStyleMap, Surfer.getSurfers(), false));
    } 
    else {
      SurferFormData data = formData.get();
      //Map<String, Boolean> typeMap = SurferTypes.getTypes(data.type);
      //Map<String, Boolean> footStyleMap = FootstyleTypes.getFootStyleTypes(data.footstyle);
      Surfer.addSurfer(data);
      UpdateDB.addUpdate("add", data.name);
      //return ok(NewSurfer.render(formData));
      return redirect(routes.Application.index());
    }
  }
  
  /**
   * A list of Updates.
   * @return The update page
   */
  @Security.Authenticated(Secured.class)
  public static Result updateSurfer() {
    return ok(UpdateSurfer.render("Update", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), Surfer.getSurfers(), UpdateDB.getUpdates()));
  }
  
  /**
   * Provides the Login page (only to unauthenticated users). 
   * @return The Login page. 
   */
  public static Result login() {
    Form<LoginFormData> formData = Form.form(LoginFormData.class);
    return ok(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData, Surfer.getSurfers()));
  }

  /**
   * Processes a login form submission from an unauthenticated user. 
   * First we bind the HTTP POST data to an instance of LoginFormData.
   * The binding process will invoke the LoginFormData.validate() method.
   * If errors are found, re-render the page, displaying the error data. 
   * If errors not found, render the page with the good data. 
   * @return The index page with the results of validation. 
   */
  public static Result postLogin() {

    // Get the submitted form data from the request object, and run validation.
    Form<LoginFormData> formData = Form.form(LoginFormData.class).bindFromRequest();

    if (formData.hasErrors()) {
      flash("error", "Login credentials not valid.");
      return badRequest(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData, Surfer.getSurfers()));
    }
    else {
      // email/password OK, so now we set the session variable and only go to authenticated pages.
      session().clear();
      session("email", formData.get().email);
      return redirect(routes.Application.index());
    }
  }
  
  /**
   * Logs out (only for authenticated users) and returns them to the Index page. 
   * @return A redirect to the Index page. 
   */
  @Security.Authenticated(Secured.class)
  public static Result logout() {
    session().clear();
    return redirect(routes.Application.index());
  }

}
