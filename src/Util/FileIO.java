package Util;
import java.io.*;
import java.util.*;

import Exception.AutoException;
import Model.*;

public class FileIO {
	
	public FileIO() {}
	
	public Automobile buildAutoObject (String filename) throws AutoException{
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
}

