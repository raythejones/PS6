import java.io.*;
import java.net.Socket;
import java.awt.*;

/**
 * Handles communication to/from the server for the editor
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 * @author Chris Bailey-Kellogg; overall structure substantially revised Winter 2014
 * @author Travis Peters, Dartmouth CS 10, Winter 2015; remove EditorCommunicatorStandalone (use echo server for testing)
 */
public class EditorCommunicator extends Thread {
	private PrintWriter out;		// to server
	private BufferedReader in;		// from server
	protected Editor editor;		// handling communication for

	/**
	 * Establishes connection and in/out pair
	 */
	public EditorCommunicator(String serverIP, Editor editor) {
		this.editor = editor;
		System.out.println("connecting to " + serverIP + "...");
		try {
			Socket sock = new Socket(serverIP, 4242);
			out = new PrintWriter(sock.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			System.out.println("...connected");
		}
		catch (IOException e) {
			System.err.println("couldn't connect");
			System.exit(-1);
		}
	}

	/**
	 * Sends message to the server
	 */
	public void send(String msg) {
		out.println(msg);
	}

	/**
	 * Keeps listening for and handling (your code) messages from the server
	 */
	public void run() {
		try {
			// Handle messages
			String message;
			Sketch sketch = editor.getSketch();

			while(!(message = in.readLine()).equals(null)){
				String[] splitMessage = message.split(" ");
				String comm = splitMessage[0];


				if(comm.equals("move")) {
					Integer shape = Integer.parseInt(splitMessage[1]);
					sketch.moveShape(shape, Integer.parseInt(splitMessage[2]), Integer.parseInt(splitMessage[3]));
				}

				if(comm.equals("recolor")) {
					Integer shape = Integer.parseInt(splitMessage[1]);
					Color color = new Color(Integer.parseInt(splitMessage[2]));
					sketch.recolor(shape, color);
				}

				if(comm.equals("delete")) {
					Integer shape = Integer.parseInt(splitMessage[1]);

					sketch.remove(shape);
				}

				if(comm.equals("add")) {

					String shape = splitMessage[1];
					if (!shape.equals("polyline")){
						Integer second = Integer.parseInt(splitMessage[2]);
						Integer third = Integer.parseInt(splitMessage[3]);
						Integer fourth = Integer.parseInt(splitMessage[4]);
						Integer fifth = Integer.parseInt(splitMessage[5]);
						Integer sixth = Integer.parseInt(splitMessage[6]);
						if(shape.equals("ellipse")) {
							Ellipse ell = new Ellipse(second, third, fourth, fifth, new Color(sixth));
							sketch.addAtNext(ell);
						}
						else if(shape.equals("segment")) {
							Segment seg = new Segment(second, third, fourth, fifth, new Color(sixth));
							sketch.addAtNext(seg);
						}
						else if(shape.equals("rectangle")) {
							Rectangle rect = new Rectangle(second, third, fourth, fifth, new Color(sixth));
							sketch.addAtNext(rect);
						}
					}
					else if(shape.equals("polyline")) {
						message = message.substring(9);
						String[] polMes = message.split(" to ");
						//polMes.slice(1);
						Polyline poline = new Polyline(new Color(Integer.parseInt(splitMessage[6])));;
						for (int i = 0; i < polMes.length; i ++){
							String[] segMes = polMes[i].split(" ");
							Integer second = Integer.parseInt(segMes[2]);
							Integer third = Integer.parseInt(segMes[3]);
							Integer fourth = Integer.parseInt(segMes[4]);
							Integer fifth = Integer.parseInt(segMes[5]);
							Integer sixth = Integer.parseInt(segMes[6]);
							Segment seg = new Segment(second, third, fourth, fifth, new Color(sixth));
							poline.addSeg(seg);
						}
						sketch.addAtNext(poline);
					}
				}
				editor.repaint();
				}

			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				System.out.println("server hung up");
			}
		}

	// Send editor requests to the server

	//move request
	public void moveComm(int shapeid, int x, int y) {
		send("move" + " " + shapeid + " " + x + " " + y);
	}

	//recolor request
	public void recolorComm(int shapeid, int color) {
		send("recolor" + " " + shapeid + " " + color);
	}

	//delete request
	public void deleteComm(int shapeid) {
		send("delete" + " " + shapeid);
	}

	//add request
	public void addComm(Shape shape) {
		send("add" + " " + shape.toString());
	}
}
