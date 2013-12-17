package controllers;

import java.util.List;
import java.util.Map;
import models.Surfer;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.formdata.CountryTypes;
import views.formdata.FootstyleTypes;
import views.formdata.SearchFormData;
import views.formdata.SurferFormData;
import views.formdata.SurferTypes;
import views.html.Index;
import views.html.ManageSurfer;
import views.html.ShowSurfer;
import views.html.SurferList;

public class Surfers  extends Controller{

  
  private static final Form<SearchFormData> searchFormData = Form.form(SearchFormData.class);
  private static final Form<SurferFormData> surferFormData = Form.form(SurferFormData.class);
  
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
        SurferTypes.getTypes(), 
        CountryTypes.getCountryTypes(), 
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
        FootstyleTypes.getFootStyleTypes(), 
        SurferTypes.getTypes(), 
        CountryTypes.getCountryTypes(), 
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
        SurferTypes.getTypes(), 
        CountryTypes.getCountryTypes(), 
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
        FootstyleTypes.getFootStyleTypes(), 
        filledSurferTypeMap, 
        CountryTypes.getCountryTypes(), 
        searchFormData));
  }
  
  /**
   * Post a surfer's page. 
   * @return The resulting home page. 
   */
  public static Result postSurfer() {
    Form<SurferFormData> filledFormData = surferFormData.bindFromRequest();
    System.out.println(filledFormData);
   // String type = filledFormData.get().type;
   // Map<String, Boolean> surferTypeMap = SurferTypes.getTypes(type);
    if (filledFormData.hasErrors()) {
      return badRequest(ManageSurfer.render("Manage", 
          Secured.isLoggedIn(ctx()), 
          Secured.getUserInfo(ctx()), 
          filledFormData, 
          FootstyleTypes.getFootStyleTypes(), 
          SurferTypes.getTypes(), 
          CountryTypes.getCountryTypes(), 
          searchFormData));
    } else {
      SurferFormData data = filledFormData.get();
      //SurferFormData data = surferFormData.get();
      Surfer.addSurfer(data);
      return redirect(routes.Application.index());
    }
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
