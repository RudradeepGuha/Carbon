import acm.graphics.*;
import java.util.*;

/**
* The Social Network 
* @author  Rachit Rawat, Rudradeep Guha
* @version 1.0 
*/

public class Profile implements Constants {

 public Profile(String name, String pass) {
  username = name;
  password = pass;
 }

 /** This method returns the user name of the profile.
  * @return username - Returns the name under which the profile is stored. 
  */
 public String getName() {

  return username;
 }

 /** This method returns the password associated with the name of the profile.
  * @return password Returns the password for the profile.
  */
 public String getPass() {

  return password;
 }


 /** 
  * This method returns the image of the profile.  
  * If there is no image, the method returns null. 
  * @return picture Returns profile picture chosen by the user.
  * */
 public GImage getPicture() {

  if (picture == null) {
   return null;
  } else {
   return picture;
  }
 }

 /** 
  * This method returns the cover image of the profile.  
  * If there is no cover image, the method returns null. 
  * @return CImage - Returns the cover image of the profile.
  * */
 public GImage getCoverPicture() {

  if (CImage == null) {
   return null;
  } else {
   return CImage;
  }
 }

 /** This method sets the image of the profile. 
  * @param image Takes a GImage 
 */
 public void setPicture(GImage image) {

  picture = image;
 }

 /** This method sets the cover image of the profile. 
  * @param cimage Takes an image to use as cover picture
 */
 public void setCoverPicture(GImage cimage) {

  CImage = cimage;
 }

 /** 
  * This method returns the status of the profile.
  * If there is no status, the method
  * returns the empty string.
  * @return String - Returns the status the profile is associated with.
  */
 public String getStatus() {

  return Status;
 }

 /** This method sets the status of the profile.
  * @param status Takes a string as the status.
 */
 public void setStatus(String status) {

  Status = status;
 }

 /** 
  * This method returns the location of the user.
  * If there is no location entered, the method
  * returns the empty string.
  * @return Location - Returns the location the profile is associated with.
  */
 public String getLocation() {

  return Location;
 }

 /** This method sets the location of the user. 
  * @param location Takes a string as the location of the user.
 */
 public void setLocation(String location) {

  Location = location;
 }

 /** 
  * This method adds the specified friend to the current profile's list of 
  * friends. It returns true if the friend's name was not already
  * in the list of friends and the name is added 
  * to the list. It returns false if the friend is already in the list of friends
  * and then the friend is not added to the list of friends.
  * @param friend Takes a string as the name of the friend the user wants to add.
  * @return true/false - Returns true if the friend is not already a friend and is added, and false if the person is already a friend.
  */
 public boolean addFriend(String friend) {

  if (friendsList.contains(friend)) {
   return false;
  } else {
   friendsList.add(friend);
   return true;
  }
 }

 /** 
  * This method removes the specified friend from the list
  * of friends. It returns true if the friend's name is in the 
  * list of friends for this profile and the name is removed from
  * the list. The method returns false if the given friend 
  * was not in the list of friends for this profile.
  * @param friend Takes a string as the name of the friend the user wants to remove
  * @return true/false - Returns true if friend is removed from the list, and false if friend is not a friend.
  */
 public boolean removeFriend(String friend) {

  if (friendsList.contains(friend)) {
   friendsList.remove(friendsList.indexOf(friend));
   return true;
  } else {
   return false;
  }
 }

 /** 
  * This method returns an iterator over the list of friends user of the profile.
  * @return An iterator that iterates over the friendsList.
  */
 public Iterator < String > getFriendsList() {

  return friendsList.iterator();
 }

 /** 
  * This method returns a string representation of the profile.  
  * The string consists of the name, status and the list of the friends.
  * @return String - A profile with its main information stored in string form.
  */
 public String toString() {

  String aP = username + " (" + Status + "): ";
  Iterator < String > iter = friendsList.iterator();

  while (iter.hasNext()) {
   aP += iter.next() + ", ";
  }
  return aP;
 }

 private String username = "";
 private String password = "";
 private GImage picture = null;
 private GImage CImage = null;
 private String Status = "No current status";
 private String Location = "No current location";

 private ArrayList < String > friendsList = new ArrayList < String > ();
}