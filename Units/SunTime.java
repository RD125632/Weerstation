package Units;

/**
 * Time Object
 * 
 * @author Tim Schijvenaars
 */
public class SunTime
{
      private short rawValue;
      private String value;
      
      /** Constructor
       * 
       * @param rawValue    - value in whole numbers
       */
      public SunTime(short rawValue){
            this.setValue(rawValue);
      }
       
      /**
       * Converts value to String
       * 
       * @param rawValue    - value in whole numbers
       * @return time       - string with digital time notation 
       */
      private String valueToString(short rawValue){
          String hours = "";
          String minutes = "";
          String time = "";

          try
          {
              if(rawValue > 0 || rawValue < 2400)
              {
                  hours += (rawValue / 100);
                  minutes += rawValue % 100;
                  time = hours + ":" + minutes;
                  
                  if (minutes == "0"){
                      minutes = "00";
                  } 
                    
                  if (minutes.length() == 1) { 
                      minutes = "0" + minutes;
                  }
                } else {
                    throw new Exception("Ongeldige waarde sunTime : " + rawValue);
                }
            }
            catch (Exception e)
            {
            }
          return time;  
      }
       
      /**
       * @param rawValue    - value in whole numbers
       */
      public void setValue(short rawValue){ 
          this.rawValue = rawValue; 
          this.value = this.valueToString(rawValue); 
      }
     
      /**
       * @return rawValue   - value in whole numbers
       */
      public short getRawValue(){ 
          return this.rawValue; 
      }
      
      /**
       * @return timestring  - string with time notation
       */
      public String getValue(){ 
          return this.value; 
      }
}
