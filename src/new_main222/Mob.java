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
public class Mob extends JPanel  implements Runnable {
    private BufferedImage image;
    private  int imgW, imgH;
    private Random rand = new Random();
    private  int frmH,frmW,x,y,r1,r;
    private  boolean Flag = true;
    private ImageIcon hit[]=new ImageIcon[2];
    private ImageIcon die[]=new ImageIcon[18];
    private ImageIcon attack[]=new ImageIcon[24];
    private ImageIcon walk[]=new ImageIcon[16];
    private ImageIcon stand[]=new ImageIcon[22];
    private JLabel jlb=new JLabel();
    private JLabel jlbHp=new JLabel("       10");
    private Timer t1;
    private  Timer walkT;
    private  Timer standT;
    private  Timer attackT;
    private  Timer dieT;
    private  Timer hitT;
    private boolean d = false;
    private  int nowHp;
    private int damage=10;
    private boolean hitstatus=false;
    public Mob(int frmH, int frmW,boolean d){
        this.setLayout(new GridLayout(3,1,0,0));
        this.d=d;
        this.nowHp=nowHp;
        this.frmH= frmH;
        this.frmW = frmW;
        this.setOpaque(false);
        jlbHp.setForeground(Color.GREEN);
        jlbHp.setFont(new Font("Serif",Font.BOLD,30));
        x=rand.nextInt(frmW);
        setMobAnimal();

        init();

    }
    private void init(){
        x=rand.nextInt(frmW);
        //y=rand.nextInt(frmH-100);
        // y=390;
        y=175;
//        sety();
//        r=rand.nextInt(6);
        r=rand.nextInt(2);

        // setMobAnimal();
        if(r==1){
            this.Flag=false;
        }else {
            this.Flag=true;
        }
        this.setOpaque(false);
        this.setBounds(x,y,500,500);
        this.add(jlbHp);
        this.add(jlb);
//        this.setIcon(walk[r]);


    }

    private void setdead(boolean d){
        this.d=d;
    }
    public   boolean getdead(){
        return d;
    }
    public int gethit(){
        hitstatus=true;
        if(!d) {
            hitT = new Timer(500, new ActionListener() {
                int hitcount = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (hitcount < 1) {
                        if (r == 1) {
                            jlb.setIcon(hit[1]);
                            hitcount++;
                            if (hitcount == 2) {
                                hitcount = 0;
                                t1.start();
                                hitT.stop();

//                            walkT.start();

                            }
                            // Monster.this.repaint();
                        } else {
                            jlb.setIcon(hit[0]);
                            hitcount++;
                            if (hitcount == 2) {
                                hitcount = 0;

                                t1.start();
                                hitT.stop();

//                            walkT.start();

                            }

                        }

                        setDamage();
                        hitstatus = false;
                    }
                }
            });
        }
        hitT.start();

        return 1;
    }
    public void setDamage(){
        if(damage>0) {
            damage--;
            jlbHp.setText("       "+Integer.toString(damage));
        }

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

                        jlb.setIcon(walk[t1Tmp % 8]);
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

                    if ((x+jlb.getIcon().getIconWidth()+20)<frmW) {
                        //向右
                        jlb.setIcon(walk[t1Tmp % 8+8]);
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
                    jlb.setIcon(stand[t1Tmp % 11+11]);
                    t1Tmp++;
                    if(t1Tmp==22){
                        standT.stop();

                        walkT.start();
                        t1Tmp=0;
                    }
                    // Monster.this.repaint();
                } else {
                    jlb.setIcon(stand[t1Tmp % 11]);

                    t1Tmp++;
                    if(t1Tmp==22){
                        standT.stop();

                        walkT.start();
                        t1Tmp=0;
                    }
                }
            }
        });
        attackT = new Timer(500, new ActionListener() {
            int t1Tmp = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (r == 1) {
                    jlb.setIcon(attack[t1Tmp % 12+12]);
                    t1Tmp++;
                    if(t1Tmp==16){
                        gety(145);

                    }else {
                        gety(175);
                    }
                    if(t1Tmp==24){
                        attackT.stop();

                        walkT.start();
                        t1Tmp=0;
                    }
                    // Monster.this.repaint();
                } else {
                    jlb.setIcon(attack[t1Tmp % 12]);

                    t1Tmp++;
                    if(t1Tmp==3){
                        gety(145);
                    }else {
                        gety(175);
                    }
                    if(t1Tmp==24){

                        attackT.stop();

                        walkT.start();
                        t1Tmp=0;
                    }
                }
            }
        });
        dieT =new Timer(250, new ActionListener() {
            int t1Tmp = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t1Tmp < 9) {
                    if (r == 1) {
                        jlb.setIcon(die[t1Tmp % 9 + 9]);
                        t1Tmp++;
                        if (t1Tmp == 18) {
                            dieT.stop();
                            t1Tmp = 0;
                            setdead(true);
                        }
                        // Monster.this.repaint();
                    } else {
                        jlb.setIcon(die[t1Tmp % 9]);
                        t1Tmp++;
                        if (t1Tmp == 18) {
                            dieT.stop();
                            t1Tmp = 0;
                            setdead(true);
                        }

                    }
                }
            }

        });
//        walkT.start();
        t1 = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                r1 = rand.nextInt(30000);
                if (r1 >15000) {

                } else {
                    // walk1.stop();
                    walkT.stop();
                    standT.start();
                }
            }
        });
        while (true) {
            if (jlbHp.getText().equals("       0")) {
                setdead(true);
                walkT.stop();
                standT.stop();
                dieT.start();


            }else if(hitstatus){
                standT.setDelay(600);
                walkT.setDelay(600);
                t1.setDelay(600);
//                standT.stop();
//                walkT.stop();
//                t1.stop();
            }
            else{
//                walkT.start();
                t1.start();
            }


        }

    }

    public  int getImgWidth(){return imgW;}
    public int getImgHeight(){
        return imgH;
    }
    public int sety(){

        return y;
    }
    public void gety(int y){
        this.y=y;

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
        for(int i=0;i<12;i++) {
            attack[i] = new ImageIcon("ox/attack/type1/left/attack1." + Integer.toString(i) + ".png");
        }
        for(int i=12;i<24;i++) {
            attack[i]=new ImageIcon("ox/attack/type1/right/attack1."+Integer.toString(i-12)+".png");
        }
        for(int i=0;i<1;i++) {
            hit[i] = new ImageIcon("ox/hit/left/hit1." + Integer.toString(i) + ".png");
        }
        for(int i=1;i<2;i++) {
            hit[i]=new ImageIcon("ox/hit/right/hit1."+Integer.toString(i-1)+".png");
        }
        for(int i=0;i<9;i++) {
            die[i] = new ImageIcon("ox/die/left/die1." + Integer.toString(i) + ".png");
        }
        for(int  i=9;i<18;i++) {
            die[i]=new ImageIcon("ox/die/right/die1."+Integer.toString(i-9)+".png");
        }
    }
}

