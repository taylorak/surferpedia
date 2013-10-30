package views.formdata;

import java.util.Arrays;
import java.util.List;

/**
 * List of Legal surfer types.
 * @author Taylor Kennedy
 *
 */
public class FootstyleTypes {

  private static String[] types = {"Regular", "Goofy"};

  /**
   * Initializes list of surfers footstyle.
   * @return typeMap
   */
  public static List<String> getFootStyleTypes() {
    return Arrays.asList(types);
  }

  /**
   * Returns true if footstyle type is a valid surfer type.
   * @param footstyleType
   * @return True if type is legal, False if not.
   */
  public static boolean isType(String footstyleType) {
    return FootstyleTypes.getFootStyleTypes().contains(footstyleType);
  }
}