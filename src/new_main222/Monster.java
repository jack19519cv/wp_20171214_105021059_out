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
    private ImageIcon walk[]=new ImageIcon[6];
    private ImageIcon stand[]=new ImageIcon[6];
    private boolean up,down,right,left,att=false;
    private JLabel jlb=new JLabel();
    private JLabel jlbHp=new JLabel("10");
    private JLabel jlbName=new JLabel("惡水靈");
    private  Dimension place = new Dimension(50,30);
   // private ImageIcon[][] imgIcon={{new ImageIcon("slimetest/slime1.png")},{new ImageIcon("slimetest/slime.png")}};
private  boolean Flag = true;
    private  Timer t1;
    private  Timer walkT;
    private  Timer walk1;
    private  Timer standT;
    private Random rand = new Random();

  private  Mob mob;
    public Monster(int frmH, int frmW ){
        this.setLayout(new GridLayout(3,1,0,0));

        setMobAnimal();
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
        this.frmH= frmH;
        this.frmW = frmW;
        this.setOpaque(false);
        jlbHp.setForeground(Color.orange);
        jlbName.setForeground(Color.orange);
        x=rand.nextInt(frmW);
        //y=rand.nextInt(frmH-100);
       // y=390;
        y=340;
//        r=rand.nextInt(6);
        r=rand.nextInt(2);

       // setMobAnimal();
        if(r==1){
            this.Flag=false;
        }else {
            this.Flag=true;
        }
       // this.setIcon(imgIcon[r][r1=rand.nextInt(1)]);
       this.setBounds(x,y,90,150);//panel大小
        jlbHp.setSize(place);
        jlbName.setSize(place);
        this.add(jlbHp);
        this.add(jlb);
        
        this.add(jlbName);
    }
    @Override
    public  void run(){

    walkT = new Timer(125/*走路速度*/, new ActionListener() {

        int t1Tmp = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Monster.this.Flag) {
                if ((x - 10) > 0) {
                    //向左

                    jlb.setIcon(walk[t1Tmp % 3]);

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

                if ((x+jlb.getIcon().getIconWidth()+20)<frmW) {
                    //向右
                    jlb.setIcon(walk[t1Tmp % 3+3]);

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
//                Monster.this.repaint();
            }
        }
    });
//
//    walk1 = new Timer(500, new ActionListener() {
//        int t1Tmp = 0;
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//            if (r == 1) {
//
//
//                left = true;//左
//                t1Tmp++;
//                // Monster.this.repaint();
//            } else {
//
//
//                right = true;//右
//                t1Tmp++;
//
//            }
//            Monster.this.repaint();
//        }
//
//    });
    standT = new Timer(500, new ActionListener() {
        int t1Tmp = 0;
        @Override
        public void actionPerformed(ActionEvent e) {
            if (r == 1) {
                jlb.setIcon(stand[t1Tmp % 3+3]);

                left = true;
                t1Tmp++;
                if(t1Tmp==6){
                    standT.stop();

                    walkT.start();
                    t1Tmp=0;
                }
                // Monster.this.repaint();
            } else {
                jlb.setIcon(stand[t1Tmp % 3]);
                right = true;
                t1Tmp++;
                if(t1Tmp==6){
                    standT.stop();

                    walkT.start();
                    t1Tmp=0;
                }

            }
//            Monster.this.repaint();
//            System.out.println(t1Tmp);
        }

    });
    t1 = new Timer(250, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            r1 = rand.nextInt(500);
            if (r1 >250) {

            } else {
                // walk1.stop();
                walkT.stop();
                standT.start();
            }
        }
    });




t1.start();


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

        for(int  i=3;i<6;i++) {

            stand[i]=new ImageIcon("Slime/stand/right/stand."+Integer.toString(i-3)+".png");

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
//        @Override
//    protected  void paintComponent(Graphics g){
//        Graphics2D g2d=(Graphics2D) g;
//        super.paintComponent(g);
//        g2d.drawImage(image,0,0,null);
//            Monster.this.repaint();
//    }
}

