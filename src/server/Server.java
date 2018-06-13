package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server extends DefaultSocketServer{

	public Server(int iPort) {
		super(iPort);
		// TODO Auto-generated constructor stub
	}

	public static void main (String arg[]){   /* debug main; does daytime on local host */ 
		Server server = new Server(4443);
		System.out.println("Server is running, waiting for connections");
		server.start();
		}
}

/* Output:
 ***************************************************************************
 Hello. Enter the option you want.
1. Upload Properties file
2. Configure a car
3. Quit
1
Enter the file path
CarProperties.txt
Successful upload.
Hello. Enter the option you want.
1. Upload Properties file
2. Configure a car
3. Quit
2
1. FordWagonZTW
2. ToyotaCamry
1
Automotive: FordWagonZTW
Base Price: 5000
Option Set: Color
 Option: Blue| Price: 10.0
 Option: Red| Price: 20.0
 Chosen Option: default | Price: 0.0
Option Set: Transmission
 Option: Automoatic| Price: 300.0
 Option: Manual| Price: -200.0
 Chosen Option: default | Price: 0.0
Total Price is: 5000.0

1. Option Set: Color
 Option: Blue| Price: 10.0
 Option: Red| Price: 20.0

2. Option Set: Transmission
 Option: Automoatic| Price: 300.0
 Option: Manual| Price: -200.0

1
Option Set: Color
 Option: Blue| Price: 10.0
 Option: Red| Price: 20.0

1. Blue
2. Red
1
Automotive: FordWagonZTW
Base Price: 5000
Option Set: Color
 Option: Blue| Price: 10.0
 Option: Red| Price: 20.0
 Chosen Option: Blue | Price: 10.0
Option Set: Transmission
 Option: Automoatic| Price: 300.0
 Option: Manual| Price: -200.0
 Chosen Option: default | Price: 0.0
Total Price is: 5010.0

Hello. Enter the option you want.
1. Upload Properties file
2. Configure a car
3. Quit
2
1. FordWagonZTW
2. ToyotaCamry
1
Automotive: FordWagonZTW
Base Price: 5000
Option Set: Color
 Option: Blue| Price: 10.0
 Option: Red| Price: 20.0
 Chosen Option: Blue | Price: 10.0
Option Set: Transmission
 Option: Automoatic| Price: 300.0
 Option: Manual| Price: -200.0
 Chosen Option: default | Price: 0.0
Total Price is: 5010.0

1. Option Set: Color
 Option: Blue| Price: 10.0
 Option: Red| Price: 20.0

2. Option Set: Transmission
 Option: Automoatic| Price: 300.0
 Option: Manual| Price: -200.0

1
Option Set: Color
 Option: Blue| Price: 10.0
 Option: Red| Price: 20.0

1. Blue
2. Red
2
Automotive: FordWagonZTW
Base Price: 5000
Option Set: Color
 Option: Blue| Price: 10.0
 Option: Red| Price: 20.0
 Chosen Option: Red | Price: 20.0
Option Set: Transmission
 Option: Automoatic| Price: 300.0
 Option: Manual| Price: -200.0
 Chosen Option: default | Price: 0.0
Total Price is: 5020.0

Hello. Enter the option you want.
1. Upload Properties file
2. Configure a car
3. Quit
2
1. FordWagonZTW
2. ToyotaCamry
2
Automotive: ToyotaCamry
Base Price: 7000
Option Set: Color
 Option: Sunset Red| Price: 50.0
 Option: Grass Green| Price: 15.0
 Chosen Option: default | Price: 0.0
Option Set: Transmission
 Option: Automoatic| Price: 300.0
 Option: Manual| Price: -200.0
 Chosen Option: default | Price: 0.0
Total Price is: 7000.0

1. Option Set: Color
 Option: Sunset Red| Price: 50.0
 Option: Grass Green| Price: 15.0

2. Option Set: Transmission
 Option: Automoatic| Price: 300.0
 Option: Manual| Price: -200.0

2
Option Set: Transmission
 Option: Automoatic| Price: 300.0
 Option: Manual| Price: -200.0

1. Automoatic
2. Manual
1
Automotive: ToyotaCamry
Base Price: 7000
Option Set: Color
 Option: Sunset Red| Price: 50.0
 Option: Grass Green| Price: 15.0
 Chosen Option: default | Price: 0.0
Option Set: Transmission
 Option: Automoatic| Price: 300.0
 Option: Manual| Price: -200.0
 Chosen Option: Automoatic | Price: 300.0
Total Price is: 7300.0

Hello. Enter the option you want.
1. Upload Properties file
2. Configure a car
3. Quit
2
1. FordWagonZTW
2. ToyotaCamry
2
Automotive: ToyotaCamry
Base Price: 7000
Option Set: Color
 Option: Sunset Red| Price: 50.0
 Option: Grass Green| Price: 15.0
 Chosen Option: default | Price: 0.0
Option Set: Transmission
 Option: Automoatic| Price: 300.0
 Option: Manual| Price: -200.0
 Chosen Option: Automoatic | Price: 300.0
Total Price is: 7300.0

1. Option Set: Color
 Option: Sunset Red| Price: 50.0
 Option: Grass Green| Price: 15.0

2. Option Set: Transmission
 Option: Automoatic| Price: 300.0
 Option: Manual| Price: -200.0

1
Option Set: Color
 Option: Sunset Red| Price: 50.0
 Option: Grass Green| Price: 15.0

1. Sunset Red
2. Grass Green
1
Automotive: ToyotaCamry
Base Price: 7000
Option Set: Color
 Option: Sunset Red| Price: 50.0
 Option: Grass Green| Price: 15.0
 Chosen Option: Sunset Red | Price: 50.0
Option Set: Transmission
 Option: Automoatic| Price: 300.0
 Option: Manual| Price: -200.0
 Chosen Option: Automoatic | Price: 300.0
Total Price is: 7350.0

Hello. Enter the option you want.
1. Upload Properties file
2. Configure a car
3. Quit
3
Connection terminated. Bye

 * */
