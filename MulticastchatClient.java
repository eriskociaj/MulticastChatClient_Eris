import java.net.*;
import java.io.*;

public class MulticastchatClient {

    public static void main(String args[]) throws Exception {

        // Default port number we are going to use
        int portnumber = 4446; // Changed default port number to match the server

        if (args.length > 0) // Changed condition to check if at least one argument is provided
            portnumber = Integer.parseInt(args[0]);

        // Create a MulticastSocket
        MulticastSocket chatMulticastSocket = new MulticastSocket(portnumber);

        // Determine the IP address of a host, given the host name
        InetAddress group = InetAddress.getByName("225.4.5.6");

        // Create a SocketAddress from the IP address and port number
        InetSocketAddress groupAddress = new InetSocketAddress(group, portnumber);

        // Obtain a network interface for multicast
        NetworkInterface networkInterface = NetworkInterface.getNetworkInterfaces().nextElement(); // Adjusted to match server's approach

        // Joins a multicast group using the new method
        chatMulticastSocket.joinGroup(groupAddress, networkInterface);

        // Clear the console or add a visual separator before the prompt
        System.out.println("\n-------------------------------------------------");
        System.out.println("Type a message for the server: ");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String msg = br.readLine();

        // Send the message to Multicast address
        DatagramPacket data = new DatagramPacket(msg.getBytes(), 0, msg.length(), group, portnumber);

        chatMulticastSocket.send(data);

        // Close the socket
        chatMulticastSocket.close();
    }
}