package models;

import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import com.avaje.ebean.Page;
import play.db.ebean.Model;
import views.formdata.SurferForm;

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
  
  public static Page<Surfer> page(int size, int page, String name, String country, String surferType) {
    return (surferType == "")? 
        find.where().contains("name", name).contains("country", country)
            .findPagingList(size).getPage(page) : 
        find.where().contains("name", name).contains("country", country).eq("type", surferType)
            .findPagingList(size).getPage(page);
        // return find.where().orderBy("name asc")
        //        .findPagingList(size).getPage(page);
  }

  
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
  public static Surfer addSurfer(SurferForm formData) {
    //String slug = formData.name.toLowerCase().replaceAll("[^a-z0-9-]", "_");
    Surfer surfer;
    if (!contains(formData.slug)){
      surfer = new Surfer(formData.name, formData.home, formData.awards, formData.carouselUrl, formData.bioUrl, formData.bio, formData.slug, formData.type, formData.footstyle,  formData.country);
      surfer.save();
      SurferUpdate.addUpdate("Create", surfer);
    } else {
      surfer = getSurfer(formData.slug);
      surfer.setName(formData.name);
      surfer.setHome(formData.home);
      surfer.setAwards(formData.awards);
      surfer.setCarouselUrl(formData.carouselUrl);
      surfer.setBio(formData.bio);
      surfer.setBioUrl(formData.bioUrl);
      surfer.setType(formData.type);
      surfer.setFootStyle(formData.footstyle);
      surfer.setCountry(formData.country);
      surfer.save();
      SurferUpdate.addUpdate("Edit", surfer);
    }
    return surfer;

  }
  
  /**
   * Deletes a surfer from in memory database.
   * @param id
   */
  public static void deleteSurfer(String slug) {
    SurferUpdate.addUpdate("Delete",find.ref(slug));
    find.ref(slug).delete();
  }
  
  /**
   * Return in memory database containing all surfers.
   * @return surfers
   */
  public static List<Surfer> getSurfers() {
    return find.all();
  }

  /**
   * Return in memory database containing all surfers.
   * @return surfers
   */
  public static List<Surfer> getRandomSurfers(int max) {
    List<Surfer> surfers = Surfer.getSurfers();
    Collections.shuffle(surfers);
    return surfers.subList(0, max);
  }
  
  /**
   * Returns surfer with specified id.
   * @param id
   * @return surfer
   */
  public static Surfer getSurfer(String slug) {
    return find.where().eq("slug", slug).findUnique();
  }
  
  /**
   * Check if slug exists.
   * @param slug
   * @return true if contains key false if not
   * */
  public static boolean contains(String slug) {
    return (getSurfer(slug) != null);
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
