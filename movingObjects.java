package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// This abstract class would be used to create the basic attributes and methods for the moving images in the game.
public abstract class movingObjects {
	
	// The following variables are to dictate the position of an images, its dimensions, and if it was collided with the image.
	int randomNum;
	private double xPos, yPos;
	private double width, height;
	boolean collision;
	
	// The following lines of code would be used set or return the dimensions of an image, the image itself, or the position.
	public abstract double getHeight();
	public abstract double getWidth();
	public abstract Image getImage();
	public abstract ImageView getNode();
	public abstract double getX();
	public abstract double getY();
	public abstract void moveY(double height); 
}
