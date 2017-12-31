import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by jackwang on 2017/12/14.
 */
public class Monster extends JLabel implements Runnable {

    private BufferedImage image;

    // private Image image;

    private  int imgW, imgH;

    private  int frmH,frmW,r1,r;

    private int x,y=0;

    private ImageIcon jump[]=new ImageIcon[3];

    private ImageIcon walk[]=new ImageIcon[6];

    private ImageIcon stand[]=new ImageIcon[6];

    private boolean up,down,right,left,att=false;

    // private ImageIcon[][] imgIcon={{new ImageIcon("slimetest/slime1.png")},{new ImageIcon("slimetest/slime.png")}};

    private  boolean Flag = true;

    private  Timer t1;

    private  Timer walkT;

    private  Timer walk1;

    private  Timer standT;

    private Random rand = new Random();

    public Monster(int frmH, int frmW ){

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

        x=rand.nextInt(frmW);

        //y=rand.nextInt(frmH-100);

        y=400;

//        r=rand.nextInt(6);

        r=1;

        setMobAnimal();

        if(r==1){

            this.Flag=false;

        }

        // this.setIcon(imgIcon[r][r1=rand.nextInt(1)]);

        this.setIcon(walk[r]);

        this.setBounds(x,y,80,80);//label 大小

    }

    @Override

    public  void run(){

        walkT= new Timer(125/*走路速度*/, new ActionListener() {



            int t1Tmp=0;

            @Override

            public void actionPerformed(ActionEvent e) {
                if(Monster.this.Flag){
                    if ((x-10)>0){
                        //向左
                        Monster.this.setIcon(walk[t1Tmp% 3]);
                        left=true;//左
                        t1Tmp++;
                        x-=10;
                        Monster.this.repaint();
                    }else{
                        Monster.this.Flag=!Monster.this.Flag;
                        r=1;
                        x+=10;
                    }
                    Monster.this.setLocation(x,y);
                    //   Monster.this.repaint();
                }else{
                    if((x+ Monster.this.getIcon().getIconWidth()+20)<frmW){

                        //向右

                        Monster.this.setIcon(walk[t1Tmp% 3+ 3 ]);

                        right=true;//右

                        t1Tmp++;

                        x+=10;

                        //  Monster.this.repaint();

                    }else{

                        Monster.this.Flag=!Monster.this.Flag;

                        r=0;

                        x-=10;

                    }

                    Monster.this.setLocation(x,y);

                    Monster.this.repaint();

                }

            }

        });



        walk1=new Timer(500, new ActionListener() {

            int t1Tmp=0;

            @Override

            public void actionPerformed(ActionEvent e) {



                if(r==1) {



                    Monster.this.setIcon(walk[t1Tmp% 3+ 3]);

                    left=true;//左

                    t1Tmp++;

                    // Monster.this.repaint();

                }else  {



                    Monster.this.setIcon(walk[t1Tmp% 3 ]);

                    right=true;//右

                    t1Tmp++;



                }

                Monster.this.repaint();

            }



        });

        standT=new Timer(500, new ActionListener() {

            int t1Tmp=0;

            @Override

            public void actionPerformed(ActionEvent e) {



                if(r==1) {



                    Monster.this.setIcon(stand[t1Tmp% 3+ 3]);

                    left=true;

                    t1Tmp++;

                    // Monster.this.repaint();

                }else  {



                    Monster.this.setIcon(stand[t1Tmp% 3 ]);

                    right=true;

                    t1Tmp++;



                }

                Monster.this.repaint();

            }



        });

        t1=new Timer(250, new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                r1=rand.nextInt(60000);

                if(r1>10000){

                    standT.stop();

                    // walk1.start();

                    walkT.start();

                }else{

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

    public void paint(Graphics g){

        Graphics2D g2d=(Graphics2D) g;

        super.paintComponent(g);

        g2d.drawImage(image,0,0,null,this);

//        if(x<=0){

//            g2d.drawImage(image,1000+x,y,80, 80,this);

//        }

//        g2d.drawImage(image,x,y,80, 80,this);

//        if(x<=-1000){

//            x=0;

//        }

    }

    public boolean getleft(){

        return left;

    }

    public boolean getright(){

        return right;

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

//    public void paint(Graphics g){

//        Graphics2D g2d=(Graphics2D) g;

////        g2d.drawImage(image,0,0,null,this);

//        if(x<=0){

//            g2d.drawImage(image,1000+x,y,1000, 600,this);

//        }

//        g2d.drawImage(image,x,y,1000, 600,this);

//        if(x<=-1000){

//            x=0;

//        }

//    }

    public  int getImgWidth(){return imgW;}

    public int getImgHeight(){

        return imgH;

    }



}