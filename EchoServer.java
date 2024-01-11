import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    public static void main(String[] args) {
        int port = 4243;
        if (args.length > 0)
            port = Integer.parseInt(args[0]);
        try {
            // step 2.2
            ServerSocket sock = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            String message = "";
            while (!message.equalsIgnoreCase("quit")) {
                // step 2.3 accept a connection
                Socket client = sock.accept();
                System.out.println(
                        "Got a connection from port " + client.getPort() + " on " + client.getInetAddress() + ".");
                // step 2.4 read the message from the client
                Scanner in = new Scanner(client.getInputStream());
                message = in.nextLine();
                // step 2.5 send a "message received: <message>" response
                PrintWriter out = new PrintWriter(client.getOutputStream());
                out.println("message received: " + message);
                out.flush(); // flush the output stream to ensure the message is sent
                // step 2.6 close the connection to the client but NOT server socket
                out.close(); // close the output stream
                client.close(); // close the connection to the client
                in.close(); // close the input stream

            }
            // we only close the server when we get quit
            sock.close();
        } catch (IOException ioe) {
            System.out.println("Problem connecting " + ioe.getMessage());
        }
        System.out.println("goodbye");
    }
}