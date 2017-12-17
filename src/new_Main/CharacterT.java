package new_Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterT extends Thread {
    private ImageIcon alert[]=new ImageIcon[6];
    private ImageIcon jump[]=new ImageIcon[2];
    private ImageIcon walk[]=new ImageIcon[8];
    private ImageIcon stand[]=new ImageIcon[6];
    private Timer walkT;
    private Timer alertT;
    private Timer standT;
    private Timer jumpT;
    private String now;
    public  CharacterT(ImageIcon alert[], ImageIcon jump[], ImageIcon walk[], ImageIcon stand[], String now,int noChar){
        this.alert=alert;
        this.jump=jump;
        this.walk=walk;
        this.stand=stand;
        this.now=now;
        walkT=new Timer(125, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        alertT=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        standT=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        jumpT=new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    @Override
    public void run() {
        if(now.equals("alert")){
            alertT.start();
        }else if(now.equals("walk")){
            walkT.start();
        }else if(now.equals("jump")){
            jumpT.start();
        }else if(now.equals("stand")){
            standT.start();
        }
    }
}
