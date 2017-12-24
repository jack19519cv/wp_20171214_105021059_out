package new_Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SkyPane extends JPanel implements Runnable{
    private BufferedImage image;
   // private Image image;
    private Timer t1;
    private int x,y=0;
    private  int imgW, imgH;
    public SkyPane(){
       init();
        try{
            image= ImageIO.read(new File("skybackgroundRe.png"));
           // imgW= image.getWidth();
            imgW= image.getWidth();
            imgH = image.getHeight();
           // imgH = 500;
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }
    private void init(){


    }

    @Override
    public void run() {
        t1=new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x-=10;
                SkyPane.this.repaint();
            }
        });
        t1.start();
    }
    public void paint(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
//        g2d.drawImage(image,0,0,null,this);
        if(x<=0){
            g2d.drawImage(image,1000+x,y,1000, 600,this);
        }
        g2d.drawImage(image,x,y,1000, 600,this);
        if(x<=-1000){
            x=0;
        }
    }
    public  int getImgWidth(){
        return imgW;
    }
    public int getImgHeight(){
        return imgH;
    }
}
