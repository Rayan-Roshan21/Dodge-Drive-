package application;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//This class gets the attributes and methods from the abstract class called movingObjects.
public class trashObjects extends movingObjects {
	
	// The following variables are to dictate the position of an images, its dimensions, and if it was collided with the image.
	private double xPos, yPos;
	private double width, height;
	private Image trashImg = new Image("file:Banana_peel.png", 200, 200, true, true);
	private ImageView iviewTrash = new ImageView(trashImg);
	private Random rnd = new Random();
	
	// This constructor would be used to intialize specific variables such as the position of an images, its dimensions, and the image node itself.
	public trashObjects() {
		xPos = 0;
		yPos = 0;
		iviewTrash = new ImageView(trashImg);
		width = trashImg.getWidth();
		height = trashImg.getHeight();
		iviewTrash.setX(xPos);
		iviewTrash.setY(yPos);
	}
	
	// This function is used to return the height of the image.
	public double getHeight() {
		height = trashImg.getHeight();
		return height;
	}
	
	// This function is used to return the width of the image.
	public double getWidth() {
		width = trashImg.getWidth();
		return width;
	}
	
	// This is used to return the image file of the image in the class.
	public Image getImage() {
		return trashImg;
	}
	
	// This is used to return the imageview of the image.
	public ImageView getNode() {
		return iviewTrash;
	}
	
	// This function is used to return x-position of the image.
	public double getX() {
		xPos = iviewTrash.getX();
		return xPos;
	}
	
	// This function is used to return y-position of the image.
	public double getY() {
		yPos = iviewTrash.getY();
		return yPos;
	}
	
	// This function is used to set the width of the image.
	public void setWidth (double width) {
		this.width = width;
	}
	
	// This function is used to set the height of the image.
	public void setHeight(double height) {
		this.height = height;
	}
	
	// This function is used to set the x-position of the image.
	public void setX (double width) {
		int x = (int)rnd.nextDouble(100, width - 150);
		xPos = x;
		iviewTrash.setX(xPos);
	}
	
	// This function is used to set the y-position of the image.
	public void setY(double y) {
		this.yPos = y;
		iviewTrash.setY(yPos);
	}

	// This function is used to change and set the y-position of the image.
	public void moveY(double height) {
		// TODO Auto-generated method stub
		yPos += 8;
		iviewTrash.setY(yPos);
	}

	// This function is used to set the location of the image.
	public void setLocation(double frameWidth, double frameHeight)
	{
		xPos = (int)rnd.nextDouble(100, frameWidth - 100);
		yPos = 0;
		iviewTrash.setX(xPos);
		iviewTrash.setY(yPos);
	}

}