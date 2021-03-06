package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.*;

public class Automobile implements Serializable{
	private String name; 
	private int basePrice;
	private OptionSet optset[];
	
	//data structures for scalability
	private String make;
	private String model;
	private ArrayList<OptionSet> optionSet;
	private ArrayList<Option> choice;
	
	//descriptive constructor
	public Automobile(String name, int basePrice, int size) {
		this.choice = new ArrayList<Option>(size);
		this.name = name;
		this.basePrice = basePrice;
		optionSet = new ArrayList<OptionSet>(size);
		for(int i=0; i < size; i++) {
			optionSet.add(i, new OptionSet());
			choice.add(i, new Option());
		}
	}
	//default constructor
	public Automobile() {
		name = "default";
		basePrice = 0;			
	}
	
	public  void setName(String name) {
		this.name = name;
	}
	
	public  void setBasePrice(int p) {
		basePrice = p;
	}
	
	public void initOptionSet(int size) {
		optionSet = new ArrayList<OptionSet>(size);
		
		for(int i=0; i < size; i++) {
			optionSet.add(i, new OptionSet());
		}
	}
	
	public  void setOptionSet(int index, String name, int size) {
		optionSet.set(index, new OptionSet(name, size));
	}
	
	/**
	 * @param index_set
	 * 			the index of the Option Set in the OptionSet array
	 * @param index_op
	 * 			the index of the option in the particular OptionSet
	 * @param name
	 * 			name of the option
	 * @param price
	 * 			price of the option
	 */
	public  void setOption(int index_set, int index_op, String name, float price) {
		optionSet.get(index_set).setOption(index_op, name, price);
	}
	
	public  String getName() {
		return name;
	}
	
	public  int getBasePrice() {
		return basePrice;
	}
	
	public  OptionSet getOptionSet(int index) {
		return optionSet.get(index);
	}
	
	public int getExactOptionSetSize(int index) {
		return optionSet.get(index).getSize();
	}
	
	public  int getOptionSetSize() {
		return optionSet.size();
	}
	
	public String getOptionSetName(int index) {
		return optionSet.get(index).getName();
		}
	
	public  OptionSet findOptionSet(String name) {
		for(int i=0; i<optionSet.size(); i++) {
			if(optionSet.get(i).getName().equals(name)) {
				return optionSet.get(i);
			}
		}
		return null;
	}
	
	public void printOptionSet(int index) {
		OptionSet optSet = getOptionSet(index);
		System.out.println(optSet);
	}
	
	public String getOptionName(String optionSetName, int index) {
		OptionSet optSet = findOptionSet(optionSetName);
		return optSet.getOption(index).getName();
		
	}
	
	public  Option findOption(String name) {
		for(int i=0; i<optionSet.size(); i++) {
			for(int j=0; j<optionSet.get(i).getSize(); j++) {
				if(optionSet.get(i).getOption(j).getName().equals(name)) {
					return optionSet.get(i).getOption(j);
				}
			}
		}
		return null;
	}
	
	public  void updateOptionSetName(String name, String newName) {
		OptionSet opset = findOptionSet(name);
		opset.setName(newName);
	}
	
	public  void updateOptionPrice(String name, float price) {
		Option opt = findOption(name);
		opt.setPrice(price);
	}
	
	public  boolean updateOptionName(String optionName, String newOptionName) {
		for(int i=0; i<optionSet.size(); i++) {
			for(int j=0; j<optionSet.get(i).getSize(); j++) {
				Option curr = optionSet.get(i).getOption(j);
				if(curr.getName().equals(optionName)) {
					curr.setName(newOptionName);
				}
				return true;
			}
		}
		return false;
	}
	
	public  boolean deleteOptionSet(String name) {
		for(int i=0; i<optset.length; i++) {
			
			if(optionSet.get(i).getName().equals(name)) {
				optionSet.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public  boolean deleteOption(String optionName) {
		for(int i=0; i<optset.length; i++) {
			optionSet.get(i).deleteOption(optionName);;
			return true;
		}
		return false;
	}
	
	//Refactored new code for Assignement 3
	
	public  String getOptionChoice(String setName) {
		for(int i=0; i < optionSet.size(); i++) {
			if(optionSet.get(i).getName().equals(setName)) {
				return choice.get(i).getName();
			}
		}
		
		return null;
	}
	
	public  float getOptionChoicePrice(String setName) {
		for(int i=0; i < optionSet.size(); i++) {
			if(optionSet.get(i).getName().equals(setName)) {
				return choice.get(i).getPrice();
			}
		}
		
		return -9999999;
	}
	
	public float getTotalPrice() {
		float cost = this.basePrice;
		for(int i=0; i < optionSet.size(); i++) {
			cost += getOptionChoicePrice(optionSet.get(i).getName());
		}
		return cost;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setOptionChoice(String setName, String optionName) {
		for(int i=0; i < optionSet.size(); i++) {
			OptionSet optSet = optionSet.get(i);
			if(optSet.getName().equals(setName)) {
				for(int j=0; j < optSet.getSize(); j++) {
					Option opt = optSet.getOption(j);
					if(opt.getName().equals(optionName)) {
						choice.set(i, opt);
					}
				}
			}
		}
	}
	
	public  String toString() {
		StringBuilder sb = new StringBuilder("Automotive: ");
		sb.append(name);
		sb.append(System.lineSeparator());
		sb.append("Base Price: ");
		sb.append(basePrice);
		sb.append(System.lineSeparator());
		
		for(int i=0; i<optionSet.size(); i++) {
			sb.append(optionSet.get(i).toString());
			sb.append(" Chosen Option: " + choice.get(i).getName() + " | Price: " + choice.get(i).getPrice());
			sb.append(System.lineSeparator());
		}
		
		sb.append("Total Price is: " + getTotalPrice());
		sb.append(System.lineSeparator());
		
		return sb.toString();
	}
}
	
