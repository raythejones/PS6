import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * A multi-segment Shape, with straight lines connecting "joint" points -- (x1,y1) to (x2,y2) to (x3,y3) ...
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Spring 2016
 * @author CBK, updated Fall 2016
 */
public class Polyline implements Shape {
	// TODO: YOUR CODE HERE
	private Color color;
	private ArrayList<Point> pieces;

	public Polyline(Color c ){
		pieces = new ArrayList<Point>();
		color = c; 
	}

	public Polyline(Point s, Color c){
		pieces = new ArrayList<Point>();
		pieces.add(s);
		color = c;
	}

	public Polyline(String comm, Color color) {
		String[] enter = comm.split(" ");
		pieces = new ArrayList<Point>();
		
		for(int i = 1; i < enter.length-1; i+=2) {
			Point p = new Point(Integer.parseInt(enter[i]), Integer.parseInt(enter[i+1]));
			pieces.add(p);
		}
	}

	@Override
	public void moveBy(int dx, int dy) {
		for(Point p : pieces) {
			pieces.remove(p);
			pieces.add(new Point(p.x+dx,p.y+dy));
		}
	}

	public void addPoint(Point p) {
		pieces.add(p);
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public boolean contains(int x, int y) {
		boolean retval = false;
	for(int i = 0; i < pieces.size()-1; i++) {
			if(Segment.pointToSegmentDistance(x, y, pieces.get(i).x, pieces.get(i).y, pieces.get(i+1).x, pieces.get(i+1).y) < 5){
				retval = true;
		}}
		return retval;
	
}




	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		for(int i = 0; i < pieces.size()-1; i++) {
			g.drawLine(pieces.get(i).x, pieces.get(i).y, pieces.get(i+1).x, pieces.get(i+1).y);
		}
	}

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
