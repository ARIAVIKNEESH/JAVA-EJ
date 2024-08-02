import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SimpleUDPClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 6789;

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(hostname);
            Scanner scanner = new Scanner(System.in);

            String text;
            byte[] buffer = new byte[1024];

            do {
                System.out.print("Enter message: ");
                text = scanner.nextLine();
                byte[] data = text.getBytes();

                DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
                socket.send(packet);

                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(responsePacket);

                String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
                System.out.println(response);

            } while (!text.equals("bye"));
        } catch (Exception e) {
            System.out.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
