

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * User: Odin
 * Date: Dec 11, 2003
 * Time: 7:43:58 PM
 */

public class Scroller extends JApplet {

    private Image offI;
    private Tegner gfx;
    private Logic logikk;

    public void init(){
        this.setSize(Constants.width, Constants.height);
        offI = this.createImage(Constants.width, Constants.height);
        logikk = new Logic();
        gfx = new Tegner(this, logikk);
        this.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e) {
                if (e.getX() > 130 && e.getX() < 140 && e.getY() > Constants.height-15 ){
                    gfx.ampIncrement();
                } else if (e.getX() > 141 && e.getX() < 150 && e.getY() > Constants.height-15 ){
                    gfx.ampDecrement();
                } else if (e.getX() > 204 && e.getX() < 212 && e.getY() > Constants.height-15 ){
                    gfx.speedIncrement();
                } else if (e.getX() > 213 && e.getX() < 222 && e.getY() > Constants.height-15 ){
                    gfx.speedDecrement();
                } else if (e.getX() > 290 && e.getX() < 300 && e.getY() > Constants.height-15 ){
                    gfx.freqIncrement();
                } else if (e.getX() > 301 && e.getX() < 311 && e.getY() > Constants.height-15 ){
                    gfx.freqDecrement();
                } else if (e.getX() > 360 && e.getX() < 398 && e.getY() > Constants.height-15 ){
                    if (logikk.moreToAdd() == false){
                        gfx.reset();
                    }
                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

        });
        setContentPane(gfx);
    }

    public Image getBufferImage() {
        return offI;
    }
}

