import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by jackwang on 2017/12/14.
 */
public class Monster extends JLabel implements Runnable {
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
    public Monster(int frmH,int frmW ){
        this.frmH= frmH;
        this.frmW = frmW;
        x=rand.nextInt(frmW-100);
        //y=rand.nextInt(frmH-100);
        y=400;
        r=rand.nextInt(6);
        setMobAnimal();
        if(r==1){
            this.Flag=false;
        }
       // this.setIcon(imgIcon[r][r1=rand.nextInt(1)]);
        this.setIcon(walk[r]);
       this.setBounds(x,y,80,80);//label 大小
//
//        if(right==true&&left==true){
//            r1=rand.nextInt(1000);
//            if(r1>700) {
//               standT.start();
//                walk1.stop();
//                walkT.stop();
//                r1=rand.nextInt(1000);
//            }else{
//                standT.stop();
//                walk1.start();
//                walkT.start();
//                r1=rand.nextInt(1000);
//            }
//
//        }
    }

    @Override
    public  void run(){






        walkT= new Timer(125/*走路速度*/, new ActionListener() {

//            int t1Tmp=1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Monster.this.Flag){
                    if ((x-10)>0){

                        x-=10;
                    }else{
                        Monster.this.Flag=!Monster.this.Flag;
                        r=1;

                        x+=10;
                    }
                    Monster.this.setLocation(x,y);
                }else{
                    if((x+Monster.this.getIcon().getIconWidth()+20)<frmW){
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
        standT=new Timer(250, new ActionListener() {
            int t2Tmp=0;
            @Override
            public void actionPerformed(ActionEvent e) {

                if(r==1) {
                    Monster.this.setIcon(stand[t2Tmp% 3+ 3]);

                    t2Tmp++;

                }else  {
                    Monster.this.setIcon(stand[t2Tmp% 3 ]);
                    t2Tmp++;

                }
                Monster.this.setLocation(x,y);
            }

        });
t1=new Timer(1000, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int randm=0;
        randm=rand.nextInt(10000);
        if(getleft()||getright()){

            if(randm>5000){
                walk1.stop();
                walkT.stop();
                standT.start();
            }
        }else{
//            int randm=0;
//            randm=rand.nextInt(10000);
//            if(randm<5000){
                standT.stop();
                walk1.start();
                walkT.start();
//            }

        }
    }
});






        walk1.start();
        walkT.start();
t1.start();
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



//     t1= new Timer(rand.nextInt(1000) + 50, new ActionListener() {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//          if(Monster.this.Flag){
//              if ((x-10)>0){
//                  x-=10;
//              }else{
//                  Monster.this.Flag=!Monster.this.Flag;
//                  r=1;
//                  Monster.this.setIcon(imgIcon[r][r1]);
//                  x+=10;
//              }
//              Monster.this.setLocation(x,y);
//          }else{
//   if((x+Monster.this.getIcon().getIconWidth()+20)<frmW){
//       x+=10;
//   }else{
//       Monster.this.Flag=!Monster.this.Flag;
//       r=0;
//       Monster.this.setIcon(imgIcon[r][r1]);
//       x-=10;
//   }
//   Monster.this.setLocation(x,y);
//          }
//         }
//     });

    }

}
