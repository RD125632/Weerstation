package Modules;

import Modules.IO;
import Modules.Objects.Measurement;
import Units.*;

import java.lang.reflect.Array;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;


/**
 * Created by Timothy Verdonck on 7-10-2014.
 */
public class Controller {
    /** Attributen
     * 
     */
    private Backloader backloader;
    private ArrayList<Measurement> loadedList;

     /** Constructor
     */
    public Controller(){
       Dbconnector dbObject = new Dbconnector("84.24.41.72","5329","aws_data","aws","aws");
       this.backloader = new Backloader(dbObject);
       this.loadedList = this.backloader.getAllMeasurementsLast24h();
       //this.loadedList = this.backloader.getAllMeasurements();
   }

   public ArrayList<Measurement> getLoadedList()
   {
      return this.loadedList;
   }
   
    /**
     * @param date          - date as GregorianCalendar object
     * @return              - stamp as Timestamp object
     */
    private Timestamp calendarToTimestamp(GregorianCalendar date){
        String stampText = date.get(Calendar.YEAR) + "-" + (date.get(Calendar.MONTH)+1) + "-" + date.get(Calendar.DATE) + " "+ date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE) + ":" + date.get(Calendar.SECOND) + ".10000";
        Timestamp stamp = Timestamp.valueOf(stampText);
        return stamp;
    }
    

    /**
     * @param startDate         - startDate as GregorianCalendar object
     * @param endDate           - endDate as GregorianCalendar object
     * @return                  - ArrayList containing Measurement objects
     */
    public ArrayList<Measurement> getMeasurmentsFromPeriods(GregorianCalendar startDate, GregorianCalendar endDate){

        Timestamp start = this.calendarToTimestamp(startDate);
        Timestamp end = this.calendarToTimestamp(endDate);

        ArrayList<Measurement> searchStack = new ArrayList<Measurement>();
        ArrayList<Measurement> list = this.loadedList;
        int i = 0;
        for (Measurement m : list){
                 
            if(m.getDateStamp().equals(start)){
                searchStack.add(m);
            }else{
                if(m.getDateStamp().before(end)){
                    searchStack.add(m);
   
            
                }else{
                    if(m.getDateStamp().equals(end)){
                        searchStack.add(m);
                    }
                }
            }
            i++;
        }
        return searchStack;
    }
    
    /**
     * @param periode         - Number of coherent period
     * @return                - ArrayList containing Measurement objects
     */
    
    public ArrayList<Measurement> gregorianCalendarPeriods(int periode){
        ArrayList<Measurement> n = new ArrayList<Measurement>();
        GregorianCalendar calendar = new GregorianCalendar();
        
                switch (periode) {
                    case 1:  //Jaar
                    n = getMeasurmentsFromPeriods(calendar, new GregorianCalendar(calendar.get(GregorianCalendar.YEAR)-1,
                                                                                calendar.get(GregorianCalendar.MONTH),
                                                                                (calendar.get(GregorianCalendar.DAY_OF_MONTH)),
                                                                                calendar.get(GregorianCalendar.HOUR_OF_DAY),
                                                                                calendar.get(GregorianCalendar.MINUTE),
                                                                                calendar.get(GregorianCalendar.SECOND)));
                     
                    break;
                    case 2:  //Maand
                    n = getMeasurmentsFromPeriods(calendar, new GregorianCalendar(calendar.get(GregorianCalendar.YEAR),
                                                                                calendar.get(GregorianCalendar.MONTH)-1,
                                                                                (calendar.get(GregorianCalendar.DAY_OF_MONTH)),
                                                                                calendar.get(GregorianCalendar.HOUR_OF_DAY),
                                                                                calendar.get(GregorianCalendar.MINUTE),
                                                                                calendar.get(GregorianCalendar.SECOND)));

                    break;
                    case 3:  //Week
                    n = getMeasurmentsFromPeriods(calendar,new GregorianCalendar(calendar.get(GregorianCalendar.YEAR),
                                                                                calendar.get(GregorianCalendar.MONTH),
                                                                                (calendar.get(GregorianCalendar.DAY_OF_MONTH)-7),
                                                                                calendar.get(GregorianCalendar.HOUR_OF_DAY),
                                                                                calendar.get(GregorianCalendar.MINUTE),
                                                                                calendar.get(GregorianCalendar.SECOND)));
                    break;
                    case 4:  //Dag
                    n = getMeasurmentsFromPeriods(calendar,new GregorianCalendar(calendar.get(GregorianCalendar.YEAR),
                                                                                calendar.get(GregorianCalendar.MONTH),
                                                                                (calendar.get(GregorianCalendar.DAY_OF_MONTH)-1),
                                                                                calendar.get(GregorianCalendar.HOUR_OF_DAY),
                                                                                calendar.get(GregorianCalendar.MINUTE),
                                                                                calendar.get(GregorianCalendar.SECOND)));
                    break;
                    case 5:  //minuut
                    n = getMeasurmentsFromPeriods(calendar,new GregorianCalendar(calendar.get(GregorianCalendar.YEAR),
                                                                                calendar.get(GregorianCalendar.MONTH),
                                                                                (calendar.get(GregorianCalendar.DAY_OF_MONTH)),
                                                                                calendar.get(GregorianCalendar.HOUR_OF_DAY)-1,
                                                                                calendar.get(GregorianCalendar.MINUTE),
                                                                                calendar.get(GregorianCalendar.SECOND)));
                    break;
            }
            
            return this.getLoadedList();
        }
    
    
    /**
     * @param searchStack         - ArrayList of Measurements
     * @param menuInt             - Integer for coherent menu navigation
     * @return                    - Array containing values of one quantity
     */
    public double[] setObjectToArray(ArrayList<Measurement> searchStack, int menuInt)
    {
        int arrayLength = searchStack.size();
        int i = 0;
        double[] array = new double[arrayLength];
        double value = 0;
        
        for(Measurement m : searchStack)
        {
            
            switch (menuInt) {
                case 1: value = m.getBarometer().getValue(); break;   //Airpressure           
                case 2: value = m.getInsideTemp().getValue();  break; //Inside Temperature
                case 3: value = m.getOutsideTemp().getValue(); break; //Outside Temperature
                case 4: value = m.getInsideHum().getValue();  break;  //Inside Humidity
                case 5: value = m.getOutsideHum().getValue(); break;  //Outside Humidity
                case 6: value = m.getRainRate().getValue();   break;  //Rain rate
                case 7: value = m.getSunRise().getRawValue(); break;  //Sun Rise
                case 8: value = m.getSunSet().getRawValue(); break;   //Sun Set
                case 9: value = m.getUVLevel().getValue();  break;  //UV Index
                case 10: value = m.getWindDir().getValue();  break;//Wind Direction
                case 11: value = m.getWindSpeed().getValue();  break; //Wind Velocity
                case 12: value = m.getBattLevel().getValue(); break;   //Battery
            }
            
            array[i] = value;
            i++;
        }
        
        return array;
    }

    
    
    /** 
     * @param array[UnitObject]                     - Array of doubles containing all values of coherent UnitObj
     * @return maximum value array[UnitObject]      - Double value of maximum value
     */
    public static double maxValueFromArray(double[] array){
        double c = array[0];
        for(int x = 0; x < array.length; x ++){
            if(array[x] > c){
                c = array[x];
            }
        }
        return c;
    }

    /** 
     * @param array[UnitObject]                     - Array of doubles containing all values of coherent UnitObj
     * @return minimum value array[UnitObject]      - Double value of minimum value
     */
    public static double minValueFromArray(double[] array){
        double c = array[0];
        for(int x = 0; x < array.length; x ++){
            if(array[x] < c){
                c = array[x];
            }
        }
        return c;
    }

    /** 
     * @param array[UnitObject]                     - Array of doubles containing all values of coherent UnitObj
     * @return minimum value array[UnitObject]      - Double value of mean value
     */
    public static double meanFromArray(double[] array)
    {
        double total = 0;
        int arrayCount = 0;
        
        for(int x = 0; x < array.length; x++){
           total += array[x];
           arrayCount += 1;
        }        
        double mean = total / arrayCount;
        return (double) Math.round(mean * 100) / 100;
    }
    
    /** 
     * @param modus                                - ArrayList of integers
     * @param list                                 - ArrayList of Doubles
     * @return modusget                            - Modus from that specific list
     */
    public static double modusFromArray(double[] array)
    {
        ArrayList<Double> list = new ArrayList<Double>();
        ArrayList<Integer> modus = new ArrayList<Integer>();
        for(double d : array) 
        {
            list.add(d);
        }
        int number = 0;
        int refnumber = 0;
        double modusGet = 0;
        
        for(double n : list)
        {
            modus.add(Collections.frequency(list, n));
            number = Collections.frequency(list, n);
            if(number > refnumber)
            {
                refnumber = number;
                modusGet = n;
            }
        }
        
        return modusGet;
        
    }
    
    /** 
     * @param array                                - Array of doubles
     * @param list                                 - ArrayList of Doubles from array
     * @return median                              - Median from that specific list
     */
    public static double mediaanFromArray(double[] array)
    {
        ArrayList<Double> list = new ArrayList<Double>();
        for(double d : array) 
        {
            list.add(d);
        }
        
        int middle = list.size() / 2;
        
        if(list.size()%2!= 0)
        {
            return list.get(middle);
        }
        else
        {
            return (list.get(middle) - 1 + list.get(middle)) / 2;
        }
    }
    
    public static double standardDeviationFromArray(double[] array)
    {
        double mean = meanFromArray(array);
        ArrayList<Double> deviation = new ArrayList<Double>();
        double deviationTotal= 0;
        
        for(double n : array)
        {
            deviation.add(n - mean);
        }
        
        for(double dev : deviation)
        {
            deviationTotal += (dev * dev);
        }
        
        double avgDeviation = deviationTotal / deviation.size();
        return Math.sqrt(avgDeviation);
    }
       
    //Tim & Richard
    /** 
     * @param searchStack                                               - ArrayList of Measurements
     * @return "" + arrayStoredPeriod.get(0).getDateStamp()+ "," +
                arrayStoredPeriod.get(arrayStoredPeriod.size()-1)
                .getDateStamp()inimum                                   - String of Timestamps
     */
    public String getLowestDrought(ArrayList<Measurement> searchStack){

        ArrayList<Measurement> arrayCurrentPeriod = new ArrayList<Measurement>();
        ArrayList<Measurement> arrayStoredPeriod = new ArrayList<Measurement>();

        for(Measurement m: searchStack){
            double rainRate = m.getRainRate().getValue();

            if(rainRate == 0.0){
                arrayCurrentPeriod.add(m);
            }else{
                if(arrayStoredPeriod.size() < arrayCurrentPeriod.size()){
                    arrayStoredPeriod = arrayCurrentPeriod;
                }else{
                    arrayCurrentPeriod.clear();
                }
            }
        }
        return "" + arrayStoredPeriod.get(0).getDateStamp()+ "," +
                arrayStoredPeriod.get(arrayStoredPeriod.size()-1).getDateStamp();
    }

    /** 
     * @param searchStack                                               - ArrayList of Measurements
     * @param maxRain                                                   - Integer of rainfall limit
     * @return "" + arrayStoredPeriod.get(0).getDateStamp()+ "," +
                arrayStoredPeriod.get(arrayStoredPeriod.size()-1)
                .getDateStamp()inimum                                   - String of Timestamps
     */
    public String getLowestDroughtWitchMaxRainFall(ArrayList<Measurement> searchStack, int maxRain){

        ArrayList<Measurement> arrayCurrentPeriod = new ArrayList<Measurement>();
        ArrayList<Measurement> arrayStoredPeriod = new ArrayList<Measurement>();


        for(Measurement m: searchStack){
            double rainRate = m.getRainRate().getValue();

            if(rainRate <= maxRain){
                arrayCurrentPeriod.add(m);
            }else{
                if(arrayStoredPeriod.size() < arrayCurrentPeriod.size()){
                    arrayStoredPeriod = arrayCurrentPeriod;
                }else{
                    arrayCurrentPeriod.clear();
                }
            }
        }

        return "" + arrayStoredPeriod.get(0).getDateStamp()+ "," +
                arrayStoredPeriod.get(arrayStoredPeriod.size()-1).getDateStamp();
    }

    /** 
     * @param searchStack                                               - ArrayList of Measurements
     * @return "" + arrayStoredPeriod.get(0).getDateStamp()+ "," +
                arrayStoredPeriod.get(arrayStoredPeriod.size()-1)
                .getDateStamp()inimum                                   - String of Timestamps
     */
    public String getLongestRainPeriod(ArrayList<Measurement> searchStack){

        ArrayList<Measurement> arrayCurrentPeriod = new ArrayList<Measurement>();
        ArrayList<Measurement> arrayStoredPeriod = new ArrayList<Measurement>();


        for(Measurement m: searchStack){
            double rainRate = m.getRainRate().getValue();

            if(rainRate > 0.0){
                arrayCurrentPeriod.add(m);
            }else{
                if(arrayStoredPeriod.size() < arrayCurrentPeriod.size()){
                    arrayStoredPeriod = arrayCurrentPeriod;
                }else{
                    arrayCurrentPeriod.clear();
                }
            }
        }

        return "" + arrayStoredPeriod.get(0).getDateStamp()+ "," +
                arrayStoredPeriod.get(arrayStoredPeriod.size()-1).getDateStamp();

    }

    /** 
     * @param searchStack                           - ArrayList of Measurements
     * @return maxShower                            - Double of maximum rainfall in a certain period
     */
    public double getMaxRainContinous(ArrayList<Measurement> searchStack){

        ArrayList<Measurement> arrayCurrentPeriod = new ArrayList<Measurement>();
        ArrayList<Measurement> arrayStoredPeriod = new ArrayList<Measurement>();
        double maxShower = 0.0;

        for(Measurement m: searchStack){
            double rainRate = m.getRainRate().getValue();

            if(rainRate > 0.0){
                arrayCurrentPeriod.add(m);
            }else{
                double periodShower = 0.0;
                for(Measurement n : arrayCurrentPeriod)
                {
                    periodShower = periodShower + n.getRainRate().getValue();
                }
                if(periodShower > maxShower){
                    maxShower = periodShower;
                    arrayCurrentPeriod.clear();
                }
            }


        }

        return maxShower;
    }
    
    //Yorick
    /** 
     * @param searchStack                          - ArrayList of Measurements
     * @return temperature                         - Integer of temperature
     */
    private int giveGraaddag(int temperature)
    {
        int referencetemp;
        referencetemp = 18;
        if(temperature < referencetemp)
        {
            temperature = referencetemp - temperature;
        }
        else
        {
            return 0;
        }

        return temperature;
    }

    /** 
     * @param searchStack                          - ArrayList of Measurements
     * @return value                               - Integer of total amount of graaddagen
     */
    public int calcGraaddagen(ArrayList<Measurement> searchStack)
    {
        int value = 0;
        for(Measurement m: searchStack)
        {
            int graaddag =  (int) m.getOutsideTemp().getValue();
            value += giveGraaddag(graaddag);
        }
        return value;

    }

    /** 
     * @param searchStack                          - ArrayList of Measurements
     * @return longestperiod                       - Double of longest Summerday streak
     */
    public double calcZomerseDagen(ArrayList<Measurement> searchStack)
    {
        int longestperiod = 0;
        int currentperiod = 0;
        for(Measurement m : searchStack) //alles parsen
        {
            if( isZomerseDag(m.getOutsideTemp().getValue())) //als zomersedag = true
            {
                currentperiod++;
            }
            else
            {
                if(currentperiod > longestperiod) // als deze telling > langste,dan set je dit naar longestperiod, dan verder gaan met serie.
                {
                    longestperiod = currentperiod;
                }
                currentperiod = 0; //reset

            }


        }
        return longestperiod;

    }

    /** 
     * @param searchStack                          - ArrayList of Measurements
     * @return boolean                             - True if temperature bigger then 25
     */
    private boolean isZomerseDag(double temperature)
    {
        if(temperature > 25)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    /** 
     * @param searchStack                          - ArrayList of Measurements
     * @return boolean                             - True if temperature bigger then 30
     */
    private boolean isTropischeDag(double temperature)
    {
        if(temperature > 30)
        {
            return true;
        }
        else
        {
            return false;
        }

    }


    /** 
     * @param searchStack                          - ArrayList of Measurements
     * @return boolean                             - True if the period has a heatwave
     */
    public boolean isHittegolf(ArrayList<Measurement> searchStack)
    {
        int i = 0; //increment int zomerse
        int t = 0; //increment int tropische
        for(Measurement m : searchStack)
        {

            if(isZomerseDag(m.getOutsideTemp().getValue()))
            {
                i++; // is zomerse dag, dus i++
                if(isTropischeDag(m.getOutsideTemp().getValue()))
                {
                    t++; // is tropische dag, dus t++
                }

                if(i >= 5 && t >= 3) // als 5 zomerse dag, waarvan 3 tropisch, dan hittegolf = true
                {
                    return true;

                }

            }
            else
            {
                i = 0;
                t = 0; //resets
            }


        }
        return false;
    }

    /** 
     * @param searchStack                          - ArrayList of Measurements
     * @return duration                            - Integer of amount of days with increasing temperature (next day hotter then previous)
     */
    public int longestTempRaise(ArrayList<Measurement> searchStack)
    {
        double templast = 0;
        int duration = 0;
        for(Measurement m : searchStack)
        {
            if( templast< m.getOutsideTemp().getValue())
            {
                templast = m.getOutsideTemp().getValue();
                duration++;

            }
            else
            {
                duration = 0;
                templast = 0;
            }

        }
        return duration;
    }
    
   
   /**  
    * @param searchStack               - ArrayList of Measurements
    * @return indexVal                 - value of apparent temperature
    */
   
   public double ApparentTemperature(ArrayList<Measurement> searchStack){
        CalcApparentTemp apparentTemp;
        double indexVal = 0.0;
        
        for(Measurement m: searchStack){
             if(m.getOutsideTemp().getValue() > 26.0){
                 
                 indexVal = new CalcApparentTemp(m.getOutsideTemp().getValue(), 
                                    m.getWindSpeed().getValue(), 
                                    m.getOutsideHum().getValue()).getHeatIndex();
                                    
                } else if (m.getOutsideTemp().getValue() < 10.0){
                    
                 indexVal = new CalcApparentTemp(m.getOutsideTemp().getValue(), 
                                        m.getWindSpeed().getValue(), 
                                        m.getOutsideHum().getValue()).getChillIndex();
                 
            }
            
        }
        
        return indexVal;
    }
   
}
