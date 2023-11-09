import java.io.*;
import java.net.*;

@SuppressWarnings("resource")
public class WebCalculatorServer {
	public static void main(String[] args) throws IOException {
		double num1, num2, result = 0;
		String operator, response;
		int nport = 6789;
		ServerSocket welcomeSocket;
		
		welcomeSocket = new ServerSocket(nport);
		
		System.out.println("Server start.. (port#=" + nport + ")\n");

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			
			num1 = ((double) Double.parseDouble(inFromClient.readLine()));
			outToClient.writeBytes("ok\n");
			
			System.out.println("NUMBER 1 FROM CLIENT: " + num1);
			
			num2 = ((double) Double.parseDouble(inFromClient.readLine()));
			outToClient.writeBytes("ok\n");
			
			System.out.println("NUMBER 2 FROM CLIENT: " + num2);
			
			operator = inFromClient.readLine();
			outToClient.writeBytes("ok\n");
			
			System.out.println("OPERATOR FROM CLIENT: " + operator);
			
			if (operator.equals("+")) {
				result = num1 + num2;
				outToClient.writeBytes("" + result);
			}
			else if (operator.equals("-")) {
				result = num1 - num2;
				outToClient.writeBytes(result + "\n");
			}
			else if (operator.equals("*")) {
				result = num1 * num2;
				outToClient.writeBytes(result + "\n");
			}
			else if (operator.equals("/")) {
				result = num1 / num2;
				outToClient.writeBytes(result + "\n");
			}
			else if (operator.equals("%")) {
				result = num1 % num2;
				outToClient.writeBytes(result + "\n");
			}
			else outToClient.writeBytes("no\n");
			
			response = inFromClient.readLine();

			System.out.println("RESPONSE FROM CLIENT: " + response);
			
			if (!response.equalsIgnoreCase("ok")) {
				System.out.println("A: Bad response from client");
				
				System.exit(0);
			}
		}
	}
}
