package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import adapter.BuildAuto;
import adapter.ProxyAutomobile;
import model.Automobile;
import util.FileIO;

public class BuildCarModelOptions implements AutoServer{
	private Properties prop;
	
	public BuildCarModelOptions(Properties prop) {
		this.prop = prop;
	}
	
	public void addToLHM(Automobile[] autos) {
		try {
			Automobile[] cars = createModel(prop);
			AutoServer proxy = new BuildAuto();
			proxy.addToLHM(cars);
			return;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Something went wrong with the server object streams");
			e.printStackTrace();
		}
		System.out.println("Could not create the Automobile.");
	}
	
	public Automobile[] createModel(Properties prop) throws IOException, ClassNotFoundException{
		
	    FileIO data = new FileIO();
	    Automobile[] cars = data.readPropertiesObject(prop);
	    
	    
	    return cars;
	    
	}

}