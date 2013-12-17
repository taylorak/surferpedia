package controllers;

import models.Surfer;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.CountryTypes;
import views.formdata.LoginForm;
import views.formdata.RegistrationForm;
import views.formdata.SearchFormData;
import views.formdata.SurferTypes;
import views.html.Index;
import views.html.Login;
import views.html.Registration;
import views.html.SurferList;

/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {
  
  private static final Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
 
  /**
   * Returns the home page. 
   * @return The resulting home page. 
   */
  public static Result index() {
    return ok(Index.render("Home", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        Surfer.getRandomSurfers(3), 
        SurferTypes.getTypes(), 
        CountryTypes.getCountryTypes(), 
        searchFormData));
  }  
  
  /**
   * Provides the Registration page (only to unauthenticated users). 
   * @return The Registration page. 
   */
  public static Result register() {
    Form<RegistrationForm> registrationFormData = Form.form(RegistrationForm.class);
    return ok(Registration.render("Registration", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        registrationFormData, 
        SurferTypes.getTypes(), 
        CountryTypes.getCountryTypes(), 
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
  public static Result postRegister() {

    Form<RegistrationForm> formData = Form.form(RegistrationForm.class);
    Form<RegistrationForm> filledFormData = formData.bindFromRequest();
    
    if (filledFormData.hasErrors()) {
      //flash("error", "Registration infromation not valid.");
      return badRequest(Registration.render("Registration", 
          Secured.isLoggedIn(ctx()), 
          Secured.getUserInfo(ctx()), 
          filledFormData, 
          SurferTypes.getTypes(), 
          CountryTypes.getCountryTypes(), 
          searchFormData));
    }
    else {
      RegistrationForm data = filledFormData.get();
      User.addUser(data);
      return redirect(routes.Application.index());
    }
  }
  
  /**
   * Provides the Login page (only to unauthenticated users). 
   * @return The Login page. 
   */
  public static Result login() {
    Form<LoginForm> formData = Form.form(LoginForm.class);

    return ok(Login.render("Login", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        formData, 
        SurferTypes.getTypes(), 
        CountryTypes.getCountryTypes(), 
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
          filledFormData, 
          SurferTypes.getTypes(), 
          CountryTypes.getCountryTypes(), 
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
        SurferTypes.getTypes(), 
        CountryTypes.getCountryTypes(), 
        searchFormData));
  }

}
