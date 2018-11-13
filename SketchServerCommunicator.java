import java.io.*;
import java.net.Socket;

/**
 * @author Donia Tung
 * @author August Ray Jones
 *
 * Handles communication between the server and one client, for SketchServer
 *
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012; revised Winter 2014 to separate SketchServerCommunicator
 */
public class SketchServerCommunicator extends Thread {
	private Socket sock;					// to talk with client
	private BufferedReader in;				// from client
	private PrintWriter out;				// to client
	private SketchServer server;			// handling communication for

	public SketchServerCommunicator(Socket sock, SketchServer server) {
		this.sock = sock;
		this.server = server;
	}

	/**
	 * Sends a message to the client
	 * @param msg
	 */
	public void send(String msg) {
		out.println(msg);
	}

	/**
	 * Keeps listening for and handling (your code) messages from the client
	 */
	public void run() {
		try {
			System.out.println("someone connected");

			// Communication channel
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream(), true);

			// Tell the client the current state of the world
			//adds the shapes already in the sketch to the world
			for(int val : server.getSketch().getShapes().keySet()) {
				send("Add " + (server.getSketch().getShapes().get(val)));
			}

			// Keep getting and handling messages from the client
			String message;
			Sketch serverSketch = server.getSketch();

			message = in.readLine();
			//while there are more messages from the client...
			while(!message.equals(null))
			{
				//read and interpret the message
				Read.read(serverSketch, message);
				System.out.println("Recieved: " + message);
				server.broadcast(message);
				message = in.readLine();
			}

			// Clean up -- note that also remove self from server's list so it doesn't broadcast here
			server.removeCommunicator(this);
			out.close();
			in.close();
			sock.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
