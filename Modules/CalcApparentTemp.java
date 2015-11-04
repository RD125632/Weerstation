package Modules;


/**
 * Write a description of class ApparentTemp here.
 * 
 * @author (Tim Schijvenaars) 
 * @version (1.0)
 */
public class CalcApparentTemp
{   
    private double wind;
    private double temp;
    private double hum;

    private double chillIndex;
    private double heatIndex;
    
    /** Constructor
     * 
     * @param temp - Double of temperature
     * @param wind - Double of wind speed
     * @param hum - Double of humidity
     */
    public CalcApparentTemp(double temp, double  wind, double hum)
    {
        this.wind = wind;
        this.temp = temp;
        this.hum = hum;
    }
    
    /** 
     * 
     */
    private void checkTemperature()
    {        
        if( temp < 10.0 ){
            this.setChillIndex(); 
        } else if ( this.temp > 26.0 ){
            this.setHeatIndex(); 
        }  
    }
    
    /**  
     * @param temp - Double of temperature
     * @param wind - Double of wind speed
     * @return windChillIndex - Value of wind chill index
     */
    private double calculateChillIndex(double temp, double wind)
    {
        double windChillIndex = (10 * Math.sqrt(wind) + 10.5) * (33 - temp);
        
        return windChillIndex; //kcal / m2 / h 
    }
    
    /**  
     * @param temp - Double of temperature
     * @param wind - Double of wind speed
     * @return windChillIndex - Value of heat index
     */
    private double calculateHeatIndex(double temp, double hum)
    {
        double heatIndex = temp + (5/9) * ((61.12 * (7.5 * temp) / (237.7 + temp)) 
                                                        * (hum / 100) - 10);
        return heatIndex; //Celcius
    }
    
    /**  Set methods
     */
    public void setChillIndex(){ this.chillIndex = this.calculateChillIndex(temp, wind); }
    public void setHeatIndex(){ this.heatIndex = this.calculateHeatIndex(temp, hum);}
    
    /**  Get methods
     * @return chillIndex - double
     * @return heatIndex - double
     */
    public double getChillIndex(){ return this.chillIndex; }
    public double getHeatIndex(){ return this.heatIndex; }
    
}
    

