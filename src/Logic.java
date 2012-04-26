
import java.util.ArrayList;

/**
 * User: Odin
 * Date: Dec 22, 2003
 * Time: 7:18:32 PM
 */

public class Logic {
    public ArrayList elementList;
    public ArrayList startList;

    public static int amplitude = 20;
    public static double periode = 0.3;
    public static int speed = 3;

    public Logic(){
        // Initialiserer
        elementList = new ArrayList();
        startList = new ArrayList();

        // Legger til de ulike elementene til sinescrolleren.
        // Dette er bare en hack, gjør det penere senere:)

        fillStartList();
    }

    private void fillStartList() {
        int startPos = 400;
        startList.add(new SineElement(startPos, "S"));
        startList.add(new SineElement(startPos, "I"));
        startList.add(new SineElement(startPos, "N"));
        startList.add(new SineElement(startPos, "E"));
        startList.add(new SineElement(startPos, " "));
        startList.add(new SineElement(startPos, " "));
        startList.add(new SineElement(startPos, "I"));
        startList.add(new SineElement(startPos, "S"));
        startList.add(new SineElement(startPos, " "));
        startList.add(new SineElement(startPos, " "));
        startList.add(new SineElement(startPos, "C"));
        startList.add(new SineElement(startPos, "O"));
        startList.add(new SineElement(startPos, "O"));
        startList.add(new SineElement(startPos, "L"));
        startList.add(new SineElement(startPos, "!"));
    }

    public void updateElements(){
        SineElement element;
        for (int i = 0; i<elementList.size(); i++){
            element = (SineElement)elementList.get(i);
            element.updatePos();
        }
    }

    public void addElement() {
        try {
        elementList.add(startList.remove(0));
        } catch (Exception e){

        }
    }

    public boolean moreToAdd() {
        if (startList.isEmpty()){
            return false;
        } else return true;
    }

    public void ampIncrement() {
        if (amplitude < 55){
            amplitude+=5;
        }
    }

     public void ampDecrement() {
        if (amplitude > 5){
            amplitude-=5;
        }
    }

    public void speedIncrement() {
        if (speed < 5){
            speed++;
        }
    }

    public void speedDecrement() {
        if (speed > 0){
            speed--;
        }
    }

    public void freqIncrement() {
        if (periode < 2){
            periode +=0.1;
        }
    }

    public void freqDecrement() {
        if (periode > 0){
            periode -=0.1;
        }

    }

    public void reset() {
        elementList.clear();
        amplitude = 20;
        periode = 0.3;
        fillStartList();
    }

}
