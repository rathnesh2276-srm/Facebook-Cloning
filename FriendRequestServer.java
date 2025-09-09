import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FriendRequestServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Waiting for friend request...");

            Socket socket = serverSocket.accept();
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            String request = input.readUTF();
            System.out.println("Received: " + request);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Do you want to accept the friend request? (yes/no): ");
            String response = scanner.nextLine();

            output.writeUTF(response);

            if (response.equalsIgnoreCase("yes")) {
                System.out.println("Friend request accepted! Start chatting:");

                // Start chat
                while (true) {
                    String message = input.readUTF();
                    System.out.println("Friend: " + message);
                    
                    System.out.print("You: ");
                    String reply = scanner.nextLine();
                    output.writeUTF(reply);
                }
            } else {
                System.out.println("Friend request denied.");
            }

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
