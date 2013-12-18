package models;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import com.avaje.ebean.Page;
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
  
  /**URL to a video of surfer**/
  private String vidUrl;
  
  /**Surfer ID**/
  private long id = -1;
  
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
   * @param vidUrl Video URL of surfer.
   * @param bio The surfer's biography.
   * @param slug The surfer's slug field.
   * @param type The surfer's type.
   */
  public Surfer(long id, String name, String home, String awards, String carouselUrl, String bioUrl, String vidUrl, String bio, String slug,
      String type, String footstyle, String country) {
    this.setId(id);
    this.slug = slug;
    this.setName(name);
    this.setHome(home);
    this.setAwards(awards);
    this.setCarouselUrl(carouselUrl);
    this.setBioUrl(bioUrl);
    this.setVidUrl(vidUrl);
    this.setBio(bio);
    this.setType(type);
    this.setFootStyle(footstyle);
    this.setCountry(country);
  }
  
  /**
   * Creates a list of all countries that are in the database.
   * @return 
   */
  public static Map<String, Boolean> getCountries() {
    Map<String, Boolean> countryMap = new HashMap<>();
    List<Surfer> countryList = Surfer.getSurfers();
    for(Surfer surfer : countryList) {
      if(! countryMap.containsKey(surfer.getCountry())) {
        countryMap.put(surfer.getCountry(), false);
      }
    }
    return countryMap;
  }
  
  /**
   * Returns up to three of the most recent surfers added to the application.
   * @return
   */
  public static List<Surfer> getRecentSurfers() {
    
    //Checks to see if there are any out of sync ids and if so, fixes them.
    int index = 0;
    List<Surfer> sortedSurfer = new ArrayList<>();
    List<Surfer> newSurfers = new ArrayList<>();
    sortedSurfer = Surfer.find.where().orderBy("id desc").findList();
    boolean needSort = false;
    while(index < sortedSurfer.size()) {
      if(sortedSurfer.get(index).id == -1) {
        needSort = true;
      }
      index++;
    }
    if(needSort) {
      resortSurferList();
    }
    
    //Takes up to three most recent added surfers
    index = 0;  
    sortedSurfer = Surfer.find.where().orderBy("id desc").findList();
    while(index < 3 && index < sortedSurfer.size()) {
      newSurfers.add(sortedSurfer.get(index));
      index++; 
    }
    
    
    return newSurfers;
  }
  
  /**
   * Resorts surfer list so that surfer ids match total number of surfers in the database
   */
  public static void resortSurferList() {
    int index = 0;
    List<Surfer> sortedSurfer = new ArrayList<>();
    sortedSurfer = Surfer.find.where().orderBy("id").findList();
    while(index < sortedSurfer.size()) {
      sortedSurfer.get(index).setId(index+1);
      sortedSurfer.get(index).save();
      index++;
    }
  }
  
  /**
   * @throws IOException 
   * Determines whether or not a url returns a valid web page.
   * @param url
   * @return
   * @throws  
   */
  public static boolean isValidUrl(String url) throws IOException {
    final URL testingUrl = new URL(url);
    HttpURLConnection huc = (HttpURLConnection) testingUrl.openConnection();
    int responseCode = huc.getResponseCode();
    
    if(responseCode == 200) {
      return true;
    }
    return false;
  }
  
  /**
   * Creates a new contact and adds it to the database.
   * @param formData
   * @return contact
   */
  public static Surfer addSurfer(SurferFormData formData) {
    Surfer surfer;
    if(formData.id == -1) {
    surfer = new Surfer(Surfer.getSurfers().size()+1, formData.name, formData.home, formData.awards, formData.carouselUrl, formData.bioUrl, formData.vidUrl, formData.bio, formData.slug, formData.type, formData.footstyle,  formData.country);
    }else {
    surfer = new Surfer(formData.id, formData.name, formData.home, formData.awards, formData.carouselUrl, formData.bioUrl, formData.vidUrl, formData.bio, formData.slug, formData.type, formData.footstyle,  formData.country);  
    }
    surfer.save();
    return surfer;
  }
  
  /**
   * Deletes a surfer from in memory database.
   * @param id
   */
  public static void deleteSurfer(String slug) {
    Surfer.find.ref(slug).delete();
    resortSurferList();
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


  /**
   * @return the vidUrl
   */
  public String getVidUrl() {
    return vidUrl;
  }


  /**
   * @param vidUrl the vidUrl to set
   */
  public void setVidUrl(String vidUrl) {
    this.vidUrl = vidUrl;
  }


  /**
   * @return the id
   */
  public long getId() {
    return id;
  }


  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
  }

}
