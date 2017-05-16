public interface Constants {

 /** The width of the application window */
 public static final int APPLICATION_WIDTH = 800;

 /** The height of the application window */
 public static final int APPLICATION_HEIGHT = 500;

 /** Number of characters for each of the text input fields */
 public static final int TEXT_FIELD_SIZE = 25;

 /** Text to be used to create an "empty" label to put space
  *  between interactors on EAST border of application.**/
 public static final String EMPTY_LABEL_TEXT = " ";

 /** Name of font used to display the application message at the
  *  bottom of the display canvas */
 public static final String MESSAGE_FONT = "Dialog-18";

 /** Name of font used to display the name in a user's profile */
 public static final String PROFILE_NAME_FONT = "Dialog-24-bold";

 /** Name of font used to display the text "No Image" in user
  *  profiles that do not contain an actual image */
 public static final String PROFILE_IMAGE_FONT = "Dialog-20";

 /** Name of font used to display the status in a user's profile */
 public static final String PROFILE_STATUS_FONT = "Dialog-16-bold";

 /** Name of font used to display the location in a user's profile */
 public static final String PROFILE_LOCATION_FONT = "Dialog-16-bold";


 /** Name of font used to display the label "Friends" above the
  *  user's list of friends in a profile */
 public static final String PROFILE_FRIEND_LABEL_FONT = "Dialog-17-bold";

 /** Name of font used to display the names from the user's list
  *  of friends in a profile */
 public static final String PROFILE_FRIEND_FONT = "Dialog-16-bold";

 /** The width (in pixels) that profile images should be displayed */
 public static final double IMAGE_WIDTH = 180;

 /** The width (in pixels) that cover images should be displayed */
 public static final double CIMAGE_WIDTH = 1000;


 /** The height (in pixels) that profile picture should be displayed */
 public static final double IMAGE_HEIGHT = 180;

 /** The height (in pixels) that cover picture should be displayed */
 public static final double CIMAGE_HEIGHT = 350;

 /** The number of pixels in the vertical margin between the bottom 
  *  of the canvas display area and the baseline for the message 
  *  text that appears near the bottom of the display */
 public static final double BOTTOM_MESSAGE_MARGIN = 20;

 /** The number of pixels in the horizontal margin between the 
  *  left side of the canvas display area and the Name, Image, and 
  *  Status components that are display in the profile */
 public static final double LEFT_MARGIN = 20;

 public static final double CONTENT_LEFT_MARGIN = LEFT_MARGIN * 4;


 public static final double PP_LEFT_MARGIN = 30; //for profile picture


 /** The number of pixels in the vertical margin between the top 
  *  of the canvas display area and the top (NOT the baseline) of 
  *  the Name component that is displayed in the profile */
 public static final double TOP_MARGIN = 20;

 /** The number of pixels in the vertical margin between the 
  *  baseline of the Name component and the top of the Image 
  *  displayed in the profile */
 public static final double IMAGE_MARGIN = 200;

 /** The number of pixels in the vertical margin between the 
  *  baseline of the Name component and the top of the cover Image 
  *  displayed in the profile */
 public static final double CIMAGE_MARGIN = 20;


 /** The number of vertical pixels in the vertical margin between 
  *  the bottom of the Image and the top of the Status component 
  *  in the profile */
 public static final double STATUS_MARGIN = 20;

 /** The number of vertical pixels in the vertical margin between 
  *  the bottom of the Image and the top of the location component 
  *  in the profile */
 public static final double LOCATION_MARGIN = 30;


}