import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FriendRequestClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

            output.writeUTF("Friend Request: Will you be my friend?");

            String response = input.readUTF();
            if (response.equalsIgnoreCase("yes")) {
                System.out.println("Friend request accepted! Start chatting:");

                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.print("You: ");
                    String message = scanner.nextLine();
                    output.writeUTF(message);

                    String reply = input.readUTF();
                    System.out.println("Friend: " + reply);
                }
            } else {
                System.out.println("Friend request denied.");
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
