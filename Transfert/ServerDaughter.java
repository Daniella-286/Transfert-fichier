package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerDaughter {
    int port;
    ServerSocket socket;

    public ServerDaughter() {
        
    }

    public ServerDaughter(int port, ServerSocket socket) {
        this.port = port;
        this.socket = socket;
    }
    
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }

    public ServerSocket getSocket() {
        return socket;
    }

    public void setSocket(ServerSocket socket) {
        this.socket = socket;
    }

    public byte[] receive() throws IOException {
        byte[] bytes = null;
        try {
            ServerSocket server = this.getSocket();
            Socket client = server.accept();

            ///mandray ilay ligne avy any @ serverPricip
            InputStream is = client.getInputStream();                                       //lire le message envoyé
            ObjectInputStream message = new ObjectInputStream(is);
            Object obj  = message.readObject();
            bytes= (byte[]) obj ; 
            for (int i = 0; i < bytes.length; i++) {
                System.out.println(bytes[i]);
            }

            is.close();

        } catch (Exception e) {
            e.printStackTrace();   
        }
        return bytes;
    }

    public void creationFichier() throws IOException
    {
        String path = "file.txt";
        File fichier=new File(path);
        byte[] bytes = this.receive();
        FileWriter pen=new FileWriter(fichier,true);
        BufferedWriter pencil=new BufferedWriter(pen);
        for (int i = 0; i < bytes.length; i++) {
            pencil.write(String.valueOf(bytes[i]) + ";" );
        }
        pencil.close();
    }

    public String lireFichier() throws IOException
    {
        FileReader read=new FileReader("file.txt");
        BufferedReader ab=new BufferedReader(read);
        return ab.readLine();
    }
    

    public void sendPrincip() throws IOException{
        try {
            ServerSocket server = this.getSocket();
            Socket client = server.accept();

            OutputStream output = client.getOutputStream(); 
            Object obj  = this.lireFichier(); 
            ObjectOutputStream objoutput = new ObjectOutputStream(output); 
            objoutput.writeObject(obj);

            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
