package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import views.formdata.SurferFormData;


/**
 * Database for holding a list of contacts in memory.
 * @author taylorak
 *
 */
public class SurferDB {
  
  private static Map<String, Surfer> surfers = new HashMap<>();
  
  /**
   * Creates a new contact and adds it to in memory database.
   * @param formData
   * @return contact
   */
  public static Surfer addSurfer(SurferFormData formData) {
    String slug = formData.slug;
    Surfer surfer = new Surfer(formData.name, formData.home, formData.awards, formData.carouselUrl, formData.bioUrl, formData.bio, formData.slug, formData.type);
    surfers.put(slug, surfer);
    return surfer;
  }
  
  /**
   * Deletes a surfer from in memory database.
   * @param id
   */
  public static void deleteSurfer(String slug) {
    surfers.remove(slug);
  }
  
  /**
   * Return in memory database containing all surfers.
   * @return surfers
   */
  public static List<Surfer> getSurfers() {
    return new ArrayList<>(surfers.values());
  }

  /**
   * Returns surfer with specified id.
   * @param id
   * @return surfer
   */
  public static Surfer getSurfer(String slug) {
    Surfer contact = surfers.get(slug);
    if (contact == null) {
      throw new RuntimeException("Passed a bogus id " + slug);
    }
    return contact;
  }
  
  /**
   * Check if slug exists.
   * @param slug
   * @return true if contains key false if not
   * */
  public static boolean contains(String slug) {
    return surfers.containsKey(slug);
  }
}
