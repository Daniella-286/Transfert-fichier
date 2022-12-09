package affichage;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Affichage {
    Listener list=new Listener();
    JTextField texte;
    Fenetre fen;
    
    public void setFen(Fenetre fen) {
        this.fen = fen;
    }

    public void setTexte(JTextField text) {
        this.texte = text;
    }

    public JTextField getTexte() {
        return texte;
    }

    public JPanel window() {
        JPanel pannel = new JPanel();
        JButton bouton=new JButton("UPLOAD");
        JButton bouton1=new JButton("DOWNLOAD");
        JLabel label = new JLabel("Veiller presser sur le boutton de votre choix");
        pannel.setPreferredSize(new Dimension(10,10));
        pannel.add(label);
        pannel.add(bouton);
        pannel.add(bouton1);
        bouton.addMouseListener(list);
        bouton1.addMouseListener(list);
        list.setAffichage(this);
        list.setFen(fen);
        return pannel;
    }

    public JPanel upload() {
        JPanel pannel = new JPanel();
        JLabel attr = new JLabel("Entrer le port : ");
        JButton bouton = new JButton("Valider");
        JButton bouton1 = new JButton("Choisir votre fichier");
        pannel.setLayout(new GridLayout(6,1));
        pannel.setPreferredSize(new Dimension(50,50));

            JPanel pan = new JPanel();
            texte = new JTextField();
            texte.setPreferredSize(new Dimension(70,25));
            pan.add(attr);
            pan.add(texte);
            pannel.add(pan);
       
        JPanel panel = new JPanel();
        panel.add(bouton);
        panel.add(bouton1);
        pannel.add(panel);
        bouton.addMouseListener(list);
        bouton1.addMouseListener(list);
        return pannel;
    }

    public JPanel download() {
        JPanel pannel = new JPanel();

        return pannel;
    }

   

}
