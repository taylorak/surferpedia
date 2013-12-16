package views.formdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Surfer;

public class CountryTypes {
  /**
   * Creates a list of all countries in the database
   * @return 
   */
  public static Map<String, Boolean> getCountryTypes() {
    Map<String, Boolean> countryMap = new HashMap<>();
    List<Surfer> countryList = Surfer.getSurfers();
    for(Surfer surfer : countryList) {
      if(! countryMap.containsKey(surfer.getCountry())) {
        countryMap.put(surfer.getCountry(), false);
      }
    }
    return countryMap;
  }
}
