package client;

import java.net.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import adapter.ProxyAutomobile;
import model.*;
import util.FileIO;

import java.io.*;
public class DefaultSocketClient extends Thread implements SocketClientConstants{
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket sock;
	private String strHost;
	private int iPort;
	
	public DefaultSocketClient(String strHost, int iPort) {
		setHost(strHost);
		setPort (iPort);
		}//constructor
	
	public void run(){
		if (openConnection()){
			handleSession();
			closeSession();   }    
		}//run
	
	public boolean openConnection(){
		try {     
			sock = new Socket(strHost, iPort);
			}
		catch(IOException socketError){
			System.out.println("Unable to connect to " + strHost);
			return false;
			}
		
		try {
			out = new ObjectOutputStream(sock.getOutputStream());
		    in = new ObjectInputStream(sock.getInputStream());
			} catch (IOException e){
				System.out.println("Unable to connect client streams to " + strHost );
				return false;  }
		return true;
		}
	
	public void handleSession(){
		String command;
		Scanner scanner = new Scanner(System.in);
		do {
			printMainMenu();
			command = scanner.next();
			try {
				if(command.equals("1")) {
					System.out.println("Enter the file path");
					String filePath = scanner.next();
					Properties props = new FileIO().loadPropertiesFile(filePath);
					out.writeObject("Upload");
					out.writeObject(props);
					String response = (String) in.readObject();
					System.out.println(response);
				}else if(command.equals("2")) {
					out.writeObject("Config");
					ArrayList<String> models = (ArrayList<String>) in.readObject();
					String names[] = printModelSet(models);
					int index = scanner.nextInt();
					String chosenCar = names[index-1];
					out.writeObject(chosenCar);
					Automobile car = (Automobile) in.readObject();
					configureCar(scanner, car);
					
				}else if(command.equals("3")) {
					out.writeObject("Quit");
					System.out.println("Connection terminated. Bye");
				}
				
			}catch(Exception e) {e.printStackTrace();}
				
		}while(!command.equals("3"));
		
	}       
	
	private void printMainMenu() {
		System.out.println("Hello. Enter the option you want.");
		System.out.println("1. Upload Properties file");
		System.out.println("2. Configure a car");
		System.out.println("3. Quit");
	}
	
	private String[] printModelSet(ArrayList<String> models) {
		int i = 0;
		String names[] = new String[models.size()]; 
		for(String modelName : models) {
			System.out.println(i+1 + ". " + modelName);
			names[i] = modelName;
			i++;
		}
		return names;
	}
	
	private String[] printConfigureMenu(Automobile auto) {
		String optionSets[] = new String[auto.getOptionSetSize()];
		for(int i=0; i<auto.getOptionSetSize(); i++) {
			System.out.println(i+1 + ". " + auto.getOptionSet(i));
			optionSets[i] = auto.getOptionSetName(i);
		}
		return optionSets;
	}
	
	private String[] printOptionSetMenu(int index, Automobile auto) {
		String options[] = new String[auto.getExactOptionSetSize(index)];
		for(int i=0; i<auto.getExactOptionSetSize(index); i++) {
			System.out.println(i+1 + ". " + auto.getOptionName(auto.getOptionSetName(index),i));
			options[i] = auto.getOptionName(auto.getOptionSetName(index),i);
		}
		return options;
	}
	
	private void configureCar(Scanner scanner, Automobile auto) {
		System.out.println(auto);
		printConfigureMenu(auto);
		int optSetIndex = scanner.nextInt() - 1;
		auto.printOptionSet(optSetIndex);
		String options[] = printOptionSetMenu(optSetIndex, auto);
		int optionIndex = scanner.nextInt();
		String option = options[optionIndex-1];
		auto.setOptionChoice(auto.getOptionSetName(optSetIndex), option);
		System.out.println(auto);
		
		
	}
	    
	    public void closeSession(){
	    	try {
	    		out.close();
	    		in.close();
	    		sock.close();
	    		}catch (IOException e){
	    			if (DEBUG) System.err.println("Error closing socket to " + iPort);
	    			}
	    	}
	    
	    public void setPort(int iPort){
	    	this.iPort = iPort;  
	    	}
	    
	    public void setHost(String strHost) {
	    	this.strHost = strHost;
	    }
}// class DefaultSocketClient
