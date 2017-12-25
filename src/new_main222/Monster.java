package new_main222;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by jackwang on 2017/12/14.
 */
public class Monster extends JPanel implements Runnable {

    private BufferedImage image;
    // private Image image;
    private  int imgW, imgH;
    private  int frmH,frmW,r1,r;
    private int x,y=0;
    private ImageIcon jump[]=new ImageIcon[3];
    private BufferedImage walk[]=new BufferedImage[6];
    private BufferedImage stand[]=new BufferedImage[6];
    private boolean up,down,right,left,att=false;
   // private ImageIcon[][] imgIcon={{new ImageIcon("slimetest/slime1.png")},{new ImageIcon("slimetest/slime.png")}};
private  boolean Flag = true;
    private  Timer t1;
    private  Timer walkT;
    private  Timer walk1;
    private  Timer standT;
    private Random rand = new Random();
    private MainFrame mf;
  private  Mob mob;
    public Monster(int frmH, int frmW  ,MainFrame mf){
        this.setLayout(new GridLayout(3,1,5,5));

      //  this.setOpaque(false);
//        try{
//            image= ImageIO.read(new File("Slime/walk/left/move.1.png"));
//            // imgW= image.getWidth();
//            imgW= image.getWidth();
//            imgH = image.getHeight();
//            // imgH = 500;
//        }catch(IOException ie){
//            ie.printStackTrace();
//        }
this.mf=mf;
        this.frmH= frmH;
        this.frmW = frmW;
        x=rand.nextInt(frmW);
        //y=rand.nextInt(frmH-100);
        y=430;
//        r=rand.nextInt(6);
        r=1;

       // setMobAnimal();
        if(r==1){
            this.Flag=false;
        }
       // this.setIcon(imgIcon[r][r1=rand.nextInt(1)]);
       // ImageIO.read(new File(walk[r]));
       this.setBounds(x,y,80,80);//label 大小
    }
    @Override
    public  void run(){

             this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());




    walkT = new Timer(125/*走路速度*/, new ActionListener() {

        int t1Tmp = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Monster.this.Flag) {
                if ((x - 10) > 0) {
                    //向左
                    try {
                    walk[t1Tmp % 3] = ImageIO.read(new File("Slime/walk/left/move." + t1Tmp + ".png"));
                    }catch (IOException we){

                    }


                    //  Monster.this.getBackground(  walk[t1Tmp% 3])

                    left = true;//左
                    t1Tmp++;
                    x -= 10;
                    Monster.this.repaint();
                } else {
                    Monster.this.Flag = !Monster.this.Flag;
                    r = 1;

                    x += 10;
                }
                Monster.this.setLocation(x, y);
                //   Monster.this.repaint();
            } else {

                if ((x +
//                        Monster.this.getI().getIconWidth()
//                        +
                    20) < frmW) {
                    //向右
                    try {
                    walk[t1Tmp % 3 + 3] = ImageIO.read(new File("Slime/walk/left/move." + t1Tmp + ".png"));
                    }catch (IOException we){

                    }
                    //Monster.this.setBackground(walk[t1Tmp % 3 + 3]);
                    right = true;//右
                    t1Tmp++;
                    x += 10;
                    //  Monster.this.repaint();
                } else {
                    Monster.this.Flag = !Monster.this.Flag;
                    r = 0;
                    x -= 10;
                }
                Monster.this.setLocation(x, y);
                Monster.this.repaint();
            }
        }
    });

    walk1 = new Timer(500, new ActionListener() {
        int t1Tmp = 0;

        @Override
        public void actionPerformed(ActionEvent e) {

            if (r == 1) {

              //  Monster.this.setBackground(walk[t1Tmp % 3 + 3]);
                left = true;//左
                t1Tmp++;
                // Monster.this.repaint();
            } else {

              //  Monster.this.setBackground(walk[t1Tmp % 3]);
                right = true;//右
                t1Tmp++;

            }
            Monster.this.repaint();
        }

    });
    standT = new Timer(500, new ActionListener() {
        int t1Tmp = 0;

        @Override
        public void actionPerformed(ActionEvent e) {

            if (r == 1) {
                try {
                    stand[t1Tmp % 3 + 3] = ImageIO.read(new File("Slime/walk/left/move." + t1Tmp + ".png"));
                }catch (IOException we){

                }
            //    Monster.this.setBackground(stand[t1Tmp % 3 + 3]);
                left = true;
                t1Tmp++;
                // Monster.this.repaint();
            } else {
                try {
                    stand[t1Tmp % 3] = ImageIO.read(new File("Slime/walk/left/move." + t1Tmp + ".png"));
                }catch (IOException we){

                }
              //  Monster.this.setBackground(stand[t1Tmp % 3]);
                right = true;
                t1Tmp++;

            }
            Monster.this.repaint();
        }

    });
    t1 = new Timer(250, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            r1 = rand.nextInt(60000);
            if (r1 > 10000) {
                standT.stop();
                // walk1.start();
                walkT.start();
            } else {
                // walk1.stop();
                walkT.stop();
                standT.start();
            }
        }
    });




t1.start();
       // walk1.start();
      //  walkT.start();

}
//    public void paint(Graphics g){
//        Graphics2D g2d=(Graphics2D) g;
//        super.paintComponent(g);
//        g2d.drawImage(image,0,0,null,this);
//    }

    private  void  setMobAnimal(){
        try {
            for (int i = 0; i < 3; i++) {

                walk[i] = ImageIO.read(new File("Slime/walk/left/move." + Integer.toString(i) + ".png"));

            }
            for (int i = 3; i < 6; i++) {

                walk[i] = ImageIO.read(new File("Slime/walk/right/move." + Integer.toString(i - 3) + ".png"));

            }
//        for(int i=0;i<3;i++) {
//            jump[i] = new ImageIcon("Slime/jump/left/jump." + Integer.toString(i) + ".png");
//        }
//        for(int i=3;i<6;i++) {
//            jump[i]=new ImageIcon("Slime/jump/right/jump."+Integer.toString(i-3)+".png");
//        }
            for (int i = 0; i < 3; i++) {
                stand[i] = ImageIO.read(new File("Slime/stand/left/stand." + Integer.toString(i) + ".png"));

            }
            for (int i = 3; i < 6; i++) {
                stand[i] = ImageIO.read(new File("Slime/stand/right/stand." + Integer.toString(i - 3) + ".png"));
            }
        }catch (IOException e ){

        }
    }

    public  int getImgWidth(){return imgW;}
    public int getImgHeight(){
        return imgH;
    }
    public  int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
        @Override
    protected  void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        super.paintComponent(g);

        g2d.drawImage(image,getX(),getY(),null);
    }
}
class ImagePanel extends JPanel {
    private BufferedImage image;
    private  int imgW, imgH;

    public   ImagePanel(){
        this.setLayout(null);
        imgW= 110;
        imgH = 110;
//        try{
//            image = ImageIO.read(new File("background.jpg"));
//            // imgW= image.getWidth();
//            imgW= 500;
//
//
//            //imgH = image.getHeight();
//            imgH = 500;
//
//
//        }catch(IOException ex){
//            javax.swing.JOptionPane.showMessageDialog(this,ex.toString());
//        }


    }
//    @Override
//    protected  void paintComponent(Graphics g){
//        Graphics2D g2d=(Graphics2D) g;
//        super.paintComponent(g);
//        g2d.drawImage(image,getX(),getY(),null);
//    }
    public  int getImgWidth(){
        return imgW;
    }
    public int getImgHeight(){
        return imgH;
    }

}
