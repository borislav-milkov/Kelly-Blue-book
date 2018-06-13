package util;
import java.io.*;
import java.util.*;

import exception.AutoException;
import model.*;

public class FileIO {
	
	public FileIO() {}
	
	public Automobile buildAutoObject (String filename, String fileType) throws AutoException{
		
		if(fileType.equals("Text")) {
			try {
				FileReader file = new FileReader(filename);
				BufferedReader buff = new BufferedReader(file);
				boolean eof = false;
				Automobile car = new Automobile();
				
				int count = 0;
				
				while (!eof) {
					String line = buff.readLine();
					if (line == null) {
						eof = true;
					}else {
						if(count == 0) {
							car.setName(line);
							line = buff.readLine();
							count++;
						}if(count == 1) {
							car.setBasePrice(Integer.parseInt(line));
							line = buff.readLine();
							count++;
						}if(count == 2) {
							car.initOptionSet(Integer.parseInt(line));
							count++;
						}
						for(int i=0; i < car.getOptionSetSize(); i++) {
							readOptionSet(buff, i, car);
						}
					}
				}
				buff.close();
				return car;
			} catch (IOException e) {
				throw new AutoException(1, "Invalid or empty file name entered");
			}
		}else if(fileType.equals("Properties")) {
			Properties props = loadPropertiesFile(filename);
			return null;
		}else {
			return null;
		}
		
		
	}
	
	public void readOptionSet(BufferedReader buff, int index, Automobile car) {
		try {
			String line = buff.readLine();
			String line_2 = buff.readLine();
			int size = Integer.parseInt(line_2);
			car.setOptionSet(index, line, size);
			
			boolean eof = false;
			while(!eof) {
				if (line == null) {
					eof = true;
				}else {
					for(int i=0; i<size; i++) {
						line = buff.readLine();
						StringTokenizer st = new StringTokenizer(line, ",");
						String name = st.nextToken();
						float price = Float.parseFloat(st.nextToken());
						car.setOption(index, i, name, price);
					}
					eof = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void writeToDisk(Automobile car, String filename) {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(car);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Automobile readFromDisk(String filename) {
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream(filename));
			Automobile car = (Automobile) in.readObject();
			in.close();
			return car;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String readUserInput() {
		String input;
		Scanner s = new Scanner(System.in);
		System.out.println("Enter file name where the automobile is stored");
		input = s.nextLine();
		s.close();
		return input;
	}
	
	public Properties loadPropertiesFile(String filename) {
		Properties props= new Properties(); //
		FileInputStream in;
		try {
			in = new FileInputStream(filename);
			props.load(in); //This loads the entire file in memory.
		} catch (FileNotFoundException e) {
			System.out.println("The Properties file doesn't exist. Check if the name is correct");
		} catch(IOException e) {
			System.out.println("Something went wrong in loading the Property file into memory");
		}
		return props;

	}
	
	public Automobile[] readPropertiesObject(Properties props) {
		int numAutos = Integer.parseInt(props.getProperty("NumAutos"));
		Automobile autos[] = new Automobile[numAutos];
		for(int k=1; k<numAutos+1; k++) {
			String CarName = props.getProperty("CarName"+k); //this is how you read a property. It is like getting a value from HashTable.
			int basePrice = Integer.parseInt(props.getProperty("BasePrice"+k));
			int numOptSet = Integer.parseInt(props.getProperty("NumOptionSets"+k));
			Automobile car = new Automobile(CarName, basePrice, numOptSet);
			
			for(int i=1; i<numOptSet+1; i++) {
				int sizeOptSet = Integer.parseInt(props.getProperty("OptionSetSize_" + k +"_" + i));
				String optSetName = props.getProperty("OptionSet_" + k +"_" + i);
				car.setOptionSet(i-1, optSetName, sizeOptSet);
				for(int j=1; j<sizeOptSet+1; j++) {
					String option = props.getProperty("Option_" + k + "_" + i + "_" + j);
					float optionPrice = Float.parseFloat(props.getProperty("OptionPrice_" + k + "_" + i + "_" + j));
					car.setOption(i-1, j-1, option, optionPrice);
				}
			}
			autos[k-1] = car;
		}
		return autos;
	}
}

