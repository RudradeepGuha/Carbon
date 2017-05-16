import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acmx.export.javax.swing.Timer;

/**
* The Social Network 
* @author  Rachit Rawat, Rudradeep Guha
* @version 1.0 
*/

public class Canvas extends GCanvas
implements Constants {

	private static final long serialVersionUID = 1L;
 
 double height = 0;
 double X = 0;
 double Y = 0;
 String pname = null;
 Profile currentProfile = null;
 Profile friendProfile = null;

 public Canvas() {

 }

 /** 
  * This method displays a message at the bottom of the 
  * canvas. The message disappears after 5 seconds. If another message is already there,
  * the previous message is replaced by the new message.
  * @param message Takes a string which is displayed at the bottom of the screen when method is called.
  */
 public void displayMessage(String message) {

  GLabel messageC = new GLabel(message);
  double x = getWidth() / 2 - messageC.getWidth() * 3 / 4;
  double y = getHeight() - BOTTOM_MESSAGE_MARGIN;

  if (getElementAt(X, Y) != null) {
   remove(getElementAt(X, Y));
  }
  X = x;
  Y = y;
  messageC.setFont(MESSAGE_FONT);
  add(messageC, x, y);

  Timer t = new Timer(3000, new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e) {
    messageC.setVisible(false);
   }
  });
  t.setRepeats(false);
  t.start();
 }


 /** 
  * This method displays the given profile on the canvas.  The 
  * canvas is first cleared completely and then the 
  * specified profile is displayed. The profile shows the 
  * user's name, status, friends, profile picture, cover picture and the friends list.
  * @param profile Takes a profile which needs to be displayed on the canvas.
  */
 public void displayProfile(Profile profile) {

  removeAll();
  addCoverImage(profile.getCoverPicture());
  addImage(profile.getPicture());
  addName(profile.getName());
  pname = profile.getName();
  addStatus(profile.getStatus());
  addLocation(profile.getLocation());
  addFriends(profile.getFriendsList());
  currentProfile = profile;
 }

 /** Sets the cover picture or sets the boundary for the picture to show the area of the picture.
  * @param cimage Takes the image to be used as the cover picture.  
  */
 private void addCoverImage(GImage cimage) {

  double x = LEFT_MARGIN;
  double y = TOP_MARGIN + CIMAGE_MARGIN;

  if (cimage != null) {
   cimage.setBounds(x, y, CIMAGE_WIDTH, CIMAGE_HEIGHT);
   add(cimage);
  } else {
   GRect imageRect = new GRect(x, y, CIMAGE_WIDTH, CIMAGE_HEIGHT);
   add(imageRect);
   GLabel noImage = new GLabel("No Cover Picture");
   noImage.setFont(PROFILE_IMAGE_FONT);
   double labelWidth = x + CIMAGE_WIDTH / 2 - noImage.getWidth() / 2;
   double labelHeight = y + CIMAGE_HEIGHT / 2;
   add(noImage, labelWidth, labelHeight);
  }
 }

 /** Sets the cover picture or sets the boundary for the picture to show the area of the picture.
  * @param image Takes an image to be used as the profile picture. 
 */
 private void addImage(GImage image) {

  double x = PP_LEFT_MARGIN;
  double y = TOP_MARGIN + height + CIMAGE_MARGIN + IMAGE_MARGIN;

  if (image != null) {
   image.setBounds(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
   add(image);
  } else {
   GRect imageBorder = new GRect(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
   add(imageBorder);
   GLabel none = new GLabel("No Profile Picture");
   none.setFont(PROFILE_IMAGE_FONT);
   double labelWidth = x + IMAGE_WIDTH / 2 - none.getWidth() / 2;
   double labelHeight = y + IMAGE_HEIGHT / 2;
   add(none, labelWidth, labelHeight);
  }
 }

 /** Adds the name of the user to the canvas as a GLabel.
  * @param name Takes the string of the name of the user whose profile is being displayed. 
 */
 private void addName(String name) {
  GLabel Name = new GLabel(name);
  Name.setFont(PROFILE_NAME_FONT);
  Name.setColor(Color.WHITE);
  double x = PP_LEFT_MARGIN + IMAGE_WIDTH + 15;
  height = Name.getHeight();
  double y = CIMAGE_HEIGHT;
  add(Name, x, y);
 }

 /** Adds what the user has on his mind or what he is doing.
  * @param status Takes a string to be declared as the status of the profile.
 */
 private void addStatus(String status) {

  GLabel Status = new GLabel(status);
  Status.setFont(PROFILE_STATUS_FONT);
  double x = CONTENT_LEFT_MARGIN;
  double y = TOP_MARGIN + height + CIMAGE_MARGIN + IMAGE_HEIGHT + IMAGE_MARGIN + STATUS_MARGIN + Status.getHeight();

  if (getElementAt(x, y) != null) {
   remove(getElementAt(x, y));
  }
  add(Status, x, y);
 }

 /** Adds the location of the user.
  * @param location Takes a string which is the location of the user.
 */
 private void addLocation(String location) {

  GLabel Location = new GLabel(location);
  Location.setFont(PROFILE_LOCATION_FONT);
  double x = CONTENT_LEFT_MARGIN;
  double y = TOP_MARGIN + height + CIMAGE_MARGIN + IMAGE_HEIGHT + IMAGE_MARGIN + STATUS_MARGIN + LOCATION_MARGIN + Location.getHeight();

  if (getElementAt(x, y) != null) {
   remove(getElementAt(x, y));
  }
  add(Location, x, y);
 }

 /** Adds the list of friends to the right hand side of the screen.
  * @param friends Takes an iterator as the argument. It iterates over all the friends to display on screen.
 */
 private void addFriends(Iterator < String > friends) {

  GLabel listOfFriends = new GLabel(pname + "'s Friends:");
  listOfFriends.setFont(PROFILE_FRIEND_LABEL_FONT);
  double x = getWidth() / 1.2 + 40;
  double y = TOP_MARGIN + height;
  add(listOfFriends, x, y);

  Iterator < String > iter = friends;

  for (int i = 1; iter.hasNext(); i++) {

   GLabel friendName = new GLabel(iter.next());
   friendName.setFont(PROFILE_FRIEND_FONT);
   friendName.setColor(Color.BLUE);
   double height = y + listOfFriends.getHeight() * i;
   add(friendName, x, height);
  }
 }
}