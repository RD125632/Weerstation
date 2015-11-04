package Modules;

import java.sql.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import Modules.Objects.Measurement;

/**
* Backloader Class
* 
* @author Timothy Verdonck
*/
public class Backloader 
{
    private ArrayList<Measurement> storedMeasurements;
    private ArrayList<Measurement> allMeasurements;
    public  ArrayList<Measurement> lastday;
    private final Connection dbCon;

    
    /** Constructor
     * 
     * @param connector - Database connection
     */
    public Backloader(Dbconnector connector)
    {
        dbCon = connector.getConnection();

        this.storedMeasurements = new ArrayList<Measurement>();
        this.allMeasurements = new ArrayList<Measurement>();

        //Wegens tijdgebrek staat deze op 24H i.p.v. de hele measurement
        this.lastday = this.getAllMeasurementsLast24h();

    }

    /**
     * 
     */
    public void checkForMeasurements() {
        if(this.storedMeasurements.isEmpty()) {
            this.addMeasurments(this.getAllMeasurementsLast24h());
        }else{
            this.measurmentsLister();
        }
    }

    private void measurmentsLister(){
        int i = 0;
        while (true) {
            System.out.println("Getting data...." + i);
            this.addMeasurment(this.getMostRecentMeasurement());
            try {
                i++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Measurement> getMeasurements(){
        return this.storedMeasurements;
    }

    private void addMeasurment(Measurement m){
        this.storedMeasurements.add(m);
    }

    private void addMeasurments(ArrayList<Measurement> list){
        int i = 1;
        for(Measurement m : list){
            m.setId(i);
            this.addMeasurment(m);
            i++;
        }
    }
    
     /**
     * Construct Most Recent Measurement object
     * @return   <code>Measurement</code>
     * @since        1.0
     */ 
    public Measurement getMostRecentMeasurement()
    {
        Measurement m = new Measurement();
        int count = 0;
        
        try
        {
            // query:
            Statement s = dbCon.createStatement();
            s.executeQuery("SELECT stationId, timestamp, " +
                    "barometer, " +
                    "insideTemp, " +
                    "insideHum, " +
                    "outsideTemp, " +
                    "windSpeed, " +
                    "avgWindSpeed, " +
                    "windDir, " +
                    "outsideHum, " +
                    "rainRate, " +
                    "UVLevel, " +
                    "solarRad, " +
                    "xmitBatt, " +
                    "battLevel, " +
                //  "foreIcon, " +
                    "sunrise, " +
                    "sunset " +
                    "FROM measurement order by measurementId desc limit 1");

            ResultSet rs = s.getResultSet();
            
            while( rs.next() )
            {
                m.setStationId( rs.getString("stationId") );
                m.setDateStamp( rs.getTimestamp(2));
                m.setBarometer( Short.valueOf(rs.getString("barometer")) );
                m.setInsideTemp( Short.valueOf(rs.getString("insideTemp")) );
                m.setInsideHum( Short.valueOf(rs.getString("insideHum")) );
                m.setOutsideTemp( Short.valueOf(rs.getString("outsideTemp")) );
                m.setWindSpeed( Short.valueOf(rs.getString("windSpeed")) );
                //m.setAvgWindSpeed( Short.valueOf(rs.getString("avgWindSpeed")) );
                m.setWindDir( Short.valueOf(rs.getString("windDir")) );
                m.setOutsideHum( Short.valueOf(rs.getString("outsideHum")) );
                m.setRainRate( Short.valueOf(rs.getString("rainRate")) );
                m.setUVLevel( Short.valueOf(rs.getString("UVLevel")) );
               // m.setSolarRad( Short.valueOf(rs.getString("solarRad")) );
               //   m.setXmitBatt( Short.valueOf(rs.getString("xmitBatt")) );
                m.setBattLevel( Short.valueOf(rs.getString("battLevel")) );
                // m.setForeIcon( Short.valueOf(rs.getString("foreIcon")) );
                m.setSunRise( Short.valueOf(rs.getString("sunrise")) );
                m.setSunSet( Short.valueOf(rs.getString("sunset")) );

                count++;
            }
            
            rs.close();
            s.close();
        }
        catch( SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch( Exception ex)
        {
            System.out.println("getMeasurement: " + ex.getMessage());
        }

        return m;
    }

    /**
     * Construct ArrayList from Measurement objects
     * @return   <code>Measurement</code>
     * @since        1.0
     */
    public ArrayList<Measurement> getAllMeasurements()
    {
        ArrayList<Measurement> mArr = new ArrayList<Measurement>();
        int count = 0;
        
        try
        {
            // query:
            Statement s = dbCon.createStatement();
            s.executeQuery("SELECT stationId, timestamp, " +
                    "barometer, " +
                    "insideTemp, " +
                    "insideHum, " +
                    "outsideTemp, " +
                    "windSpeed, " +
                    "avgWindSpeed, " +
                    "windDir, " +
                    "outsideHum, " +
                    "rainRate, " +
                    "UVLevel, " +
                    "solarRad, " +
                    "xmitBatt, " +
                    "battLevel, " +
                //  "foreIcon, " +
                    "sunrise, " +
                    "sunset " +
                    "FROM measurement");

            ResultSet rs = s.getResultSet();

            while( rs.next() )
            {
                Measurement m = new Measurement();

                m.setStationId( rs.getString("stationId") );
                m.setDateStamp( rs.getTimestamp(2));
                m.setBarometer( Short.valueOf(rs.getString("barometer")) );
                m.setInsideTemp( Short.valueOf(rs.getString("insideTemp")) );
                m.setInsideHum( Short.valueOf(rs.getString("insideHum")) );
                m.setOutsideTemp( Short.valueOf(rs.getString("outsideTemp")) );
                m.setWindSpeed( Short.valueOf(rs.getString("windSpeed")) );
               // m.setAvgWindSpeed( Short.valueOf(rs.getString("avgWindSpeed")) );
                m.setWindDir( Short.valueOf(rs.getString("windDir")) );
                m.setOutsideHum( Short.valueOf(rs.getString("outsideHum")) );
                m.setRainRate( Short.valueOf(rs.getString("rainRate")) );
                m.setUVLevel( Short.valueOf(rs.getString("UVLevel")) );
                //m.setSolarRad( Short.valueOf(rs.getString("solarRad")) );
                //m.setXmitBatt( Short.valueOf(rs.getString("xmitBatt")) );
                m.setBattLevel( Short.valueOf(rs.getString("battLevel")) );
                // m.setForeIcon( Short.valueOf(rs.getString("foreIcon")) );
                m.setSunRise( Short.valueOf(rs.getString("sunrise")) );
                m.setSunSet( Short.valueOf(rs.getString("sunset")) );

                mArr.add(m);

                count++;
            }
            rs.close();
            s.close();
        }
        catch( SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch( Exception ex)
        {
            System.out.println("getMeasurement: " + ex.getMessage());
        }

        return mArr;
    }

    /**
     * Get all measurements between Date 1 & Date 2
     * @params d1    Begin datum
     * @params d2    Eind datum. d2 >= d1
     * @return   <code>Measurement</code>
     * @since        1.0
     */
    public ArrayList<Measurement> getAllMeasurementsBetween(GregorianCalendar d1, GregorianCalendar d2)
    {

        String sd1 = d1.get(Calendar.YEAR) + "-" + (d1.get(Calendar.MONTH)+1) + "-" + d1.get(Calendar.DATE) + " 0:0:0";
        String sd2 = d2.get(Calendar.YEAR) + "-" + (d2.get(Calendar.MONTH)+1) + "-" + d2.get(Calendar.DATE) + " 23:59:59";

        ArrayList<Measurement> mArr = new ArrayList<Measurement>();
        
        int count = 0;
        
        try
        {
            // query:
            Statement s = dbCon.createStatement();
            s.executeQuery("SELECT stationId, timestamp, " +
                    "barometer, " +
                    "insideTemp, " +
                    "insideHum, " +
                    "outsideTemp, " +
                    "windSpeed, " +
                    "avgWindSpeed, " +
                    "windDir, " +
                    "outsideHum, " +
                    "rainRate, " +
                    "UVLevel, " +
                    "solarRad, " +
                    "xmitBatt, " +
                    "battLevel, " +
                //  "foreIcon, " +
                    "sunrise, " +
                    "sunset " +
                    "FROM measurement where timestamp between " +
                    "'" + sd1 + "' and '" + sd2 + "'");

            ResultSet rs = s.getResultSet();
           
            while( rs.next() )
            {
                Measurement m = new Measurement();

                m.setStationId( rs.getString("stationId") );
                m.setDateStamp( rs.getTimestamp(2));
                m.setBarometer( Short.valueOf(rs.getString("barometer")) );
                m.setInsideTemp( Short.valueOf(rs.getString("insideTemp")) );
                m.setInsideHum( Short.valueOf(rs.getString("insideHum")) );
                m.setOutsideTemp( Short.valueOf(rs.getString("outsideTemp")) );
                m.setWindSpeed( Short.valueOf(rs.getString("windSpeed")) );
                //m.setAvgWindSpeed( Short.valueOf(rs.getString("avgWindSpeed")) );
                m.setWindDir( Short.valueOf(rs.getString("windDir")) );
                m.setOutsideHum( Short.valueOf(rs.getString("outsideHum")) );
                m.setRainRate( Short.valueOf(rs.getString("rainRate")) );
                m.setUVLevel( Short.valueOf(rs.getString("UVLevel")) );
              //  m.setSolarRad( Short.valueOf(rs.getString("solarRad")) );
              //  m.setXmitBatt( Short.valueOf(rs.getString("xmitBatt")) );
                m.setBattLevel( Short.valueOf(rs.getString("battLevel")) );
                //  m.setForeIcon( Short.valueOf(rs.getString("foreIcon")) );
                m.setSunRise( Short.valueOf(rs.getString("sunrise")) );
                m.setSunSet( Short.valueOf(rs.getString("sunset")) );

                mArr.add(m);

                count++;
            }
            rs.close();
            s.close();
        }
        catch( SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch( Exception ex)
        {
            System.out.println("getMeasurement: " + ex.getMessage());
        }

        return mArr;
    }

    /**
     * Get all measurements from last 24 hours
     * @return   <code>Measurement</code>
     * @since        1.0
     */
    public ArrayList<Measurement> getAllMeasurementsLast24h()
    {
        return getAllMeasurementsLastHours(24);
    }


    /**
     * Get all measurements from (param hour)
     * @params hour (Timespan = hour)
     * @return   <code>Measurement</code>
     * @since        1.0
     */
    public ArrayList<Measurement> getAllMeasurementsLastHours(int hour)
    {
        ArrayList<Measurement> mArr = new ArrayList<Measurement>();
        int count = 0;
             
        try
        {
            // query:
            Statement s = dbCon.createStatement();
            s.executeQuery("SELECT stationId, timestamp, " +
                    "barometer, " +
                    "insideTemp, " +
                    "insideHum, " +
                    "outsideTemp, " +
                    "windSpeed, " +
                    "avgWindSpeed, " +
                    "windDir, " +
                    "outsideHum, " +
                    "rainRate, " +
                    "UVLevel, " +
                    "solarRad, " +
                    "xmitBatt, " +
                    "battLevel, " +
                //  "foreIcon, " +
                    "sunrise, " +
                    "sunset " +
                    "FROM measurement where timestamp between NOW() - INTERVAL " +
                    hour + " HOUR and NOW()");


            ResultSet rs = s.getResultSet();
 
            while( rs.next() )
            {
                Measurement m = new Measurement();

                m.setStationId( rs.getString("stationId") );
                m.setDateStamp( rs.getTimestamp(2));
                m.setBarometer( Short.valueOf(rs.getString("barometer")) );
                m.setInsideTemp( Short.valueOf(rs.getString("insideTemp")) );
                m.setInsideHum( Short.valueOf(rs.getString("insideHum")) );
                m.setOutsideTemp( Short.valueOf(rs.getString("outsideTemp")) );
                m.setWindSpeed( Short.valueOf(rs.getString("windSpeed")) );
                //m.setAvgWindSpeed( Short.valueOf(rs.getString("avgWindSpeed")) );
                m.setWindDir( Short.valueOf(rs.getString("windDir")) );
                m.setOutsideHum( Short.valueOf(rs.getString("outsideHum")) );
                m.setRainRate( Short.valueOf(rs.getString("rainRate")) );
                m.setUVLevel( Short.valueOf(rs.getString("UVLevel")) );
               // m.setSolarRad( Short.valueOf(rs.getString("solarRad")) );
               // m.setXmitBatt( Short.valueOf(rs.getString("xmitBatt")) );
                m.setBattLevel( Short.valueOf(rs.getString("battLevel")) );
               //  m.setForeIcon( Short.valueOf(rs.getString("foreIcon")) );
                m.setSunRise( Short.valueOf(rs.getString("sunrise")) );
                m.setSunSet( Short.valueOf(rs.getString("sunset")) );

                mArr.add(m);

                count++;
            }
            rs.close();
            s.close();
        }
        catch( SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch( Exception ex)
        {
            System.out.println("getMeasurement: " + ex.getMessage());
        }

        return mArr;
    }
}
