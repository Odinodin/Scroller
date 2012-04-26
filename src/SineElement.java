
import java.awt.*;

/**
 * User: Odin
 * Date: Dec 22, 2003
 * Time: 7:19:52 PM
 */

public class SineElement {

    private int x, y;
    private String element;
    private boolean goingLeft;
    private Color darkRed;
    private Color lightRed;

    // Sinus-kurve variabler
    private double periode;
    private int midtlinje = 75;

    public SineElement(int x, String s) {
        // Initialiserer
        this.x = x;
        this.element = s;
        goingLeft = true;
        periode = 0;
        darkRed = new Color(165, 9, 13);
        lightRed = new Color(239, 32, 37);
    }


    public void updatePos(){
        periode += Logic.periode;
        y = (int) (Logic.amplitude * Math.sin(periode/5)) + midtlinje;

        if (x < 0){
            goingLeft = false;
        } else if (x > Constants.width-8){
            goingLeft = true;
        }

        if (goingLeft){
            x-=Logic.speed;
        } else {
            x+=Logic.speed;
        }
    }

    public void drawMe(Graphics2D g2d){
        if (goingLeft){
            g2d.setColor(lightRed);
        } else {
            g2d.setColor(darkRed);
        }
        g2d.drawString(element, x, y);
    }

    public boolean isBehind() {
        return (!goingLeft);
    }

}
