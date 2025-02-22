package application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class playerCar {
	
	// The following variables are to dictate the position of an images, its dimensions, and if it was collided with the image.
	private double xPos, yPos;
	private double width, height;
	private Image imgCar = new Image("file:playerCar.png", 200, 200, true, true);
	private ImageView iviewCar = new ImageView(imgCar);
	private int right = 0, left = 1; 
	
	// This constructor would be used to intialize specific variables such as the position of an images, its dimensions, and the image node itself.
	public playerCar() {
		xPos = 480;
		yPos = 350;
		iviewCar = new ImageView(imgCar);
		width = imgCar.getWidth();
		height = imgCar.getHeight();
		
		iviewCar.setX(xPos);
		iviewCar.setY(yPos);
	}

	// This function is used to return the height of the image.
	public double getHeight() {
		height = imgCar.getHeight();
		return height;
	}
	
	// This function is used to return the width of the image.
	public double getWidth() {
		width= imgCar.getWidth();
		return width;
	}
	
	// This is used to return the image file of the image in the class.
	public Image getImage() {
		return imgCar;
	}
	
	// This is used to return the imageview of the image.
	public ImageView getNode() {
		return iviewCar;
	}
	
	// This function is used to return x-position of the image.
	public double getX() {
		xPos = iviewCar.getX();
		return xPos;
	}
	
	// This function is used to return y-position of the image.
	public double getY() {
		yPos = iviewCar.getY();
		return yPos;
	}
	
	// This function is used to change and set the x-position of the image.
	public void moveX(int direction, double scenewidth) {
		if (direction == right) {
			if (xPos + this.width >= scenewidth - 150)
				xPos += 0;
			else 
				xPos += 10;
		}
		
		if (direction == left)
		{
			if (xPos <= 100)
				xPos += 0;
			else
				xPos += -10;
		}
		
		setX(xPos);
		setY(yPos);
	}
	
	// This function is used to set the width of the image.
	public void setWidth (double width) {
		this.width = width;
	}
	
	// This function is used to set the height of the image.
	public void setHeight(double height) {
		this.height = height;
	}
	
	// This function is used to return the x-position of the image.
	public void setX (double x) {
		xPos = x;
		iviewCar.setX(xPos);
	}
	
	// This function is used to return the y-position of the image.
	public void setY(double y) {
		this.yPos = y;
		iviewCar.setY(yPos);
	}
}
