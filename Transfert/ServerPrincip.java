package server;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.util.Vector;

import javax.management.ValueExp;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JTextField;


public class ServerPrincip {
    int port;
    int portChild;
    int portDaugther;
    int portSon;
    ServerSocket serverSocket;

    public ServerPrincip(int port, ServerSocket serverSocket) {
        this.port = port;
        this.serverSocket = serverSocket;
    }

    public ServerPrincip(int port, int portChild, int portDaugther, int portSon, ServerSocket serverSocket) {
        this.port = port;
        this.portChild = portChild;
        this.portDaugther = portDaugther;
        this.portSon = portSon;
        this.serverSocket = serverSocket;
    }

    public ServerPrincip(){}


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPortChild() {
        return portChild;
    }

    public void setPortChild(int portChild) {
        this.portChild = portChild;
    }

    public int getPortDaugther() {
        return portDaugther;
    }

    public void setPortDaugther(int portDaugther) {
        this.portDaugther = portDaugther;
    }

    public int getPortSon() {
        return portSon;
    }

    public void setPortSon(int portSon) {
        this.portSon = portSon;
    }


    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    
    

    public byte[] receiveClient() throws IOException{
        byte[] bytes = null;
        try {
            ServerSocket server = this.getServerSocket();
            Socket client = server.accept();

            
            //mandray ilay fichier
            InputStream is = client.getInputStream();
            ObjectInputStream message = new ObjectInputStream(is);
            Object obj  = message.readObject();  
            File fichier = (File) obj; 
            System.out.println(" fichier : "+ fichier);


            ///maka ilay byte fichier afahany mitsinjara azy
            bytes = Files.readAllBytes(fichier.toPath());

            is.close();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    //mandef ilay partie sasany ilay fichier mankany amin'ny serverHafa
    public void sendChild() throws Exception {
        try {

            //mampiasa ilay port Child andefasan fichier
            Socket client = new Socket("localhost" , this.getPortChild());


            byte[] bytes = receiveClient();


            // mizara ilay byte[] 3 hakany @ serverSecondaire
            int taille  = bytes.length / 3;
            int i = 0;

            byte[] b1 = new byte[taille];
            for (int j = 0; j < 277; j++) {
                b1[j] = bytes[j];
            }
            OutputStream output = client.getOutputStream(); 
            DataOutputStream outputStream = new DataOutputStream(output); 
            outputStream.write(b1);


            //mapiasa ilay port Daughter andefasan fichier
            Socket client1 = new Socket("localhost" , this.getPortDaugther());

            
            byte[] b2 = new byte[taille];
            for (int j = 277; j < 554; j++) {
                b2[i] = bytes[j];
                i++;
            }
            OutputStream output1 = client1.getOutputStream(); 
            DataOutputStream outputStream1 = new DataOutputStream(output1); 
            outputStream1.write(b2);




            ///mapiasa ilay port son andefasan fichier
            Socket client2 = new Socket("localhost" , this.getPortSon());

            
            byte[] b3 = new byte[taille];
            for (int j = 554 ; j < bytes.length; j++) {
                b3[i] = bytes[j];
                i++;
            }
            OutputStream output2 = client2.getOutputStream(); 
            DataOutputStream outputStream2 = new DataOutputStream(output2); 
            outputStream2.write(b3); 
      
            output.close();
            output1.close();
            output2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

    public String receiveChild() throws Exception {
        String path = "" ;
        try {
            //mampiasa ilay port Child andraisana fichier
            Socket client = new Socket("localhost" , this.getPortChild());

            ///mandray ilay objet avy any @ Child
            InputStream is = client.getInputStream();                                      
            ObjectInputStream message = new ObjectInputStream(is);
            Object obj  = message.readObject();
            System.out.println(" Server1 : "+ obj );



            //mapiasa ilay port Daughter andraisana fichier
            Socket client1 = new Socket("localhost" , this.getPortDaugther());
            
            ///mandray ilay objet avy any @ Daughter
            InputStream is1 = client1.getInputStream();                                       
            ObjectInputStream message1= new ObjectInputStream(is1);
            Object obj1  = message1.readObject();
            //System.out.println(" Server2 : "+ obj1 );

            

            ///mapiasa ilay port Son andraisana fichier
            Socket client2 = new Socket("localhost" , this.getPortSon());
            
            ///mandray ilay objet avy any @ Son
            InputStream is2 = client2.getInputStream();                                       
            ObjectInputStream message2 = new ObjectInputStream(is2);
            Object obj2  = message2.readObject();
            //System.out.println(" Server3 : "+ obj2 );


            ///manambatra ilay objets rehetra avy any @ serversecondaire
            Object[] objects = new Object[3];
            objects[0] = obj ; 
            objects[1] = obj1 ; 
            objects[2] = obj2 ; 

            ///avadika fichier mba andefasana azy any @ client
            path = "final.txt";
            File fichier=new File(path);
            fichier.createNewFile();
            FileWriter pen=new FileWriter(fichier,true);
            BufferedWriter pencil=new BufferedWriter(pen);
            for (int j = 0; j < objects.length; j++) {
                pencil.write((String)(objects[j]));
            }
            pencil.close();

            is.close();
            is1.close();
            is2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    ///mamerina ilay fichier manakany @ client
    public void sendClient() throws Exception {
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}



