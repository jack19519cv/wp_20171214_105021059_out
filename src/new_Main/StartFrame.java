package new_Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartFrame extends JFrame{
    private Container cp;
    private Timer t1;
    private JPanel backgroundpane=new JPanel(new BorderLayout(0,0));
    private Image image;
    private int x,y=0;
    private Graphics g;
    public StartFrame(){
        initComp();
    }
    private void initComp(){
        this.setBounds(200,200,1000,600);
        cp=this.getContentPane();
        cp.add(backgroundpane);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try{
            image= ImageIO.read(new File("skybackground.jpg"));
        }catch(IOException ie){
            ie.printStackTrace();;
        }

        t1=new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x-=10;
                repaint();
            }
        });
        t1.start();

    }
    public void paint(Graphics g){
//        Graphics2D g2d=(Graphics2D) g;
//        g2d.drawImage(image,0,0,null,this);
        if(x<=0){
            g.drawImage(image,1000+x,y,1000, 600,this);
        }
        g.drawImage(image,x,y,1000, 600,this);
        if(x<=-1000){
            x=0;
        }



    }
}
