import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
* The Social Network 
* @author  Rachit Rawat, Rudradeep Guha
* @version 1.0 
*/

public class Database implements Constants {

 public Database() {

 }

 /** 
  * This method adds the specified profile to the database. If the 
  * name of the profile is the same as the name of another profile, 
  * a message is displayed saying that the profile already exists.
  * @param profile Takes a Profile object to add to the database.
  */
 public void addProfile(Profile profile) {

  if (!profilesList.containsKey(profile.getName())) {
   profilesList.put(profile.getName(), profile);
  } else {
   profilesList.remove(profile.getName());
   profilesList.put(profile.getName(), profile);
  }
 }

 /** 
  * This method returns the profile associated with the specified name. 
  * If there is no profile with the specified name, the method returns null.
  * @param name Takes a string as a name.
  * @return Profile(name) - Returns the profile associated with the name the user inputs.
  */
 public Profile getProfile(String name) {

  if (profilesList.containsKey(name)) {
   return profilesList.get(name);
  } else {
   return null;
  }

 }


 /** 
  * This method removes the profile associated with the given name
  * from the database. It also updates the list of friends of all
  * other profiles so that the name is removed from the list of friends of any other profile. 
  * If there is no profile with the given name, then nothing is changed upon calling this method.
  * @param name Takes a string as the name of the profile to be deleted.
  */
 public void deleteProfile(String name) {

  if (profilesList.containsKey(name)) {
   Profile removeProfile = profilesList.get(name);
   Iterator < String > iter = removeProfile.getFriendsList();
   while (iter.hasNext()) {
    String friend = iter.next();
    Profile profileOfFriend = profilesList.get(friend);
    profileOfFriend.removeFriend(name);
   }
   profilesList.remove(name);
  }
 }

 /** 
  * This method returns true if there is a profile
  * that has the specified name. Otherwise it returns false.
  * @param name Takes a string as the name of the profile.
  * @return true/false - Returns true if the database contains the profile with the input name and false if it does not exist.
  */
 public boolean hasProfile(String name) {

  if (profilesList.containsKey(name)) {
   return true;
  } else {
   return false;
  }
 }

 private Map < String, Profile > profilesList = new HashMap < String, Profile > ();
}