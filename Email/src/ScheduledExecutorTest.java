

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {
    private static  ScheduledExecutorService scheduExec;
    
    public long start;
    
    ScheduledExecutorTest(){
        scheduExec =  Executors.newScheduledThreadPool(2);  
        this.start = System.currentTimeMillis();
    }
    
    public   void timerTwo(){
        scheduExec.scheduleAtFixedRate(new Runnable(){
            public void run(){
            	try {
					MailTest.mail();
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        },0,60000,TimeUnit.MILLISECONDS);
    }
    
    public static void main(String[] args){
    	toMail();
    }
    public   static void toMail(){
    	 ScheduledExecutorTest test = new ScheduledExecutorTest();
    	 test.timerTwo();
    	
    }
}