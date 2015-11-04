package Modules;

import java.util.ArrayList;
import java.util.LinkedList;
import java.text.DecimalFormat;

import Modules.IO;


/**
 * Guicontroller Class
 * 
 * @author Timothy Verdonck
 */
public class Guicontroller {

    short[] top = {0x18, 0x16, 0x14, 0x12, 0x10};
    short[] left = {0x24, 0x22, 0x20};
    short[] right = {0x34, 0x32, 0x30};

    /** Constructor
     *
     * Initialize IO
     */
    public Guicontroller() {
        IO.init();
    }

    public void clrDMDisplay() 
    { 
        //Clears the display
        IO.writeShort(0x40,0xFE); 
        IO.writeShort(0x40,0x01); 
    } 
    
     public void loadAnimation() {
        this.clrDMDisplay();       

        this.writeStringToMatrix("Loading");
        IO.writeShort(0x40,'\n'); 
        IO.writeShort(0x40,'\n'); 
        this.writeStringToMatrix("       ");
        this.writeStringToMatrix("       ");
        this.writeStringToMatrix("Screen");
        this.drawProccesLine();
        this.pixelTest();
     
    }
    
    public void pixelTest()
    {
        int opcode = 3 ;
        int x,y;
        opcode = 1 ; 
        for (int idx = 0; idx < 50; idx ++)
        { 
            x = (int) (Math.random()* 128);
            y = (int) (Math.random()*32);
            IO.writeShort (0x42, opcode << 12 | x << 5 | y);
            IO.delay(100);
        }
    }
    
    public void drawProccesLine() 
    {    
        for(int a = 10; a < 123  ; a++)
        {
            IO.writeShort(0x42, 1 << 12 | a << 5 | 15);
            IO.delay(20);
        }
        
        
        for(int a = 8; a < 23; a++)
        {
            IO.writeShort(0x42, 1 << 12 | 68 << 5 | a);
            IO.delay(91);
        }
    }
    
    /**
     * @param str
     */
    public void writeStringToMatrix(String str) {
        //Writes a string to the matrix
        char[] chars = str.toCharArray();
        for (char character : chars) {
            IO.writeShort(0x40, character);
        }
    }
   
    /**
     * @param decimalNumber
     */
    public void writeTimeToTop(double decimalNumber) {
        //Writes a double to the top field
        try {
            if (decimalNumber <= 99999) {
                int i = (int)decimalNumber;
                String format = "%1$04d";
                String result = String.format(format, i);
                char[] charList = result.toCharArray();
                
                IO.writeShort(0x10, charList[3]);
                IO.writeShort(0x12, charList[2]);
                IO.writeShort(0x14, (0x100 | 128 ));
                IO.writeShort(0x16, charList[1]);
                IO.writeShort(0x18, charList[0]);       
            } else {
                throw new Exception("Can't put a bigger number than 99999 on the top display.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
   public void writeTrueDecimalToTop(double decimal)
   {      
       String doubleString = Double.toString(decimal);
       String[] parts = doubleString.split("\\.");
       
       char[] number = parts[0].toCharArray();
       char[] decimals = parts[1].toCharArray(); 
       
       IO.writeShort(0x16, number[0]);
       IO.writeShort(0x14, (0x100 | 128 ));
       IO.writeShort(0x12, decimals[0]);
       IO.writeShort(0x10, decimals[1]);     

   }
    
    /**
     * @param decimalNumber
     */
   public void writeDecimalToTop(double decimalNumber) {
        try {
            if (decimalNumber <= 99999) {
                
                int ones = (int)decimalNumber % 10;
                int tens = (int)(decimalNumber / 10) % 10;;
                int hundreds = (int)(decimalNumber / 100) % 10;
                int thousands = (int)(decimalNumber / 1000) % 10;
                int tenthousands = (int)(decimalNumber / 10000) % 10;
                
                IO.writeShort(0x10, ones);
                IO.writeShort(0x12, tens);
                IO.writeShort(0x14, hundreds);
                IO.writeShort(0x16, thousands);
                IO.writeShort(0x18, tenthousands);
                
                if(tenthousands == 0 && thousands == 0 && hundreds == 0){
                    IO.writeShort(0x14, 0x100);
                    IO.writeShort(0x16, 0x100);
                    IO.writeShort(0x18, 0x100);
                } else if (tenthousands == 0 && thousands == 0){
                    IO.writeShort(0x16, 0x100);
                    IO.writeShort(0x18, 0x100);
                } else if (tenthousands == 0){
                    IO.writeShort(0x18, 0x100);
                }
                
                
            } else {
                throw new Exception("Can't put a bigger number than 99999 on the top display.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * @param decimalNumber
     */
    public void writeDecimalToRight(int decimalNumber) {
        try {
            if (decimalNumber <= 999) {
                int ones = decimalNumber % 10;
                int tens = (decimalNumber / 10) % 10;
                int hundreds = (decimalNumber / 100) % 10;
                
                IO.writeShort(0x30, ones);
                IO.writeShort(0x32, tens);
                IO.writeShort(0x34, hundreds);
            } else {
                throw new Exception("Can't put a bigger number than 99999 on the top display.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    /**
     * @param decimalNumber
     */
    public void writeDecimalToLeft(int decimalNumber) {
        //Writes decimal numbers to the left screen
        try {
            if (decimalNumber <= 999) {
                int ones = decimalNumber % 10;
                int tens = (decimalNumber / 10) % 10;
                int hundreds = (decimalNumber / 100) % 10;
                
                IO.writeShort(0x20, ones);
                IO.writeShort(0x22, tens);
                IO.writeShort(0x24, hundreds);
            } else {
                throw new Exception("Can't put a bigger number than 99999 on the top display.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * @return true or false - depends if button is pressed
     */
    public boolean getRightState()
    {
        //Checks if there is anything written on the right screen
        if(IO.readShort(0x80) == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    /**
     * @return true or false - depends if button is pressed
     */
    public boolean getMiddleState()
    {
        //Checks if there is anything written on the middle screen
        if(IO.readShort(0x100) == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * @return true or false - depends if button is pressed
     */
    public boolean getLeftState()
    {
        //Checks if there is anything written on the left screen
        if(IO.readShort(0x90) == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
