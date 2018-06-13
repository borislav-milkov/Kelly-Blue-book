package server;

import java.net.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import adapter.ProxyAutomobile;
import model.*;

import java.io.*;
public class DefaultSocketServer extends Thread implements SocketClientConstants{
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private ServerSocket server;
	private Socket clientSocket;
	private int iPort;
	
	public DefaultSocketServer(int iPort) {
		setPort (iPort);
		}//constructor
	
	public void run(){
		if (openConnection()){
			handleSession();
			closeSession();   }    
		}//run
	
	public boolean openConnection(){
		try {     
			server = new ServerSocket(iPort);
			}
		catch(IOException socketError){
			System.out.println("Could not listen on port " + iPort);
			return false;
			}
		
		try {
			clientSocket = server.accept();
			out = new ObjectOutputStream(clientSocket.getOutputStream());
		    in = new ObjectInputStream(clientSocket.getInputStream());
			} catch (IOException e){
				System.out.println("Accept failed.");
				return false;  }
		return true;
		}
	
	public void handleSession(){
		String command = null;
		do {
			try {
				command = (String) in.readObject();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			if (command != null && command.equals("Upload")) {
				try {
					Properties prop = (Properties) in.readObject(); // take the uploaded properties and add the automobile to the LHM
					if(prop != null) {
						out.writeObject("Successful upload.");
					}else {
						out.writeObject("Unsuccessful upload.");
					}
					BuildCarModelOptions build = new BuildCarModelOptions(prop);
					build.addToLHM(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if (command != null && command.equals("Config")) {
				try {
				ArrayList<String> models = ProxyAutomobile.getModels();
				out.writeObject(models); //return a set of auto names to the client
				String chosenCar = (String) in.readObject();
				Automobile car = ProxyAutomobile.getAuto(chosenCar);
				out.writeObject(car);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}while(!command.equals("Quit"));
		
		return;
		
	}       
	
	    
	    public void closeSession(){
	    	try {
	    		out.close();
	    		in.close();
	    		clientSocket.close();
	    		}catch (IOException e){
	    			if (DEBUG) System.err.println("Error closing socket to " + iPort);
	    			}
	    	}
	    
	    public void setPort(int iPort){
	    	this.iPort = iPort;  
	    	}
	    
	    public ServerSocket getSock() {
	    	return server;
	    } 
}// class DefaultSocketClient