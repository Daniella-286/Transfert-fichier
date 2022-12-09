package affichage;
import javax.swing.*;

import affichage.Affichage;

import java.awt.*;;

public class Fenetre extends JFrame {
    CardLayout card=new CardLayout();
    Container container;

    public CardLayout getCard() {
        return card;
    }

    public Container getContainer() {
        return container;
    }

    public Fenetre()
    {

        setTitle("Formulaire");
        setSize(500,500);
        setLayout(card);
        container = getContentPane();


        Affichage affichage=new Affichage();
        affichage.setFen(this);
        JPanel pannel = affichage.window();
        container.add("a",pannel);
        setVisible(true);
    }

}
