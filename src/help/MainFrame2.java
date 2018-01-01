package help;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame2 extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    private Container c;
    //設定共用的變數與類別
    int m[][] = new int[5][10];
    int xt, yt, xb, yb, xdir, ydir, flag;
    Timer t;

    public MainFrame2()   //建構元，名稱改一改
    {
        super("打磚塊");
        //初始化共用變數
        int x,y;
        xt=200;yt=450;xb=xt+23;yb=yt-3;
        xdir=8;ydir=8;flag=0;
        for (y=0;y<5;y=y+1)
            for (x=0;x<10;x=x+1)
                m[y][x]=1;
        c=getContentPane();//取得ContentPane
        //設定版面設定
        //初始化UI元件
        //將UI元件加入ContentPane
        //設定UI元件與滑鼠的事件觸發傾聽者
        t=new Timer(50,this);
        addMouseListener( this);
        addMouseMotionListener( this);
        setSize(500,480);//設定size，顯示出去
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);//畫出元件
        //額外的畫圖程式寫在這裡
        int x, y;
        g.fillRect(xt, yt, 50, 20);
        g.fillOval(xb, yb, 5, 5);
        if (xb <= 0 || xb >= 500) xdir = -xdir;
        if (yb <= 0) ydir = -ydir;
        if (yb > 500) {
            flag = 0;
            xb = xt + 23;
            yb = yt - 3;
            t.stop();
        }
        if (xb >= xt && xb <= xt + 50 && yb + 5 >= yt && yb <= yt + 20)
            ydir = -8;

        for (y = 0; y < 5; y = y + 1)
            for (x = 0; x < 10; x = x + 1) {
                if (m[y][x] == 0)  continue;

                if (m[y][x] == 0){
                    JOptionPane.showMessageDialog(null, "reset");
                    if (xb >= x * 50 && xb <= x * 50 + 50 && yb >= 100 + y * 25 && yb <= 125 + y * 25) {
                        m[y][x] = 0;
                        ydir = -ydir;
                    }
                    if (m[y][x] == 1)
                        g.drawRect(x * 50, 100 + y * 25, 50, 25);
                }

                if (xb >= x * 50 && xb <= x * 50 + 50 && yb >= 100 + y * 25 && yb <= 125 + y * 25) {
                    m[y][x] = 0;
                    ydir = -ydir;
                }
                if (m[y][x] == 1)
                    g.drawRect(x * 50, 100 + y * 25, 50, 25);

            }


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        t.start();flag=1;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    public void setAll(){





    }
    public static void main(String[] args) {
        MainFrame2 MF2= new MainFrame2();
      MF2.setVisible(true);
    }
}
