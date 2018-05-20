package Model;

import java.io.Serializable;
import java.util.ArrayList;

class OptionSet implements Serializable{
	private ArrayList<Option> opt;
	private String name;
	
	OptionSet(){
		this.name = "default";
	}
	
	OptionSet(String n, int size){
		opt = new ArrayList<Option>(size);
		name = n;
		for(int i=0; i < size; i++) {
			opt.add(i, new Option());
		}

	}
	
	protected void setSize(int size) {
		opt = new ArrayList<Option>(size);
		
		for(int i=0; i < size; i++) {
			opt.add(i, new Option());
		}
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	protected void setOption(int index, String name, float price) {
		opt.get(index).setName(name);
		opt.get(index).setPrice(price);
	}
	
	protected String getName() {
		return name;
	}
	
	protected Option getOption(int index) {
		return opt.get(index);
	}
	
	protected int getSize() {
		return opt.size();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("Option Set: ");
		sb.append(name);
		sb.append(System.lineSeparator());
		for(int i=0; i<opt.size(); i++) {
			sb.append(opt.get(i));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
	
}
