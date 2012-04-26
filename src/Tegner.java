
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

/**
 * User: Odin
 * Date: Dec 11, 2003
 * Time: 7:46:26 PM
 */

public class Tegner extends JPanel implements Runnable {

    private Thread traaden;
    private Graphics2D g2d;    // Tegner med
    private Graphics2D offG;    // Buffer gfx
    private Image offI;         // Buffer image
    private Scroller scroller;
    private Logic logikk;
    private Color bakgrunnsfarge;
    private Background bakgrunn;
    private Font sinefont;
    private Font guiFont;
    public Image dot;
    public int sineElementSpeed;
    private SineElement drawElement;



    public Tegner(Scroller stardust, Logic logikk) {
        // Initialiserer
        this.scroller = stardust;
        offI = stardust.getBufferImage();
        this.logikk = logikk;
        offG = (Graphics2D) offI.getGraphics();     // Setter opp buffer
        bakgrunnsfarge = new Color(102, 204, 255);
        sinefont = new Font("Impact", Font.BOLD, 25);
        guiFont = new Font("Arial", Font.PLAIN, 10);
        bakgrunn = new Background(this);
        sineElementSpeed = 3;
        loadImages();



        traaden = new Thread((this));
        traaden.start();
    }

    public void paint(Graphics g){
        update(g);
    }

    public synchronized void update(Graphics g){
        g2d = (Graphics2D)g;

        // Wiper skjermen
        offG.setColor(bakgrunnsfarge);
        offG.fillRect(0, 0, Constants.width, Constants.height);

        // Tegner på dotter
        bakgrunn.drawMe(offG);

        // Tegner på Sinus-elementene
        offG.setFont(sinefont);
        for(int i = 0; i < logikk.elementList.size(); i++){
            drawElement = (SineElement)logikk.elementList.get(i);
            if (drawElement.isBehind()){
                drawElement.drawMe(offG);
            }
        }
        for(int i = 0; i < logikk.elementList.size(); i++){
            drawElement = (SineElement)logikk.elementList.get(i);
            if (!drawElement.isBehind()){
                drawElement.drawMe(offG);
            }
        }


        // Legger på GUI
        offG.setColor(Color.RED);
        offG.setFont(guiFont);
        offG.drawString("Amplitude:  +  - ", 75, Constants.height-5);
        offG.drawString("Speed:  +  - ", 165, Constants.height-5);
        offG.drawString("Frequency:  +  - ", 235, Constants.height-5);
        offG.drawString("RESET", 360, Constants.height-5);

        // Smeller på det buffrede bildet
        g2d.drawImage(offI, 0,0, null);
    }



    public void run() {
        long sjekkTid = Calendar.getInstance().getTimeInMillis();
        while(true){
            try {
                Thread.sleep(40);

                } catch (InterruptedException e) {System.out.println("Noe gikk galt i run");}
            // Flytter på sine-elementene og bakgrunn-dottene.
            logikk.updateElements();
            bakgrunn.updatePos();


            // Brukes for å legge til sinus-elementene på riktig tidspunkt.
            // Når startlista er tom så hoppes denne biten over.
            if (logikk.moreToAdd()){
                if (Calendar.getInstance().getTimeInMillis() - sjekkTid > 300){
                   logikk.addElement();
                   sjekkTid = Calendar.getInstance().getTimeInMillis();
                }
            }
            Logic.speed = sineElementSpeed;
            repaint();
        }
    }

    public void loadImages(){
        dot = scroller.getImage(scroller.getCodeBase(), "gfx/dot.png");
    }

    // Metodene under her brukes til GUI
    public void ampIncrement() {
        logikk.ampIncrement();
    }

    public void ampDecrement() {
        logikk.ampDecrement();
    }

    public void speedIncrement() {
        if (sineElementSpeed<3){
            sineElementSpeed++;
        }
    }

    public void speedDecrement() {
        if (sineElementSpeed>0){
            sineElementSpeed--;
        }
    }

    public void freqIncrement() {
        logikk.freqIncrement();
    }

    public void freqDecrement() {
        logikk.freqDecrement();
    }

    // Resetter alt
    public void reset() {
        sineElementSpeed=3;
        logikk.reset();
    }
}
