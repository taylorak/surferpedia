package controllers;

import java.util.List;
import java.util.Map;
import models.Surfer;
import models.SurferUpdate;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.CountryTypes;
import views.formdata.FootstyleTypes;
import views.formdata.LoginForm;
import views.formdata.SearchFormData;
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
  
  private static final Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
  private static final Form<SurferFormData> surferFormData = Form.form(SurferFormData.class);

  private static final Map<String, Boolean> surferTypeMap = SurferTypes.getTypes();
  private static final Map<String, Boolean> countryTypeMap = CountryTypes.getCountryTypes();
  private static final List<String> footStyleMap = FootstyleTypes.getFootStyleTypes();
  

  
  /**
   * Returns the home page. 
   * @return The resulting home page. 
   */
  public static Result index() {
    return ok(Index.render("Home", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        Surfer.getRandomSurfers(3), 
        surferTypeMap, 
        countryTypeMap, 
        searchFormData));
  }
  
  /**
   * Creates a new surfer's page.
   * @return The resulting home page. 
   */
  @Security.Authenticated(Secured.class)
  public static Result newSurfer() {
    
    Form<SurferFormData> filledSurferFormData = surferFormData.fill(new SurferFormData());

    return ok(ManageSurfer.render("Manage", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        filledSurferFormData, 
        footStyleMap, 
        false, 
        surferTypeMap, 
        countryTypeMap, 
        searchFormData));
  }
  
  /**
   * Gets an existing surfer's page.
   * @return The resulting home page. 
   */
  public static Result getSurfer(String slug) {
    return ok(ShowSurfer.render("Surfer", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        Surfer.getSurfer(slug), 
        surferTypeMap, 
        countryTypeMap, 
        searchFormData));
  }
  
  /**
   * Gets all surfers. 
   * @return The resulting home page. 
   */
  public static Result getSurfers(int page, String name, String country, String surferType) {
    return ok(SurferList.render("Surfer List", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        Surfer.page(5,page,name,country,surferType), 
        surferTypeMap, 
        countryTypeMap, 
        searchFormData));
  }
  
  /**
   * Deletes a surfer's page. 
   * @return The resulting home page. 
   */
  @Security.Authenticated(Secured.class)
  public static Result deleteSurfer(String slug) {
    Surfer.deleteSurfer(slug);
    return ok(Index.render("Home", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        Surfer.getRandomSurfers(3), 
        surferTypeMap, 
        countryTypeMap, 
        searchFormData));
  }
  
  /**
   * Edit a surfer's page. 
   * @return The resulting home page. 
   */
  @Security.Authenticated(Secured.class)
  public static Result manageSurfer(String slug) {
    SurferFormData data = new SurferFormData(Surfer.getSurfer(slug),true);
    Form<SurferFormData> filledFormData = surferFormData.fill(data);
    Map<String, Boolean> filledSurferTypeMap = SurferTypes.getTypes(data.type);
    return ok(ManageSurfer.render("Manage",
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        filledFormData, 
        footStyleMap, 
        true, 
        filledSurferTypeMap, 
        countryTypeMap, 
        searchFormData));
  }
  
  /**
   * Post a surfer's page. 
   * @return The resulting home page. 
   */
  public static Result postSurfer() {
    Form<SurferFormData> filledFormData = surferFormData.bindFromRequest();
    System.out.println(filledFormData.errors());
    if (filledFormData.hasErrors()) {
      return badRequest(ManageSurfer.render("Manage", 
          Secured.isLoggedIn(ctx()), 
          Secured.getUserInfo(ctx()), 
          filledFormData, 
          footStyleMap, 
          false, 
          surferTypeMap, 
          countryTypeMap, 
          searchFormData));
    } else {
      SurferFormData data = filledFormData.get();
      //SurferFormData data = surferFormData.get();
      Surfer.addSurfer(data);
      return redirect(routes.Application.index());
    }
  }
  
  /**
   * A list of Updates.
   * @return The update page
   */
  @Security.Authenticated(Secured.class)
  public static Result updateSurfer() {
    return ok(UpdateSurfer.render("Update", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        SurferUpdate.getUpdates(), 
        surferTypeMap, 
        countryTypeMap, 
        searchFormData));
  }
  
  /**
   * Provides the Login page (only to unauthenticated users). 
   * @return The Login page. 
   */
  public static Result login() {
    Form<LoginForm> formData = Form.form(LoginForm.class);
    //Form<User> formData = Form.form(User.class);

    return ok(Login.render("Login", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        formData, 
        surferTypeMap, 
        countryTypeMap, 
        searchFormData));
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
    Form<LoginForm> formData = Form.form(LoginForm.class);
    Form<LoginForm> filledFormData = formData.bindFromRequest();

    if (filledFormData.hasErrors()) {
      flash("error", "Login credentials not valid.");
      return badRequest(Login.render("Login", 
          Secured.isLoggedIn(ctx()), 
          Secured.getUserInfo(ctx()), 
          formData, 
          surferTypeMap, 
          countryTypeMap, 
          searchFormData));
    }
    else {
      // email/password OK, so now we set the session variable and only go to authenticated pages.
      session().clear();
      session("email", filledFormData.get().email);
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
  
  public static Result surferSearch() {
    Form<SearchFormData> formData = searchFormData.bindFromRequest();
    SearchFormData data = formData.get();
    return ok(SurferList.render("Search", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        Surfer.page(5,0,data.name,data.country,data.surferType), 
        surferTypeMap, 
        countryTypeMap, 
        searchFormData));
  }

}
