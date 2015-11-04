import Modules.*;

/**
 * Main Class
 * 
 * @author TIB2
 */
public class Main 
{
    /**
     * Initialize IO
     * 
     * Initialize Animation thread
     * Initialize Menu thread
     */
    public static void main(String [] args) 
    {
        IO.init();
       
        Thread menuThread = new Thread()
        {
            public void run()
            {
                Menu menu = new Menu();
                while(1==1)
                {
                    try{
                        this.sleep(50);
                    }
                    catch(Exception e)
                    {
                        
                    }
                    menu.start();
                }
            }
        };
        
        Thread loadThread = new Thread()
        {
            public void run()
            {
                Guicontroller gui = new Guicontroller();
                gui.loadAnimation();
                this.stop();
            }
            
        };
        
        loadThread.run();
        menuThread.run();
        
    }   
}
