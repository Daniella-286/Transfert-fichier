package client;

import java.io.*;
import java.net.*;
import java.util.Vector;


public class Client {
    int portC;

    public int getPortC() {
        return portC;
    }

    public void setPortC(int portC) {
        this.portC = portC;
    }

    public Client(int portC) {
        this.portC = portC;
    }

    public Client(){};

    public String[] Split() throws IOException
    {
        String rep = lireFichier();
        String[] val=rep.split(";");
        return val;
    }


    //mandef an'ilay fichier manakany amn ServerPrincip
    public void sendFiche(String path) throws IOException {
        try {
            Socket client = new Socket("localhost" , this.getPortC());

            File file = new File(path);

            OutputStream output = client.getOutputStream();                                       
            ObjectOutputStream objoutput = new ObjectOutputStream(output); 
            objoutput.writeObject(file);

            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String lireFichier() throws IOException
    {
        FileReader read=new FileReader("fichier.txt");
        BufferedReader ab=new BufferedReader(read);
        return ab.readLine();
    }

    ///mandray ilay fichier tanuy @ sreverPricipal
    public void receiveFiche() throws Exception {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
