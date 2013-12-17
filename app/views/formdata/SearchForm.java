package views.formdata;


/**
 * The backing class for the search widget.
 * @author Evan Komiyama
 *
 */
public class SearchForm {

  /**Name field**/
  public String name = "";
  
  /**Country field**/
  public String country = "";
  
  /**Surfer type field**/
  public String surferType = "";
  
  public SearchForm() {
    //Do nothing
  }
  
  public SearchForm(String name, String country, String surferType) {
    this.name = name;
    this.country = country;
    this.surferType = surferType;
  }
  
}
