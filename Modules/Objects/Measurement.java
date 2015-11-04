package Modules.Objects;

import Units.*;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;

/**
 * Measurement Object
 *
 * @author Richard Danen
 * Group TI B2
 */
public class Measurement
{
    private int ID;
    private Timestamp timeStamp;
    private String creator;
    private String info;
    private String location;

    //Unit attributen
    private Airpressure airPressure;
    private Temperature insideTemprature;
    private Temperature outsideTemprature;
    private Humidity insideHumidity;
    private Humidity outsideHumidity;
    private WindVelocity windSpeed;
    private WindDirection windDir;
    private Rainmeter rainRate;
    private UVIndex uvLevel;
    
    private Batteryspan battery;
    private SunTime sunRise;
    private SunTime sunSet;

    /** Constructor */
    public Measurement(){

    }

    

    public int getID(){
        return this.ID;
    }

    
    /** Setters
     *  ID
     *  Station
     *  Timestamp
     *  Airpressure
     *  InsideTemperature
     *  InsideHumidity
     *  OutsideTemperature
     *  OutsideHumidity
     *  Windvelocity
     *  WindDirection
     *  Rainrate
     *  UVLevel
     *  Battery level
     *  Sunset
     *  Sunrise
     */
    public void setId(int id){ this.ID = id; }
    public void setStationId (String str) { this.location = str; }
    public void setDateStamp (java.sql.Timestamp ts) { this.timeStamp = ts; }
    public void setBarometer (short val) { this.airPressure = new Airpressure(val);}
    public void setInsideTemp (short val) { this.insideTemprature = new Temperature(val);}
    public void setInsideHum (short val) { this.insideHumidity = new Humidity(val);}
    public void setOutsideTemp (short val) { this.outsideTemprature = new Temperature(val);}
    public void setOutsideHum (short val) { this.outsideHumidity = new Humidity(val);}
    public void setWindSpeed (short val) { this.windSpeed = new WindVelocity(val);}
    public void setWindDir (short val) { this.windDir = new WindDirection(val);}
    public void setRainRate (short val) { this.rainRate = new Rainmeter(val);}
    public void setUVLevel (short val) { this.uvLevel = new UVIndex(val);}
    public void setBattLevel (short val) { this.battery = new Batteryspan(val);}
    public void setSunRise (short val) { this.sunRise = new SunTime(val);}
    public void setSunSet (short val) { this.sunSet = new SunTime(val);}
    
    /** Getters
     *  ID
     *  Station
     *  Timestamp
     *  Airpressure
     *  InsideTemperature
     *  InsideHumidity
     *  OutsideTemperature
     *  OutsideHumidity
     *  Windvelocity
     *  WindDirection
     *  Rainrate
     *  UVLevel
     *  Battery level
     *  Sunset
     *  Sunrise
     */
    public String getStationId () { return location; };
    public java.sql.Timestamp getDateStamp () { return timeStamp; };
    public Airpressure getBarometer () { return airPressure; };
    public Temperature getInsideTemp () { return insideTemprature; };
    public Humidity getInsideHum () { return insideHumidity; };
    public Temperature getOutsideTemp () { return outsideTemprature; };
    public Humidity getOutsideHum () { return outsideHumidity; };
    public WindVelocity getWindSpeed () { return windSpeed; };
    public WindDirection getWindDir () { return windDir; };
    public Rainmeter getRainRate () { return rainRate; };
    public UVIndex getUVLevel () { return uvLevel; };
    public Batteryspan getBattLevel () { return battery; };
    public SunTime getSunRise () { return sunRise; };
    public SunTime getSunSet () { return sunSet; };
}
