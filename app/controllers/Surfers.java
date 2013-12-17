package controllers;

import java.util.Map;
import models.Surfer;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.CountryTypes;
import views.formdata.FootstyleTypes;
import views.formdata.SearchForm;
import views.formdata.SurferForm;
import views.formdata.SurferTypes;
import views.html.Index;
import views.html.ManageSurfer;
import views.html.ShowSurfer;
import views.html.SurferList;

/**
 * Implements the surfer controllers for this application.
 */
public class Surfers  extends Controller{

  
  private static final Form<SearchForm> searchForm = Form.form(SearchForm.class);
  private static final Form<SurferForm> surferForm = Form.form(SurferForm.class);
  
  /**
   * Gets all surfers. 
   * @return The resulting home page. 
   */
  public static Result index(int page, String name, String country, String surferType) {
    return ok(SurferList.render("Surfer List", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        Surfer.page(5,page,name,country,surferType), 
        SurferTypes.getTypes(), 
        CountryTypes.getCountryTypes(), 
        searchForm));
  }
  
  /**
   * Gets an existing surfer's page.
   * @return The surfer's page. 
   */
  public static Result getSurfer(String slug) {
    return ok(ShowSurfer.render("Surfer", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        Surfer.getSurfer(slug), 
        SurferTypes.getTypes(), 
        CountryTypes.getCountryTypes(), 
        searchForm));
  }
  
  /**
   * Creates a new surfer's page.
   * @return The new surfer form. 
   */
  @Security.Authenticated(Secured.class)
  public static Result newSurfer() {
    
    Form<SurferForm> filledSurferForm = surferForm.fill(new SurferForm());

    return ok(ManageSurfer.render("New", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        filledSurferForm, 
        FootstyleTypes.getFootStyleTypes(), 
        SurferTypes.getTypes(), 
        CountryTypes.getCountryTypes(), 
        searchForm));
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
        SurferTypes.getTypes(), 
        CountryTypes.getCountryTypes(), 
        searchForm));
  }
  
  /**
   * Edit a surfer's page. 
   * @return The edit surfer form. 
   */
  @Security.Authenticated(Secured.class)
  public static Result manageSurfer(String slug) {
    SurferForm data = new SurferForm(Surfer.getSurfer(slug),true);
    Form<SurferForm> filledSurferForm = surferForm.fill(data);
    Map<String, Boolean> filledSurferTypeMap = SurferTypes.getTypes(data.type);
    return ok(ManageSurfer.render("Manage",
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        filledSurferForm, 
        FootstyleTypes.getFootStyleTypes(), 
        filledSurferTypeMap, 
        CountryTypes.getCountryTypes(), 
        searchForm));
  }
  
  /**
   * Post a surfer's page. 
   * @return The resulting home page, or edit form on error. 
   */
  @Security.Authenticated(Secured.class)
  public static Result postSurfer() {
    Form<SurferForm> filledSurferForm = surferForm.bindFromRequest();
    String type = filledSurferForm.data().get("type");
    if (filledSurferForm.hasErrors()) {
      return badRequest(ManageSurfer.render("Manage", 
          Secured.isLoggedIn(ctx()), 
          Secured.getUserInfo(ctx()), 
          filledSurferForm, 
          FootstyleTypes.getFootStyleTypes(), 
          SurferTypes.getTypes(type), 
          CountryTypes.getCountryTypes(), 
          searchForm));
    } 
    else {
      SurferForm data = filledSurferForm.get();
      Surfer.addSurfer(data);
      return redirect(routes.Application.index());
    }
  }
  
  /**
   * Searches for surfers.
   * @return The resulting list of surfers who satisfy the search.
   */
  public static Result surferSearch() {
    Form<SearchForm> formData = searchForm.bindFromRequest();
    SearchForm data = formData.get();
    return ok(SurferList.render("Search", 
        Secured.isLoggedIn(ctx()), 
        Secured.getUserInfo(ctx()), 
        Surfer.page(5, 0, data.name, data.country, data.surferType), 
        SurferTypes.getTypes(), 
        CountryTypes.getCountryTypes(), 
        searchForm));
  }
}
