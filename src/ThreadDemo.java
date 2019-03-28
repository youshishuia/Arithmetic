import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author youshishuia
 * @date 2019/03/28
 */
public class ThreadDemo {
    private static char[]c = {'I','m','a','s','o','n'};
    private static  AtomicInteger i = new AtomicInteger(0);
    public static void main (String[] args){
        Runnable r = new Runnable(){
            @Override
           public void run(){
               while(true){
                   synchronized(i) {
                       if(i.get()<6) {
                           System.out.println(Thread.currentThread().getName() + "/" + c[i.get()]);
                           i.getAndIncrement();
                           try {
                               Thread.sleep(1000);
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                       else { break; }
                   }
               }
           }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
    }
}
