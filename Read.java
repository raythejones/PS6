import java.awt.*;

public class Read {
	
	public static void read(Sketch sketch, String message) {

				String[] splitMessage = message.split(" ");
				String comm = splitMessage[0];
				String shape = splitMessage[1];

				if(comm.equals("move")) {
					sketch.moveShape(Integer.parseInt(shape), Integer.parseInt(splitMessage[2]), Integer.parseInt(splitMessage[3]));
				}

				if(comm.equals("recolor")) {
					Color color = new Color(Integer.parseInt(splitMessage[2]));
					sketch.recolor(Integer.parseInt(shape), color);
				}

				if(comm.equals("delete")) {
					sketch.remove(Integer.parseInt(shape));
				}

				if(comm.equals("add")) {

						if(shape.equals("ellipse")) {
							Ellipse ell = new Ellipse(Integer.parseInt(splitMessage[2]), Integer.parseInt(splitMessage[3]), Integer.parseInt(splitMessage[4]), Integer.parseInt(splitMessage[5]), new Color(Integer.parseInt(splitMessage[6])));
							sketch.addAtNext(ell);
						}
						else if(shape.equals("segment")) {
							Segment seg = new Segment(Integer.parseInt(splitMessage[2]), Integer.parseInt(splitMessage[3]), Integer.parseInt(splitMessage[4]), Integer.parseInt(splitMessage[5]), new Color(Integer.parseInt(splitMessage[6])));
							sketch.addAtNext(seg);
						}
						else if(shape.equals("rectangle")) {
							Rectangle rect = new Rectangle(Integer.parseInt(splitMessage[2]), Integer.parseInt(splitMessage[3]), Integer.parseInt(splitMessage[4]), Integer.parseInt(splitMessage[5]), new Color(Integer.parseInt(splitMessage[6])));
							sketch.addAtNext(rect);
						}
					
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