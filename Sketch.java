import java.util.*;
import java.awt.*;

public class Sketch {
	
	private TreeMap<Integer, Shape> shapes;
	private int id = -1;
	
	public Sketch() { 
		shapes = new TreeMap<Integer, Shape>();
	}
	

	public TreeMap<Integer, Shape> getShapes() {
		return shapes;
	}
	

	public Shape getShape(int idVal) {
		return shapes.get(idVal);
	}
	
	public Integer getidVal(Shape shape) {
		for(Integer idVal : shapes.keySet()) {
			if(shapes.get(idVal).equals(shape)) {
				return idVal;
			}
		}
		return null;
	}

	public void addAtNext(Shape shape) {
		shapes.put(NextidVal(), shape);
	}
	

	public void add(int idVal, Shape shape) {
		shapes.put(idVal, shape);
	}
		

	public int NextidVal() {
		return id++;
	}
	

	public void remove(int idVal) {
		shapes.remove(idVal);
	}
	
	public void moveShape(int idVal, int dx, int dy) {
		shapes.get(idVal).moveBy(dx, dy);
	}

	public void recolor(int idVal, Color color) {
		shapes.get(idVal).setColor(color);
	}

	public Shape containsPoint(Point p) {
		Shape shape = null;
		for(int idVal : shapes.navigableKeySet()) { //ordered by order they were added to tree map
			if(shapes.get(idVal).contains(p.x, p.y)) { //if shape contains point
				shape = shapes.get(idVal);
			}
		}
		return shape;
	}
	
	public void draw(Graphics g) {
		for(int idVal : shapes.navigableKeySet()) {
			shapes.get(idVal).draw(g);
		}
	}
}