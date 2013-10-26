package views.formdata;

import java.util.ArrayList;
import java.util.List;
import models.Surfer;
import models.SurferDB;
import play.data.validation.ValidationError;

/**
 * The backing class for form data.
 * @author taylorak
 *
 */
public class SurferFormData {
  
  /** Slug Field **/
  public String slug;
  
  /** Name Field **/
  public String name;
  
  /** Home Field **/
  public String home;
  
  /** Awards Field **/
  public String awards;
  
  /** Carousel Picture URL Field **/
  public String carouselUrl;
  
  /** Bio Picture URL Field **/
  public String bioUrl;
  
  /** Bio Field **/
  public String bio;
  
  /** Type of Surfer Field**/
  public String type;
  
  
  /**
   * The default constructor.
   */
  public SurferFormData() {
    // default no arg constructor
  }
  
  /**
   * Initialize contact form data.
   * @param name
   * @param home
   * @param awards
   * @param carouselUrl
   * @param bioUrl
   * @param bio
   * @param slug
   * @param type
   */
  public SurferFormData(String name, String home, String awards, String carouselUrl, String bioUrl, String bio, String slug,
      String type) {
    this.slug = slug;
    this.name = name;
    this.home = home;
    this.awards = awards;
    this.carouselUrl = carouselUrl;
    this.bioUrl = bioUrl;
    this.bio = bio;
    this.type = type;
  }

  /**
   * Create a surfer form data object based on surfer.
   * @param surfer
   */
  public SurferFormData(Surfer surfer) {
    this.slug = surfer.getSlug();
    this.name = surfer.getName();
    this.home = surfer.getHome();
    this.awards = surfer.getAwards();
    this.carouselUrl = surfer.getCarouselUrl();
    this.bioUrl = surfer.getBioUrl();
    this.bio = surfer.getBio();
    this.type = surfer.getType();
  }
  
  /** Checks if form is valid.
   * @return null if no errors, List of errors if there are.
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();
    
    if (slug == null || slug.length() == 0) {
      errors.add(new ValidationError("slug", "Slug is required."));
    }
    if (SurferDB.getSurfer(slug) == null) {
      errors.add(new ValidationError("slug", "Slug has already been added."));
    }
    if (name == null || name.length() == 0) {
      errors.add(new ValidationError("name", "Name is required."));
    }
    if (home == null || home.length() == 0) {
      errors.add(new ValidationError("home", "Home is required."));
    }
    if (carouselUrl == null || carouselUrl.length() == 0) {
      errors.add(new ValidationError("carouselUrl", "Carousel URL is required."));
    }
    if (bioUrl == null || bioUrl.length() == 0) {
      errors.add(new ValidationError("bioUrl", "Bio URL is required."));
    }
    if (bio == null || bio.length() == 0) {
      errors.add(new ValidationError("bio", "Bio paragraph is required."));
    }
    if (!SurferTypes.isType(type)) {
      errors.add(new ValidationError("type", "Invalid surfer type."));
    }

    return errors.isEmpty() ? null : errors;
  }


}
