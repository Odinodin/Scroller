
import java.awt.*;
import java.util.ArrayList;

/**
 * User: Odin
 * Date: Dec 22, 2003
 * Time: 8:28:29 PM
 */

public class Background {

    private Tegner tegner;
    private ArrayList linjeListe;

    public Background(Tegner tegner){
        this.tegner = tegner;
        linjeListe = new ArrayList();
        initialize();
    }

    public void initialize(){
        linjeListe.add(new BackgroundLine(0, 0, 12, tegner));
        linjeListe.add(new BackgroundLine(25, 25, 12, tegner));
        linjeListe.add(new BackgroundLine(0, 50, 12, tegner));
        linjeListe.add(new BackgroundLine(25, 75, 12, tegner));
        linjeListe.add(new BackgroundLine(0, 100, 12, tegner));
        linjeListe.add(new BackgroundLine(25, 125, 12, tegner));
        linjeListe.add(new BackgroundLine(0, 150, 12, tegner));
        linjeListe.add(new BackgroundLine(25, 175, 12, tegner));
        linjeListe.add(new BackgroundLine(0, 200, 12, tegner));
    }


    public void updatePos(){
        BackgroundLine currentLine;

        for (int i = 0; i < linjeListe.size(); i++ ){
            // Hvis en av dottene er utenfor skjermen så skal de inn igjen på "andre siden"
            currentLine = (BackgroundLine) linjeListe.get(i);
            if (currentLine.isDueToMove()){
                currentLine.reset();

            } else {
                currentLine.move();
            }
        }
    }



    public void drawMe(Graphics2D g2d){
        for (int i = 0; i < linjeListe.size(); i++ ){
            ((BackgroundLine) linjeListe.get(i)).drawMe(g2d);
        }
    }
}
