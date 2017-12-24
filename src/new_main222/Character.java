package new_main222;

import java.awt.*;

public class Character{
    private String id;
    private int nowHp,maxHp;
    private boolean charface=false;
    private Point local;
    private int charType;
    private String nowC="alert";
    public Character(String id,int nowHp,int maxHp,int charType){
        this.id=id;
        this.nowHp=nowHp;
        this.maxHp=maxHp;
//        this.charface=charface;
        //角色面方向
//        this.local=local;
        //位置
        this.charType=charType;
        //哪隻角色
//        this.nowC=nowC;
        //正在使用的指令
    }
    public void setId(String id){
        this.id=id;
    }

    public String getid(){
        return id;
    }
    public void setNowHp(int NowHp){
        this.nowHp=nowHp;
    }

    public int getNowHp(){
        return nowHp;
    }
    public void setMaxHp(int maxHp){
        this.maxHp=maxHp;
    }

    public int getMaxHp(){
        return maxHp;
    }
    public void setCharface(boolean charface){
        this.charface=charface;
    }

    public boolean getCharface(){
        return charface;
    }
    public void setLocal(Point local){
        this.local=local;
    }
    public Point getLocal(){
        return local;
    }
    public void setCharType(int charType){
        this.charType=charType;
    }
    public int getCharType(){
        return charType;
    }

//    @Override
//    public void run() {
//
//    }
}
