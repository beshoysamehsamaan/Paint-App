/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectpaint;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;
import javafx.util.Pair;

/**
 *
 * @author Beshoy Sameh
 */
public class Line extends Shape{
    
     int x1,y1 ,x2,y2 ;

    public Line() {
    }

    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public Line(int x1, int y1, int x2, int y2 , Color c , int d) {
        super(c,d);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    
    
    
    
}
