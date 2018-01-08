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
public  class Monster extends JPanel implements Runnable {

    private BufferedImage image;
    // private Image image;
    private  int imgW, imgH;
    private  int frmH,frmW,r1,r;
    private int x,y=0;
    private ImageIcon hit[]=new ImageIcon[2];
    private ImageIcon walk[]=new ImageIcon[6];
    private ImageIcon stand[]=new ImageIcon[6];
    private ImageIcon die[]=new ImageIcon[6];
    private boolean up,down,right,left,att=false;
    private JLabel jlb=new JLabel();
    private JLabel jlbHp=new JLabel("10");
    private JLabel jlbName=new JLabel("惡水靈");
    private  Dimension place = new Dimension(50,30);
   // private ImageIcon[][] imgIcon={{new ImageIcon("slimetest/slime1.png")},{new ImageIcon("slimetest/slime.png")}};
private  boolean Flag = true;
    private  Timer t1;
    private  Timer walkT;
    private  Timer dieT;
    private  Timer standT;
    private  Timer hitT;
    private boolean d = false;
    private  int nowHp;
    private Random rand = new Random();
    private int damage=10;
    private boolean hitstatus=false;
  //frmW=移動範圍 d=diestatus
    public  Monster(int frmH, int frmW,boolean d ){

        this.setLayout(new GridLayout(3,1,0,0));
        this.d=d;
        this.nowHp=nowHp;
        setMobAnimal();
        this.frmH= frmH;
        this.frmW = frmW;
        this.setOpaque(false);
        jlbHp.setForeground(Color.orange);
        jlbName.setForeground(Color.orange);
        x=rand.nextInt(frmW);
        //y=rand.nextInt(frmH-100);
        y=385;
       // y=340;
//        r=rand.nextInt(6);
        r=rand.nextInt(2);

       // setMobAnimal();
        if(r==1){
            this.Flag=false;
        }else {
            this.Flag=true;
        }
       // this.setIcon(imgIcon[r][r1=rand.nextInt(1)]);
       this.setBounds(x,y,90,200);//panel大小
        jlbHp.setSize(place);
        jlbName.setSize(place);
        this.add(jlbHp);
        this.add(jlb);
        
       // this.add(jlbName);


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

                            left = true;
                            hitcount++;
                            if (hitcount == 2) {
                                hitT.stop();

//                            walkT.start();
                                hitcount = 0;
                                hitstatus = false;
                            }
                            // Monster.this.repaint();
                        } else {
                            jlb.setIcon(hit[0]);
                            right = true;
                            hitcount++;
                            if (hitcount == 2) {
                                hitT.stop();

//                            walkT.start();
                                hitcount = 0;
                                hitstatus = false;
                            }

                        }

                        setDamage();
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
            jlbHp.setText(Integer.toString(damage));
        }

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


dieT =new Timer(250, new ActionListener() {
    int t1Tmp = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (t1Tmp < 3) {
            if (r == 1) {
                jlb.setIcon(die[t1Tmp % 3 + 3]);

                left = true;
                t1Tmp++;
                if (t1Tmp == 6) {
                    dieT.stop();
                    t1Tmp = 0;
                    setdead(true);
                }
                // Monster.this.repaint();
            } else {
                jlb.setIcon(die[t1Tmp % 3]);
                right = true;
                t1Tmp++;
                if (t1Tmp == 6) {
                    dieT.stop();
                    t1Tmp = 0;
                    setdead(true);
                }

            }
        }
    }

});
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
            if (jlbHp.getText().equals("0")) {
                setdead(true);
                walkT.stop();
                standT.stop();
                dieT.start();


            }else if(hitstatus){
                standT.setDelay(600);
                walkT.setDelay(600);
                t1.setDelay(600);


            }
            else{
                t1.start();
            }


        }




}


    private  void  setMobAnimal(){

        for(int i=0;i<3;i++){

            walk[i]=new ImageIcon("Slime/walk/left/move."+Integer.toString(i)+".png");

        }

        for(int i=3;i<6;i++){

            walk[i]=new ImageIcon("Slime/walk/right/move."+Integer.toString(i-3)+".png");

        }

        for(int i=0;i<1;i++) {

            hit[i] = new ImageIcon("Slime/die/left/hit." + Integer.toString(i) + ".png");

        }

        for(int i=1;i<2;i++) {

            hit[i]=new ImageIcon("Slime/die/right/hit."+Integer.toString(i-1)+".png");

        }

              for(int i=0;i<3;i++) {

            stand[i] = new ImageIcon("Slime/stand/left/stand." + Integer.toString(i) + ".png");

        }

        for(int  i=3;i<6;i++) {

            stand[i]=new ImageIcon("Slime/stand/right/stand."+Integer.toString(i-3)+".png");

        }

        for(int i=0;i<3;i++) {

            die[i] = new ImageIcon("Slime/die/left/die." + Integer.toString(i) + ".png");

        }

        for(int  i=3;i<6;i++) {

            die[i]=new ImageIcon("Slime/die/right/die."+Integer.toString(i-3)+".png");

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

