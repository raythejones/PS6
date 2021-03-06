import java.io.*;
import java.net.Socket;
import java.awt.*;

/**
 * @author Donia Tung
 * @author August Ray Jones
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
			//while there are new messages from the editor
			while(!(message = in.readLine()).equals(null)){
				//Class read handles the interpretation of the message
				Read.read(sketch, message);
				editor.repaint();
				}

			}
			//Catch io exceptions and print stack trace
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
