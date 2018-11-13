import java.util.*;
import java.awt.*;


/**
* @author Donia Tung
* @author August Ray Jones
*
* Class holding the current shape list
*/

public class Sketch {

	//instance variable that will hold a map of all the shapes in the sketch
	private TreeMap<Integer, Shape> shapes;
	private int id = -1;

	/**
	* Basic constructor, instantiates the treeMap shapes
	*/
	public Sketch() {
		shapes = new TreeMap<Integer, Shape>();
	}

	/**
	* Method that returns the treeMap of shapes held in the sketch
	*/
	public synchronized TreeMap<Integer, Shape> getShapes() {
		return shapes;
	}

	/**
	* Method that returns the shape in the map at the given id value
	*/
	public synchronized Shape getShape(int idVal) {
		return shapes.get(idVal);
	}

	/**
	* Method that returns the id value of a given shape
	*/
	public synchronized Integer getidVal(Shape shape) {
		//loops through each shape within the treeMap shape
		//checks if the parameter shape matches any of the shapes within the Map
		for(Integer idVal : shapes.keySet()) {
			if(shapes.get(idVal).equals(shape)) {
				return idVal;
			}
		}
		return null;
	}

	/**
	* adds a given shape to the treeMap shapes at the end of the map
	*/
	public synchronized void addAtNext(Shape shape) {
		shapes.put(NextidVal(), shape);
	}

	/**
	* adds a given shape to a specified id/location within the treeMap
	*/
	public synchronized void add(int idVal, Shape shape) {
		shapes.put(idVal, shape);
	}

	/**
	* returns the next id to be filled in within the map
	*/
	public synchronized int NextidVal() {
		return id++;
	}

	/**
	* removes the shape with a key of the given idVal
	*/
	public synchronized void remove(int idVal) {
		shapes.remove(idVal);
	}

	/**
	* moves the shape with a key of the given idVal a distance of dx and dy
	*/
	public synchronized void moveShape(int idVal, int dx, int dy) {
		//runs each shape's own moveBy method
		shapes.get(idVal).moveBy(dx, dy);
	}

	/**
	* Gets the shape with a key of the given idVal and sets it's color to parameter color
	*/
	public synchronized void recolor(int idVal, Color color) {
		shapes.get(idVal).setColor(color);
	}

	/**
	* Checks which shape within the map shapes contains the given point
	* returns that shape if the point is within one, null if not within a shape
	*/
	public synchronized Shape containsPoint(Point p) {
		Shape shape = null;
		//Loops through the shapes in the TreeMap
		//if a given shape contains that point, set local var shape = to that shape
		for(int idVal : shapes.navigableKeySet()) {
			if(shapes.get(idVal).contains(p.x, p.y)) {
				shape = shapes.get(idVal);
			}
		}
		return shape;
	}

	/**
	* Draw each shape within the treeMap of shapes
	*/
	public synchronized void draw(Graphics g) {
		for(int idVal : shapes.navigableKeySet()) {
			shapes.get(idVal).draw(g);
		}
	}
}
