package Units;


/**
 * Windvelocity Object
 * 
 * @author Tim Schijvenaars
 */
 
public class WindVelocity
{
    private short rawValue;
    private double value;
    
    /**
     * @param rawValue  - speed in Miles per Hour
     */
    public WindVelocity(short rawValue){
        this.setValue(rawValue);
    }
    
    /**
     * @param rawValue  - speed in Miles per Hour
     * @param lastValue - speed in Kilometers per Hour
     */
    private double calculateValue(short rawValue){
        double lastValue = 0.0;
        
        try
        {
            if(rawValue >= 0) {
                double velocity = rawValue * 1.609344;
                lastValue = (int)Math.round(velocity);
                } else {
                throw new Exception("Ongeldige waarde Velocity : " + rawValue);
            }   
        }
        catch (Exception e)
        {
           //Error message
        }
        
        return lastValue; 
    }

    /**
     * @param rawValue  - speed in Miles per Hour
     */
    public void setValue(short rawValue){
       this.rawValue = rawValue;
       this.value = this.calculateValue(rawValue);
    }
  
    /**
     * @return rawValue - speed in Miles per Hour
     */
    public short getRawValue(){
        return this.rawValue;
    }  

    /**
     * @return value - speed in Kilometers per Hour
     */
    public double getValue(){    
        return this.value;
    }
  

}
