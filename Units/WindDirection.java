package Units;

/**
 * Wind Direction Object
 * 
 * @author Tim Schijvenaars 
 */

public class WindDirection
{
    private short value;
    
    /**
     * @param value  - direction in degrees
     */
    public WindDirection(short value){
        this.setValue(value);
    }
    
    /**
     * @param value  - direction in degrees
     */
    public void setValue(short value){ 
        this.value = value;
    }
   
    /**
     * @param value  - direction in degrees
     */
    public short getValue(){ 
        return this.value;
    }
}