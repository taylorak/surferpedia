package controllers;

import java.util.Map;
import models.SurferDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.formdata.SurferFormData;
import views.formdata.SurferTypes;
import views.html.Index;
import views.html.ManageSurfer;
import views.html.ShowSurfer;


/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {

  /**
   * Returns the home page. 
   * @return The resulting home page. 
   */
  public static Result index() {
    return ok(Index.render(SurferDB.getSurfers()));
  }
  
  /**
   * Creates a new surfer's page.
   * @return The resulting home page. 
   */
  public static Result newSurfer() {
    SurferFormData data = new SurferFormData();
    Form<SurferFormData> formData = Form.form(SurferFormData.class).fill(data);
    Map<String, Boolean> surferTypeMap = SurferTypes.getTypes(data.type);
    return ok(ManageSurfer.render(formData, surferTypeMap));
  }
  
  /**
   * Gets an existing surfer's page.
   * @return The resulting home page. 
   */
  public static Result getSurfer(String slug) {
    return ok(ShowSurfer.render(SurferDB.getSurfer(slug)));
  }
  
  /**
   * Deletes a surfer's page. 
   * @return The resulting home page. 
   */
  public static Result deleteSurfer(String slug) {
    SurferDB.deleteSurfer(slug);
    return ok(Index.render(SurferDB.getSurfers()));
  }
  
  /**
   * Edit a surfer's page. 
   * @return The resulting home page. 
   */
  public static Result manageSurfer(String slug) {
    return ok(ManageSurfer.render("Welcome to the home page."));
  }
  
  /**
   * Post a surfer's page. 
   * @return The resulting home page. 
   */
  public static Result postSurfer() {
    Form<SurferFormData> formData = Form.form(SurferFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      Map<String, Boolean> typeMap = SurferTypes.getTypes();
      return badRequest(ManageSurfer.render(formData, typeMap));
    } 
    else {
      SurferFormData data = formData.get();
      Map<String, Boolean> typeMap = SurferTypes.getTypes(data.type);
      SurferDB.addSurfer(data);
      //return ok(NewContact.render(formData));
      return redirect("/");
    }
  }
  

}
