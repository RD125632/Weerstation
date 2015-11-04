package Units;

/**
 * Humidity Object
 * 
 * @author Richard 
 */

public class Humidity
{
    private double value;
    
    /** Constructor
     * 
     * @param rawValue  - humidity 
     */
    public Humidity(int rawValue)
    {
        this.setValue(rawValue);
    }
   
    /**
     * @param value   - humidity 
     */
    public void setValue(int rawValue){ 
        this.value = rawValue; 
    }
    
    /**
     * @return value   - humidity 
     */
    public double getValue(){ 
        return this.value; 
    }
}
