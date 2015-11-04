package Units;

/**
 * Rainmeter Object
 * 
 * @author Richard
 */

public class Rainmeter
{
    private double rawValue;
    private double value;

    /** Constructor 
     *
     * @param rawValue   - raw rain value in inches
     */
    public Rainmeter(double rawValue)
    {
        this.setValue(rawValue);
    }

    /**
     * Calculates inch to Milimeter
     * 
     * @param rawValue      - value in inches
     * @return lastValue    - value in milimeters
     */
    private double calculateRainFall(double rawValue)
    {
        double lastValue = 0;
        
        try
        {
            if(rawValue >= 0 && rawValue < 300)
            {
                double newValue = (rawValue / 100) * 25.4; 
                lastValue = (int)Math.round(newValue);
            } else {
                throw new Exception("Ongeldige waarde Rainmeter : " + rawValue); 
            }
        } 
        catch ( Exception e)
        {
            //Error message
        }
            
        return lastValue;
    }
    
    /**
     * @param rawValue   - rain in inches
     */
    private void setValue(double rawValue){ 
        this.rawValue = rawValue;
        this.value = this.calculateRainFall(rawValue); 
    }
  
    /**
     * @return rawValue  - value in inches
     */
    public double getRawValue(){ 
        return this.rawValue; 
    }
    
    /**
     * @return value - value in milimeters
     */
    public double getValue(){ 
        return this.value;
    }
}
