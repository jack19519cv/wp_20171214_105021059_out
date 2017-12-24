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
    private  int frmH,frmW,x,y,r1,r;
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

        this.frmH= frmH;
        this.frmW = frmW;
        x=rand.nextInt(frmW-100);
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

//            int t1Tmp=1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Monster.this.Flag){
                    if ((x-10)>0){
                    //向左
                        x-=10;
                    }else{
                        Monster.this.Flag=!Monster.this.Flag;
                        r=1;

                        x+=10;
                    }
                    Monster.this.setLocation(x,y);
                }else{

                    if((x+ Monster.this.getIcon().getIconWidth()+20)<frmW){
                        //向右
                        x+=10;

                    }else{
                        Monster.this.Flag=!Monster.this.Flag;
                        r=0;
                        x-=10;
                    }
                    Monster.this.setLocation(x,y);
                }
            }
        });

        walk1=new Timer(500, new ActionListener() {
            int t1Tmp=0;
            @Override
            public void actionPerformed(ActionEvent e) {

                    if(r==1) {

                        Monster.this.setIcon(walk[t1Tmp% 3+ 3]);
                        left=true;
                        t1Tmp++;

                    }else  {

                        Monster.this.setIcon(walk[t1Tmp% 3 ]);
                        right=true;
                        t1Tmp++;

                    }
                }

        });







        walk1.start();
        walkT.start();

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
            stand[i] = new ImageIcon("Slime/stand/left/move." + Integer.toString(i) + ".png");
        }
        for(int i=0;i<3;i++) {
            stand[i]=new ImageIcon("Slime/stand/right/move."+Integer.toString(i-3)+".png");
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
