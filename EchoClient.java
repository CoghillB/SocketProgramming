/*Author: Brayden Coghill
 * Date: December 3, 2023
 * Description: This program is a client that sends a message to the server and receives a response.
 */
// Step 1.1 copy in the skeleton code
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    public static void main(String args[]) {
        int port = 4243;
        try {
            // Step 1.2 create a connection to the server
            Socket socket = new Socket("localhost", port);
            // Step 1.3 get a message from the user to send to the server
            Scanner input = new Scanner(System.in);
            try {
                System.out.print("Enter a message to send: ");
                String message = input.nextLine();

                // Step 1.4 sent the message to the server
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.println(message);

                // step 1.5 receive response from the server
                Scanner in = new Scanner(socket.getInputStream());
                if (in.hasNextLine()) {
                    String response = in.nextLine();
                    System.out.println("Server responded: " + response);
                } else {
                    System.out.println("No response from the server.");
                }

                // step 1.6 closing everything
                out.close();
                in.close();
            } finally {
                input.close();
            }
            socket.close();

        } catch (IOException ioe) {
            System.out.println("Problem connecting " + ioe.getMessage());
        }
    }
}
