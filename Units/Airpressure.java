package Units;

/**
 * AirPressure Object
 * 
 * @author Richard 
 */

public class Airpressure
{
    private double rawValue;
    private double value;

    /** Constructor
     * 
     * @param rawValue  -  raw airpressure in inch/1000hg
     */
    public Airpressure(double rawValue)
    {
        this.setValue(rawValue);
    }

    /**
     * Calculates inch/hg1000 to Pascal 
     * 
     * @param rawValue      - value in inch/1000hg
     * @return lastvalue    - value in pascal            
     */
    private double calculateAirPressure(double rawValue)
    {
        double lastValue = 0;
        
        try
        {
            if(rawValue >= 0 && rawValue <= 99999.9 )
            {
                double newValue = rawValue / 29.5299830714;
                lastValue = Math.round(newValue);
            } else {
               throw new Exception("Ongeldige waarde Airpressure : " + rawValue);
            }
        } 
        catch(Exception e)
        {
            //Error message
        }
        
        return lastValue;
    }
    
    /** 
     * @param rawValue  - value in inch/1000hg
     */
    private void setValue(double rawValue){ 
        this.rawValue = rawValue;
        this.value = this.calculateAirPressure(rawValue); 
    }
   
    /** 
     * @return rawValue  - value in inch/1000hg
     */
    public double getRawValue(){ 
        return this.rawValue; 
    }
    
    /** 
     * @return value  - value in Pascal
     */
    public double getValue(){ 
        return this.value; 
    }
}
