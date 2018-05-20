package Model;

import java.io.Serializable;

class Option implements Serializable{
	private String name;
	private float price;
	
	protected Option(){
		this.name = "default";
		this.price = 0;
	}
	
	protected Option(String n, int p){
		this.name = n;
		this.price = p;
	}
	
	protected void setName(String n) {
		name = n;
	}
	
	protected void setPrice(float p) {
		price = p;
	}
	
	protected float getPrice() {
		return price;
	}
	
	protected String getName() {
		return name;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder(" Option: ");
		sb.append(name);
		sb.append("| Price: ");
		sb.append(price);
		
		return sb.toString();
	}
}
