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
            String dTostring = this.data(d).toString().replaceAll(" ", "");
            if (this.checkPacket(dTostring)){
                
            }

        }
    }

    //Check if incoming packets are in the correct format
    public boolean checkPacket(String data){
        //Checks if data is empty
        if(data.isEmpty()){
            System.out.println("Data is empty: '" + data + "'");
            return false;
        }
        
        //Checks if data can be split in 2
        String[] dataFormat = data.split(":");
        if(dataFormat.length != 2){
            System.out.print("Data cannot be split: '" + data + "'");
            return false;
        }

        //Checks if data is an integer
        int[] isInteger = new int[dataFormat.length];
        try {
            for(int i = 0; i < dataFormat.length; i++){
                isInteger[i] = Integer.parseInt(dataFormat[i]);
            }
        } catch (Exception e) {
            System.out.println("Data is not an integer: '" + data + "'");
            return false;
        }

        //Check if data is positive (May not be necessary)
        for(int i = 0; i < dataFormat.length; i++){
            if(isInteger[i] < 0){
                System.out.println("Data" + isInteger[i] + "is negative");
                return false;
            }
        }

        System.out.println("Data format is acceptable: " + data);
        return true;
    }

    public String parseData(String data){
        return data; //Change this later
    }
    

// A utility method to convert the byte array
// data into a string representation.
public StringBuilder data(byte[] a)
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