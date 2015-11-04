package Modules.Graphs;

import Modules.IO;


/**
 * Created by Timothy on 12-10-2014.
 */
public class Controller {

    private double[] data;
    private double minVal;
    private double maxVal;
    private double range;
    
    /**
     * 
     * @param graph - Graph object
     */
    public void makeGraph(Graph graph){
        data = graph.get();
        this.clearDisplay();
        this.writeGraph(data);
    }
    
    /**
     * 
     * @param min - minimum value in the whole graph spectrum
     * @param max - maximum value in the whole graph spectrum
     * 
     */
    private void createAxis(double min, double max)
    {
        int x,y;
        double diff = max-min;
        double valpix = diff/32;
        if(min<=0){
            if(min==0){
                y = 31;
            }
            else{
                y = (int) ( (diff-max) / valpix);
                y = 31 - y;
                System.out.println("Y: " + y);
            }
            for(int x2 = 0; x2 < 128; x2++){
                IO.writeShort(0x42, 1 << 12 | x2 << 5 | y );
            }
        }
        x = 0;
        for(int y2 = 0; y2 < 32; y2++){
            IO.writeShort(0x42, 1 << 12 | x << 5 | y2 );
        }
    }

    
    /**
     * 
     * @param data - Array of doubles containing all values of specific period and physical quantity
     * 
     */
    private void writeGraph(double[] data) {
        {
            double min = Modules.Controller.minValueFromArray(data);
            double max = Modules.Controller.maxValueFromArray(data);
            //System.out.print("Min: " + min + "/n");
            //System.out.print("Max: " + max);
            this.createAxis(min, max);
            int x, y;
            double getal;
            for (double i = 0; i < data.length; i++) {
                getal = data[(int) i];
                x = (int) ((i / data.length) * 127.0);
                double setVal = ((getal - min) / (max - min));
                y = (int) (setVal * 31.0);
                y = 31 - y;
                IO.writeShort(0x42, 1 << 12 | x << 5 | y);
            }
        }
    }

    
    /**
     * Clears the display
     */
    private void clearDisplay(){
        IO.writeShort(0x42, 3 << 12);
    }

    
    /**
     * Draws a canvas on the matixboard
     */
    private void drawCanvas(){
        for(int x = 0; x < 128; x++){
            IO.writeShort(0x42, 1 << 12 | x << 5 | 30);
        }

        for(int y =0; y < 32; y++){
            IO.writeShort(0x42, 1 << 12 | 1 << 5 | y);
        }
    }

}
