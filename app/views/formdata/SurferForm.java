package views.formdata;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.Lob;
import models.Surfer;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.Pattern;
import play.data.validation.Constraints.Required;

/**
 * The backing class for form data.
 * @author taylorak
 *
 */
public class SurferForm {
  
  @Id
  @Required(message="Slug is required.")
  @Pattern(value="[0-9a-zA-Z]+", message="Letters and digits to be used.") 
  public String slug;

  @Required(message="Name is required.")
  public String name;

  @Required(message="Home is required.")
  public String home;

 public String awards;

  @Required(message="Carousel image required.")
  public String carouselUrl;

  @Required(message="Bio image required.")
  public String bioUrl;

  @Lob
  @Required(message="Bio paragraph required.")
  public String bio;

  @Required(message="Surfer type required.")
  public String type;

  @Required(message="Footstyle required.")
  public String footstyle;
  
  @Required(message="Country required.")
  public String country;
  
  @Required
  public boolean isEditable;
  
  /**
   * The default constructor.
   */
  public SurferForm() {
    // default no arg constructor
    this.isEditable = false;
  }
  
  /**
   * Initialize surfer form.
   * @param name
   * @param home
   * @param awards
   * @param carouselUrl
   * @param bioUrl
   * @param bio
   * @param slug
   * @param type
   */
  public SurferForm(String name, String home, String awards, String carouselUrl, String bioUrl, String bio, String slug,
      String type, String footstyle, String country) {
    this.slug = slug;
    this.name = name;
    this.home = home;
    this.awards = awards;
    this.carouselUrl = carouselUrl;
    this.bioUrl = bioUrl;
    this.bio = bio;
    this.type = type;
    this.footstyle = footstyle;
    this.country = country;
    this.isEditable = false;
  }

  /**
   * Create a surfer form data object based on surfer.
   * @param surfer
   */
  public SurferForm(Surfer surfer, boolean isEditable) {
    this.slug = surfer.getSlug();
    this.name = surfer.getName();
    this.home = surfer.getHome();
    this.awards = surfer.getAwards();
    this.carouselUrl = surfer.getCarouselUrl();
    this.bioUrl = surfer.getBioUrl();
    this.bio = surfer.getBio();
    this.type = surfer.getType();
    this.footstyle = surfer.getFootStyle();
    this.country = surfer.getCountry();
    this.isEditable = isEditable;
  }

  /** Checks if form is valid.
   * @return null if no errors, List of errors if there are.
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();
    
    if (Surfer.contains(slug) && isEditable == false) {
      errors.add(new ValidationError("slug", "Slug already exists."));
    }
    if (!SurferTypes.isType(type)) {
      errors.add(new ValidationError("type", "Invalid surfer type."));
    }
    if (!FootstyleTypes.isType(footstyle)) {
      errors.add(new ValidationError("footstyle", "Invalid foot style type."));
    }
    return errors.isEmpty() ? null : errors; 
  }

}
