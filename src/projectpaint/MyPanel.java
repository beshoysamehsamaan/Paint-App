package projectpaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Beshoy Sameh
 */
public class MyPanel extends JPanel {

    int x1, y1, x2, y2, w, h;

    Vector<Line> lvec = new Vector<>();
    Vector<Oval> ovec = new Vector<>();
    Vector<Rect> rvec = new Vector<>();
    Vector<FreeHand> fvec = new Vector<>();
    Vector<Eraser> evec = new Vector<>();

    String myshape;
    String mytemp;
    int filled, dotted;
    int counterForLine, counterForOval, counterForRect , counterForFree , counterForEraser , counterForImage;
    

    JButton line, oval, rect, freeHand , clearAll , eraser , save,undo;
    JButton redbutton, greenbutton, bluebutton;
    JCheckBox checkFill, checkDotted;
    Color c;

    public MyPanel() {
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        filled = 0;
        dotted = 0;

        checkFill = new JCheckBox("Filled");
        checkFill.setForeground(Color.WHITE);
        checkFill.setBackground(Color.BLACK);
        checkFill.setPreferredSize(new Dimension(80, 50));
        checkFill.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    filled = 1;
                } else {
                    filled = 0;
                }
            }

        });
        
        checkDotted = new JCheckBox("Dotted");
        checkDotted.setForeground(Color.WHITE);
        checkDotted.setBackground(Color.BLACK);
        checkDotted.setPreferredSize(new Dimension(80, 50));
        checkDotted.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    dotted = 1;
                } else {
                    dotted = 0;
                }
            }

        });
        

        line = new JButton("line");
        line.setPreferredSize(new Dimension(80, 50));
        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myshape = "Line";
            }

        });
        

        oval = new JButton("Oval");
        oval.setPreferredSize(new Dimension(80, 50));
        oval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myshape = "Oval";
            }

        });
        
        rect = new JButton("Rect");
        rect.setPreferredSize(new Dimension(80, 50));
        rect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myshape = "Rect";
            }

        });
        
        
        freeHand = new JButton("Free-Hand");
        freeHand.setPreferredSize(new Dimension(110, 50));
        freeHand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myshape = "Free";
            }

        });
        
        
        eraser = new JButton("Eraser");
        eraser.setPreferredSize(new Dimension(110, 50));
        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myshape = "Eraser";
            }

        });
        
        
        clearAll = new JButton("Clear-All");
        clearAll.setPreferredSize(new Dimension(110, 50));
        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myshape = "Clear";
                lvec.clear();
                ovec.clear();
                rvec.clear();
                counterForLine=0;
                counterForEraser=0;
                counterForRect=0;
                counterForOval=0;
                counterForFree=0;
                myshape="";
                updateUI();
            }

        });
        

        redbutton = new JButton();
        redbutton.setPreferredSize(new Dimension(80, 50));
        redbutton.setBackground(Color.RED);
        redbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c = Color.RED;
            }

        });
        

        greenbutton = new JButton();
        greenbutton.setPreferredSize(new Dimension(80, 50));
        greenbutton.setBackground(Color.GREEN);
        greenbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c = Color.GREEN;
            }

        });
        

        bluebutton = new JButton();
        bluebutton.setPreferredSize(new Dimension(80, 50));
        bluebutton.setBackground(Color.BLUE);
        bluebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c = Color.BLUE;
            }

        });
        
        
        
        save = new JButton("Snipping");
        save.setPreferredSize(new Dimension(150, 70));
        save.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    BufferedImage screencapture = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                    File file = new File("Painting1.jpg");
                    while (file.exists()) {
                        counterForImage++;
                        file = new File("Painting" + counterForImage + ".jpg");
                    }
                    ImageIO.write(screencapture, "jpg", file);

                } catch (Exception e) {
                }
                JOptionPane.showMessageDialog(save, "Image saved successfully");
            }
        });
 /*       
        undo = new JButton("Undo");
        undo.setEnabled(true);
        undo.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (myshape == "Clear") {
                    //shapes = temps;
                } else if (fvec.get(fvec.size()-1)instanceof FreeHand|| evec.get(evec.size()-1)instanceof Eraser) {
                    for (int i = fvec.size() - 1, count = 0; i >= 0; i--, count++) {
                        if (fvec.get(i)instanceof FreeHand) {
                            fvec.remove(i);
                        } else {
                            break;
                        }
                        if (count == 25) {
                            break;
                        }
                    }
                    for (int i = evec.size() - 1, count = 0; i >= 0; i--, count++) {
                        if (evec.get(i) instanceof Eraser) {
                            evec.remove(i);
                        } else {
                            break;
                        }
                        if (count == 25) {
                            break;
                        }
                    }
                } else {
                    //shapes.remove(shapes.size() - 1);
                }
                repaint();
            }
        });
*/
        this.add(redbutton);
        this.add(greenbutton);
        this.add(bluebutton);
        this.add(line);
        this.add(oval);
        this.add(rect);
        this.add(checkFill);
        this.add(checkDotted);
        this.add(freeHand);
        this.add(eraser);
        this.add(clearAll);
        this.add(save);
        //this.add(undo);


        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (myshape == "Line") {
                    x1 = e.getX();
                    y1 = e.getY();
                }
                if (myshape == "Oval") {
                    x1 = e.getX();
                    y1 = e.getY();
                }
                if (myshape == "Rect") {
                    x1 = e.getX();
                    y1 = e.getY();
                }
                if (myshape == "Free") {
                    x1 = e.getX();
                    y1 = e.getY();
                    w=5;
                    h=5;
                    filled=1;
                    dotted=0;
                    fvec.add(counterForFree, new FreeHand(x1, y1, w, h,filled, c, dotted));
                    counterForFree++;
                    updateUI();
                }
                if (myshape == "Eraser") {
                    x1 = e.getX();
                    y1 = e.getY();
                    w=8;
                    h=8;
                    filled=1;
                    dotted=0;
                    evec.add(counterForEraser, new Eraser(x1, y1, w, h, filled, c, dotted));
                    counterForEraser++;
                    updateUI();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (myshape == "Line")
                {
                    x2 = e.getX();
                    y2 = e.getY();
                    counterForLine++;
                }
                if (myshape == "Oval")
                {
                    w = e.getX() - x1;
                    h = e.getY() - y1;
                    counterForOval++;
                }
                if (myshape == "Rect")
                {
                    w = e.getX() - x1;
                    h = e.getY() - y1;

                    counterForRect++;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (myshape == "Line")
                {
                    x2 = e.getX();
                    y2 = e.getY();
                    lvec.add(counterForLine, new Line(x1, y1, x2, y2, c, dotted));
                    updateUI();
                }
                if (myshape == "Oval")
                {
                    w = e.getX() - x1;
                    h = e.getY() - y1;
                    ovec.add(counterForOval, new Oval(x1, y1, w, h, filled, c, dotted));
                    updateUI();
                }
                if (myshape == "Rect")
                {
                    w = e.getX() - x1;
                    h = e.getY() - y1;
                    rvec.add(counterForRect, new Rect(x1, y1, w, h, filled, c, dotted));
                    updateUI();
                }
                  if (myshape == "Free") {
                    x1 = e.getX();
                    y1 = e.getY();
                    w=5;
                    h=5;
                    filled=1;
                    dotted=0;
                    fvec.add(counterForFree, new FreeHand(x1, y1, w, h,filled, c, dotted));
                    counterForFree++;
                    updateUI();
                }
                if (myshape == "Eraser") {
                    x1 = e.getX();
                    y1 = e.getY();
                    w=8;
                    h=8;
                    filled=1;
                    dotted=0;
                    evec.add(counterForEraser, new Eraser(x1, y1, w, h,filled, c, dotted));
                    counterForEraser++;
                    updateUI();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

        g.setColor(c);
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);

        if (myshape == "Line") 
        {
            g.setColor(c);
            if (dotted == 0) {
                g.drawLine(x1, y1, x2, y2);
            } else if (dotted == 1) {
                g2d.drawLine(x1, y1, x2, y2);
            }
        } else if (myshape == "Oval") 
        {
            g.setColor(c);
            if (filled == 0)
            {
                if (dotted == 1) {
                    g2d.drawOval(x1, y1, w, h);
                } else {
                    g.drawOval(x1, y1, w, h);
                }
            } else if (filled == 1) {
                g.fillOval(x1, y1, w, h);
            }
        } else if (myshape == "Rect") 
        {
            g.setColor(c);
            if (filled == 0)// draw
            {
                if (dotted == 1) {
                    g2d.drawRect(x1, y1, w, h);
                } else {
                    g.drawRect(x1, y1, w, h);
                }
            } else if (filled == 1) {
                g.fillRect(x1, y1, w, h);
            }
        }else if (myshape == "Free") 
        {
            g.setColor(c);
            g.fillOval(x1, y1, w, h);
        }else if (myshape == "Eraser") 
        {
             g.setColor(Color.WHITE);
            g.fillOval(x1, y1, w, h);
        }

        for (int i = 0; i < counterForLine; i++)
        {
            //g.setColor(lvec.get(i).shapeColor);
            if (lvec.get(i).dotted == 0) {
                g.setColor(lvec.get(i).shapeColor);
                g.drawLine(lvec.get(i).x1, lvec.get(i).y1, lvec.get(i).x2, lvec.get(i).y2);
            } else if (lvec.get(i).dotted == 1) {
                g2d.setColor(lvec.get(i).shapeColor);
                g2d.drawLine(lvec.get(i).x1, lvec.get(i).y1, lvec.get(i).x2, lvec.get(i).y2);
            }
        }

        for (int i = 0; i < counterForOval; i++)// lines
        {
           // g.setColor(ovec.get(i).shapeColor);
            if (ovec.get(i).f == 0) {
                if (ovec.get(i).dotted == 1) {
                    g2d.setColor(ovec.get(i).shapeColor);
                    g2d.drawOval(ovec.get(i).x1, ovec.get(i).y1, ovec.get(i).w, ovec.get(i).h);
                } else {
                    g.setColor(ovec.get(i).shapeColor);
                    g.drawOval(ovec.get(i).x1, ovec.get(i).y1, ovec.get(i).w, ovec.get(i).h);
                }
            } else if (ovec.get(i).f == 1) {
                g.setColor(ovec.get(i).shapeColor);
                g.fillOval(ovec.get(i).x1, ovec.get(i).y1, ovec.get(i).w, ovec.get(i).h);
            }
        }

        for (int i = 0; i < counterForRect; i++)// lines
        {
           // g.setColor(rvec.get(i).shapeColor);
            if (rvec.get(i).f == 0) {
                if (rvec.get(i).dotted == 1) {
                    g2d.setColor(rvec.get(i).shapeColor);
                    g2d.drawRect(rvec.get(i).x1, rvec.get(i).y1, rvec.get(i).w, rvec.get(i).h);
                } else {
                    g.setColor(rvec.get(i).shapeColor);
                    g.drawRect(rvec.get(i).x1, rvec.get(i).y1, rvec.get(i).w, rvec.get(i).h);
                }
            } else if (rvec.get(i).f == 1) {
                g.setColor(rvec.get(i).shapeColor);
                g.fillRect(rvec.get(i).x1, rvec.get(i).y1, rvec.get(i).w, rvec.get(i).h);
            }
        }
        
        for (int i = 0; i < counterForFree; i++)
        {
            g.setColor(fvec.get(i).shapeColor);
            g.fillOval(fvec.get(i).x1, fvec.get(i).y1, 5, 5);
        }

        for (int i = 0; i < counterForEraser; i++)
        {
            g.setColor(Color.BLACK);
            g.fillRect(evec.get(i).x1, evec.get(i).y1, 5, 5);
        }

    }
}
