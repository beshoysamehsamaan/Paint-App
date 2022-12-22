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
public class Oval extends Shape{
        int x1, y1;
        int w, h;
        int f ;

    public Oval() {
    }

    public Oval(int x1, int y1, int w, int h, int f) {
        this.x1 = x1;
        this.y1 = y1;
        this.w = w;
        this.h = h;
        this.f = f;
    }
    
    public Oval(int x1, int y1, int w, int h, int f , Color c , int d) {
        super(c, d);
        this.x1 = x1;
        this.y1 = y1;
        this.w = w;
        this.h = h;
        this.f = f;
    }

      
}
