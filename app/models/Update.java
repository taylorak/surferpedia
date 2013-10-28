package models;

public class Update {
  private String date;
  private String action;
  private String surfer;
  
  public Update(String date, String action, String surfer) {
    this.setDate(date);
    this.setAction(action);
    this.setSurfer(surfer);
  }
  /**
   * @return the date
   */
  public String getDate() {
    return date;
  }
  /**
   * @param date the date to set
   */
  public void setDate(String date) {
    this.date = date;
  }
  /**
   * @return the action
   */
  public String getAction() {
    return action;
  }
  /**
   * @param action the action to set
   */
  public void setAction(String action) {
    this.action = action;
  }
  /**
   * @return the surfer
   */
  public String getSurfer() {
    return surfer;
  }
  /**
   * @param surfer the surfer to set
   */
  public void setSurfer(String surfer) {
    this.surfer = surfer;
  }
}
