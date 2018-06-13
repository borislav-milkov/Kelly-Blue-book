package exception;

import java.io.*;
import java.text.*;
import java.util.*;

public class AutoException extends Exception{
	
	private int errorNum;
	private String message;	
	
	public AutoException(int err, String msg){
		this.errorNum = err;
		this.message = msg;
		writeLog(msg);
		
	}
	
	public int getErrNum() {
		return errorNum;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void fix(int errNo) {
		
		Fix1to100 f1 = new Fix1to100();
		if(errNo > 0 && errNo <= 100) {
			switch (errNo) {
			case 1:
				f1.fix1();
				break;
			case 99:
				f1.fix99(message);
				break;
			}
		}
	}
	
	public void writeLog(String errlog) {
		try {
			DateFormat d1 = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
			Date date = new Date();
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("errlog.txt", true)));
			writer.println("[" + d1.format(date) + "] " + errlog);
			writer.close();
		}
		catch (IOException e) {
			System.out.println("IO Error, try restarting the process");
			System.exit(1);
		}
		finally {
			
		}
	}
	

}
