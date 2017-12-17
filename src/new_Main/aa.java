package new_Main;

public class aa  implements Runnable{
    boolean h=false;
    boolean done=false;
    Thread jumpThread;
    public aa(){
        jumpThread =new Thread(this);
        jumpThread.start();

    }

    @Override
    public void run() {
        long beforeTime,timeDiff,sleep;
        beforeTime=System.currentTimeMillis();
        while(done == false ){
            timeDiff=System.currentTimeMillis()-beforeTime;
            sleep=10-timeDiff;
            System.out.println("執行"+sleep);
            if(sleep<0){
                sleep=2;
            }
            try{
                Thread.sleep(sleep);
            }catch (Exception e){

            }
            beforeTime=System.currentTimeMillis();
        }
        done=false;
        h=false;
//        k=false;
    }
}
