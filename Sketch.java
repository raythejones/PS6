import java.util.*;
import java.awt.*;

public class Sketch {

	private TreeMap<Integer, Shape> shapes;
	private int id = -1;

	public Sketch() {
		shapes = new TreeMap<Integer, Shape>();
	}


	public synchronized TreeMap<Integer, Shape> getShapes() {
		return shapes;
	}


	public synchronized Shape getShape(int idVal) {
		return shapes.get(idVal);
	}

	public synchronized Integer getidVal(Shape shape) {
		for(Integer idVal : shapes.keySet()) {
			if(shapes.get(idVal).equals(shape)) {
				return idVal;
			}
		}
		return null;
	}

	public synchronized void addAtNext(Shape shape) {
		shapes.put(NextidVal(), shape);
	}


	public synchronized void add(int idVal, Shape shape) {
		shapes.put(idVal, shape);
	}


	public synchronized int NextidVal() {
		return id++;
	}


	public synchronized void remove(int idVal) {
		shapes.remove(idVal);
	}

	public synchronized void moveShape(int idVal, int dx, int dy) {
		shapes.get(idVal).moveBy(dx, dy);
	}

	public synchronized void recolor(int idVal, Color color) {
		shapes.get(idVal).setColor(color);
	}

	public synchronized Shape containsPoint(Point p) {
		Shape shape = null;
		for(int idVal : shapes.navigableKeySet()) { 
			if(shapes.get(idVal).contains(p.x, p.y)) { 
				shape = shapes.get(idVal);
			}
		}
		return shape;
	}

	public synchronized void draw(Graphics g) {
		for(int idVal : shapes.navigableKeySet()) {
			shapes.get(idVal).draw(g);
		}
	}
}
