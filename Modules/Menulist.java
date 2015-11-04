package Modules;
import java.util.*;

/**
 * Write a description of class Menulist here.
 * 
 * @author (Yorick Rommers) 
 * @version (a version number or a date)
 */
public class Menulist
{
    private ArrayList<String> menulist;
    private String lastMenu;
    private int lastSection;
    private String option;
    private String period;
    
    /**
    Initializes variables and calls the first array
    **/
    public Menulist()
    {
        menulist = new ArrayList<String>();
        lastMenu = "";
        option = "";
        period = "";
        lastSection = 0;
        initMenuArray();
    }

    /**
    Returns Menulist
    **/
    public ArrayList<String> getMenulist()
    {
        return menulist;
    }
    
    /**
    Sets menulevel
    **/
    public void setMenuLevel(String level)
    {
        lastMenu = level;
    }
    
    /**
    Adds a string to menulevel, takes a string.
    **/
    public void addMenuLevel(String level)
    {
        lastMenu = lastMenu + " " + level;
    }
    
    /**
    Sets lastsection
    **/
    public void setLastSection(int section)
    {
        lastSection = section;
    }
    
    /**
    Sets option
    **/
    public void setOption(String chosen)
    {
        option = chosen;
    }
    
    /**
    Sets period
    **/
    public void setPeriod(String period)
    {
        this.period = period;
    }
    
    /**
    Resets all menu variabled
    **/
    public void resetAll()
    {
        this.setLastSection(0);
        this.setMenuLevel("");
        this.setOption("");
        this.setPeriod("");
    }
    
    /**
    Returns Period
    **/
    public String getPeriod()
    {
        return period;
    }
    
    /**
    Returns option
    **/
    public String getOption()
    {
        return option;
    }
    
    /**
    Returns lastsection
    **/
    public int getLastSection()
    {
        return lastSection;
    }
    
    /**
    Returns menulevel
    **/
    public String getMenuLevel()
    {
        return lastMenu;
    }
    
    /**
    Picks an array
    **/
    public void pickArray()
    {
        switch(lastMenu)
        {
            case "Ga terug": 
                    initMenuArray();
                    break;
            case "Luchtdruk": 
                    airPressureArray();
                    break;
            case "Temperatuur": 
                    temperatureArray();
                    break;
            case "Luchtvochtigheid": 
                    humidityArray();
                    break;
            case "Wind": 
                    windArray();
                    break;
            case "Hoeveelheid regen": 
                    rainArray();
                    break;
            case "UV straling": 
                    uvArray();
                    break;            
            case "Zonsopkomst": 
                    sunriseArray();
                    break;        
            case "Zonsondergang": 
                    sunsetArray();
                    break;
            case "Temperatuur Binnen":
                    temperatureArray();
                    break;
            case "Temperatuur Buiten":
                    temperatureArray();
                    break;
            case "Luchtvochtigheid Binnen":
                    humidityArray();
                    break;       
            case "Luchtvochtigheid Buiten":
                    humidityArray();
                    break;   
            case "Windsnelheid":
                    windArray();
                    break;
            case "Windrichting":
                    windArray();
                    break; 
        }
    }
    
    /**
    Initializes the menuarray
    **/
    public void initMenuArray()
    {
        menulist.clear();
        menulist.add(new String("Luchtdruk"));
        menulist.add(new String("Temperatuur"));
        menulist.add(new String("Luchtvochtigheid"));
        menulist.add(new String("Wind"));
        menulist.add(new String("Hoeveelheid regen"));
        menulist.add(new String("UV straling"));
        menulist.add(new String("Zonsopkomst"));
        menulist.add(new String("Zonsondergang"));
        menulist.add(new String("Batterij niveau"));
    }
    
    /**
    Sets temperaturearray
    **/
    public void temperatureArray()
    {
        menulist.clear();
        switch(lastSection)
        {
            case 1:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Binnen"));
                menulist.add(new String("Buiten"));
                break;
            case 2:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Meest recent"));
                menulist.add(new String("1 dag"));
                menulist.add(new String("1 week"));
                menulist.add(new String("1 maand"));
                menulist.add(new String("1 jaar"));
                break;
            case 3:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Minimum"));
                menulist.add(new String("Maximum"));
                menulist.add(new String("Gemiddelde"));
                menulist.add(new String("Mediaan"));
                menulist.add(new String("Modus"));
                menulist.add(new String("Standaard Afwijking"));
                menulist.add(new String("Grafiek"));
                break;
                
        }
    }
    
    /**
    Sets airPressureArray
    **/
    public void airPressureArray()
    {
        menulist.clear();
        switch(lastSection)
        {
            case 1:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Meest recent"));
                menulist.add(new String("1 dag"));
                menulist.add(new String("1 week"));
                menulist.add(new String("1 maand"));
                menulist.add(new String("1 jaar"));
                break;
            case 2:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Minimum"));
                menulist.add(new String("Maximum"));
                menulist.add(new String("Gemiddelde"));
                menulist.add(new String("Grafiek"));
                break;
        }
        
    }
    
     /**
    Sets humidityArray
    **/
    public void humidityArray()
    {
        menulist.clear();
        switch(lastSection)
        {
            case 1:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Binnen"));
                menulist.add(new String("Buiten"));
                break;
            case 2:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Meest recent"));
                menulist.add(new String("1 dag"));
                menulist.add(new String("1 week"));
                menulist.add(new String("1 maand"));
                menulist.add(new String("1 jaar"));
                break;
            case 3:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Minimum"));
                menulist.add(new String("Maximum"));
                menulist.add(new String("Gemiddelde"));
                menulist.add(new String("Grafiek"));
                break;    
        }
    }
    
     /**
    Sets windArray
    **/
    public void windArray()
    {
        switch(lastSection)
        {
            case 1:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Windsnelheid"));
                menulist.add(new String("Windrichting"));
                break;

            case 2:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Meest recent"));
                menulist.add(new String("1 dag"));
                menulist.add(new String("1 week"));
                menulist.add(new String("1 maand"));
                menulist.add(new String("1 jaar"));
                break;
            case 3:
                switch(lastMenu)
                {
                    case "Windrichting":
                            menulist.add(new String("Ga terug"));
                            menulist.add(new String("Grafiek"));
                            break;
                    case "Windsnelheid":
                            menulist.add(new String("Ga terug"));
                            menulist.add(new String("Minimum"));
                            menulist.add(new String("Maximum"));
                            menulist.add(new String("Gemiddelde"));
                            menulist.add(new String("Grafiek"));
                            break;
                }
            

        }
        
    }
   
    /**
    Sets rainArray
    **/
    public void rainArray()
    {
        switch(lastSection)
        {
            case 1:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Meest recent"));
                menulist.add(new String("1 dag"));
                menulist.add(new String("1 week"));
                menulist.add(new String("1 maand"));
                menulist.add(new String("1 jaar"));
                break;
            case 2:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Minimum"));
                menulist.add(new String("Maximum"));
                menulist.add(new String("Gemiddelde"));
                menulist.add(new String("Grafiek"));
                break;
        }
    }
    
    /**
    Sets uvArray
    **/
    public void uvArray()
    {
        switch(lastSection)
        {
            case 1:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Meest recent"));
                menulist.add(new String("1 dag"));
                menulist.add(new String("1 week"));
                menulist.add(new String("1 maand"));
                menulist.add(new String("1 jaar"));
                break;
            case 2:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Minimum"));
                menulist.add(new String("Maximum"));
                break;
        }
    }
    
    /**
    Sets sunriseArray
    **/
    public void sunriseArray()
    {
        switch(lastSection)
        {
            case 1:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Meest recent"));
                menulist.add(new String("1 dag"));
                menulist.add(new String("1 week"));
                menulist.add(new String("1 maand"));
                menulist.add(new String("1 jaar"));
                break;
            case 2:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Minimum"));
                menulist.add(new String("Maximum"));
                break;
        }
    }
    
    /**
    Sets sunsetArray
    **/
    public void sunsetArray()
    {
        switch(lastSection)
        {
            case 1:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Meest recent"));
                menulist.add(new String("1 dag"));
                menulist.add(new String("1 week"));
                menulist.add(new String("1 maand"));
                menulist.add(new String("1 jaar"));
                break;
            case 2:
                menulist.add(new String("Ga terug"));
                menulist.add(new String("Minimum"));
                menulist.add(new String("Maximum"));
                break;
        }
    }
}
