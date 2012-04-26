
import java.awt.*;

/**
 * User: Odin
 * Date: Dec 23, 2003
 * Time: 9:38:24 PM
 */

public class BackgroundLine {

    int posisjonsMatrise[][];
    int x,y;
    private Tegner tegner;


    public BackgroundLine(int x, int y, int lengde, Tegner tegner){
        // Initialiserer
        posisjonsMatrise = new int[lengde][2];
        this.x = x;
        this.y = y;
        this.tegner = tegner;
        initialize();
    }

    // Setter opp posisjonsmatrisen. Det er ei linje med dotter horisontalt med 50 pixler mellomrom
    public void initialize(){
        for (int i=0; i < posisjonsMatrise.length; i++){
            posisjonsMatrise[i][0] = i*50 + x;
            posisjonsMatrise[i][1] = y;
        }
    }


    // Flytt hele linja til bunnen av skjermen.
    public void reset(){
        for (int i=0; i < posisjonsMatrise.length; i++){
            posisjonsMatrise[i][0] = i*50;
            posisjonsMatrise[i][1] = Constants.height;
        }
    }


    public void drawMe(Graphics2D g2d){
        for (int i = 0; i<posisjonsMatrise.length; i++){
         g2d.drawImage(tegner.dot, posisjonsMatrise[i][0], posisjonsMatrise[i][1], null);
        }
    }

    // Dytt alle dottene en pixel mot nordvest
    public void move() {
        for (int i=0; i < posisjonsMatrise.length; i++){
            posisjonsMatrise[i][0] --;
            posisjonsMatrise[i][1] --;
        }
    }

    public boolean isDueToMove() {
        if (posisjonsMatrise[0][1]<-50){
            return true;
        } else return false;
    }
}
