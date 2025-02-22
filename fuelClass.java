package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// This class gets the attributes and methods from the abstract class called movingObjects.
public class fuelClass extends movingObjects{

	// The following variables are to dictate the position of an images, its dimensions, and if it was collided with the image.
	private double xPos, yPos;
	private double width, height;
	private Image fuelImg = new Image("file:fuelTank.png", 150, 150, true, true);
	private ImageView iviewfuel = new ImageView(fuelImg);
	private Random rnd = new Random();
	
	// This constructor would be used to intialize specific variables such as the position of an images, its dimensions, and the image node itself.
	public fuelClass() {
		xPos = 0;
		yPos = 0;
		iviewfuel = new ImageView(fuelImg);
		width = fuelImg.getWidth();
		height = fuelImg.getHeight();
		iviewfuel.setX(xPos);
		iviewfuel.setY(yPos);
	}
	
	// This function is used to return the height of the image.
	public double getHeight() {
		height = fuelImg.getHeight();
		return height;
	}
	
	// This function is used to return the width of the image.
	public double getWidth() {
		width= fuelImg.getWidth();
		return width;
	}
	
	// This is used to return the image file of the image in the class.
	public Image getImage() {
		return fuelImg;
	}
	
	// This is used to return the imageview of the image.
	public ImageView getNode() {
		return iviewfuel;
	}
	
	// This function is used to return x-position of the image.
	public double getX() {
		xPos = iviewfuel.getX();
		return xPos;
	}
	
	// This function is used to return y-position of the image.
	public double getY() {
		yPos = iviewfuel.getY();
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
	public void setX (double scenewidth) {
		int x = (int)rnd.nextDouble(100, scenewidth - 200);
		xPos = x;
		iviewfuel.setX(xPos);
	}
	
	// This function is used to set the y-position of the image.
	public void setY(double y) {
		this.yPos = y;
		iviewfuel.setY(yPos);
	}

	// This function is used to change and set the y-position of the image.
	public void moveY(double height) {
		// TODO Auto-generated method stub
		if (fuelImg.getHeight()>= height) {
			yPos += 0;
		}
		else {
			yPos += 6;
		}
		iviewfuel.setY(yPos);
	}

	// This function is used to set the location of the image.
	public void setLocation(double frameWidth, double frameHeight)
	{
		xPos = (int)rnd.nextDouble(100, frameWidth - 100);
		yPos = 0;
		iviewfuel.setX(xPos);
		iviewfuel.setY(yPos);
	}

}
