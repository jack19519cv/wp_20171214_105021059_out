package new_Main;

public class Map {
    private int map[][];
    private int arrayW;
    private int arrayH;
    public Map(int winW,int winH){
        //1格為10px
        map=new int[arrayH=winH/10][arrayW=winW/10];
        for(int i=0;i<winH/10;i++){
            for(int j=0;j<winW/10;j++){
                map[i][j]=0;
            }
        }
        //floor
        //x:0 y:500 h:100 w:1000
        setObj(0,500,100,1000);
    }
    public void setObj(int x,int y,int h,int w){
        int startX=x/10;
        int startY=y/10;
        for(int i=0;i<h/10;i++){
            for(int j=0;j<w/10;j++){
                map[startY+i][startX+j]=1;
//                System.out.print("["+startY+i+"],["+startX+j+"]\t");
            }
//            System.out.println();
        }
    }
    public void setChar(int x,int y,int h,int w){
        int startX=x/10;
        int startY=y/10;
        for(int i=0;i<h/10;i++){
            for(int j=0;j<w/10;j++){
                map[startY+i][startX+j]=5;
//                System.out.print("["+startY+i+"],["+startX+j+"]\t");
            }
//            System.out.println();
        }
    }
    public boolean setObjCheck(int x,int y,int h,int w){
        boolean flag=true;
        int startX=x/10;
        int startY=y/10;
        for(int i=0;i<h/10;i++){
            for(int j=0;j<w/10;j++){
               if( map[startY+i][startX+j]!=0){
                   flag=false;
               }
            }
        }
        if(flag==false){
            return false;
        }else{
            return true;
        }
    }
    public boolean setCharWalk(int x,int y,int n){
        boolean flag=true;
        //+charLabel h and w
        int startX =(x+84-10)/10;
//        System.out.println("setX");
        //y [49] =490~500 500為地板 490為人物
        int startY =(y+81-10)/10;
        //Debug用
//        System.out.println("setY:"+y);
//        System.out.println("setstartY:"+(y+81-10)/10);
        if(n>=0){
            //向右走 抓取右邊腳的前一格
            startX=startX+n/10;
            if(map[startY][startX]==1){
                flag=false;
            }
            if(flag==false){
                //印出判定陣列 測試用
                printMap();
                System.out.println(flag);
                System.out.println(startY+","+startX);
                return false;
            }else{
                System.out.println(flag);
                for(int i=0;i<81/10;i++){
                    map[(startY-7+i)][startX-(8)]=0;
                    map[(startY-7+i)][startX]=5;
                }
                //印出判定陣列 測試用
                printMap();
                System.out.println(startY+","+startX);
                return true;
            }

        }else{
            //向左走 抓取左邊腳的前一格
            startX=(startX+n/10-7);
            if(map[startY][startX]==1){
                flag=false;
            }
            if(flag==false){
                //印出判定陣列 測試用
                printMap();
                System.out.println(flag);
                System.out.println(startY+","+startX);
                return false;
            }else{
                System.out.println(flag);
                for(int i=0;i<81/10;i++){
                    map[(startY-7+i)][startX]=5;
                    map[(startY-7+i)][startX+8]=0;
                }
                //印出判定陣列 測試用
                printMap();
                System.out.println(startY+","+startX);
                return true;
            }

        }

    }
    public boolean setWalkCheck(int x,int y,int h,int w, boolean face){
        boolean charface=face;
        int charX;
        int charY;
        if(charface==true){
            charX=(x)/10;
            charY=(y+81-10)/10;
            if(map[charY][(charX-1)]!=1){
                return true;
            }else{
                System.out.println("Walk:"+(charX-1)+","+charY);
                return false;
            }
        }else if(charface==false){
            charX=(x+84-10)/10;
            charY=(y+81-10)/10;
            if(map[charY][(charX+1)]!=1){
                return true;
            }else{
                return false;
            }
        }else{
            System.out.println("walk錯誤");
            return  false;
        }
    }
    public boolean setCharJump(int x,int y,int n){
        boolean flag=true;
        //+charLabel h:81 and w:84
        int startX=(x+84)/10;
        int startY=(y+81)/10;
        if(map[startY][startX]==1){
            flag=false;
        }
        if(flag==false){
            return false;
        }else{
            return true;
        }
    }
    public void printMap() {
        System.out.println(arrayH+"\t"+arrayW);
        for(int j=0;j<arrayW;j++){
            System.out.print(j+"\t");
        }
        System.out.println();
       for(int i=0;i<arrayH;i++){
           System.out.print(i+"\t");
            for(int j=0;j<arrayW;j++){
                System.out.print(map[i][j]+"\t");
            }
           System.out.println();
       }
        System.out.print("/"+"\t");
        for(int j=0;j<arrayW;j++){
            System.out.print(j+"\t");
        }
        System.out.println();
    }
    public boolean charDropCheck(int x,int y){
        //人物最左邊那一格
        int charX=x/10;
        //+10px為腳底下面那一格
        int charY=(y+71+10)/10;
        //抓底下8格做判定
        if(map[charY][(charX+20/10)]==0&&map[charY][(charX+50/10)]==0){
            return true;
        }else{
            return false;
        }
    }
    public void charDrop(int x,int y){
        //人物最左邊那一格
        int charX=x/10;
        int charY=y/10;
        //刷新陣列
        for(int i=0;i<8;i++){
            map[charY][(charX+i)]=0;
            map[(charY+8)][(charX+i)]=5;
        }
    }
    public void removeChar(int x,int y, int h ,int w){
        int startX=x/10;
        int startY=y/10;
        for(int i=0;i<h/10;i++){
            for(int j=0;j<w/10;j++){
                map[startY+i][startX+j]=0;
//                System.out.print("["+startY+i+"],["+startX+j+"]\t");
            }
//            System.out.println();
        }
    }

}
