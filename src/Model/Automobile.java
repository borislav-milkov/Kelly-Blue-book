package Model;

import java.io.Serializable;
import java.util.ArrayList;
import Model.*;

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
		this.name = name;
		this.basePrice = basePrice;
		optionSet = new ArrayList<OptionSet>(size);
		for(int i=0; i < size; i++) {
			optionSet.add(i, new OptionSet());
		}
	}
	//default constructor
	public Automobile() {
		name = "default";
		basePrice = 0;			
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setBasePrice(int p) {
		basePrice = p;
	}
	
	public void initOptionSet(int size) {
		optionSet = new ArrayList<OptionSet>(size);
		
		for(int i=0; i < size; i++) {
			optionSet.add(i, new OptionSet());
		}
	}
	
	public void setOptionSet(int index, String name, int size) {
		optionSet.get(index).setName(name);
		optionSet.get(index).setSize(size);
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
	public void setOption(int index_set, int index_op, String name, float price) {
		optionSet.get(index_set).setOption(index_op, name, price);
	}
	
	public String getName() {
		return name;
	}
	
	public int getBasePrice() {
		return basePrice;
	}
	
	public OptionSet getOptionSet(int index) {
		return optionSet.get(index);
	}
	
	public int getOptionSetSize() {
		return optionSet.size();
	}
	
	public OptionSet findOptionSet(String name) {
		for(int i=0; i<optionSet.size(); i++) {
			if(optionSet.get(i).getName().equals(name)) {
				return optionSet.get(i);
			}
		}
		return null;
	}
	
	public Option findOption(String name) {
		for(int i=0; i<optionSet.size(); i++) {
			for(int j=0; j<optionSet.get(i).getSize(); j++) {
				if(optionSet.get(i).getOption(j).getName().equals(name)) {
					return optionSet.get(i).getOption(j);
				}
			}
		}
		return null;
	}
	
	public void updateOptionSetName(String name, String newName) {
		OptionSet opset = findOptionSet(name);
		opset.setName(newName);
	}
	
	public void updateOptionPrice(String name, float price) {
		Option opt = findOption(name);
		opt.setPrice(price);
	}
	
	public boolean deleteOptionSet(String name) {
		for(int i=0; i<optset.length; i++) {
			
			if(optionSet.get(i).getName().equals(name)) {
				optionSet.remove(i);
				return true;
			}
		}
		return false;
	}
	
	//Refactored new code for Assignement 3
	
	public String getOptionChoice(String setName) {
		for(int i=0; i < optionSet.size(); i++) {
			if(optionSet.get(i).getName().equals(setName)) {
				return choice.get(i).getName();
			}
		}
		
		return null;
	}
	
	public float getOptionChoicePrice(String setName) {
		for(int i=0; i < optionSet.size(); i++) {
			if(optionSet.get(i).getName().equals(setName)) {
				return choice.get(i).getPrice();
			}
		}
		
		return -9999999;
	}
	
	public float getTotalPrice() {
		float cost = 0;
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
					choice.set(i, opt);
				}
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("Automotive: ");
		sb.append(name);
		sb.append(System.lineSeparator());
		sb.append("Base Price: ");
		sb.append(basePrice);
		sb.append(System.lineSeparator());
		
		for(int i=0; i<optionSet.size(); i++) {
			sb.append(optionSet.get(i).toString());
		}
		
		return sb.toString();
	}
}
	
