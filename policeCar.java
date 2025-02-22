package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//This class gets the attributes and methods from the class called trashObjects.
public class policeCar extends trashObjects{
	
	// The following variables are to dictate the position of an images, its dimensions, and if it was collided with the image.
	private double xPos, yPos;
	private double width, height;
	private Image policeImg = new Image("file:policeCar.png", 200, 200, true, true);
	private ImageView iviewpolice = new ImageView(policeImg);
	private Random rnd = new Random();
	
	// This constructor would be used to intialize specific variables such as the position of an images, its dimensions, and the image node itself.
	public policeCar() {
		xPos = 0;
		yPos = 0;
		iviewpolice = new ImageView(policeImg);
		width = policeImg.getWidth();
		height = policeImg.getHeight();
		iviewpolice.setX(xPos);
		iviewpolice.setY(yPos);
	}
	
	// This function is used to return the height of the image.
	public double getHeight() {
		height = policeImg.getHeight();
		return height;
	}
	
	// This function is used to return the width of the image.
	public double getWidth() {
		width= policeImg.getWidth();
		return width;
	}
	
	// This is used to return the image file of the image in the class.
	public Image getImage() {
		return policeImg;
	}
	
	// This is used to return the imageview of the image.
	public ImageView getNode() {
		return iviewpolice;
	}
	
	// This function is used to return x-position of the image.
	public double getX() {
		xPos = iviewpolice.getX();
		return xPos;
	}
	
	// This function is used to return y-position of the image.
	public double getY() {
		yPos = iviewpolice.getY();
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
		int x = (int)rnd.nextDouble(100, scenewidth - 150);
		xPos = x;
		iviewpolice.setX(xPos);
	}
	
	// This function is used to set the y-position of the image.
	public void setY(double y) {
		this.yPos = y;
		iviewpolice.setY(yPos);
	}

	// This function is used to change and set the y-position of the image.
	public void moveY(double height) {
		// TODO Auto-generated method stub
		if (policeImg.getHeight()>= height) {
			yPos += 0;
		}
		else {
			yPos += 9;
		}
		iviewpolice.setY(yPos);
	}

	// This function is used to set the location of the image.
	public void setLocation(double frameWidth, double frameHeight)
	{
		xPos = (int)rnd.nextDouble(100, frameWidth - 100);
		yPos = 0;
		iviewpolice.setX(xPos);
		iviewpolice.setY(yPos);
	}
}
