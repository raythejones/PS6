import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Donia Tung
 * @author August Ray Jones
 *
 * A multi-segment Shape, with straight lines connecting "joint" points -- (x1,y1) to (x2,y2) to (x3,y3) ...
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Spring 2016
 * @author CBK, updated Fall 2016
 */
public class Polyline implements Shape {
	private Color color;
	private ArrayList<Point> pieces;

	/**
	* Constructor, taking in color
	*/
	public Polyline(Color c ){
		pieces = new ArrayList<Point>();
		color = c;
	}

	/**
	* Constructor, taking in a single point and a color
	*/
	public Polyline(Point s, Color c){
		//Instantiates the arraylist, and adds the point to it
		pieces = new ArrayList<Point>();
		pieces.add(s);
		color = c;
	}

	/**
	* Constructor, taking in a String communicator, and a color
	*/
	public Polyline(String comm, Color color) {
		//splits the message on white space
		String[] enter = comm.split(" ");
		//instantiates the arrayList
		pieces = new ArrayList<Point>();
		//for each point within the comm, add it to the arrayList
		for(int i = 1; i < enter.length-1; i+=2) {
			Point p = new Point(Integer.parseInt(enter[i]), Integer.parseInt(enter[i+1]));
			pieces.add(p);
		}
	}

	/**
	* Method to update the location of each individual point in the polyline
	*/
	@Override
	public void moveBy(int dx, int dy) {
		//loop through to each point
		//add new values to each point, add values to ArrayList, remove old values
		for (int i = 0; i < pieces.size(); i ++){
			Point p = pieces.get(i);
			pieces.remove(i);
			pieces.add(i, new Point(p.x+dx,p.y+dy));
		}
	}

	/**
	* Adds a point to the ArrayList of the polyline
	*/
	public void addPoint(Point p) {
		pieces.add(p);
	}

	/**
	* returns the color of the polyline
	*/
	@Override
	public Color getColor() {
		return color;
	}

	/**
	* sets the color of the polyline
	*/
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	* Returns the boolean representing whether or not a given point is
	* contained on the polyline
	*/
	@Override
	public boolean contains(int x, int y) {
		boolean retval = false;
		//loops through the ArrayList
		//if the point is on/close enough to any individual segment, return true
		for(int i = 0; i < pieces.size()-1; i++) {
			if(Segment.pointToSegmentDistance(x, y, pieces.get(i).x, pieces.get(i).y, pieces.get(i+1).x, pieces.get(i+1).y) < 5){
				retval = true;
			}
		}
		return retval;
	}

/**
* Draw the given polyline by looping through and drawing each given line
* between two points
*/
	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		//Loop through ArrayList, draw Line between each two points
		for(int i = 0; i < pieces.size()-1; i++) {
			g.drawLine(pieces.get(i).x, pieces.get(i).y, pieces.get(i+1).x, pieces.get(i+1).y);
		}
	}

	/**
	* returns a String of the polyline, followed by the various
	* points contained within it
	*/
	@Override
	public String toString() {
		String result =  "polyline ";
		//add each point to the result
		for(Point p : pieces) {
			result += " "+p.x+ " "+p.y;
		}
		return result+" "+color.getRGB();
	}

}
