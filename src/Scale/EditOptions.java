package scale;

import adapter.ProxyAutomobile;

public class EditOptions extends ProxyAutomobile implements Runnable{
	
	private int opNumber;
	private String[] input;
	private int threadno;
	private boolean sync = true;
	
	public EditOptions(int opNumber, String[] input) {
		this.opNumber = opNumber;
		this.input = input;
		this.threadno = Integer.parseInt(input[1]);
	}
	
	public void runSyncUpdate() {
		this.sync = true;
		new Thread(this).start();
	}
	
	public void runNonSyncUpdate() {
		this.sync = false;
		new Thread(this).start();
	}

	public void run() {
		if(sync) {
			switch (opNumber) {
			case 1:
				try {
					Thread.sleep(Long.parseLong(input[6]));
				} catch (NumberFormatException e) {
					System.out.println("The sleep time you inputted is not the correct format");
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Start thread " + threadno + " Update Option");
				break;
			}
			ops();
			System.out.println("Stopping thread " + threadno);
		}else {
			switch (opNumber) {
			case 1:
				try {
					Thread.sleep(Long.parseLong(input[6]));
				} catch (NumberFormatException e) {
					System.out.println("The sleep time you inputted is not the correct format");
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Start thread " + threadno + " Update Option");
				break;
			}
			nonSyncOps();
			System.out.println("Stopping thread " + threadno);
		}

	}

	private synchronized void ops() {
		switch (opNumber) {
		case 1:
			updateOptionName(input[2], input[4], input[5]);
			printAuto(input[2]);
			break;
		}
	}
	
	private void nonSyncOps() {
		switch (opNumber) {
		case 1:
			updateOptionName(input[2], input[4], input[5]);
			printAuto(input[2]);
			break;
		}
	}
	
	
}
