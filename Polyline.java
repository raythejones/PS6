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
	List<Segment> pieces;
	Color color;

	public Polyline(Segment s, Color c){
		pieces = new ArrayList<Segment>();
		pieces.add(s);
		color = c;
	}

	@Override
	public void moveBy(int dx, int dy) {
		for (Segment s : pieces){
			s.moveBy(dx, dy);
		}
	}

	public void addSeg(Segment s){
		pieces.add(s);
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
		for (Segment s : pieces){
			if (s.contains(x, y)){
				retval = true;
			}
		}
		return retval;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		for (Segment s: pieces){
			s.draw(g);
		}
	}

	@Override
	public String toString() {
		String str = "polyline ";
		for (Segment s : pieces){
			str += s.toString() + " to ";
		}
		return str;
	}

}
