package Driver;
import Util.FileIO;
import Adapter.*;
import Exception.AutoException;
import Scale.Scalable;


public class Driver {

	public static void main(String[] args) throws AutoException {
		//read Automotive from text file
		CreateAuto car = new BuildAuto();
			FileIO f = new FileIO();
			String filename = f.readUserInput();
			car.BuildAuto(filename);
			
			car.printAuto("Ford Wagon ZTW");
			
			Scalable scaleable = new BuildAuto();
		
			String input[] = {"1", "1", "Ford Wagon ZTW", "Color", "Fort Knox Gold Clearcoat Metallic", "BlueCoastalGoldHawaii", "0"};
			String input2[] = {"1", "2", "Ford Wagon ZTW", "Color", "Fort Knox Gold Clearcoat Metallic", "BlueCoastalGoldCalifornia", "1000"};
			
			//calling threads with synchronization
			scaleable.operation(input);
			scaleable.operation(input2);
			
			
	}

}
