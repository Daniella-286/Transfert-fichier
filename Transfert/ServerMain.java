package servermain;
import java.io.*;
import java.net.*;

import affichage.Fenetre;
import client.Client;
import server.ServerChild;
import server.ServerDaughter;
import server.ServerPrincip;
import server.ServerSon;

public class ServerMain {
    public static void main(String[] args) throws IOException{
        try {
            
            // ServerSocket serverSocket=new ServerSocket(1912);
            // ServerPrincip serverPrincip=new ServerPrincip(1912,1826,1742,1112,serverSocket);
            // Client client=new Client(1912);

            // client.sendFiche("E:/fonts/fontawesome.html");
            // serverPrincip.receiveClient();

            // //////////////////////////

            // ServerSocket serverSocket2 = new ServerSocket(1826);
            // ServerChild serverChild = new ServerChild(1826, serverSocket2);

            // ServerSocket serverSocket3 = new ServerSocket(1742);
            // ServerDaughter serverDaughter = new ServerDaughter(1742,serverSocket3);

            // ServerSocket serverSocket4 = new ServerSocket(1112);
            // ServerSon serverSon = new ServerSon(1112,serverSocket4);

            // serverPrincip.sendChild();
            // serverChild.receive();
            // serverDaughter.receiveLine();
            // serverSon.receiveLine();


            Fenetre fen = new Fenetre();

            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
        

    


