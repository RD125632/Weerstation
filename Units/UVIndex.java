package Units;

/**
 * UV Index Object
 * 
 * @author Richard 
 */

public class UVIndex
{
    private double rawValue;
    private double value;
    
    /** Constructor 
     * 
     * @param rawValue    - raw value
     */
    public UVIndex(int rawValue)
    {
        this.setValue(rawValue);
    }

    /**
     * Calculates value to index
     * 
     * @param rawValue      - value from database
     * @return lastValue    - index
     */
    private double calculateUVIndex(double rawValue)
    {
       double lastValue = 0.0;
        try
        {               
            if(rawValue >=0  && rawValue <= 30)
            {
                double tempValue = rawValue / 10; 
               
                if(tempValue >= 0 &&  tempValue < 3) { lastValue = 1; }
                else if(tempValue >= 3 &&  tempValue < 6) { lastValue = 2; }
                else if(tempValue >= 6 &&  tempValue < 8) { lastValue = 3; }     
                else if(tempValue >= 8 &&  tempValue < 11) { lastValue = 4; }
                else { lastValue = 5; }
                
            } else {
                throw new Exception("Ongeldige waarde UV Index :" + rawValue);
            }
        }
        catch (Exception e)
        {
            //Error message
        }
        
        return lastValue;
    }
    
    /**
     * @param rawValue    - value from database
     */
    public void setValue(double rawValue){ 
        this.rawValue = rawValue;
        this.value = this.calculateUVIndex(rawValue); 
    }
     
    /**
     * @return rawValue   - value from database
     */
    public double getRawValue() {
        return this.rawValue;
    }
    
    /**
     * @return value  - calcualted index
     */
    public double getValue(){ 
        return this.value; 
    }
}
