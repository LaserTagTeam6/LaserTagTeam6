import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class UDPSystem {

    DatagramSocket receiver;
    DatagramSocket sender;
    InetAddress broadcast_add;
    DBConnector db;
    
    //Declaring Socket Objects
    UDPSystem(int recvPort, int sendPort, DBConnector d) throws SocketException, UnknownHostException{
        db = d;
        this.receiver = new DatagramSocket(recvPort);
        this.sender = new DatagramSocket(sendPort);
        this.broadcast_add = InetAddress.getLocalHost();
    }

    //Listening Function
    public void listener() throws NumberFormatException, Exception{

        System.out.println("Server is listening...");
        while(true){
            //Declaration of byte/packet
            byte[] d = new byte[32];
            DatagramPacket packet = new DatagramPacket(d, d.length);
            
            //Receive Packet
            try {
                receiver.receive(packet);
                //System.out.println("Packet has been received: " + packet);
                
            } catch (IOException e) {
                e.printStackTrace();
            }

            //System.out.println("Checking packet format...");
            String dTostring = this.data(d).toString().replaceAll(" ", "");
            if (this.checkPacket(dTostring)){
                //System.out.println("Data format is correct");
                View.hit_counter_display.setText(this.parseData(dTostring));
                broadcast(Integer.parseInt(dTostring.split(":")[1]));;

            }
            else{
                System.out.println("Data format is incorrect");
            }
        }
    }

    public void broadcast(int idTagged) throws Exception {
        ByteBuffer buffer;
        buffer = ByteBuffer.allocate(4);
        buffer.putInt(idTagged);

        DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.array().length, this.broadcast_add, this.sender.getLocalPort());

        this.sender.send(packet);
        System.out.println(idTagged + " got shot");
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
                System.out.println("Data is negative: " + isInteger[i]);
                return false;
            }
        }

        System.out.println("Data format is acceptable: " + data);
        return true;
    }

    public String parseData(String data){
        //Data is split so we can reference them individually
        String[] splitData = data.split(":");
        int p1 = Integer.parseInt(splitData[0]);
        int p2 = Integer.parseInt(splitData[1]);
        boolean ATKisRed = false;
        boolean ATKisGreen = false;
        boolean DEFisRed = false;
        boolean DEFisGreen = false;
        String event = "";
        String tempRname = "";
        String tempGname = "";
        int tempRIndex = 0;
        int tempGIndex = 0;

        ArrayList<Integer> redIDs = db.pullIDs("red");
        ArrayList<String> redCodenames = db.pullPlayer("red");

        ArrayList<Integer> greenIDs = db.pullIDs("green");
        ArrayList<String> greenCodenames = db.pullPlayer("green");

        for(int i = 0; i < redIDs.size(); i++)
        {
            if(p1 == redIDs.get(i)){ 
                ATKisRed = true;
                //System.out.println("Red Player Attacker: " + redCodenames.get(i));
                tempRname = redCodenames.get(i);
                tempRIndex = i;
                
            }
            else if(p2 == redIDs.get(i)){
                DEFisRed = true;
                //System.out.println("Red Player Defender: " + redCodenames.get(i));
                tempRname = redCodenames.get(i);
                tempRIndex = i;
            }
        }

        for(int j = 0; j < greenIDs.size(); j++)
        {
            if(p1 == greenIDs.get(j)){
                ATKisGreen = true;
                //System.out.println("Green Player Attacker: " + greenCodenames.get(j));
                tempGname = greenCodenames.get(j);
                tempGIndex = j;
            }
            else if(p2 == greenIDs.get(j)){
                DEFisGreen = true;
                //System.out.println("Green Player Defender: " + greenCodenames.get(j));
                tempGname = greenCodenames.get(j);
                tempGIndex = j;
            }
        }
        if(ATKisRed && DEFisGreen)
        {
            //Red Player Individual Score
            event = tempRname + " shot " + tempGname;
            int playerScore = Integer.parseInt(View.lblRedScores[tempRIndex].getText());
            View.lblRedScores[tempRIndex].setText(String.valueOf(playerScore + 10));

            //Red Total Score
            int totalPlayerScore = Integer.parseInt(View.lblRedTotalScore.getText());
            View.lblRedTotalScore.setText(String.valueOf(totalPlayerScore + 10));
        }
        else if(ATKisGreen && DEFisRed)
        {
            //Green Player Individual Score
            event = tempGname + " shot " + tempRname;
            int playerScore = Integer.parseInt(View.lblGreenScores[tempGIndex].getText());
            View.lblGreenScores[tempGIndex].setText(String.valueOf(playerScore + 10));
            
            //Green Total Score
            int totalPlayerScore = Integer.parseInt(View.lblGreenTotalScore.getText());
            View.lblGreenTotalScore.setText(String.valueOf(totalPlayerScore + 10));
        }
        else{
            System.out.println("Something went wrong");
        }
        return event;
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