package Units;

import Modules.*;


/**
 * Windchill Object
 * 
 * @author Richard 
 * Group TI B2
 */

public class Apparenttemperature
{
    /*private WindVelocity wind;
    private Temperature temp;
    private Humidity hum;
    
    private double windSpeed;
    private double outsideTemp;
    private int outsideHum;
    private double chillIndex;
    private double heatIndex;

     Constructor 
    public Apparenttemperature(Temperature temp, WindVelocity wind, Humidity hum)
    {
        this.wind = wind;
        this.temp = temp;
        this.hum = hum;
        
        this.setOutsideTemp();
        this.setWindSpeed();
        this.setOutsideHum();
 
        this.setChillIndex(0);
        this.setHeatIndex(0);
        this.checkTemperature();
    }
    
    private void checkTemperature()
    {        
        if( outsideTemp < 10 ){
            this.setChillIndex(); 
        } else if ( this.outsideTemp > 26 ){
            this.setHeatIndex(); 
        }  
    }
    
    
    private double calculateChillIndex(double outsideTemperature, double windSpeed)
    {
        double windChillIndex = (10 * Math.sqrt(windSpeed) + 10.5) * (33 - outsideTemperature);
        
        return windChillIndex; //kcal / m2 / h 
    }
    
    private double calculateHeatIndex(double outsideTemperature, double windSpeed, double outsideHum)
    {
        double vapourPressure = (61.12 * (7.5 * outsideTemperature) / (237.7 + outsideTemperature)) * (outsideHum / 100);
        double heatIndex = outsideTemperature + (5/9) * (vapourPressure - 10);
        
        return heatIndex; //Celcius
    }
    
    
     SETTERS 
    public void setWindSpeed(){ this.windSpeed = wind.getWindSpeed().getValue(); }
    public void setOutsideTemp(){ this.outsideTemp = temp.getOutsideTemp().getValue(); }
    public void setOutsideHum(){ this.outsideHum = hum.getOutsideHumidity().getValue(); }
    
    
    public void setChillIndex(int rawIndex){ this.chillIndex = rawIndex; }
    public void setChillIndex(){ this.chillIndex = this.calculateChillIndex(outsideTemp, windSpeed); }
    public void setHeatIndex(int rawIndex){ this.heatIndex = rawIndex; }
    public void setHeatIndex(){ this.heatIndex = this.calculateHeatIndex(outsideTemp, windSpeed, outsideHum);}
    
    /* GETTERS 
    public double getChillIndex(){ return this.chillIndex; }
    public double getHeatIndex(){ return this.heatIndex; }
    
    public String getIndexToString(String option)
    {
        //Initialize String
        String myString = new String();
        
        switch(option.toLowerCase())
        {
            case "heat" :
                myString = String.valueOf(this.getHeatIndex());
                break;
            case "chill" :
                myString = String.valueOf(this.getChillIndex());
                break;
        }
        
        return myString;
    }*/
}
