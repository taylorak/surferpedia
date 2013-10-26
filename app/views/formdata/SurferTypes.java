package views.formdata;

import java.util.HashMap;
import java.util.Map;

/**
 * List of Legal surfer types.
 * @author Taylor Kennedy
 *
 */
public class SurferTypes {

  private static String[] types = {"Male", "Female", "Grom"};

  /**
   * Initializes map of surfers.
   * @return typeMap
   */
  public static Map<String, Boolean> getTypes() {
    Map<String, Boolean> typeMap = new HashMap<>();
    for (String type : types) {
      typeMap.put(type, false);
    }
    return typeMap;
  }

  /**
   * Returns map of surfer with selected surfer mapped to true.
   * Assumes surferType is a legal type.
   * @param surferType
   * @return typeMap
   */
  public static Map<String, Boolean> getTypes(String surferType) {
    Map<String, Boolean> typeMap = SurferTypes.getTypes();
    if (isType(surferType)) {
      typeMap.put(surferType, true);
    }
    return typeMap;
  }

  /**
   * Returns true if surferType is a valid surfer type.
   * @param type
   * @return True if type is legal, False if not.
   */
  public static boolean isType(String surferType) {
    return SurferTypes.getTypes().keySet().contains(surferType);
  }
}