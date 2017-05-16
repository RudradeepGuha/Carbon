import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.apache.commons.io.FileUtils;
import acm.graphics.GImage;
import acm.program.Program;
import acm.util.ErrorException;
import acmx.export.java.io.FileInputStream;
import acmx.export.java.io.FileReader;
import org.apache.commons.codec.binary.Base64;

/**
 * The Social Network 
 * @author  Rachit Rawat, Rudradeep Guha
 * @version 1.0 
 */

public class SocialNetwork extends Program
implements Constants {

 private static final long serialVersionUID = 1L;
 // creating a new canvas and a new database
 private static Database newDB = new Database();
 private static Canvas canvas = new Canvas();
 //create Logger
 private static final Logger logger =
  Logger.getLogger(SocialNetwork.class.getName());

 // this variable always stores the profile being shown
 private Profile profileBeingShown = null;

 DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");

 /**
  * This method initializes the 
  * interactors in the program, and takes care of any other 
  * initializations.
  */

 public void init() {

  //create database in bin
  new File("Database/Profiles").mkdirs();

  backButton = new JButton("Back");
  backButton.setForeground(Color.WHITE);
  backButton.setBackground(Color.BLACK);
  add(backButton, NORTH);
  backButton.setVisible(false);

  nameLabel = new JLabel("  User Name ");
  add(nameLabel, NORTH);

  nameField = new JTextField(TEXT_FIELD_SIZE);
  add(nameField, NORTH);

  passLabel = new JLabel(" Password ");
  add(passLabel, NORTH);

  passField = new JPasswordField(TEXT_FIELD_SIZE);
  add(passField, NORTH);

  addButton = new JButton("Sign Up");
  addButton.setForeground(Color.WHITE);
  addButton.setBackground(Color.BLACK);
  add(addButton, NORTH);

  loginButton = new JButton("Log In");
  loginButton.setForeground(Color.WHITE);
  loginButton.setBackground(Color.BLACK);
  add(loginButton, NORTH);

  searchButton = new JButton("Search");
  searchButton.setForeground(Color.WHITE);
  searchButton.setBackground(Color.BLACK);
  add(searchButton, NORTH);

  logoutButton = new JButton("Logout");
  logoutButton.setForeground(Color.WHITE);
  logoutButton.setBackground(Color.BLACK);
  add(logoutButton, NORTH);

  deleteButton = new JButton("Delete");
  deleteButton.setForeground(Color.BLACK);
  deleteButton.setBackground(Color.RED);
  add(deleteButton, NORTH);

  statusField = new JTextField(TEXT_FIELD_SIZE);
  add(statusField, WEST);
  statusField.addActionListener(this);

  statusButton = new JButton("Change Status");
  statusButton.setForeground(Color.WHITE);
  statusButton.setBackground(Color.BLACK);
  add(statusButton, WEST);

  add(new JLabel(EMPTY_LABEL_TEXT), WEST);

  locationField = new JTextField(TEXT_FIELD_SIZE);
  add(locationField, WEST);
  locationField.addActionListener(this);

  locationButton = new JButton("Change Location");
  locationButton.setForeground(Color.WHITE);
  locationButton.setBackground(Color.BLACK);
  add(locationButton, WEST);

  add(new JLabel(EMPTY_LABEL_TEXT), WEST);

  friendField = new JTextField(TEXT_FIELD_SIZE);
  add(friendField, WEST);
  friendField.addActionListener(this);

  friendButton = new JButton("Add Friend");
  friendButton.setForeground(Color.WHITE);
  friendButton.setBackground(Color.BLACK);
  add(friendButton, WEST);

  add(new JLabel(EMPTY_LABEL_TEXT), WEST);

  removeFriendField = new JTextField(TEXT_FIELD_SIZE);
  add(removeFriendField, WEST);
  removeFriendField.addActionListener(this);

  removeFriendButton = new JButton("Remove Friend");
  removeFriendButton.setForeground(Color.WHITE);
  removeFriendButton.setBackground(Color.BLACK);
  add(removeFriendButton, WEST);

  add(new JLabel(EMPTY_LABEL_TEXT), WEST);

  pictureButton = new JButton("Change Profile Picture");
  pictureButton.setForeground(Color.WHITE);
  pictureButton.setBackground(Color.BLACK);
  add(pictureButton, WEST);

  add(new JLabel(EMPTY_LABEL_TEXT), WEST);

  cpictureButton = new JButton("Change Cover Picture");
  cpictureButton.setForeground(Color.WHITE);
  cpictureButton.setBackground(Color.BLACK);
  add(cpictureButton, WEST);

  add(new JLabel(EMPTY_LABEL_TEXT), WEST);

  gameButton1 = new JButton("Play Ball Game");
  gameButton1.setForeground(Color.WHITE);
  gameButton1.setBackground(Color.BLACK);
  add(gameButton1, WEST);

  add(new JLabel(EMPTY_LABEL_TEXT), WEST);

  gameButton2 = new JButton("Play Breakout Game");
  gameButton2.setForeground(Color.WHITE);
  gameButton2.setBackground(Color.BLACK);
  add(gameButton2, WEST);

  add(new JLabel(EMPTY_LABEL_TEXT), WEST);

  sourceButton = new JButton("View Source Code");
  sourceButton.setForeground(Color.WHITE);
  sourceButton.setBackground(Color.BLACK);
  add(sourceButton, WEST);

  setButtonVisibility(false);

  addActionListeners();

  add(canvas);
  canvas.setBackground(Color.LIGHT_GRAY);

  //restore profiles from database
  File f = new File("Database/Profiles");
  if (f.exists() && f.isDirectory()) {
   File[] files = new File("Database/Profiles/").listFiles();
   try {
    showFiles(files);
   } catch (FileNotFoundException e) {
    logger.log(Level.WARNING, "No existing profiles to restore", e);
   } catch (IOException e) {
    logger.log(Level.WARNING, "No existing profiles to restore", e);
   }
  }

 }

 //function for setting button visibility
 private void setButtonVisibility(boolean x) {

  removeFriendButton.setVisible(x);
  deleteButton.setVisible(x);
  pictureButton.setVisible(x);
  cpictureButton.setVisible(x);
  statusField.setVisible(x);
  statusButton.setVisible(x);
  locationField.setVisible(x);
  locationButton.setVisible(x);
  friendField.setVisible(x);
  friendButton.setVisible(x);
  removeFriendField.setVisible(x);
  loginButton.setVisible(x);
  searchButton.setVisible(x);
  logoutButton.setVisible(x);
 }

 private void setTextFieldVisibility(boolean x) {
   nameLabel.setVisible(x);
   passLabel.setVisible(x);
   nameField.setVisible(x);
   passField.setVisible(x);
  }
  //method for encrypting string
 private String encryptString(String str) throws UnsupportedEncodingException {

   byte[] encryptArray = Base64.encodeBase64(str.getBytes());
   String encstr = new String(encryptArray, "UTF-8");
   return encstr;
  }
  //method for decrypting string
 private static String decryptString(String str) throws UnsupportedEncodingException {

  byte[] dectryptArray = str.getBytes();
  byte[] decarray = Base64.decodeBase64(dectryptArray);
  String decstr = new String(decarray, "UTF-8");
  return decstr;
 }

 public static void showFiles(File[] files) throws FileNotFoundException, IOException {
  for (File file: files) {
   //enable login button if existing profiles are found	  
   loginButton.setVisible(true);
   if (file.isDirectory()) {
    profileName = file.getName();
    showFiles(file.listFiles()); // Calls same method again.
   } else {

    Profile profile = new Profile(profileName, decryptString(readTextFiles(profileName, "password.txt")));
    newDB.addProfile(profile);
    File f = null;

    f = new File("Database/Profiles/" + profileName + "/" + "status.txt");
    if (f.exists()) {
     profile.setStatus(profileName + " is " + readTextFiles(profileName, "status.txt"));
    }

    f = new File("Database/Profiles/" + profileName + "/" + "location.txt");
    if (f.exists()) {
     profile.setLocation(profileName + " is in " + readTextFiles(profileName, "location.txt"));
    }

    f = new File("Database/Profiles/" + profileName + "/" + "coverimage.jpg");
    if (f.exists()) {
     profile.setCoverPicture(new GImage("Database/Profiles/" + profileName + "/" + "coverimage.jpg"));
    }

    f = new File("Database/Profiles/" + profileName + "/" + "profileimage.jpg");
    if (f.exists()) {
     profile.setPicture(new GImage("Database/Profiles/" + profileName + "/" + "profileimage.jpg"));
    }

    f = new File("Database/Profiles/" + profileName + "/" + "friendlist.txt");
    if (f.exists()) {

     String lines[] = readTextFilesAppend(profileName, "friendlist.txt").split("\\r?\\n");

     for (int i = 0; i < lines.length; i++) {
      profile.addFriend(lines[i]);
      if (newDB.hasProfile(lines[i])) {
       Profile friendsProfile = newDB.getProfile(lines[i]);
       friendsProfile.addFriend(profile.getName());
      }


     }

    }

   }

  }
 }

 public static void removeFriend(String name, String Path) throws IOException {
  File inputFile = new File(Path + "\\friendlist.txt");
  File tempFile = new File(Path + "\\tempfriendlist.txt");

  BufferedReader reader = new BufferedReader(new FileReader(inputFile));
  BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
  String currentLine;

  while ((currentLine = reader.readLine()) != null) {
   String trimmedLine = currentLine.trim();
   if (trimmedLine.equals(name)) continue;
   writer.write(currentLine + "\n");
  }
  reader.close();
  writer.close();
  inputFile.delete();
  tempFile.renameTo(inputFile);
 }

 private static String readTextFilesAppend(String profileName, String fileName) throws FileNotFoundException, IOException {


  try (BufferedReader br = new BufferedReader(new FileReader("Database/Profiles/" + profileName + "/" + fileName))) {
   StringBuilder sb = new StringBuilder();
   String line = br.readLine();

   while (line != null) {
    sb.append(line);
    sb.append(System.lineSeparator());
    line = br.readLine();
   }
   String everything = sb.toString();
   return everything;
  }
 }
 private static String readTextFiles(String profileName, String fileName) throws FileNotFoundException, IOException {

  try (BufferedReader br = new BufferedReader(new FileReader("Database/Profiles/" + profileName + "/" + fileName))) {
   String line = br.readLine();
   return line;
  }
 }


 /**
  * This method creates the frame and basic structure for the FileChooser  
  * dialog box which is opened after clicking on the "Change Profile Picture" 
  * or "Change Cover Picture" buttons.
  */

 private void prepareGUI() {
  mainFrame = new JFrame("File Chooser");
  mainFrame.setSize(300, 300);
  mainFrame.setLayout(new GridLayout(3, 2));
  headerLabel = new JLabel("", JLabel.CENTER);
  statusLabel = new JLabel("", JLabel.CENTER);

  statusLabel.setSize(250, 100);

  controlPanel = new JPanel();
  controlPanel.setLayout(new FlowLayout());

  mainFrame.add(headerLabel);
  mainFrame.add(controlPanel);
  mainFrame.add(statusLabel);
  mainFrame.setVisible(true);
 }

 /**
  * This method creates the GUI and functionality for the FileChooser  
  * dialog box which is opened after clicking on the "Change Profile Picture" 
  * or "Change Cover Picture" buttons.
  */

 private void showFileChooser(int x) {
  headerLabel.setText("Select an image");

  final JFileChooser fileDialog = new JFileChooser();
  JButton showFileDialogButton = new JButton("Open File");
  showFileDialogButton.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    int returnVal = fileDialog.showOpenDialog(mainFrame);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
     File file = fileDialog.getSelectedFile();
     statusLabel.setText("File Selected :" + file.getName());

     Profile profile = newDB.getProfile(profileBeingShown.getName());

     GImage cimage = null;

     try {
      cimage = new GImage(file.getPath());
      if (x == 1) {
       profile.setCoverPicture(cimage);

       //copy image to database
       copyImage(file.getPath(), "Database/Profiles/" + profile.getName() + "/coverimage.jpg");

      } else {
       profile.setPicture(cimage);
       //copy image to database
       copyImage(file.getPath(), "Database/Profiles/" + profile.getName() + "/profileimage.jpg");

      }
     } catch (ErrorException ex) {
      cimage = null;
     }
     canvas.displayProfile(profile);
     if (cimage == null) {
      canvas.displayMessage("Unable to open image file: " + file.getPath());
     } else {
      Date date = new Date();
      if (x == 1)
       canvas.displayMessage("Cover Picture updated at " + dateFormat.format(date));
      else
       canvas.displayMessage("Profile Picture updated at " + dateFormat.format(date));

      mainFrame.setVisible(false);
     }

    } else {
     statusLabel.setText("Open command cancelled by user.");
    }
   }

  });
  controlPanel.add(showFileDialogButton);
  mainFrame.setVisible(true);
 }

 private void writeToFileAppend(String path, String content) {
  try (FileWriter fw = new FileWriter(path, true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
   out.println(content);
  } catch (IOException ex) {
   logger.log(Level.WARNING, "Error writing to file", ex);
  }
 }

 //method for creating and writing content to a file
 private void writeToFile(String path, String content) {

  BufferedWriter bw = null;

  FileWriter fw1 = null;


  try {

   fw1 = new FileWriter(path);


   bw = new BufferedWriter(fw1);

   bw.write(content);
  } catch (IOException ex) {

   logger.log(Level.WARNING, "Error writing to file", ex);

  } finally {

   try {

    if (bw != null)
     bw.close();

    if (fw1 != null)
     fw1.close();

   } catch (IOException ex) {

    logger.log(Level.WARNING, "Error writing to file", ex);

   }

  }
 }

 //method for copying image from source to destination
 private void copyImage(String src, String des) {

  InputStream inStream = null;
  OutputStream outStream = null;

  try {

   File afile = new File(src);
   File bfile = new File(des);

   inStream = new FileInputStream(afile);
   outStream = new FileOutputStream(bfile);

   byte[] buffer = new byte[1024];

   int length;
   //copy the file content in bytes
   while ((length = inStream.read(buffer)) > 0) {

    outStream.write(buffer, 0, length);

   }

   inStream.close();
   outStream.close();


  } catch (IOException ex) {
   logger.log(Level.WARNING, "Error copying image", ex);
  }

 }



 /**
  * This method detects when the buttons are
  * clicked or interactors are used and responds to them in certain ways.
  */

 public void actionPerformed(ActionEvent e) {

  //Create new timestamp for every action
  Date date = new Date();

  String nameEntered = nameField.getText();
  char[] passEnteredArray = passField.getPassword();
  String passEntered = new String(passEnteredArray);

  //Click on Sign Up
  if (e.getActionCommand().equals("Sign Up")) {
   //if the entered name does not exist in the database, a profile is created
   if (!nameField.getText().equals("") && !new String(passField.getPassword()).equals("")) {

    if (newDB.hasProfile(nameEntered) == false) {
     Profile profile = new Profile(nameEntered, passEntered);

     //make profile database
     new File("Database/Profiles/" + nameEntered).mkdir();
     // writeToFile("Database/Profiles/" + nameEntered + "/username.txt", nameEntered);
     try {
      writeToFile("Database/Profiles/" + nameEntered + "/password.txt", encryptString(passEntered));
     } catch (UnsupportedEncodingException e1) {
      logger.log(Level.SEVERE, "Encryption Error", e1);
     }

     newDB.addProfile(profile);
     canvas.displayProfile(profile);
     canvas.displayMessage("New profile of " + nameEntered + " has been created at " + dateFormat.format(date));
     profileBeingShown = profile;
     setButtonVisibility(true);
     loginButton.setVisible(false);
     addButton.setVisible(false);
    }
    //if a profile of the name entered already exists, displays the profile
    //and displays the message that the profile already exists
    else {
     //Profile profile = newDB.getProfile(nameEntered);
     //canvas.displayProfile(profile);
     canvas.displayMessage("A profile with name " + nameEntered + " already exists!");
     //profileBeingShown = profile;
    }

   } else {
    if (nameField.getText().equals(""))
     canvas.displayMessage("Name cannot be empty!");
    else if (new String(passField.getPassword()).equals(""))
     canvas.displayMessage("Password cannot be empty!");

   }
   nameField.setText("");
   passField.setText("");
  }

  //Click on Delete
  else if (e.getActionCommand().equals("Delete")) {

   if (!nameField.getText().equals("") && !new String(passField.getPassword()).equals("")) {

    //if the entered name exists as a profile, the profile is deleted
    if (newDB.hasProfile(nameEntered) == true) {
     Profile profile = newDB.getProfile(nameEntered);
     if (new String(passField.getPassword()).equals(profile.getPass())) {
      if (profileBeingShown.getName().equals(nameEntered)) {

       //clears the canvas and sets the current profile to null
       canvas.removeAll();
       profileBeingShown = null;
       newDB.deleteProfile(nameEntered);
       canvas.displayMessage("Profile of " + nameEntered + " deleted at " + dateFormat.format(date));
       setButtonVisibility(false);
       addButton.setVisible(true);
       loginButton.setVisible(true);
       try {
        FileUtils.forceDelete(new File("Database/Profiles/" + nameEntered));
       } catch (IOException e1) {
        logger.log(Level.WARNING, "Error deleting profile from database", e1);
       }
      } else
       canvas.displayMessage("Profile of " + nameEntered + " cannot be deleted from another profile!");
     } else
      canvas.displayMessage("Profile of " + nameEntered + " cannot be deleted at because of wrong password!");

    }
    //if the entered name is not an actual profile, displays the message 
    //that the profile does not exist
    else {
     canvas.displayMessage("A profile with name " + nameEntered + " does not exist!");
    }
   } else {
    if (nameField.getText().equals(""))
     canvas.displayMessage("Name cannot be empty!");
    else if (new String(passField.getPassword()).equals(""))
     canvas.displayMessage("Password cannot be empty!");
   }
   nameField.setText("");
   passField.setText("");
  }
  //Click on Search
  else if (e.getActionCommand().equals("Search")) {

   if (!nameField.getText().equals("")) {
    //if the entered name exists is in the database, displays the profile
    if (newDB.hasProfile(nameEntered) == true) {
     previousProfile = profileBeingShown;
     canvas.removeAll(); //clears everything off the canvas
     setButtonVisibility(false);
     setTextFieldVisibility(false);
     backButton.setVisible(true);

     Profile profile = newDB.getProfile(nameEntered);
     canvas.displayProfile(profile);
     canvas.displayMessage("This is the profile of " + nameEntered);
     profileBeingShown = previousProfile;
    }
    //if the entered name does not exist, displays the message that it doesn't exist
    //and sets current profile to null
    else {
     canvas.displayMessage("A profile with name " + nameEntered + " does not exist!");
     profileBeingShown = null;
    }
    nameField.setText("");
   } else
    canvas.displayMessage("Please enter a profile name first!");
  }

  //back button is clicked
  else if (e.getActionCommand().equals("Back")) {
   canvas.displayProfile(previousProfile);
   setButtonVisibility(true);
   addButton.setVisible(false);
   loginButton.setVisible(false);
   backButton.setVisible(false);
   setTextFieldVisibility(true);
  }

  //Click on Log in
  else if (e.getActionCommand().equals("Log In")) {

   if (!nameField.getText().equals("") && !new String(passField.getPassword()).equals("")) {

    //if the entered name exists as a profile
    if (newDB.hasProfile(nameEntered) == true) {
     Profile profile = newDB.getProfile(nameEntered);
     //if the password matches
     if (new String(passField.getPassword()).equals(profile.getPass())) {
      //clears the canvas and sets the current profile to null
      canvas.removeAll();
      canvas.displayProfile(profile);
      canvas.displayMessage("Log In successful by " + nameEntered);
      profileBeingShown = profile;
      setButtonVisibility(true);
      addButton.setVisible(false);
      loginButton.setVisible(false);

     } else
      canvas.displayMessage("Cannot log in because of wrong password!");

    }
    //if the entered name is not an actual profile, displays the message 
    //that the profile does not exist
    else {
     canvas.displayMessage("A profile with name " + nameEntered + " does not exist!");
    }
   } else {
    if (nameField.getText().equals(""))
     canvas.displayMessage("Name cannot be empty!");
    else if (new String(passField.getPassword()).equals(""))
     canvas.displayMessage("Password cannot be empty!");
   }
   nameField.setText("");
   passField.setText("");
  }


  //Change Status is clicked or user pressed enter after entering a status in the text field
  else if (e.getActionCommand().equals("Change Status") || e.getSource() == statusField) {

   String statusMessage = statusField.getText();
   if (profileBeingShown != null) {
    if (!statusField.getText().equals("")) {
     Profile profile = newDB.getProfile(profileBeingShown.getName());
     //make database entry
     writeToFile("Database/Profiles/" + profile.getName() + "/status.txt", statusMessage);

     profile.setStatus(profile.getName() + " is " + statusMessage + " (Last updated on " + dateFormat.format(date) + ")");
     canvas.displayProfile(profile);
     canvas.displayMessage("Status updated to " + statusMessage + " at " + dateFormat.format(date));
    } else
     canvas.displayMessage("Cannot update with empty status!");

   } else {
    canvas.displayMessage("Please select a profile first to change status!");
   }
   statusField.setText("");
  }

  //Change Location is clicked or user pressed enter after entering a location in the text field
  else if (e.getActionCommand().equals("Change Location") || e.getSource() == locationField) {

   String locationMessage = locationField.getText();
   if (profileBeingShown != null) {
    if (!locationField.getText().equals("")) {
     Profile profile = newDB.getProfile(profileBeingShown.getName());
     //make database entry
     writeToFile("Database/Profiles/" + profile.getName() + "/location.txt", locationMessage);
     profile.setLocation(profile.getName() + " is in " + locationMessage + " (Last updated on " + dateFormat.format(date) + ")");
     canvas.displayProfile(profile);
     canvas.displayMessage("Location updated to " + locationMessage + " at " + dateFormat.format(date));
    } else
     canvas.displayMessage("Cannot update with empty location!");
   } else {
    canvas.displayMessage("Please select a profile first to change location!");
   }
   locationField.setText("");
  }

  //Change Cover Picture is clicked or user pressed enter after entering cimage name into the text field
  else if (e.getActionCommand().equals("Change Cover Picture")) {

   if (profileBeingShown != null) {
    prepareGUI();
    showFileChooser(1);
   } else {
    canvas.displayMessage("Please select a profile first to change Cover picture!");
   }
  }

  //Change Picture is clicked or user pressed enter after entering image name into the text field
  else if (e.getActionCommand().equals("Change Profile Picture")) {

   if (profileBeingShown != null) {
    prepareGUI();
    showFileChooser(0);
   } else {
    canvas.displayMessage("Please select a profile first to change profile picture!");
   }
  }

  //The button to play a game is clicked
  else if (e.getActionCommand().equals("Play Ball Game")) {

   new Thread(
    new Runnable() {
     public void run() {
      try {
       @SuppressWarnings("unused")
       Process process = new ProcessBuilder("roll a ball.exe").start();
      } catch (IOException e1) {
       logger.log(Level.WARNING, "Error opening game", e1);
      }
     }
    }).start();

  }

  //The button to play a game is clicked
  else if (e.getActionCommand().equals("Play Breakout Game")) {

   new Thread(
    new Runnable() {
     public void run() {
      try {
       Desktop.getDesktop().open(new File("Breakout.jar"));
      } catch (IOException ex) {
       System.out.println(ex.getMessage());
      }
     }
    }).start();
  }


  //The logout button is clicked
  else if (e.getActionCommand().equals("Logout")) {

   canvas.removeAll(); //clears everything off the canvas
   canvas.displayMessage("Sucessfully logged out!");
   setButtonVisibility(false);
   loginButton.setVisible(true);
   logoutButton.setVisible(false);
   addButton.setVisible(true);

  }


  //The source button to play a game is clicked
  else if (e.getActionCommand().equals("View Source Code")) {

   canvas.displayMessage("Opening github in browser!");
   URI myUri = URI.create("https://github.com/rachitrawat/Project");
   try {
    java.awt.Desktop.getDesktop().browse(myUri);
   } catch (IOException e1) {
    logger.log(Level.WARNING, "Error opening URL", e1);
   }
  }

  //Add Friend is clicked or user clicked enter after entering a friend's name into the text field
  else if (e.getActionCommand().equals("Add Friend") || e.getSource() == friendField && !friendField.getText().equals("")) {

   String friendName = friendField.getText();
   //checks to see if there is a current profile
   if (profileBeingShown != null) {
    Profile profile = newDB.getProfile(profileBeingShown.getName());
    //checks to see if the name entered is the users name. The user can't friend him/herself. 
    if (profile.getName().equals(friendName)) {
     canvas.displayMessage("You cannot friend yourself!");
    }
    //checks to see if the friend exists in the database
    else if (newDB.hasProfile(friendName)) {
     Profile friendsProfile = newDB.getProfile(friendName);
     /**checks to see if the user is already friends with the friend name entered
      *if the user and the friend entered are not friends, makes them friends. */
     if (profile.addFriend(friendName) == true) {
      profile.addFriend(friendName);
      friendsProfile.addFriend(profile.getName());
      canvas.displayProfile(profile);
      canvas.displayMessage(friendName + " added as a friend at " + dateFormat.format(date));
      writeToFileAppend("Database/Profiles/" + profile.getName() + "/friendlist.txt", friendName);
      writeToFileAppend("Database/Profiles/" + friendName + "/friendlist.txt", profile.getName());

     }
     //if the user is already friends with the friend name entered, displays this message
     else {
      canvas.displayMessage(profile.getName() + " already has " + friendName + " as a friend.");
     }
    }
    //if the friend does not exist in the database, displays a message
    else {
     canvas.displayMessage(friendName + " does not exist!");
    }
   }
   //if there is no current profile, asks user to select a profile
   else {
    canvas.displayMessage("Please select a profile first to add a friend!");
   }
   friendField.setText("");
  }

  //Remove Friend is clicked or user clicked enter after entering a friend's name into the text field
  else if (e.getActionCommand().equals("Remove Friend") || e.getSource() == removeFriendField && !removeFriendField.getText().equals("")) {

   String unfriendName = removeFriendField.getText();
   //checks to see if there is a current profile
   if (profileBeingShown != null) {
    Profile profile = newDB.getProfile(profileBeingShown.getName());
    //checks to see if the name entered is the users name. The user can't unfriend him/herself. 
    if (profile.getName().equals(unfriendName)) {
     canvas.displayMessage("You cannot unfriend yourself!");
    }
    //checks to see if the friend exists in the database
    else if (newDB.hasProfile(unfriendName)) {
     Profile friendsProfile = newDB.getProfile(unfriendName);
     //checks to see if the user is already friendFields with the friend name entered

     //if the user and the friend entered are not friends, makes them friends
     if (profile.removeFriend(unfriendName) == true) {
      profile.removeFriend(unfriendName);
      friendsProfile.removeFriend(profile.getName());
      //update database
      try {
       removeFriend(unfriendName, "Database/Profiles/" + profile.getName());
       removeFriend(profile.getName(), "Database/Profiles/" + unfriendName);

      } catch (IOException e1) {
       logger.log(Level.WARNING, "Error removing friend from database", e1);
      }
      canvas.displayProfile(profile);
      canvas.displayMessage(unfriendName + " removed as a friend at " + dateFormat.format(date));
     }
     //if the user is not friends with the friend name entered, displays this message
     else {
      canvas.displayMessage(profile.getName() + " doesn't have " + unfriendName + " as a friend.");
     }
    }
    //if the friend does not exist in the database, displays a message
    else {
     canvas.displayMessage(unfriendName + " does not exist!");
    }
   }
   //if there is no current profile, asks user to select a profile
   else {
    canvas.displayMessage("Please select a profile first to add a friend!");
   }
   removeFriendField.setText("");
  }
 }

 private static String profileName;
 private Profile previousProfile;
 private JTextField nameField;
 private JPasswordField passField;
 private JTextField statusField;
 private JTextField locationField;
 private JTextField friendField;
 private JFrame mainFrame;
 private JLabel nameLabel;
 private JLabel passLabel;
 private JLabel headerLabel;
 private JLabel statusLabel;
 private JPanel controlPanel;
 private JButton addButton;
 private JButton deleteButton;
 private JButton gameButton1;
 private JButton gameButton2;
 private JButton searchButton;
 private JButton backButton;
 private static JButton loginButton;
 private JButton logoutButton;
 private JButton locationButton;
 private JButton sourceButton;
 private JButton statusButton;
 private JButton pictureButton; //profile picture
 private JButton cpictureButton; //coverpicture
 private JButton friendButton;
 private JTextField removeFriendField;
 private JButton removeFriendButton;
}
