package Exception;

import Adapter.*;
import Model.Automobile;
import Util.FileIO;

class Fix1to100 {
	
	protected void fix1() {
		System.out.println("The file name you have used is invalid or empty.");
		try {
			System.out.println("Loading default file car_info.txt");
			FileIO f = new FileIO();
			Automobile a1 = f.buildAutoObject("car_info.txt");
			CreateAuto car = new BuildAuto();
			car.setAuto(a1.getName() ,a1);
		} catch (AutoException e) {
			if(e.getErrNum() == 1) {
				System.out.println("Default file is not present. Please enter a valid file name and try again.");
			}else {
				e.fix(e.getErrNum());
			}
		}
	}
	
	protected void fix99(String msg) {
		System.out.println(msg);
		System.out.println("This usually happens from a missing file.");
	}
}
