import java.io.*;
import java.net.*;

public class WebCalculatorClient {
	public static void main(String[] args) throws Exception {
		double num1, num2;
		String operator, result, response, serverIP = "127.0.0.1";
		int nport = 6789;
		Socket clientSocket = new Socket(serverIP, nport);
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
        System.out.println("Enter two numbers and an operator (+, -, *, /, %) once at a time.");

		num1 = ((double) Double.parseDouble(inFromUser.readLine()));
		outToServer.writeBytes("" + num1 + '\n');
		response = inFromServer.readLine();

        System.out.println("RESPONSE FROM SERVER: " + response);
		
		if (!response.equalsIgnoreCase("ok")) {
			System.out.println("1: Bad response from server");
			
			System.exit(1);
		}
		
		num2 = ((double) Double.parseDouble(inFromUser.readLine()));
		outToServer.writeBytes("" + num2 + '\n');
		response = inFromServer.readLine();

        System.out.println("RESPONSE FROM SERVER: " + response);
		
		if (!response.equalsIgnoreCase("ok")) {
			System.out.println("2: Bad response from server");
			
			System.exit(2);
		}
		
		operator = inFromUser.readLine();
		outToServer.writeBytes(operator + "\n");
		response = inFromServer.readLine();

        System.out.println("RESPONSE FROM SERVER: " + response);
		
		if (!response.equalsIgnoreCase("ok")) {
			System.out.println("3: Bad response from server");
			
			System.exit(3);
		}
		
		result = inFromServer.readLine();
		outToServer.writeBytes("ok\n");
		
		System.out.println("RESULT FROM SERVER: " + result);
		
		clientSocket.close();
	}
}
