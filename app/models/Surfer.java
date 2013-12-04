package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import play.db.ebean.Model;
import views.formdata.SurferFormData;

/**
 * A Model for a surfer.
 * @author taylorak
 *
 */
@Entity
public class Surfer extends Model{

  private static final long serialVersionUID = -6751624712871590107L;
  
  /** The slug related to surfer **/
  @Id
  private String slug;

  /** The surfer's name **/
  private String name;

  /** The surfer's home **/
  private String home;

  /** The surfer's awards **/
  private String awards;

  /** The surfer's portrait picture **/
  private String carouselUrl;

  /** The surfer's bio picture **/
  private String bioUrl;

  /** The surfer's bio **/
  @Lob
  private String bio;

  /** The surfer's type **/
  private String type;

  /** The surfer's foot style **/
  private String footstyle;
  
  /** The surfer's country **/
  private String country;
  
  public static Finder<String,Surfer> find = new Finder<String,Surfer>(
      String.class, Surfer.class
    ); 

  /**
   * Constructs a new Surfer.
   * @param name The surfer's name.
   * @param home The surfer's home-town.
   * @param awards The surfer's awards.
   * @param carouselUrl Image URL for carousel.
   * @param bioUrl Image URL for biography.
   * @param bio The surfer's biography.
   * @param slug The surfer's slug field.
   * @param type The surfer's type.
   */
  public Surfer(String name, String home, String awards, String carouselUrl, String bioUrl, String bio, String slug,
      String type, String footstyle, String country) {
    this.slug = slug;
    this.setName(name);
    this.setHome(home);
    this.setAwards(awards);
    this.setCarouselUrl(carouselUrl);
    this.setBioUrl(bioUrl);
    this.setBio(bio);
    this.setType(type);
    this.setFootStyle(footstyle);
    this.setCountry(country);
  }
  
  /**
   * Creates a new contact and adds it to the database.
   * @param formData
   * @return contact
   */
  public static Surfer addSurfer(SurferFormData formData) {
    Surfer surfer = new Surfer(formData.name, formData.home, formData.awards, formData.carouselUrl, formData.bioUrl, formData.bio, formData.slug, formData.type, formData.footstyle,  formData.country);
    surfer.save();
    return surfer;
  }
  
  /**
   * Deletes a surfer from in memory database.
   * @param id
   */
  public static void deleteSurfer(String slug) {
    Surfer.find.ref(slug).delete();
  }
  
  /**
   * Return in memory database containing all surfers.
   * @return surfers
   */
  public static List<Surfer> getSurfers() {
    return Surfer.find.all();
  }

  /**
   * Returns surfer with specified id.
   * @param id
   * @return surfer
   */
  public static Surfer getSurfer(String slug) {
    Surfer surfer = Surfer.find.byId(slug);
    if (surfer == null) {
      throw new RuntimeException("Passed a bogus id " + slug);
    }
    return surfer;
  }
  
  /**
   * Check if slug exists.
   * @param slug
   * @return true if contains key false if not
   * */
  public static boolean contains(String slug) {
    return (Surfer.find.byId(slug) != null)? true : false;
  }
  
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * @return the home
   */
  public String getHome() {
    return home;
  }
  /**
   * @param home the home to set
   */
  public void setHome(String home) {
    this.home = home;
  }
  /**
   * @return the awards
   */
  public String getAwards() {
    return awards;
  }
  /**
   * @param awards the awards to set
   */
  public void setAwards(String awards) {
    this.awards = awards;
  }
  /**
   * @return the carouselUrl
   */
  public String getCarouselUrl() {
    return carouselUrl;
  }
  /**
   * @param carouselUrl the carouselUrl to set
   */
  public void setCarouselUrl(String carouselUrl) {
    this.carouselUrl = carouselUrl;
  }
  /**
   * @return the bioUrl
   */
  public String getBioUrl() {
    return bioUrl;
  }
  /**
   * @param bioUrl the bioUrl to set
   */
  public void setBioUrl(String bioUrl) {
    this.bioUrl = bioUrl;
  }
  /**
   * @return the bio
   */
  public String getBio() {
    return bio;
  }
  /**
   * @param bio the bio to set
   */
  public void setBio(String bio) {
    this.bio = bio;
  }
  /**
   * @return the slug
   */
  public String getSlug() {
    return slug;
  }
  /**
   * @return the type
   */
  public String getType() {
    return type;
  }
  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the footStyle
   */
  public String getFootStyle() {
    return footstyle;
  }

  /**
   * @param footStyle the footStyle to set
   */
  public void setFootStyle(String footstyle) {
    this.footstyle = footstyle;
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
