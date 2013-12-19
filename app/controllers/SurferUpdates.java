package controllers;

import models.SurferUpdate;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.CountryTypes;
import views.formdata.SearchForm;
import views.formdata.SurferTypes;
import views.html.UpdateSurfer;

/**
 * Implements the update controllers for this application.
 */
public class SurferUpdates extends Controller{

  private static final Form<SearchForm> searchFormData = Form.form(SearchForm.class);

  
  /**
   * A list of Updates.
   * @return The update page
   */
  @Security.Authenticated(Secured.class)
  public static Result index() {
    return ok(UpdateSurfer.render("Update", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        SurferUpdate.getUpdates(), 
        SurferTypes.getTypes(), 
        CountryTypes.getCountryTypes(), 
        searchFormData));
  }
}
