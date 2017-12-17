import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jackwang on 2017/12/15.
 */
public class Mainframe  extends JFrame{
private Container cp ;
    private ImagePanel jpn= new ImagePanel();
    private JPanel toolPane = new JPanel(new GridLayout(1,1,5,5));
    private JButton jbtnAdd= new JButton("ADD");
    private  int imgW, imgH;
    private  int MonsterIndex = 0;
    private ArrayList<Monster> MonsterList= new ArrayList<Monster>();
    private ArrayList<Thread> threadList = new ArrayList<Thread>();

    public Mainframe(){
        imgW = jpn.getImgWidth();
        imgH = jpn.getImgHeight();
        this.setBounds(350,100,imgW,imgH+30);
        this.setResizable(false);
        jpn.setLayout(null);
        toolPane.add(jbtnAdd);
       jbtnAdd.addActionListener(new AbstractAction() {
           @Override
           public void actionPerformed(ActionEvent e) {
               MonsterList.add(new Monster(imgH,imgW));
               jpn.add(MonsterList.get(MonsterList.size()-1));
               threadList.add(new Thread(MonsterList.get(MonsterList.size()-1)));
               threadList.get(threadList.size()-1).start();
           }
       });
        cp= this.getContentPane();
        cp.setLayout(new BorderLayout(3,3));
        cp.add(jpn,BorderLayout.CENTER);
        cp.add(toolPane, BorderLayout.NORTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.validate();
    }
}

  class ImagePanel extends JPanel{
    private BufferedImage image;
    private  int imgW, imgH;

    public   ImagePanel(){
        try{
            image = ImageIO.read(new File("background.jpg"));
           // imgW= image.getWidth();
            imgW= 500;


            //imgH = image.getHeight();
            imgH = 500;


        }catch(IOException ex){
          javax.swing.JOptionPane.showMessageDialog(this,ex.toString());
        }


    }
    @Override
    protected  void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,null);
    }
    public  int getImgWidth(){
        return imgW;
    }
    public int getImgHeight(){
        return imgH;
    }

}


