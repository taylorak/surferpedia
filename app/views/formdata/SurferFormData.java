package views.formdata;

import java.util.ArrayList;
import java.util.List;
import models.Surfer;
//import models.SurferDB;
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
  
  /** Surfer's Footstyle **/
  public String footstyle;
  
  /** The surfer's country **/
  public String country;
  
  /**URL for video of surfer**/
  public String vidUrl;
  
  
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
   * @param vidUrl
   * @param bio
   * @param slug
   * @param type
   */
  public SurferFormData(String name, String home, String awards, String carouselUrl, String bioUrl, String vidUrl, String bio, String slug,
      String type, String footstyle, String country) {
    this.slug = slug;
    this.name = name;
    this.home = home;
    this.awards = awards;
    this.carouselUrl = carouselUrl;
    this.bioUrl = bioUrl;
    this.vidUrl = vidUrl;
    this.bio = bio;
    this.type = type;
    this.footstyle = footstyle;
    this.country = country;
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
    this.vidUrl = surfer.getVidUrl();
    this.bio = surfer.getBio();
    this.type = surfer.getType();
    this.footstyle = surfer.getFootStyle();
    this.country = surfer.getCountry();
  }
  
  /** Checks if form is valid.
   * @return null if no errors, List of errors if there are.
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();
    
    if (slug == null || slug.length() == 0) {
      errors.add(new ValidationError("slug", "Slug is required."));
    }
    if (!slug.matches("[0-9a-zA-Z]+")) {
      errors.add(new ValidationError("slug", "Letters and digits to be used."));
    }
    if (Surfer.contains(slug)) {
      errors.add(new ValidationError("slug", "Slug already exists."));
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
    if (!FootstyleTypes.isType(footstyle)) {
      errors.add(new ValidationError("footstyle", "Invalid foot style type."));
    }
    if (country == null || country.length() == 0) {
      errors.add(new ValidationError("country", "Country is required."));
    }
    return errors.isEmpty() ? null : errors; 
  }

  /**
   * @return the country
   */
  public String getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry(String country) {
    this.country = country;
  }


}
