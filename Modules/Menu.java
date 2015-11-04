package Modules;
 
 
/**
 * Menuklasse.
 * 
 * @author (Yorick Rommers) 
 * @version (14-10-2014)
 */
 
//Parameters voor de waarden die binnen gehaald moeten worden worden opgeslagen in de klasse Menulist, deze kun je binnenhalen met getOption(), getLastMenu(), getPeriod()
//voorbeeld: System.out.println("Execute code: " + menu.getMenuLevel() + " " + menu.getPeriod() + " " + menu.getOption());
//geeft: Execute code: Windsnelheid Meest recent Grafiek
// als je bij windsnelheid, de meeste recente waarde selecteert, en daarbij de grafiek vraagt.
 
import Modules.Graphs.Graph;
import Modules.Objects.Measurement;
import java.util.*;
 
public class Menu
{
    private Guicontroller button;
    private Controller mController;
    private Modules.Graphs.Controller guiController;
    private IO io;
    private Menulist menu;
    private ArrayList<String> menulist;
    private int top;
    private int mid;
    private int bot;
    private int section;
    private boolean listeningLeftMid;
    private boolean listeningRight;
    private boolean displaying;
    private int menuType;
    private double graphMargin;
    private double max;
    private double[] doubleList;
    private double[] nData;
    
    /** 
    Constructor, initialize variables and call displayMenu.
    **/
 
 
    public Menu()
    {
        button = new Guicontroller();
        mController = new Controller();
        guiController = new Modules.Graphs.Controller();
        io = new IO();
        menu = new Menulist();
        menulist = menu.getMenulist();
 
        io.init();
        top = 0;
        mid = 1;
        bot = 2;
        section = 0;
        displaying = false;
        listeningLeftMid = true;
        listeningRight = true;
        menuType = 0;
        graphMargin = 0.0;
 
        displayMenu(top, mid, bot);
 
    }
    
    
    /** 
    Sets the list, takes a double array as a parameter.
    **/
 
    private void set(double[] array)
    {
        double[] d = new double[1440];
        for(int i = 0; i < 1440; i++)
        {
            d[i] = array[i];
 
 
        }
        
        this.doubleList = d;
    }
    
    /** 
    Returns the list.
    **/
 
    private double[] get()
    {
        return this.doubleList;
    }
    
    /** 
    Calls the cursor.
    **/
 
 
 
 
 
 
 
 
    public void start()
    {
 
        cursor();
    }
    
    /** 
    Clears the matrix.
    **/
 
    public void clearMatrix()
    {
        IO.writeShort(0x40,0xFE); 
        IO.writeShort(0x40,0x01); 
    }
   
    /** 
    Checks if program is listening for buttons, activates listening when requirements are met.
    **/
 
    private void checkListening()
    {
        if(button.getLeftState() == false && button.getMiddleState() == false)
        {
            listeningLeftMid = true;
        }
        if(button.getRightState() == false)
        {
            listeningRight = true;
        }
    }
    
    /** 
    Enables you to go up and down the menu, and select entries.
    **/
 
 
    private void cursor()
    {
        
        if(listeningLeftMid)
        {
            if(button.getLeftState())
            {
                downMenu();
            }
            if(button.getMiddleState())
            {
                upMenu();
            }
            
        }
        else
        {
            checkListening();
        }
        
        if(listeningRight)
        {
            if(button.getRightState())
            {
                select();
            }
        }
        else
        {
            checkListening();
        }
    }
    
    /** 
    Increments section variable.
    **/
 
 
    private void incrementSection()
    {
        section++;
    }
    
    /** 
    Resets section variable.
    **/
 
    private void resetSection()
    {
        section = 0;
    }
    
    /** 
    Selects the entry you're hovering over.
    **/
 
 
    private void select()
    {
 
        listeningRight = false;
 
        switch(menulist.get(top)){
            case "Ga terug": 
                    resetSection();
                    menu.setMenuLevel(menulist.get(top));
                    menu.setLastSection(section);
                    menulist.clear();
                    break;
            case "Luchtdruk": 
                    incrementSection();
                    menu.setMenuLevel(menulist.get(top));
                    menu.setLastSection(section);
                    menulist.clear();
                    this.menuType = 1;
                    this.graphMargin = 4.0;
                    break;
            case "Temperatuur": 
                    incrementSection();
                    menu.setMenuLevel(menulist.get(top));
                    menu.setLastSection(section);
                    menulist.clear();
                    this.menuType = 23;
                    this.graphMargin = 160;
                    break;
            case "Luchtvochtigheid": 
                    incrementSection();
                    menu.setMenuLevel(menulist.get(top));
                    menu.setLastSection(section);
                    menulist.clear();
                    this.menuType = 45;
                    this.graphMargin = 80;
                    break;
            case "Wind": 
                    incrementSection();
                    menu.setMenuLevel(menulist.get(top));
                    menu.setLastSection(section);
                    menulist.clear();
                    this.menuType = 1011;
                    break;
            case "Hoeveelheid regen": 
                    incrementSection();
                    menu.setMenuLevel(menulist.get(top));
                    menu.setLastSection(section);
                    menulist.clear();
                    this.menuType = 6;
                    this.graphMargin = 80;
                    break;
            case "UV straling": 
                    incrementSection();
                    menu.setMenuLevel(menulist.get(top));
                    menu.setLastSection(section);
                    menulist.clear();
                    this.menuType = 9;
                    this.graphMargin = 6;
                    break;        
            case "Zonnestraling": 
                    incrementSection();
                    menu.setMenuLevel(menulist.get(top));
                    menu.setLastSection(section);
                    menulist.clear();
                    break;        
            case "Zonsopkomst": 
                    incrementSection();
                    menu.setMenuLevel(menulist.get(top));
                    menu.setLastSection(section);
                    menulist.clear();
                    this.menuType = 7;
                    this.graphMargin = 2401;
                    break;        
            case "Zonsondergang": 
                    incrementSection();
                    menu.setMenuLevel(menulist.get(top));
                    menu.setLastSection(section);
                    menulist.clear();
                    this.menuType = 8;
                    this.graphMargin = 2401;
                    break;

            case "Batterij niveau": 
                    incrementSection();
                    menu.setLastSection(section);
                    menu.setOption(menulist.get(top));
                    menulist.clear();

                    this.menuType = 12;
                    this.set(this.getDataForPeriodAndSpecificType(5, menuType));
                    
                    nData = this.get();
                    double level = nData[0]; 
                    button.writeDecimalToTop(level);
                    displaying = true;
                    break;     
                    
            case "Meest recent":                   
                    incrementSection();
                    menu.setLastSection(section);
                    menu.setOption(menulist.get(top));
                    menulist.clear();
                    
                    this.set(this.getDataForPeriodAndSpecificType(5, menuType));
                    
                    nData = this.get();
                    double value = nData[0];
                    
                    if(menuType == 7 || menuType == 8)
                    {
                       button.writeTimeToTop(value);
                    }
                    else
                    {
                        button.writeDecimalToTop(value);
                    }
                    
                    displaying = true;
                    break;
            case "1 dag": 
                    incrementSection();
                    menu.setLastSection(section);
                    menu.setPeriod(menulist.get(top));
                    menulist.clear();
                    this.set(this.getDataForPeriodAndSpecificType(4, menuType));
                    break;  
            case "1 week": 
                    incrementSection();
                    menu.setLastSection(section);
                    menu.setPeriod(menulist.get(top));
                    menulist.clear();
                    this.set(this.getDataForPeriodAndSpecificType(3, menuType));
                    break;
            case "1 maand": 
                    incrementSection();
                    menu.setLastSection(section);
                    menu.setPeriod(menulist.get(top));
                    menulist.clear();
                    this.set(this.getDataForPeriodAndSpecificType(2, menuType));
                    
                    break;
            case "1 jaar": 
                    incrementSection();
                    menu.setLastSection(section);
                    menu.setPeriod(menulist.get(top));
                    menulist.clear();
                    this.set(this.getDataForPeriodAndSpecificType(1, menuType));
                    break;     
            case "Binnen":
                    incrementSection();
                    menu.addMenuLevel(menulist.get(top));
                    menu.setLastSection(section);
                    menulist.clear();
                    if(menuType == 23)
                    {
                        menuType = 2;
                    }
                    else if(menuType == 45)
                    {
                        menuType = 4;
                    }
                    break; 
            case "Buiten":
                    incrementSection();
                    menu.addMenuLevel(menulist.get(top));
                    menu.setLastSection(section);
                    menulist.clear();
                    if(menuType == 23)
                    {
                        menuType = 3;
                    }
                    else if(menuType == 45)
                    {
                        menuType = 5;
                    }
                    break;
                    
            case "Minimum": 
                    incrementSection();
                    menu.setLastSection(section);
                    menu.setOption(menulist.get(top));
                    menulist.clear();                   
                    nData = new Graph(this.get(), this.graphMargin).get();
                    double minVal = this.mController.minValueFromArray(nData);
                    
                    if(menuType == 7 || menuType == 8)
                    {
                       button.writeTimeToTop(minVal);
                    }
                    else
                    {
                        button.writeDecimalToTop(minVal);
                    }
                    
                    displaying = true;
                    break;   
                    
            case "Maximum": 
                    incrementSection();
                    menu.setLastSection(section);
                    menu.setOption(menulist.get(top));
                    menulist.clear();                    
                    nData = new Graph(this.get(), this.graphMargin).get();
                    double maxVal = this.mController.maxValueFromArray(nData);
                    
                    if(menuType == 7 || menuType == 8)
                    {
                       button.writeTimeToTop(maxVal);
                    }
                    else
                    {
                        button.writeDecimalToTop(maxVal);
                    }
                    
                    displaying = true;
                    break;         
            case "Gemiddelde": 
                    incrementSection();
                    menu.setLastSection(section);
                    menu.setOption(menulist.get(top));
                    menulist.clear();
                    
                    nData = new Graph(this.get(), this.graphMargin).get();
                    double avgVal = this.mController.meanFromArray(nData);
                    button.writeDecimalToTop(avgVal);
                    displaying = true;
                    break;
             case "Mediaan": 
                    incrementSection();
                    menu.setLastSection(section);
                    menu.setOption(menulist.get(top));
                    menulist.clear();
                    
                    nData = new Graph(this.get(), this.graphMargin).get();
                    double medVal = this.mController.mediaanFromArray(nData);
                    button.writeDecimalToTop(medVal);
                    displaying = true;
                    break;
             case "Modus": 
                    incrementSection();
                    menu.setLastSection(section);
                    menu.setOption(menulist.get(top));
                    menulist.clear();
                    
                    nData = new Graph(this.get(), this.graphMargin).get();
                    double mVal = this.mController.modusFromArray(nData);
                    button.writeDecimalToTop(mVal);
                    displaying = true;
                    break;
             case "Standaard Afwijking": 
                    incrementSection();
                    menu.setLastSection(section);
                    menu.setOption(menulist.get(top));
                    menulist.clear();
                    
                    nData = new Graph(this.get(), this.graphMargin).get();
                    double sdVal = this.mController.standardDeviationFromArray(nData);
                    button.writeTrueDecimalToTop(sdVal);
                    displaying = true;
                    break;         
            case "Grafiek": 
                    incrementSection();
                    menu.setLastSection(section);
                    menu.setOption(menulist.get(top));
                    menulist.clear();
                    
                    nData = this.get();
                    summonGraph(nData,this.graphMargin);
                    displaying = true;
                    break;  
            case "Windsnelheid":
                    incrementSection();
                    menu.setMenuLevel(menulist.get(top));
                    menu.setLastSection(section);
                    menulist.clear();
                    this.menuType = 11;
                    this.graphMargin = 140;
                    break;
            case "Windrichting":
                    incrementSection();
                    menu.setMenuLevel(menulist.get(top));
                    menu.setLastSection(section);
                    menulist.clear();
                    this.menuType = 10;
                    this.graphMargin = 362;
                    break;       
        }
        
        if(displaying == false)
        {
            menu.pickArray();
            menulist = menu.getMenulist();
            switchMenu();
        }
        else
        {
            while(displaying == true)
            {
               if(button.getRightState() == false)
               {
                    displaying = false;  
                    resetSection();
                    menu.resetAll();
                    menulist.clear();
                    menu.initMenuArray();
                    switchMenu(); 
               }
            } 
        }   
    }
    
    /** 
    Gives data for period and a specific type, returns a double array, takes two ints defining the period and typeid.
    **/
    private double[] getDataForPeriodAndSpecificType(int period ,int typeId)
    {
        ArrayList<Measurement> currentMeasurement =  this.mController.gregorianCalendarPeriods(period);
        double[] doubleArraySingleType = this.mController.setObjectToArray(currentMeasurement ,typeId);
        return doubleArraySingleType;
        
    }
    
    /** 
    Draws the graph based on it's parameters, it takes a double array containing the data, and a double containing the margin.
    **/
    private void summonGraph(double[] data , double margin)
    {
        Graph myGraph = new Graph(data, margin);
        Modules.Graphs.Controller graphController = new Modules.Graphs.Controller(); 
        graphController.makeGraph(myGraph);
    }
    
    /** 
    Resets values when going trough the menu.
    **/
    private void switchMenu()
    {
        top = 0;
        mid = 1;
        bot = 2;
        redraw();
    }
    
    /** 
    Draws the cursor line.
    **/
    private void drawline()
    {
        int opcode = 1;
        int x = 1;
        int y = 10;
        for( int i = 0; i < 50; i++)
        {
            x++;
            IO.writeShort(0x42, (opcode << 12 | x << 5 | y));
        }
        
    }
    
    /** 
    Goes up in the menu
    **/
    private void upMenu()
    {
        if(bot == menulist.size() + 1)
        {
            //do nothing
        }
        else
        {
            top++;
            mid++;
            bot++;
            listeningLeftMid = false;
            redraw();
        }
        
    }
    
    /** 
    Goes down in the menu
    **/
    private void downMenu()
    {
        if(top == 0)
        {
            //do nothing
        }
        else
        {
            top--;
            mid--;
            bot--;
            listeningLeftMid = false;
            redraw();
        }
    }
    
    /** 
    Redraws menu
    **/
    private void redraw()
    {
        displayMenu(top, mid, bot);
    }
    
    /** 
    Draws menu, takes 3 integers, defining the top, mid and bot position in the array.
    **/
    private void displayMenu(int top, int mid, int bot)
    {
        String part1 = "";
        String part2 = "";
        String part3 = "";
        int count = 0;
        
        for( String s : menulist)
        {
            
            if(count == top)
            {
                part1 = s;
            }
            else if(count == mid)
            {
                part2 = s;
            }
            else if(count == bot)
            {
                part3 = s;
            }
            count++;
        }
        clearMatrix();
        drawline();
        for( int i = 0; i < part1.length(); i++)
        {
            IO.writeShort(0x40, part1.charAt(i)); 
        }
        IO.writeShort(0x40, '\n');
        for( int x = 0; x < part2.length(); x++)
        {
            IO.writeShort(0x40, part2.charAt(x)); 
        }
        IO.writeShort(0x40, '\n');
        for( int y = 0; y < part3.length(); y++)
        {
            IO.writeShort(0x40, part3.charAt(y)); 
        }
    }
}





