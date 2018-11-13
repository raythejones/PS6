/**
* @author Donia Tung
* @author August Ray Jones
*
* Class (essentially like a class library, but there's only one method) for handling messages
*/


import java.awt.*;

public class Read {

	/**
	* Takes in a sketch and String message, interprets the message and updates
	* the sketch accordingly.
	*
	*/
	public static void read(Sketch sketch, String message) {
				//splits message on white space, store general command as comm, given shape as shape
				String[] splitMessage = message.split(" ");
				String comm = splitMessage[0];
				String shape = splitMessage[1];
				//if move, then move the shape with the given id in the sketch's Map
				if(comm.equals("move")) {
					sketch.moveShape(Integer.parseInt(shape), Integer.parseInt(splitMessage[2]), Integer.parseInt(splitMessage[3]));
				}
				//if recolor, then recolor the shape with the given id in the sketch's Map
				if(comm.equals("recolor")) {
					Color color = new Color(Integer.parseInt(splitMessage[2]));
					sketch.recolor(Integer.parseInt(shape), color);
				}
				//if delete, then remove the shape with the given id in the sketch's map
				if(comm.equals("delete")) {
					sketch.remove(Integer.parseInt(shape));
				}
				//if add...
				if(comm.equals("add")) {
						//if ellipse, then create a new ellipse with corners at given vertices and add to sketch
						if(shape.equals("ellipse")) {
							Ellipse ell = new Ellipse(Integer.parseInt(splitMessage[2]), Integer.parseInt(splitMessage[3]), Integer.parseInt(splitMessage[4]), Integer.parseInt(splitMessage[5]), new Color(Integer.parseInt(splitMessage[6])));
							sketch.addAtNext(ell);
						}
						// if segment, create a new segment with endpoints at given points, and add to sketch
						else if(shape.equals("segment")) {
							Segment seg = new Segment(Integer.parseInt(splitMessage[2]), Integer.parseInt(splitMessage[3]), Integer.parseInt(splitMessage[4]), Integer.parseInt(splitMessage[5]), new Color(Integer.parseInt(splitMessage[6])));
							sketch.addAtNext(seg);
						}
						//if rectangle, create a new rectangle with corners at given points, and add to sketch
						else if(shape.equals("rectangle")) {
							Rectangle rect = new Rectangle(Integer.parseInt(splitMessage[2]), Integer.parseInt(splitMessage[3]), Integer.parseInt(splitMessage[4]), Integer.parseInt(splitMessage[5]), new Color(Integer.parseInt(splitMessage[6])));
							sketch.addAtNext(rect);
						}
						//if polyline, loop through message, and add information to info
						//create and add new polyline
					else if(shape.equals("polyline")) {
						String info = "";
						for(int i = 2; i < splitMessage.length-1; i++) {
							info += splitMessage[i] + " ";
						}
						Polyline polyline = new Polyline(info, new Color(Integer.parseInt(splitMessage[splitMessage.length-1])));
						sketch.addAtNext(polyline);
					}
				}
			}
		}
