import play.GlobalSettings;
import play.Play;
import models.Surfer;
import models.UserInfo;
//import models.SurferDB;
import models.UserInfoDB;
import play.Application;
import views.formdata.SurferFormData;

/**
 * Initialize the contact table.
 * @author taylorak
 * @param Application app
 * @returns the application
 */
public class Global extends GlobalSettings {

  public void onStart(Application app) {

    String adminEmail = Play.application().configuration().getString("surferpedia.admin.email");
    String adminPassword = Play.application().configuration().getString("surferpedia.admin.password");

    UserInfo admin = UserInfoDB.getUser(adminEmail);

    if (admin == null) {

      UserInfoDB.defineAdmin("Administrator", adminEmail, adminPassword);
      
      if (UserInfoDB.adminDefined()) {

       UserInfoDB.addUserInfo("John Smith", "smith@example.com", "password");
       Surfer.addSurfer(new SurferFormData("Laird Hamilton", "San Fransisco, California", "",
            "http://swellseekers.ie/home/wp-content/themes/classifiedstheme/thumbs//laird-hamilton-surf.jpg",
            "http://assets-s3.mensjournal.com/img/article/laird-hamiltons-high-performance-breathing/298_298_laird-hamiltons-high-performance-breathing.jpg",
            "Laird was born Laird John Zerfas in San Francisco on March 2, 1964, in an experimental salt-water sphere"
                + " at UCSF Medical Center designed to ease the mother's labor. His Greek birth father, L. G. Zerfas, left the"
                + " family before his first birthday. While he was an infant, Laird and his mother, Joann, moved to Hawaii. While"
                + " still a young boy living on Oahu, Laird met with 1960s surfer Bill Hamilton, a bachelor at the time, on"
                + " Pupukea beach on the North Shore. Bill Hamilton was a surfboard shaper and glasser on Oahu in the 1960s and"
                + " 1970s and owned a small business handmaking custom, high-performance surfboards for the Oahu North Shore big"
                + " wave riders of the era. The two became immediate companions. The young Laird invited Bill Hamilton home to"
                + " meet his mother. Bill Hamilton married Laird's then-single mother, becoming Laird's adoptive father.",
                "lairdhamilton", "Male", "Regular", "USA"));

        Surfer.addSurfer(new SurferFormData("Bethany Hamilton", "Lihue, Hawaii", "",
            "http://upload.wikimedia.org/wikipedia/commons/a/a4/Bethany_Hamilton_20070311.jpg",
            "http://blog.zap2it.com/frominsidethebox/bethany-hamilton-acm.jpg",
            "Bethany Hamilton is an American professional surfer. She is known for surviving a"
                + " shark attack in which her left arm was bitten off, and for overcoming the injury"
                + " to ultimately return to professional surfing. She wrote about her experience in the" 
                + " 2004 autobiography Soul Surfer: A True Story of Faith, Family, and Fighting to Get Back" 
                + " on the Board. In April 2011, the feature film Soul Surfer was released, based on the book"
                + " and additional interviews. She has appeared on many television shows since the loss of her arm.",
                "bethanyhamilton", "Female", "Regular", "USA"));

        Surfer.addSurfer(new SurferFormData("Jake Marshall", "San Diego, California", "",
            "http://cdn.surf.transworld.net/wp-content/blogs.dir/443/files/2012/06/Jake-Marshall.jpg",
            "http://www.surfingamerica.org/wp-content/uploads/2011/01/JakeMarshall_AM7H9425-2.jpg",
            "Many young surfers have the potential to make an impact on our sport, but none look more poised to do so"
                + "than Jake Marshall. Raised on the rippable beachbreaks and reefs of San Diego’s North County, Jake has"
                + "developed a solid base of smooth rail work as well as the kind of flair that few 14-year-old surfers can"
                + "match. His progression is due in no small part to the numerous world-class talents in his neighborhood that"
                + "have taught him a thing or two about tearing Seaside apart. “I see Rob Machado, Josh Kerr, Damien Hobgood, and"
                + "Taylor Knox out all the time, and it’s really fun to surf with guys on that level,” says Jake. “I look at them"
                + " and think, ‘That’s how I have to surf if I want to make the Tour one day.’ That really helps me push my"
                + " limits.” Already, he’s had remarkable success in a jersey, including a recent win at the U.S. Surfing"
                + " Championships at Lower Trestles. But while surf stardom seems inevitable for Jake, he’s still just a kid, and"
                + " knows where his priorities should be. “I surf and try to improve all the time, but I still put a lot of"
                + " emphasis on school,” says Jake. “I know that education is really important, and if surfing doesn’t work out,"
                + " it’s always good to have a backup plan.",
                "jakemarshall", "Grom", "Regular", "USA"));
      
      } 
    }
  }

}
