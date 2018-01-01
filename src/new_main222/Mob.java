package new_main222;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by jackwang on 2017/12/23.
 */
public class Mob extends JLabel {
    private BufferedImage image;
    // private Image image;
    private Timer t1;
  //  private int x,y=0;
    private  int imgW, imgH;
    private  int frmH,frmW,x,y,r1,r;
    private ImageIcon jump[]=new ImageIcon[3];
    private ImageIcon walk[]=new ImageIcon[6];
    private ImageIcon stand[]=new ImageIcon[6];
    public Mob(int frmH, int frmW){
        this.frmH= frmH;
        this.frmW = frmW;
        init();

    }
    private void init(){
//        try{
//                image = ImageIO.read(new File("Slime/walk/left/move.0.png"));
//            // imgW= image.getWidth();
//            imgW= image.getWidth();
//            imgH = image.getHeight();
//            // imgH = 500;
//        }catch(IOException ie){
//            ie.printStackTrace();
//        }

    }

//    @Override
//    public void run() {
//        t1=new Timer(200, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                x-=10;
//                Mob.this.repaint();
//            }
//        });
//        t1.start();
//    }
//    public void paint(Graphics g){
//        Graphics2D g2d=(Graphics2D) g;
////        g2d.drawImage(image,0,0,null,this);
//        if(x<=0){
//            g2d.drawImage(image,1000+x,y,80, 60,this);
//        }
//        g2d.drawImage(image,x,y,80, 60,this);
//        if(x<=-1000){
//            x=0;
//        }
//    }
    public  int getImgWidth(){return imgW;}
    public int getImgHeight(){
        return imgH;
    }
        @Override
    protected  void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        super.paintComponent(g);
        g2d.drawImage(image,getX(),getY(),null);

    }
    private  void  setMobAnimal(){
        for(int i=0;i<3;i++){
            walk[i]=new ImageIcon("Slime/walk/left/move."+Integer.toString(i)+".png");
        }
        for(int i=3;i<6;i++){
            walk[i]=new ImageIcon("Slime/walk/right/move."+Integer.toString(i-3)+".png");
        }
        for(int i=0;i<3;i++) {
            jump[i] = new ImageIcon("Slime/jump/left/move." + Integer.toString(i) + ".png");
        }
        for(int i=0;i<3;i++) {
            jump[i]=new ImageIcon("Slime/jump/right/move."+Integer.toString(i-3)+".png");
        }
        for(int i=0;i<3;i++) {
            stand[i] = new ImageIcon("Slime/stand/left/stand." + Integer.toString(i) + ".png");
        }
        for(int i=0;i<3;i++) {
            stand[i]=new ImageIcon("Slime/stand/right/stand."+Integer.toString(i-3)+".png");
        }

    }
}

