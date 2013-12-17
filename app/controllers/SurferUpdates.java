package controllers;

import java.util.Map;
import models.SurferUpdate;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.CountryTypes;
import views.formdata.SearchFormData;
import views.formdata.SurferTypes;
import views.html.UpdateSurfer;

public class SurferUpdates extends Controller{

  private static final Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);

  private static final Map<String, Boolean> surferTypeMap = SurferTypes.getTypes();
  private static final Map<String, Boolean> countryTypeMap = CountryTypes.getCountryTypes();
  
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
        surferTypeMap, 
        countryTypeMap, 
        searchFormData));
  }
}
