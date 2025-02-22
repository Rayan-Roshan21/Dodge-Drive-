/* ICS4U1 CPT: Dodge & Drive!
 * 
* Rayan Roshan
* 
* Mr. Conway
* 
* The purpose of this game is to drive as long as possible as your partner has just robbed a bank. But, due to the objects on the 
* road and the police always trying to stop, you need to make sure that you keep dodging this obstacles. If you collide with the objects
* on the road, you could lose one heart if its a banana peel or all three if its a cop car. that your gas tank is full. 
* To do this, you can collect gas tanks that would be on the road to collide with. 
* 
* - In this project, you're going to see decision structures that would dictate if the user continues with the game or not after they collide 
* with something. 
* -In addition, we would be checking if the user has ran out of fuel based on the size of fuel tank label.
* - If the user's game is over, the program would show the top three players and the leaderboard position of the current user, as this is done
* with the labels. 
* - When it comes to the getting the user information, I am going to use a textfield that is on a gridpane. In addition, I would make
* sure that the user does not continue without entering something into the textfield.
* - When it comes to the movement of the car, I have created classes where it could be used change the x-position of the car's image based on what was pressed.
* - When it comes to the movement of objects, I have created an abstract classes that has the default methods and attributes that are necessary for an
* object to move down in the pane.
* - In this project, you're also going to see three different primary stages/scenes. The first stage is to introduce the game, controls, where to exit, and more.
* - The second primary stage is the game itself and then it moves on to the third primary stage as it introduces the high scores and leaderboard position of the current user.
*/ 
package application;

// This is to import any external libraries. 
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	
	// The following block of code is to intialize global variables that are necessary throughout the entire project.
	
	// This is for the first image you see on the first alert.
	private Image introImage = new Image("file:introImage.jpg", 100, 100, true, true);
	private ImageView ivIntroImage = new ImageView(introImage);
	
	// This is to get user's name.
	private String username;
	private TextField userNameInput;
	
	// This is for the first scrolling background that is shown to the user when they going to start the game.
	private AnimationTimer animationBackground;
	
	// This is for the countdown that goes down to zero, at the beginning of the game.
	private int seconds = 5;
	private Label lblSeconds;
	
	// This is for the moving background during the game,
	private Timeline timer;
	
	// This is to hold the user's score.
	private int score = 0;
	
	// This is to call the various classes.
	private fuelClass fuel = new fuelClass();
	private playerCar car = new playerCar();
	private trashObjects trashObject = new trashObjects();
	private trashObjects policeCar = new policeCar();
	private scoresDataEntry scoresEntry = new scoresDataEntry();
	
	// This is to hold the list of objects that are going to flow down during the game.
	private ArrayList<fuelClass> fuelObjects = new ArrayList<fuelClass>();
	private ArrayList<trashObjects> trashList = new ArrayList<trashObjects>();
	private ArrayList<trashObjects> policeCarObjects = new ArrayList<trashObjects>();
	
	// This is for the controls of the car.
	private boolean right, left;
	
	// This is to get the image of the car, for the game.
	private Image carImg = new Image("file:playerCar.png", 200, 200, true ,true);
	private ImageView ivCar = new ImageView(carImg);
	
	// This is used to hold and understand the amount of fuel that the user has.
	private int fuelAmount = 175;
	
	// This is to intialize the position of the car.
	private double xPos = car.getX();
	private double yPos = car.getY();
	
	// These timelines for the keyframs that are necessary to create the objects at specific times.
	private Timeline clock, clock2, clock3;
	
	// This is to understand the position of the two background used to create a scrolling background for the game.
	private int yBack1, yBack2;
	
	// This is for the images of the hearts.
	private Image [] hearts = new Image[3];
	private ImageView [] ivHearts = new ImageView[3];
	private Image fullHeart = new Image("file:heart1.png", 65, 65, true, true);
	
	// This is to hold the number of lives for the user.
	private int lives = 3;
	
	// This is to create the labels for the game itself.
	private Label fuelAmountLbl, scorelbl, fuellbl;
	
	// This is for the primary stage in the game.
	private Stage primaryStage;
	
	// This is to play the initial background music and the various sound effects.
	private Media media;
	private MediaPlayer mplayer;
	private File backgroundMusic;
	private AudioClip startCarSound;
	private AudioClip clickSound = new AudioClip("file:clickSoundEffect.mp3");
	
	public void start(Stage primaryStage) {
		try {
			// This is to play background music.
			backgroundMusic = new File("heistBackgroundMusic.mp3");
			media = new Media(backgroundMusic.toURI().toString());
			mplayer = new MediaPlayer(media);
			mplayer.setAutoPlay(true);
			mplayer.setVolume(0.27);
			mplayer.play();
			
			// This is to introduction the game for the user.
			Alert introduction = new Alert(AlertType.CONFIRMATION);
			introduction.setTitle("Dodge & Drive!");
			introduction.setHeaderText(null);
			introduction.setContentText("Hello, welcome to Dodge & Drive! "
					+ "\nI just robbed a bank and need a good getaway driver!" + "\nIf you wish to accept, please click OK!\nOtherwise, please leave as I need this money!");
			introduction.setGraphic(ivIntroImage);
			introduction.getButtonTypes().clear();
			introduction.getButtonTypes().addAll(ButtonType.OK, ButtonType.NO);
			
			
			Optional<ButtonType> chosenOption = introduction.showAndWait();
			/* This if-else statement is used to check if 
			 * the user would like to exit the program or not.
			 */
			if (chosenOption.get() == ButtonType.NO) {
				/* If the user decides to exit the program, 
				 * this function is called to create a new alert.
				 */
				// This would end the game and create a click sound.
				alertSoundClick();
				thankuser();
			}
			else {
				
				// This would create a click sound.
				alertSoundClick();
				
				// This is to create the gridpane.
				GridPane root1 = new GridPane();
				root1.setPadding(new Insets(10, 10, 10, 10));
				root1.setHgap(10);
				Scene scene1 = new Scene(root1,500, 200);
				
				// This is to hold the title of the game onto the gridpane.
				Label titleName = new Label();
				titleName.setText("DODGE & DRIVE");
				Font titleFont = Font.font("file:04B_30_.TTF", FontWeight.BOLD, 30);
				titleName.setFont(titleFont);
				titleName.setLayoutX(100);
				titleName.setAlignment(Pos.CENTER);
				
				// This is to add some text for the the user. This would tell the user to enter their name.
				Label textFieldName = new Label();
				textFieldName.setText("Hey, tell me your name!\nI can't trust everyone!");
				textFieldName.setPrefSize(500, 60);
				Font titleFont2 = Font.font("file:04B_30_.TTF", FontWeight.BOLD, 20);
				textFieldName.setFont(titleFont2);
				
				// This is for the textfield. This is where the user would enter their name.
				userNameInput = new TextField();
				userNameInput.setLayoutX(100);
				userNameInput.setLayoutY(20);
				
				// This vbox is to hold the text and within specific gaps.
				VBox userNameTitles = new VBox(10);
				
				// This is to hold and place the buttons horizontally.
				HBox textFieldButtons = new HBox(10);
				
				// This is button to enter the user's name and to confirm that the user has entered something.
				Button submitBtn = new Button();
				submitBtn.setLayoutX(100);
				submitBtn.setLayoutY(100);
				submitBtn.setFont(Font.font("file:04B_30_.TTF", FontWeight.BOLD, 10));
				submitBtn.setText("DONE");
				submitBtn.setOnAction(e -> checkUserName(primaryStage));
				
				// This button would allow the user to clear the textfield.
				Button clearBtn = new Button();
				clearBtn.setLayoutX(100);
				clearBtn.setLayoutY(100);
				clearBtn.setText("CLEAR");
				clearBtn.setFont(Font.font("file:04B_30_.TTF", FontWeight.BOLD, 10));
				clearBtn.setOnAction(e -> clear());
				
				// This would add elements on to the VBox/HBox such as buttons, labels, and textfield.
				textFieldButtons.getChildren().addAll(submitBtn,clearBtn);
				userNameTitles.getChildren().addAll(textFieldName, userNameInput, textFieldButtons);
				
				// This is to add an image of their character into the game.
				introImage = new Image("file:Character Intro Head.png", 100, 100, true, true);
				ivIntroImage.setImage(introImage);
				
				// This would add the VBox/HBox on to their gridpane.
				root1.add(titleName, 1, 0);
				root1.add(ivIntroImage, 0, 2);
				root1.add(userNameTitles, 1, 2);
				
				primaryStage.setScene(scene1);
				primaryStage.show();
				primaryStage.setTitle("Dodge & Drive!");
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void checkUserName(Stage primaryStage) {
		// This is to play click sound effect.
		clickSound.play();
		
		// This if-else statement would check if the user has enter their name.
		if (userNameInput.getText() == "") {
			// This would display an alert if the user has not enter anything.
			Alert errorMessage = new Alert(AlertType.ERROR);
			errorMessage.setTitle("Dodge & Drive!");
			errorMessage.setHeaderText(null);
			errorMessage.setContentText("Please re-enter your name!");
			errorMessage.showAndWait();
		}
		else {
			// This would set the user's input to username variable.
			username = userNameInput.getText();
			username = username.toUpperCase();
			startGame(primaryStage);
		}
	}	
	private void startGame(Stage primaryStage) {
		// TODO Auto-generated method stub
		
		// This is for background for the first stage of the game.
		Image firstBackground = new Image("file:firstBackground.jpg");
		ImageView ivFirstBackground = new ImageView(firstBackground);
		
		// This is the pane for the first stage of the game.
		Pane gameRoot = new Pane();
		Scene gameScene = new Scene(gameRoot, 1000, 700);
		
		// This is the title for the first stage of the game.
		Label titlelbl = new Label();
		titlelbl.setText("DODGE & DRIVE");
		titlelbl.setFont(Font.loadFont("file:RETROTECH.ttf", 55));
		titlelbl.setLayoutX(gameRoot.getWidth()/2 - 210);
		titlelbl.setLayoutY(70);
		
		// This is the subtitle where we would welcome the user with their name.
		Label subtitlelbl = new Label();
		subtitlelbl.setText("WELCOME " + username);
		subtitlelbl.setFont(Font.loadFont("file:RETROTECH.ttf", 55));
		// This if-else statement would format the subtitle based on the length user's name.
		if (username.length() >= 5) {
			subtitlelbl.setLayoutX(gameRoot.getWidth()/2 - (220 + (1 * username.length())));
		}
		else if (username.length() < 5) {
			subtitlelbl.setLayoutX(gameRoot.getWidth()/2 - (205));
		}
		else {
			subtitlelbl.setLayoutX(gameRoot.getWidth()/2 - 220);
		}
		subtitlelbl.setLayoutY(160);
		subtitlelbl.setAlignment(Pos.CENTER);
		
		// This is for the first button that we are creating use an image.
		Image startBtnImage = new Image("file:startButton.png", 250, 150, true, true);
		ImageView ivStartBtn = new ImageView(startBtnImage);
		ivStartBtn.setLayoutX(gameRoot.getWidth()/2 - 150);
		ivStartBtn.setLayoutY(gameRoot.getHeight()/2 - 80);
		
		// This is for the first button where the user would start the game itself.
		Label startlbl = new Label();
		startlbl.setText("START");
		startlbl.setFont(Font.loadFont("file:RETROTECH.ttf", 55));
		startlbl.setLayoutX(gameRoot.getWidth()/2-110);
		startlbl.setLayoutY(gameRoot.getHeight()/2 - 55);
		
		// This is for the second button that we are creating use an image.
		Image controlsBtnImage = new Image("file:startButton.png", 250, 150, true, true);
		ImageView ivcontrolsBtnImage = new ImageView(controlsBtnImage);
		ivcontrolsBtnImage.setLayoutX(gameRoot.getWidth()/2 - 150);
		ivcontrolsBtnImage.setLayoutY(gameRoot.getHeight()/2 + 50);
		
		// This is for the first button where the user could find the controls.
		Label controlslbl = new Label();
		controlslbl.setText("CONTROLS");
		controlslbl.setFont(Font.loadFont("file:RETROTECH.ttf", 40));
		controlslbl.setLayoutX(gameRoot.getWidth()/2 - 125);
		controlslbl.setLayoutY(gameRoot.getHeight()/2 + 85);
		
		// This is for the third button that we are creating use an image.
		Image exitBtnImage = new Image("file:startButton.png", 250, 150, true, true);
		ImageView ivexitBtnImage = new ImageView(exitBtnImage);
		ivexitBtnImage.setLayoutX(gameRoot.getWidth()/2 - 150);
		ivexitBtnImage.setLayoutY(gameRoot.getHeight()/2 + 180);
		
		// This is for the first button where the user could exit the game itself.
		Label exitlbl = new Label();
		exitlbl.setText("EXIT");
		exitlbl.setFont(Font.loadFont("file:RETROTECH.ttf", 55));
		exitlbl.setLayoutX(gameRoot.getWidth()/2 - 85);
		exitlbl.setLayoutY(gameRoot.getHeight()/2 + 205);
		
		// This block of code is to check the user has clicked.
		gameScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				// This if-else would check if the user had clicked on any of the buttons.
				if (e.getX() >= gameRoot.getWidth()/2 - 150 &&
						e.getX() <= gameRoot.getWidth()/2 - 150 + startBtnImage.getWidth() && 
						e.getY() >= gameRoot.getHeight()/2 - 80 &&
						e.getY() <= gameRoot.getHeight()/2 - 80 + startBtnImage.getHeight()) 
				{
					// If the user has clicked the start button, the game would start.
					gameFunc(primaryStage);
					startCarSound = new AudioClip("file:startingTheCar.mp3");
					startCarSound.play();
					startCarSound.setVolume(0.5);
				}
				else if (e.getX() >= gameRoot.getWidth()/2 - 150 &&
						e.getX() <= gameRoot.getWidth()/2 - 150 + startBtnImage.getWidth() && 
						e.getY() >= gameRoot.getHeight()/2 + 50 &&
						e.getY() <= gameRoot.getHeight()/2 + 50 + startBtnImage.getHeight()) 
				{
					/* If the user has clicked the controls button/image, it would brig up an alert where the user could learn 
					 * more about the game's controls.
					 */
					alertSoundClick();
					Alert controls = new Alert(AlertType.INFORMATION);
					controls.setTitle("CONTROLS");
					controls.setHeaderText(null);
					controls.setContentText("Here are the controls for the game!\n To move the car, you could use the AD keys.\n The D key is to move right.\n The A key is to move left.");
					controls.setGraphic(ivIntroImage);
					controls.showAndWait();
					
				}
				else if (e.getX() >= gameRoot.getWidth()/2 - 150 &&
						e.getX() <= gameRoot.getWidth()/2 - 150 + startBtnImage.getWidth() && 
						e.getY() >= gameRoot.getHeight()/2 + 180 &&
						e.getY() <= gameRoot.getHeight()/2 + 180 + startBtnImage.getHeight()) 
				{
					// If the user has clicked the exit button, the user would soon exit the game.
					alertSoundClick();
					thankuser();
				}
			}
		}
				);

		// This would add elements of the game such as the labels, image of the background, and images for the buttons.
		gameRoot.getChildren().addAll(ivFirstBackground, titlelbl, subtitlelbl);
		gameRoot.getChildren().addAll(ivStartBtn, startlbl, ivcontrolsBtnImage, controlslbl, ivexitBtnImage, exitlbl);
		
		// This is to move the background for the pane.
		animationBackground = new AnimationTimer() {
			public void handle(long val) {
				if (ivFirstBackground.getX() + firstBackground.getWidth() != gameScene.getWidth())
					ivFirstBackground.setX(ivFirstBackground.getX() - 2);
				else
					ivFirstBackground.setX(10);
			}
		};
		animationBackground.start();
	
		primaryStage.setScene(gameScene);
		primaryStage.show();
		primaryStage.setTitle("Dodge & Drive!");
	}
	
	private void gameFunc(Stage primaryStage) {
		// TODO Auto-generated method stub

		// This is used to display the game's instructions for the user.
		Alert instructions = new Alert(AlertType.INFORMATION);
		instructions.setTitle("CONTROLS");
		instructions.setHeaderText(null);
		instructions.setContentText("For this game, you job is to drive as long as possible!\nMake sure to dodge the banana peels on the road and the police. Otherwise, game over!\nMake sure your fuel tank is full, otherwise the car will stop!\nGood luck!");
		instructions.setGraphic(ivIntroImage);
		instructions.showAndWait();
		
		// This is used to create the background for the game itself.
		Image roadBackground = new Image("file:roadBackground.png");
		ImageView ivRoadBackground = new ImageView(roadBackground);
		Image roadBackground2 = new Image("file:roadBackground.png");
		ImageView ivRoadBackground2 = new ImageView(roadBackground2);
		
		// This is used to set the volume of the background music.
		mplayer.setVolume(0.20);
	
		// This is used to create the scene.
		Pane gameRoot = new Pane();
		Scene gameScene = new Scene(gameRoot, roadBackground.getWidth(), 700);
		
		// This is used to display the seconds for the count down.
		lblSeconds = new Label();
		lblSeconds.setText(Integer.toString(seconds));
		lblSeconds.setTextFill(Color.WHITE);
		lblSeconds.setPrefSize(250, 150);
		lblSeconds.setFont(Font.loadFont("file:RETROTECH.ttf", 200));
		lblSeconds.setAlignment(Pos.CENTER);
		lblSeconds.setLayoutX(roadBackground.getWidth()/2 - 115);
		lblSeconds.setLayoutY(700/2 - 130);
		
		// This block of code is used to check what keys did the user release.
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				if (e.getCode() == KeyCode.D)
				{
					right = false;
				}
				else if (e.getCode() == KeyCode.A)
				{
					left = false;
				}
				
			}
		});
		
		// This block of code is used to check what keys did the user press.
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				if (e.getCode() == KeyCode.D)
				{
					right = true;
				}
				else if (e.getCode() == KeyCode.A)
				{
					left = true;
				}
			}
		});
		
		/* This keyframe and the code inside is used to create the count down.
		 * Once the seconds reach zero, we would add specific elements to the game such the labels, the car, and more.
		 */
		KeyFrame kf = new KeyFrame(Duration.millis(1000), new
				EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// This would reduce the amount of seconds for the count down.
				seconds--;
				// This if-else statement would be used to dictate when to add the elements for the game.
				if (seconds == 0) {
					// Once the amount of seconds reach zero, we remove the seconds label and stop the timer timeline.
					startCarSound.stop();
					lblSeconds.setFont(Font.loadFont("file:RETROTECH.ttf", 200));
					lblSeconds.setText("GO");
					timer.stop();
					
					// We would call the move background function.
					movingBackground();
					
					// This would add the car to the game.
					gameRoot.getChildren().add(ivCar);
					
					// This would set its initialize the position of the car on the scene.
					ivCar.setY(yPos);
					ivCar.setX(xPos);
					
					// This would set the position of the first out of the two backgrounds.
					yBack1 = 0;
					yBack2 = (int) (-1 *(roadBackground.getHeight()));
					ivRoadBackground.setY(yBack1);
					ivRoadBackground2.setY(yBack2);	
					
					// This block of code is used to add the hearts on to the scene.
					double xPosition = gameRoot.getWidth()/2 - 135, yPosition = 50;
					for (int i = 0; i < 3; i++) {
						hearts[i] = fullHeart;
						ivHearts[i] = new ImageView(hearts[i]);
						gameRoot.getChildren().add(ivHearts[i]);
						ivHearts[i].setX(xPosition);
						ivHearts[i].setY(yPosition);
						xPosition += 100;
					}
				}
				else {
					// This would be used to update the count down on the scene.
					lblSeconds.setText(Integer.toString(seconds));
				}
			}

			private void movingBackground() {
				// TODO Auto-generated method stub
				
				// This label is used to display the user's score.
				scorelbl = new Label();
				scorelbl.setFont(Font.loadFont("file:RETROTECH.ttf", 50));
				scorelbl.setTextFill(Color.WHITE);
				scorelbl.setLayoutY(20);
				scorelbl.setLayoutX(150);
				
				// This label is used to show where the fuel tank is.
				fuellbl = new Label();
				fuellbl.setFont(Font.loadFont("file:RETROTECH.ttf", 50));
				fuellbl.setTextFill(Color.WHITE);
				fuellbl.setLayoutY(20);
				fuellbl.setLayoutX(roadBackground.getWidth() - 365);
				fuellbl.setText("FUEL:");
				
				// This label is used to show the amount of fuel that the user has.
				fuelAmountLbl = new Label();
				fuelAmountLbl.setLayoutY(90);
				fuelAmountLbl.setPrefSize(fuelAmount, 50);
				fuelAmountLbl.setLayoutX(roadBackground.getWidth() - 365);
				fuelAmountLbl.setStyle("-fx-background-color: yellow");
				
				// This is the keyframe that is used to inrease the score and decrease the fuel tank.
				KeyFrame kf2 = new KeyFrame(Duration.millis(1000), new
						EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						score += 1;
						scorelbl.setText("SCORE: " + score);
						fuelAmount -= 10;
						fuelAmountLbl.setPrefSize(fuelAmount, fuellbl.getHeight());
	
					}
				});
				timer = new Timeline(kf2);
				timer.setCycleCount(Timeline.INDEFINITE);
				timer.play();
				
				/* This animation time would be used to check the following things:
				 * - If the fuel tank is empty,
				 * - If the user is wanting to move the car right or left.
				 * - To move the fuel tanks, cops, and banana peels.
				 * - This is to create the scrolling background.
				 */
				animationBackground = new AnimationTimer() {
					public void handle(long val) {
						
						// This if-statement would check if the user's fuel tank is empty.
						if (fuelAmount <= 0) {
							gameOverAlert(primaryStage, 2);
						}
						
						// These two if-statement would allow the user to move the car right or left.
						// Whatever if-statement is used to check what the user has selected.
						if (right)
						{
							// This would call the car class to move the car right.
							car.moveX(0, gameScene.getWidth());
						}
						if (left)
						{
							// This would call the car class to move the car right.
							car.moveX(1, gameScene.getWidth());
						}
						// This would change the the x-position of the car.
						ivCar.setX(car.getX());
						ivCar.setY(325);
						
						// This for-loop is used to move the banana Peels and check if the objects have collide with the user's car.
						for (int i = 0; i < trashList.size(); i++)
						{
							// Move the objects intrashList.
							trashList.get(i).moveY(gameRoot.getHeight());
							trashList.get(i).getImage();
							/* This would check if the objects are out of the screen.
							 * If an object is out of the screen. They would get remove from the pane and its corresponding Arraylist.
							 */
							if (trashList.get(i).getY() >= gameRoot.getHeight())
							{
								gameRoot.getChildren().remove(trashList.get(i).getNode());
								trashList.remove(i);
								break;
							}
							
							/* This would check if the user's car has collide with any banana peels.
							 * If they do collide, it would remove a heart and life that the user has.
							 */
							if(car.getNode().getBoundsInParent().intersects(trashList.get(i).getNode().
									getBoundsInParent()) && trashList.size() > 0) {
								removeHearts(0);
								gameRoot.getChildren().remove(trashList.get(i).getNode());
								trashList.remove(i);
								
								// This would play a sound indicate that the objects and car has collided.
								AudioClip hitSound = new AudioClip("file:punchSoundEffect.mp3");
								hitSound.play();
								hitSound.setVolume(0.05);
								
								break;
							}
						}
						
						
						// This for-loop is used to move the police cars and check if the objects have collide with the user's car.
						for (int i = 0; i < policeCarObjects.size(); i++)
						{
							// This would call a function to play the police siren.
							policeSirenFunc(0);
							
							// This would move the police car on the pane.
							policeCarObjects.get(i).moveY(gameRoot.getHeight());
							policeCarObjects.get(i).getImage();
							
							/* This would check if the objects are out of the screen.
							 * If an object is out of the screen. They would get remove from the pane and its corresponding Arraylist.
							 */
							if (policeCarObjects.get(i).getY() >= gameRoot.getHeight())
							{
								gameRoot.getChildren().remove(policeCarObjects.get(i).getNode());
								policeCarObjects.remove(i);
								policeSirenFunc(1);
								break;
							}
							
							/* This would check if the user's car has collide with any police cars.
							 * If they do collide, they would lose the game due to them losing all of their hearts.
							 */
							if(car.getNode().getBoundsInParent().intersects(policeCarObjects.get(i).getNode().
									getBoundsInParent()) && policeCarObjects.size() > 0) {
								
								// This would call a function to stop the police siren.
								policeSirenFunc(1);
								
								// This would play an audio clip.
								AudioClip policeOfficierSound = new AudioClip("file:policeOfficer.mp3");
								policeOfficierSound.play();
								policeOfficierSound.setVolume(1);
								
								// This would remove all elements in the game.
								gameRoot.getChildren().removeAll(fuelAmountLbl, fuellbl, scorelbl);
								animationBackground.stop();
								gameRoot.getChildren().removeAll(ivHearts[0], ivHearts[1], ivHearts[2]);
								
								// This would call a new primary stage.
								gameOverAlert(primaryStage, 0);
								
								// This would remove the car in the pane.
								gameRoot.getChildren().remove(policeCarObjects.get(i).getNode());
								policeCarObjects.remove(i);
								break;
							}
							
						}
						
						// This for-loop is used to move the fuel tanks and check if the objects have collide with the user's car.
						for (int i = 0; i < fuelObjects.size(); i++)
						{
							// Move the objects in the fuel tanks in the pane.
							fuelObjects.get(i).moveY(gameRoot.getHeight());
							fuelObjects.get(i).getImage();
							
							/* This would check if the objects are out of the screen.
							 * If an object is out of the screen. They would get remove from the pane and its corresponding Arraylist.
							 */
							if (fuelObjects.get(i).getY() >= gameRoot.getHeight())
							{
								gameRoot.getChildren().remove(fuelObjects.get(i).getNode());
								fuelObjects.remove(i);
								break;
							}
							
							/* This would check if the user's car has collide with any fuel tanks.
							 * If they do collide, they would lose the game due to them losing all of their hearts.
							 */
							if(car.getNode().getBoundsInParent().intersects(fuelObjects.get(i).getNode().
									getBoundsInParent()) && fuelObjects.size() > 0) {
								// This would check how much fuel to add for the user.
								if (fuelAmount + 40 >= 175) {
									fuelAmount = 175;
								}
								else {
									fuelAmount += 40;
								}
								
								// This would change the length of the fuel tank label.
								fuelAmountLbl.setPrefSize(fuelAmount, 50);
								// This would remove the fuel tank from the pane.
								gameRoot.getChildren().remove(fuelObjects.get(i).getNode());
								fuelObjects.remove(i);
								
								// This would play an audio clip.
								AudioClip fuelUpSound = new AudioClip("file:fuelUp.mp3");
								fuelUpSound.play();
								fuelUpSound.setVolume(0.5);
								
								break;
							}
						}
						
						/* These two if-else statements are used to create a scrolling background based on the y-position. 
						 * If the y-position of a background is greater then the scene's height, a new background would be in-place.
						 */
						if (yBack1 >= gameRoot.getHeight())
						{
							yBack1 = (int) (-1 *(roadBackground.getHeight()));
						}
						else
						{
							yBack1 += 3;
						}

						if (yBack2 >= gameRoot.getHeight())
						{
							yBack2 = (int) (-1 *(roadBackground.getHeight()));
						}
						else
						{
							yBack2 += 3;
						}
						ivRoadBackground.setY(yBack1);
						ivRoadBackground2.setY(yBack2);
						
					}

					// This function is used to remove hearts for when the user has collided with a banana peel or a police car.
					private void removeHearts(int j) {
						// TODO Auto-generated method stub
						// This is the image that would replace the hearts.
						Image emptyHeart = new Image("file:emptyHeart.png", 80, 80, true, true);
						// This would check what heart image to change based on the number of hearts.
						if (j == 0) {
							if (lives == 3) {
								ivHearts[2].setImage(emptyHeart);
							}
							else if (lives == 2) {
								ivHearts[1].setImage(emptyHeart);
							}
							else if (lives == 1) {
								ivHearts[0].setImage(emptyHeart);
							}
						// This would remove the numbers of lives for the user.
						lives -= 1;
						}
						// This would block of code would check if the user would have to lose all of their lives.
						else {
							ivHearts[0].setImage(emptyHeart);
							ivHearts[1].setImage(emptyHeart);
							ivHearts[2].setImage(emptyHeart);
							lives -= 3;
						}
						
						// This would check if the user had lost the game.
						if (lives == 0) {
							gameOverAlert(primaryStage, 1);
						}
					}
					
				};
				animationBackground.start();
				
				// This would remove the seconds label.
				gameRoot.getChildren().remove(lblSeconds);
				// This would add the score, fuel, and fuel tank labels.
				gameRoot.getChildren().addAll(scorelbl, fuellbl, fuelAmountLbl);
				
				// This keyframe is used to add objects in the banana peel arraylist and to set the location of each object.
				KeyFrame kfClock = new KeyFrame(Duration.millis(2500), new
						EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						trashList.add(trashList.size(), new trashObjects());
						trashList.get(trashList.size() - 1).setLocation(roadBackground.getWidth(), 700);
						gameRoot.getChildren().add(trashList.get(trashList.size() - 1).getNode());
						trashList.get(trashList.size() -1 ).setX(roadBackground.getWidth());
					}
				});
				clock = new Timeline(kfClock);
				clock.setCycleCount(Timeline.INDEFINITE);
				clock.play();
				
				// This keyframe is used to add objects in the police car arraylist and to set the location of each object.
				KeyFrame kfPoliceClock = new KeyFrame(Duration.millis(9500), new
						EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						policeCarObjects.add(policeCarObjects.size(), new policeCar());
						policeCarObjects.get(policeCarObjects.size() - 1).setLocation(roadBackground.getWidth(), 700);
						gameRoot.getChildren().add(policeCarObjects.get(policeCarObjects.size() - 1).getNode());
						policeCarObjects.get(policeCarObjects.size() -1 ).setX(roadBackground.getWidth());
					}	
				});
				clock2 = new Timeline(kfPoliceClock);
				clock2.setCycleCount(Timeline.INDEFINITE);
				clock2.play();
				
				// This keyframe is used to add objects in the fuel tank arraylist and to set the location of each object.
				KeyFrame kfFuelClock = new KeyFrame(Duration.millis(7000), new
						EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						fuelObjects.add(fuelObjects.size(), new fuelClass());
						fuelObjects.get(fuelObjects.size() - 1).setLocation(roadBackground.getWidth(), 700);
						gameRoot.getChildren().add(fuelObjects.get(fuelObjects.size() - 1).getNode());
						fuelObjects.get(fuelObjects.size() -1 ).setX(roadBackground.getWidth());
					}	
				});
				clock3 = new Timeline(kfFuelClock);
				clock3.setCycleCount(Timeline.INDEFINITE);
				clock3.play();
					
			}
		});
		
		// Initialize the Timeline object
		timer = new Timeline(kf);
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
		
		// This would start the animation timer.
		animationBackground.start();
		
		// This would add the background images. and the seconds labels.
		gameRoot.getChildren().addAll(ivRoadBackground, ivRoadBackground2, lblSeconds);
		
		primaryStage.setScene(gameScene);
		primaryStage.centerOnScreen();
		primaryStage.show();
		primaryStage.setTitle("Dodge & Drive!");
	}
	
	// This function is used to show why the game had ended for the user.
		private void gameOverAlert(Stage primaryStage, int messageNum) {
			// TODO Auto-generated method stub
			// The following block of code is used to stop all animation timers and keyframes.
			clock.stop();
			clock2.stop();
			clock3.stop();
			timer.stop();
			animationBackground.stop();
			
			// The following block of code is used to create an alert in the pane.
			Platform.runLater(new Runnable() {
				public void run()
				{	
					// This would create the alert itself and adding specific elements such as text and an image.
					Alert gameOverMessage = new Alert(AlertType.INFORMATION);
					gameOverMessage.setHeaderText(null);
					gameOverMessage.setGraphic(ivIntroImage);
					gameOverMessage.setTitle("Dodge & Drive!");
					
					// This if-else statement would be used to decide what image we would be using for the alert and the text that would be included.
					if (messageNum == 0) {
						gameOverMessage.setContentText("NO!" + "\n We got arrested because you couldn't drive!" + "\n I was ready to have a nice holiday!");
						ivIntroImage.setImage(new Image("file:robberCaughtImage.jpg", 100, 100, true, true));
						gameOverMessage.setGraphic(ivIntroImage);
					}
					else if (messageNum == 1) {
						// This would play an audio clip when the car has been broken down.
						AudioClip carBreakingDown = new AudioClip("file:carBreakingDownSound.mp3");
						carBreakingDown.play();
						carBreakingDown.setVolume(0.5);
						gameOverMessage.setContentText("The car is broken now because of all those banana peels!" + "\n We got to run before the cops come!" + "\n Let's go!");
						ivIntroImage.setImage(new Image("file:smokingCarImage.png", 100, 100, true, true));
					}
					else if (messageNum == 2) {
						// This would play an audio clip when the fuel tank is empty.
						AudioClip emptyTankSound = new AudioClip("file:emptyTank.mp3");
						emptyTankSound.play();
						emptyTankSound.setVolume(1);
						
						gameOverMessage.setContentText("The car is out of fuel...NO!" + "\n We got to run before the cops come!" + "\n Let's go!");
						ivIntroImage.setImage(new Image("file:fuelGuage.png", 100, 100, true, true));
					}
					gameOverMessage.showAndWait();

					// This would be used to write the user's name and data in a text file.
					try {
						scoresEntry.writingScore(username, score);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					endingScreen(primaryStage);
				}

			});
		}
	
	// This is for the ending screen of the game.
	private void endingScreen(Stage primaryStage2) {
		
		// This would be used to create the background for the new pane.
		Image firstBackground = new Image("file:firstBackground.jpg");
		ImageView ivFirstBackground = new ImageView(firstBackground);
		
		// This would stop the current background music.
		mplayer.stop();
		
		// This is to play the new background music.
		File backgroundMusic = new File("endingMusic.mp3");
		Media media2 = new Media(backgroundMusic.toURI().toString());
		MediaPlayer mplayer2 = new MediaPlayer(media2);
		mplayer2.play();
		mplayer2.setVolume(0.7);
		
		// This is used to create the new pane.
		Pane gameRoot = new Pane();
		Scene gameScene = new Scene(gameRoot, 1000, 700);
		
		// This is used to create the new exit button for the user to exit the program.
		Image exitBtnImage = new Image("file:startButton.png", 250, 150, true, true);
		ImageView ivexitBtnImage = new ImageView(exitBtnImage);
		ivexitBtnImage.setLayoutX(gameRoot.getWidth()/2 - 130);
		ivexitBtnImage.setLayoutY(gameRoot.getHeight()/2 + 180);
		
		// This label is used to show what the button is for.
		Label exitlbl = new Label();
		exitlbl.setText("EXIT");
		exitlbl.setFont(Font.loadFont("file:RETROTECH.ttf", 55));
		exitlbl.setLayoutX(gameRoot.getWidth()/2 - 65);
		exitlbl.setLayoutY(gameRoot.getHeight()/2 + 205);
		
		// This is used to check if the user had clicked the exit button.
		gameScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (e.getX() >= gameRoot.getWidth()/2 - 150 &&
						e.getX() <= gameRoot.getWidth()/2 - 150 + exitBtnImage.getWidth() && 
						e.getY() >= gameRoot.getHeight()/2 + 180 &&
						e.getY() <= gameRoot.getHeight()/2 + 180 + exitBtnImage.getHeight()) 
				{
					// If the user had clicked the exit button, it would bring up a new alert and to play the click sound effect.
					alertSoundClick();
					thankuser();
					primaryStage2.close();
				}
			}
		}
				);

		// This would be used as the background to show the top three scores.
		Image highScoresBackground = new Image("file:scoresBackground.png", 950, 2000, true, true);
		ImageView ivhighScoresBackground = new ImageView(highScoresBackground);
		ivhighScoresBackground.setLayoutX(20);
		ivhighScoresBackground.setLayoutY(100);
		
		// This is the title for the current scene.
		Label highscoreslbl = new Label();
		highscoreslbl.setText("HIGH SCORES");
		highscoreslbl.setFont(Font.loadFont("file:RETROTECH.ttf", 55));
		highscoreslbl.setLayoutX(330);
		highscoreslbl.setLayoutY(180);
		
		// This would be used to add specific elements such as the background, the titles, and the background for the high scores.
		gameRoot.getChildren().add(ivFirstBackground);
		gameRoot.getChildren().addAll(ivhighScoresBackground, ivexitBtnImage, exitlbl, highscoreslbl);
		
		// These three arrays are used to hold the names and scores of the highest scores.
		String [] userNameScores = scoresEntry.namesArray();
		int [] userScores = scoresEntry.scoresArray();
		Label [] userScoreslabels = new Label[3];
		
		// The following for-loop is used to create a label for a high score user and for it to be added to the pane.
		int intialY = 190;
		for (int i = 0; i < 3; i++) {
			intialY += 50;
			userScoreslabels[i] = new Label();
			userScoreslabels[i].setFont(Font.loadFont("file:RETROTECH.ttf", 45));
			userScoreslabels[i].setText(Integer.toString(i + 1) + ". " + userNameScores[i] + ": " + Integer.toString(userScores[i]));
			userScoreslabels[i].setLayoutX(230);
			userScoreslabels[i].setLayoutY(intialY);
			gameRoot.getChildren().add(userScoreslabels[i]);
		}
		
		// This is used to show the position of the user in the leaderboard.
		Label userPosition  = new Label();
		int leaderboardPosition = scoresEntry.leaderBoardPosition();
		userPosition.setFont(Font.loadFont("file:RETROTECH.ttf", 45));
		userPosition.setText("Leaderboard position: " + Integer.toString(leaderboardPosition));
		userPosition.setLayoutX(230);
		userPosition .setLayoutY(intialY + 50);
		gameRoot.getChildren().add(userPosition);
		
		// This animation timer would be used to move the background of this pane.
		animationBackground = new AnimationTimer() {
			public void handle(long val) {
				if (ivFirstBackground.getX() + firstBackground.getWidth() != gameScene.getWidth())
					ivFirstBackground.setX(ivFirstBackground.getX() - 2);
				else
					ivFirstBackground.setX(10);
			}
		};
		animationBackground.start();
		
		primaryStage2.setScene(gameScene);
		primaryStage2.centerOnScreen();
		primaryStage2.show();
		primaryStage2.setTitle("Dodge & Drive!");
	}
	
	// This function is used dictate when to play the police siren. 
	public void policeSirenFunc(int i) {
		// TODO Auto-generated method stub
		AudioClip policeSiren = new AudioClip("file:policeSiren.mp3");
		if (i == 0) {
			policeSiren.setVolume(0.05);
			policeSiren.play();
		}
		else {
			policeSiren.stop();
		}
	}

	// This function is used to create and display an alert for the user, before/after they leave the game.
	private void thankuser() {
		// TODO Auto-generated method stub
		Alert thankUser = new Alert(AlertType.INFORMATION);
		thankUser.setHeaderText(null);
		ivIntroImage.setImage(introImage);
		thankUser.setGraphic(ivIntroImage);
		thankUser.setTitle("Dodge & Drive!");
		thankUser.setContentText("Thank you for your help! I am going to start running away from these cops!\nBest of luck to you!");
		thankUser.showAndWait();	
		System.exit(0);
	}
	
	// This function is used to play the click sound for specific buttons.
	public void alertSoundClick() {
		clickSound.play();
	}
	
	// This function is used to clear the text of the textfield. 
	private void clear() {
		// TODO Auto-generated method stub
		clickSound.play();
		userNameInput.setText("");
	}
	
}
