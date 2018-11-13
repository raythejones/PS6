import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Donia Tung
 * @author August Ray Jones
 *
 * A rectangle-shaped Shape
 * Defined by an upper-left corner (x1,y1) and a lower-right corner (x2,y2)
 * with x1<=x2 and y1<=y2
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 * @author CBK, updated Fall 2016
 */
public class Rectangle implements Shape {
	private int x1, y1, x2, y2;		// upper left and lower right
	private Color color;

	//Constructor
	public Rectangle(int x1, int y1, Color color) {
		this.x1 = x1; this.x2 = x1;
		this.y1 = y1; this.y2 = y1;
		this.color = color;
	}

	public void setCorners(int x1, int y1, int x2, int y2) {
		// Ensure correct upper left and lower right
		this.x1 = Math.min(x1, x2);
		this.y1 = Math.min(y1, y2);
		this.x2 = Math.max(x1, x2);
		this.y2 = Math.max(y1, y2);
	}

	/**
	 * Constructs a rectangle defined by two corners
	 */
	public Rectangle(int x1, int y1, int x2, int y2, Color color) {
		setCorners(x1, y1, x2, y2);
		this.color = color;
	}

/**
* Method to move the location of the rectangle
*/
	@Override
	public void moveBy(int dx, int dy) {
		x1 += dx;
		x2 += dx;
		y1 += dy;
		y2 += dy;
	}

/**
* returns the color of the rectangle
*/
	@Override
	public Color getColor() {
		return color;
	}

	/**
	* sets the color of the rectangle
	*/
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	* returns a boolean to indicate whether or not the rectangle contains a given x and y value
	*/
	@Override
	public boolean contains(int x, int y) {
		double xcent = (x1 - x2) / 2.0;
		double ycent = (y1 - y2) / 2.0;
		double dx = (x - (x1 + xcent));
		double dy = (y - (y1 + ycent));
		return Math.pow(dx / xcent, 2) + Math.pow(dy / ycent, 2) <= 1;
	}

	/**
	* draws the rectangle
	*/
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x1,y1,x2-x1,y2-y1);
	}

	/**
	* returns a string with "Rectangle" and then the (x1,y1) vals and (x2, y2) values, with color
	*/
	public String toString() {
		return "rectangle " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + color.getRGB();
	}
}
