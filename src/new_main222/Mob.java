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
 * Created by jackwang on 2017/12/23.
 */
public class Mob extends JLabel  implements Runnable {
    private BufferedImage image;
    private  int imgW, imgH;
    private Random rand = new Random();
    private  int frmH,frmW,x,y,r1,r;
    private  boolean Flag = true;
    private ImageIcon jump[]=new ImageIcon[3];
    private ImageIcon walk[]=new ImageIcon[16];
    private ImageIcon stand[]=new ImageIcon[22];
    private Timer t1;
    private  Timer walkT;
    private  Timer standT;
    public Mob(int frmH, int frmW){
        this.frmH= frmH;
        this.frmW = frmW;
        setMobAnimal();

        init();

    }
    private void init(){
        x=rand.nextInt(frmW);
        //y=rand.nextInt(frmH-100);
        // y=390;
        y=175;
//        r=rand.nextInt(6);
        r=rand.nextInt(2);

        // setMobAnimal();
        if(r==1){
            this.Flag=false;
        }else {
            this.Flag=true;
        }
        this.setOpaque(false);
        this.setIcon(walk[r]);

        this.setBounds(x,y,500,500);
    }
    @Override
    public  void run(){
        walkT = new Timer(225/*走路速度*/, new ActionListener() {
            int t1Tmp = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Mob.this.Flag) {
                    if ((x - 10) > 0) {
                        //向左

                        Mob.this.setIcon(walk[t1Tmp % 8]);
                        //左
                        t1Tmp++;
                        x -= 10;
                        Mob.this.repaint();
                    } else {
                        Mob.this.Flag = !Mob.this.Flag;
                        r = 1;

                        x += 10;
                    }
                    Mob.this.setLocation(x, y);
                } else {

                    if ((x+Mob.this.getIcon().getIconWidth()+20)<frmW) {
                        //向右
                        Mob.this.setIcon(walk[t1Tmp % 8+8]);
                        t1Tmp++;
                        x += 10;
                        //  Monster.this.repaint();
                    } else {
                        Mob.this.Flag = !Mob.this.Flag;
                        r = 0;
                        x -= 10;
                    }
                    Mob.this.setLocation(x, y);
                }

            }
        });
        standT = new Timer(500, new ActionListener() {
            int t1Tmp = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (r == 1) {
                    Mob.this.setIcon(stand[t1Tmp % 11+11]);
                    t1Tmp++;
                    if(t1Tmp==22){
                        standT.stop();

                        walkT.start();
                        t1Tmp=0;
                    }


                    // Monster.this.repaint();
                } else {
                    Mob.this.setIcon(stand[t1Tmp % 11]);

                    t1Tmp++;
                    if(t1Tmp==22){
                        standT.stop();

                        walkT.start();
                        t1Tmp=0;
                    }


                }
//            Monster.this.repaint();
//            System.out.println(t1Tmp);
            }

        });



        walkT.start();

    }

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
        for(int i=0;i<8;i++){
            walk[i]=new ImageIcon("ox/move/left/move."+Integer.toString(i)+".png");
        }
        for(int i=8;i<16;i++){
            walk[i]=new ImageIcon("ox/move/right/move."+Integer.toString(i-8)+".png");
        }
        for(int i=0;i<11;i++) {
            stand[i] = new ImageIcon("ox/stand/left/stand." + Integer.toString(i) + ".png");
        }
        for(int i=11;i<22;i++) {
            stand[i]=new ImageIcon("ox/stand/right/stand."+Integer.toString(i-11)+".png");
        }

    }
}

