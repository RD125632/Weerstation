package Modules.Graphs;
/**
 * Created by Timothy on 12-10-2014.
 */
public class Graph {

    private double[] dataSet;

    
    /** Constructor
     * 
     * @param dataList  -  Array of doubles containing all values of specific period and physical quantity
     * @param margin - magin of the data
     * 
     */
    public Graph(double[] dataList, double margin){

        this.normalizeData(dataList, margin);

    }

    
    /**
     * 
     * @return - return dataSet; array of doubles containing all values of a specific period and physical quantity    
     */
    public double[] get(){

        return this.dataSet;
    }

    /**
     * @param - array of doubles containing all values of a specific period and physical quantity    
     */
    private void set(double[] data){

        this.dataSet = data;

    }

     /** 
     * 
     * @param data - Array of doubles containing all values of specific period and physical quantity
     * @param margin - magin of the data
     * 
     */
    private void normalizeData(double[] data , double margin){
        double prevPrevVal = 0.0, prevVal = 0.0, nextVal = 0.0, nextNextVal = 0.0, avrVal = 0.0, setVal = 0.0;
        for(int i = 0; i < data.length; i ++){
            if(i + 1 < data.length){
                nextVal = data[i+1];
            }
            if(i + 2 < data.length){
                nextNextVal = data[i+2];
            }
            if(prevPrevVal > 0 && prevVal > 0 && nextVal > 0 && nextNextVal > 0){
                avrVal = (prevPrevVal + prevVal + nextVal + nextNextVal) / 4;
                if((data[i] - avrVal) >= margin){
                    System.out.println(data[i]);
                    setVal = avrVal;
                }else{
                    setVal = data[i];
                }
            }else{
                setVal = data[i];
            }

            data[i] = setVal;
            prevVal = data[i];

            if(i - 1 >= 0){
                prevPrevVal = data[i-1];
            }
        }
        this.set(data);
    }
}
