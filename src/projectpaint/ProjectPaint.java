package projectpaint;

import javax.swing.JFrame;

public class ProjectPaint {

    public static void main(String[] args) {
        JFrame f = new JFrame ();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("paint App");
        f.setSize(1500,1500);
        MyPanel mp = new MyPanel();
        f.setContentPane(mp);
        f.setVisible(true);
    }
    
}
