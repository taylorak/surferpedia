package models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.data.format.Formats.DateTime;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class SurferUpdate extends Model{

  private static final long serialVersionUID = -5154703792152282225L;
  
  @Id
  private long id;
  
  @DateTime(pattern="MM/dd/yyyy HH:mm:ss")
  private Date date;
  
  private String action;
  
  private String surfer;
  
  public static Finder<String,SurferUpdate> find = new Finder<String,SurferUpdate>(
      String.class, SurferUpdate.class
  ); 
  
  public SurferUpdate(Date date, String action, Surfer surfer) {
    this.setDate(date);
    this.setAction(action);
    this.setSurfer(surfer.getName());
  }
  
  /**
   * Creates a new Update.
   * @param formData
   * @return contact
   */
  public static SurferUpdate addUpdate(String action, Surfer surfer) {
    Date date = new Date();
    SurferUpdate update = new SurferUpdate(date, action, surfer);
    update.save();
    return update;
  }
  
  /**
   * Deletes an update from in memory database.
   * @param date
   */
  public static void deleteUpdate(String id) {
    find.ref(id).delete();
  }
  
  /**
   * Return in memory database containing all updates.
   * @return contacts
   */
  public static List<SurferUpdate> getUpdates() {
    return find.all();
  }

  /**
   * Returns update at specified date.
   * @param date
   * @return update
   */
  public static SurferUpdate getUpdate(String id) {
    SurferUpdate update = find.byId(id);
    if (update == null) {
      throw new RuntimeException("Passed a bogus id " + id);
    }
    return update;
  }
  
  /**
   * @return the date
   */
  public Date getDate() {
    return date;
  }
  /**
   * @param date2 the date to set
   */
  public void setDate(Date date) {
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
   * @param string the surfer to set
   */
  public void setSurfer(String string) {
    this.surfer = string;
  }
}
