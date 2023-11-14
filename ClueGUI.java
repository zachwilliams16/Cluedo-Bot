import java.util.ArrayList;

/**
 * ClueGUI
 */
public class ClueGUI {

    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";// list of of possible player id's

    ArrayList<String> clueSheet = new ArrayList<String>(); // the sheet that players use but digital, each line is a
                                                           // card

    public ClueGUI() {

    }

    /**
     * clears the board
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * prints the clue sheet
     */
    public void printsheet() {
        clearScreen();

        updateSheet();

        for (int i = 0; i < clueSheet.size(); i++) {
            System.out.println(clueSheet.get(i));
        }
    }

    /**
     * updates the Clue sheet
     */
    public void updateSheet() {
        String temp = "/";
        while (true) {// first line
            clueSheet.clear();

            for (int i = 0; i < 26; i++) {
                temp += "-";
            }

            for (int i = 0; i < MainClass.getNumPlayers(); i++) {
                temp += String.valueOf(letters.charAt(i)) + "-";
            }
            for (int i = 0; i < (6 - MainClass.getNumPlayers()); i++) {
                temp += "--";
            }
            temp += "\\";
            clueSheet.add(temp);
            break;
        }

        String divider = "|";
        while (true) {// makes the divider between types
            for (int i = 0; i < 24; i++) {
                divider += "-";
            }
            divider += "+";
            for (int i = 0; i < 13; i++) {
                divider += "-";
            }
            divider += "|";
            break;
        }

        for (Card Card : MainClass.getWhoCards()) {
            clueSheet.add(Card.toString());
        }

        clueSheet.add(divider);
        for (Card Card : MainClass.getWhatCards()) {
            clueSheet.add(Card.toString());
        }
        clueSheet.add(divider);
        for (Card Card : MainClass.getWhereCards()) {
            clueSheet.add(Card.toString());
        }

        while (true) {// last line
            temp = "\\";
            for (int i = 0; i < 38; i++) {
                temp += "-";
            }
            temp += "/";
            break;
        }
        clueSheet.add(temp);
    }

    /**
     * prints the log so that viewer can read it
     */
    public void printLog() {
        ArrayList<Turn> initTurns = MainClass.getLog().getTurns();
        for (int i = 0; i < initTurns.size(); i++) {
            System.out.println(initTurns.get(i).toString());
        }
    }

    // TODO: make a logTurn Method
    // TODO: make a edit log method
    // TODO: make method in case player is stabbed or bleeds
}