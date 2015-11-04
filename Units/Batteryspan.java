package Units;

/**
 * Battery Span Object
 * 
 * @author Richard 
 */

public class Batteryspan
{
    private int value;

    /** Constructor 
     * 
     * @param voltage   - power level of weatherstation
       */
    public Batteryspan(int voltage)
    {
        this.setValue(voltage);
    }
    
    /**
     * @param voltage   - power level of weatherstation
     */
    public void setValue(int voltage){ 
        this.value = voltage; 
    }
    
    /**
     * @return battery - battery level of weatherstation
     */
    public int getValue(){ 
        return this.value; 
    }
}
