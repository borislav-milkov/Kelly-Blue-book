package Adapter;
import java.util.LinkedHashMap;

import Exception.AutoException;
import Model.*;
import Scale.*;
import Util.FileIO;


public abstract class ProxyAutomobile {
	private static LinkedHashMap<String, Automobile> autos = 
			new LinkedHashMap<String, Automobile>();
	
	public void updateOptionSetName(String autoName, String optionSetName, String newName) {
		Automobile a1 = autos.get(autoName);
		a1.updateOptionSetName(optionSetName, newName);
	}

	public void updateOptionPrice(String autoName, String optionName, float newPrice) {
		Automobile a1 = autos.get(autoName);
		a1.updateOptionPrice(optionName, newPrice);
		
	}
	
	public void updateOptionName(String autoName, String optionName, String newOptionName) {
		Automobile a1 = autos.get(autoName);
		a1.updateOptionName(optionName, newOptionName);
	}
	
	public void deleteOption(String autoName, String optionName) {
		Automobile a1 = autos.get(autoName);
		a1.deleteOption(optionName);
	}
	
	public void setAuto(String autoName, Automobile auto) {
		autos.put(autoName, auto);
	}

	public void BuildAuto(String filename){
		FileIO f = new FileIO();
		try {
			Automobile a1 = f.buildAutoObject(filename);
			autos.put(a1.getName(), a1);
		} catch (AutoException e) {
			e.fix(e.getErrNum());
		}
	}

	public void printAuto(String autoName){
		for(int i=0; i < autos.size(); i++) {
			Automobile a1 = autos.get(autoName);
			try {
				if(a1 != null) {
					System.out.println(a1);
				}else {
					throw new AutoException(99, "There is no Auto object loaded");
				}
			}catch(AutoException e) {
				e.fix(e.getErrNum());
			}
		}
	}
	
	public void fix(int errNo, String msg) {
		AutoException e = new AutoException(errNo, msg);
		e.fix(errNo);
	}
	
	public void operation(String[] input) {
		EditOptions edOpts = new EditOptions(Integer.parseInt(input[0]), input);
		edOpts.runSyncUpdate();
	}
	
	public void nonSyncOperation(String[] input) {
		EditOptions edOpts = new EditOptions(Integer.parseInt(input[0]), input);
		edOpts.runNonSyncUpdate();
	}
	
}

