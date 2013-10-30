package models;

/**
 * A Model for a surfer.
 * @author taylorak
 *
 */
public class Surfer {

  /** The slug related to surfer **/
  private String slug;

  /** The surfer's name **/
  private String name;

  /** The surfer's home **/
  private String home;

  /** The surfer's awards **/
  private String awards;

  /** The surfer's portrait picture **/
  private String carouselUrl;

  /** The surfer's biio picture **/
  private String bioUrl;

  /** The surfer's bio **/
  private String bio;

  /** The surfer's Type **/
  private String type;

  /** The surfer's Footstyle **/
  private String footstyle;

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
      String type, String footstyle) {
    this.slug = slug;
    this.setName(name);
    this.setHome(home);
    this.setAwards(awards);
    this.setCarouselUrl(carouselUrl);
    this.setBioUrl(bioUrl);
    this.setBio(bio);
    this.setType(type);
    this.setFootStyle(footstyle);
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

}
