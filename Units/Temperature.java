package Units;


/**
 * Temperature Object
 * 
 * @author Richard 
 */

public class Temperature
{
    private short rawValue;
    private double value;

    /** Constructor 
     * 
     * @param rawValue   - temperature in farhenheit
     */
    public Temperature(short rawValue)
    {
        this.setValue(rawValue);
    }

    /**
     * @param rawValue      - temperature in farhenheit
     * @return lastValue    - temperature in celcius
     */
    private double calculateTemperature (short rawValue)
    {
        double rawVal = rawValue / 10;
        double lastValue = 2.0;
        
        try
        {
            if(rawVal > -140 && rawVal < 150 )
            {
                double tempValue = (rawVal - 32) / 1.8;
                lastValue = (int)Math.round(tempValue);
            } else {
                throw new Exception("Ongeldige waarde Temperature : " + rawVal);
            } 
        }
        catch (Exception e)
        {
           //Error message
        }
       
        return lastValue;
    }
    
    /**
     * @param rawValue  - temperature in farhenheit
     */
    public void setValue(short rawValue){ 
        this.rawValue = rawValue;
        this.value = this.calculateTemperature( rawValue ); 
    }
     
    /**
     * @return rawValue   - temperature in farhenheit
     */
    public short getRawValue() { 
        return this.rawValue; 
    }
     
    /**
     * @return value   - temperature in celcius
     */
    public double getValue(){ 
        return this.value; 
    }
}
