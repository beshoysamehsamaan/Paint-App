/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectpaint;

import java.awt.Color;

/**
 *
 * @author Beshoy Sameh
 */
public class Shape {
    Color shapeColor;
    int dotted;

    public Shape() {
    }

    public Shape(Color shapeColor, int dotted) {
        this.shapeColor = shapeColor;
        this.dotted = dotted;
    }

    public Color getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(Color shapeColor) {
        this.shapeColor = shapeColor;
    }

    public int getDotted() {
        return dotted;
    }

    public void setDotted(int dotted) {
        this.dotted = dotted;
    }


    
    
}
