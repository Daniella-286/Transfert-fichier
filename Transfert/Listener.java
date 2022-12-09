package affichage;

import javax.swing.*;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import affichage.*;
import client.Client;
import server.ServerChild;
import server.ServerDaughter;
import server.ServerPrincip;
import server.ServerSon;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;

public class Listener implements MouseListener {

    Affichage affichage;
    Fenetre fen;
    ServerPrincip  serverPrincip = new ServerPrincip();
    Client client = new Client();
    ServerChild serverChild = new ServerChild();
    ServerDaughter serverDaughter = new ServerDaughter();
    ServerSon serverSon = new ServerSon();


    public void setFen(Fenetre fen)
    {
        this.fen = fen;
    }
    
    public Affichage getAffichage() {
        return affichage;
    }

    public void setAffichage(Affichage affichage) {
        this.affichage = affichage;
    }


    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        JButton bouton= (JButton) e.getSource();
        if (bouton.getText().equals("UPLOAD")) {
            fen.getContainer().add("b",affichage.upload());
            fen.getCard().next(fen.getContainer());
        }

        String path = "";
        if (bouton.getText().equals("Choisir votre fichier")) {
            JFileChooser choose = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                
            // Ouvrez le fichier
            int res = choose.showOpenDialog(null);
            // Enregistrez le fichier
            // int res = choose.showSaveDialog(null);
                
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = choose.getSelectedFile();
                path = file.getAbsolutePath();
                System.out.println(file.getAbsolutePath());
            } 
        }

        if (bouton.getText().equals("Valider")) {
            
            String text = affichage.getTexte().getText();
            int port = Integer.parseInt(text);
            System.out.println(path);
            try {
                ServerSocket serverSocket=new ServerSocket(port);
                serverPrincip=new ServerPrincip(port,serverSocket);
                client=new Client(port);
                client.sendFiche(path);
                serverPrincip.receiveClient();
                serverPrincip.sendChild();
                serverChild.receive();
                serverDaughter.receive();
                serverSon.receive(); 
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (bouton.getText().equals("DOWNLOAD")) {
            try {
                serverChild.sendPrincip();
                serverDaughter.sendPrincip();
                serverSon.sendPrincip();
                serverPrincip.receiveChild();
                serverPrincip.sendClient();
                client.receiveFiche();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
