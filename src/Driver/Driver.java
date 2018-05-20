package Driver;
import Util.FileIO;
import Adapter.*;
import Exception.AutoException;


public class Driver {

	public static void main(String[] args) throws AutoException {
		//read Automotive from text file
		CreateAuto car = new BuildAuto();
			FileIO f = new FileIO();
			String filename = f.readUserInput();
			car.BuildAuto(filename);
		
			car.printAuto("Ford Wagon ZTW");
			
			UpdateAuto a3 = new BuildAuto();
			a3.updateOptionSetName("Ford Wagon ZTW", "Brakes", "New Brakes");
			car.printAuto("Ford Wagon ZTW");
		
		
	}

}
