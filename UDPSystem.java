import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class UDPSystem {

    DatagramSocket receiver;
    DatagramSocket sender;
    InetAddress broadcast_add;
    
    //Declaring Socket Objects
    public UDPSystem(int recvPort, int sendPort) throws SocketException, UnknownHostException{
        this.receiver = new DatagramSocket(recvPort);
        this.sender = new DatagramSocket(sendPort);
        this.broadcast_add = InetAddress.getLocalHost();
    }

    //Listening Function
    public void listener(){
        while(true){
            //Declaration of byte/packet
            byte[] d = new byte[32];
            DatagramPacket packet = new DatagramPacket(d, d.length);
            
            //Receive Packet
            try {
                receiver.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //checkPacket

        }
    }

    //Check if incoming packets are in the correct format
    public void checkPacket(){

    }


// A utility method to convert the byte array
// data into a string representation.
public static StringBuilder data(byte[] a)
{
    if (a == null)
        return null;
    StringBuilder ret = new StringBuilder();
    int i = 0;
    while (a[i] != 0)
    {
        ret.append((char) a[i]);
        i++;
    }
    return ret;
}
}