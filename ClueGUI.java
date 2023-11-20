import java.util.ArrayList;
import java.util.Scanner;

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

    /**
     * collects if the user gave the correct person for whos turn it is
     * 
     * @param initPlayer the player whos turn it might be
     * @return if the player meant to give this player
     */
    private boolean comfirmWhosTurn(Player initPlayer) {
        clearScreen();
        Scanner input = new Scanner(System.in);

        System.out.println("It is " + initPlayer.getName() + "'s turn?\n0 - yes/correct\n1 - no/incorrect");
        String initPlayerAnswer = input.nextLine();
        initPlayerAnswer.toLowerCase();

        input.close();
        try {
            int initPlayerAnswerNum = Integer.parseInt(initPlayerAnswer);
            if (initPlayerAnswerNum == 0) {
                return true;
            } else if (initPlayerAnswerNum == 1) {
                return false;
            } else {
                return comfirmWhosTurn(initPlayer);
            }

        } catch (NumberFormatException e) {
            if (initPlayerAnswer.equals("yes") || initPlayerAnswer.equals("correct")) {
                return true;
            } else if (initPlayerAnswer.equals("no") || initPlayerAnswer.equals("incorrect")) {
                return false;
            } else {
                return comfirmWhosTurn(initPlayer);
            }

        }

    }

    /**
     * 
     * @param initPlayer
     * @return
     */
    private boolean confirmWhoProved(Player initPlayer) {
        clearScreen();
        Scanner input = new Scanner(System.in);
        if (initPlayer == null) {
            System.out.println("No one proved them wrong?");
        } else {
            System.out.println(initPlayer.getName() + " proved them wrong?");
        }

        System.out.println("0 - yes/correct\n1 - no/incorrect");
        String initWhoProvedWrongInput = input.nextLine();
        input.close();

        try {
            int initPlayerAnswerNum = Integer.parseInt(initWhoProvedWrongInput);
            if (initPlayerAnswerNum == 0) {
                return true;
            } else if (initPlayerAnswerNum == 1) {
                return false;
            } else {
                return confirmWhoProved(initPlayer);
            }
        } catch (NumberFormatException e) {
            if (initWhoProvedWrongInput.equals("yes") || initWhoProvedWrongInput.equals("correct")) {
                return true;
            } else if (initWhoProvedWrongInput.equals("no") || initWhoProvedWrongInput.equals("incorrect")) {
                return false;
            } else {
                return confirmWhoProved(initPlayer);
            }
        }

    }

    private boolean confirmCard(Card initCard) {
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println("They chose the " + initCard.getName() + "?\n0 - yes\n1 - no");

        String initCardAnswerInput = input.nextLine();
        initCardAnswerInput.toLowerCase();
        input.close();

        try {
            int initCardAnswerInputInt = Integer.parseInt(initCardAnswerInput);
            if (initCardAnswerInputInt == 0) {
                return true;
            } else if (initCardAnswerInputInt == 1) {
                return false;
            } else {
                return confirmCard(initCard);
            }

        } catch (NumberFormatException e) {
            if (initCardAnswerInput.equals("yes")) {
                return true;
            } else if (initCardAnswerInput.equals("no")) {
                return false;
            } else {
                return confirmCard(initCard);
            }

        }

    }

    private void finalConfirm(Turn initTurn) {
        System.out.println(initTurn);
        System.out.println("Is this correct?\n0 - yes\n1 - no");

        Scanner input = new Scanner(System.in);
        String initUserInputString = input.nextLine();
        input.close();

        boolean initTheyWantToChange = false;
        try {
            int initUserInputInt = Integer.parseInt(initUserInputString);
            if (initUserInputInt == 0) {
                return;
            } else if (initUserInputInt == 1) {
                initTheyWantToChange = true;
            }
        } catch (Exception e) {

            if (initUserInputString.equals("yes")) {
                return;
            } else if (initUserInputString.equals("no")) {
                initTheyWantToChange = true;
            }
        }
        if (initTheyWantToChange) {
            changeEditTurn(initTurn);
        }

    }

    public void changeEditTurn(Turn initTurn) {

    }

    /**
     * 
     * @return the players whos turn it is
     */
    private Player askWhosTurn() {
        Scanner input = new Scanner(System.in);
        clearScreen();

        for (Player initPlayer : MainClass.getPlayers()) {// print out players
            System.out.println(initPlayer.getNumID() + " - " + initPlayer.getName());
        }

        System.out.println("Which player's turn is it?");// ask which player it is
        String initPlayerName = input.nextLine();
        initPlayerName.toLowerCase();
        input.close();

        try {// if they responded with a number
            int initPlayerID = Integer.parseInt(initPlayerName);
            for (Player initPlayer : MainClass.getPlayers()) {
                if (initPlayer.getNumID() == initPlayerID) {
                    return initPlayer;
                }
            }
            System.out.println("Hmm...\n I couldn't find that player. Please try again");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {

            }

        } catch (NumberFormatException e) {// if they responded with a String
            for (Player initPlayer : MainClass.getPlayers()) {
                if (initPlayer.getName().equals(initPlayerName)) {
                    return initPlayer;
                }
            }
            System.out.println("Hmm...\n I couldn't find that player. Please try again");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException b) {

            }

        }

        return askWhosTurn();

    }

    /**
     * gets who proved whos turn it was wrong
     * 
     * @return who proved wrong, null if no one did
     */
    private Player askWhoProved() {
        Scanner input = new Scanner(System.in);
        String initwhoProvedInput = "";
        clearScreen();
        while (true) {// prints out charecter names and gets who proved
            for (int i = 0; i <= MainClass.getPlayers().size(); i++) {
                if (MainClass.getNumPlayers() == i) {
                    System.out.println(i + " - no one");
                } else {
                    System.out.println(i + " - " + MainClass.getPlayer(i).getName());
                }

            }
            System.out.println("Who Proved Wrong?");
            initwhoProvedInput = input.nextLine();
            initwhoProvedInput.toLowerCase();
            break;
        }

        input.close();
        try {// if they responded with a int

            int initwhoProvedInputNum = Integer.parseInt(initwhoProvedInput);

            if (initwhoProvedInputNum == MainClass.getNumPlayers()) {
                return null;
            }

            for (Player initPlayer : MainClass.getPlayers()) {
                if (initPlayer.getNumID() == initwhoProvedInputNum) {
                    return initPlayer;
                }
            }

            System.out.println("Hmm...\nI couldn't find that player. please try again");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {

            }
        } catch (Exception e) {// if they responded with a string
            if (initwhoProvedInput.equals("no one")) {
                return null;
            }

            for (Player initPlayer : MainClass.getPlayers()) {
                if (initPlayer.getName().equals(initwhoProvedInput)) {
                    return initPlayer;
                }
            }
            System.out.println("Hmm...\nI couldn't find that player. please try again");

            try {
                Thread.sleep(1500);
            } catch (InterruptedException a) {

            }
        }

        return askWhoProved();
    }

    /**
     * 
     * @return the who card they guessed
     */
    private Card askWhoCard() {
        Scanner input = new Scanner(System.in);
        clearScreen();
        for (Card initCard : MainClass.getWhoCards()) {
            System.out.println(initCard.getID() + " - " + initCard.getName());
        }
        System.out.println("Who did they choose?");

        String getUserInputString = input.nextLine();
        input.close();
        try {
            int getUserInputInt = Integer.parseInt(getUserInputString);
            for (Card initCard : MainClass.getWhoCards()) {
                if (initCard.getID() == getUserInputInt) {
                    return initCard;
                }
            }
            System.out.println("Hmm...\nI can't find that person");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {

            }
            return askWhoCard();

        } catch (NumberFormatException e) {
            for (Card initCard : MainClass.getWhoCards()) {
                if (initCard.getName().equals(getUserInputString)) {
                    return initCard;
                }
            }
            System.out.println("Hmm...\nI can't find that person");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException a) {

            }
            return askWhoCard();
        }
    }

    /**
     * 
     * @return the what card that a player guessed on their turn
     */
    private Card askWhatCard() {
        Scanner input = new Scanner(System.in);
        clearScreen();
        for (Card initCard : MainClass.getWhatCards()) {
            System.out.println(initCard.getID() + " - " + initCard.getName());
        }
        System.out.println("What did they guess?");

        String initUserInputString = input.nextLine();
        input.close();

        initUserInputString.toLowerCase();

        try {
            int initUserInputInt = Integer.parseInt(initUserInputString);
            for (Card initCard : MainClass.getWhatCards()) {
                if (initCard.getID() == initUserInputInt) {
                    return initCard;
                }
            }
            System.out.println("Hmm...\nI can't find that thing.");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {

            }
            return askWhatCard();

        } catch (NumberFormatException e) {
            for (Card initCard : MainClass.getWhatCards()) {
                if (initCard.getName().equals(initUserInputString)) {
                    return initCard;
                }
            }
            System.out.println("Hmm...\nI can't find that thing.");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException a) {

            }
            return askWhatCard();
        }

    }

    /**
     * 
     * @return the room that the player was in
     */
    private Card askWhereCard() {
        Scanner input = new Scanner(System.in);
        clearScreen();
        for (Card initCard : MainClass.getWhereCards()) {
            System.out.println(initCard.getID() + " - " + initCard.getName());
        }
        String initCardNameString = input.nextLine();
        initCardNameString.toLowerCase();
        input.close();

        try {
            int initCardNameInt = Integer.parseInt(initCardNameString);
            for (Card initCard : MainClass.getWhereCards()) {
                if (initCard.getID() == initCardNameInt) {
                    return initCard;
                }
            }
            System.out.println("Hmm...\nI cant find that card");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {

            }
        } catch (Exception e) {
            for (Card initCard : MainClass.getWhereCards()) {
                if (initCard.getName().equals(initCardNameString)) {
                    return initCard;
                }
            }
            System.out.println("Hmm...\nI cant find that card");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException a) {

            }

        }
        return askWhereCard();
    }

    /**
     * 
     * @return true if they made to a room false if they didn't
     */
    private boolean didTheyMakeToRoom() {
        Scanner input = new Scanner(System.in);
        clearScreen();
        System.out.println("Did they make it to a room?\n0 - yes/true\n1 - no/false");

        String initUserInputString = input.nextLine();
        initUserInputString.toLowerCase();
        input.close();

        try {
            int inituserInputInt = Integer.parseInt(initUserInputString);
            if (inituserInputInt == 0) {
                return true;
            } else if (inituserInputInt == 1) {
                return false;
            } else {
                return didTheyMakeToRoom();
            }
        } catch (NumberFormatException e) {
            if (initUserInputString.equals("yes") || initUserInputString.equals("true")) {
                return true;
            } else if (initUserInputString.equals("no") || initUserInputString.equals("false")) {
                return false;
            } else {
                return didTheyMakeToRoom();
            }
        }

    }

    // TODO: make a logTurn Method
    // TODO: make a edit log method
    // TODO: make method in case player is stabbed or bleeds
    // TODO: add card shown method in case its your turn
    public void logTurn() {
        Player initWhosTurnPlayer = askWhosTurn();// whos turn it is
        Turn initTurn = null;
        while (!comfirmWhosTurn(initWhosTurnPlayer)) {
            initWhosTurnPlayer = askWhosTurn();
        }
        boolean initDidTheyMakeToRoom = didTheyMakeToRoom();// did they make it to a room
        if (!initDidTheyMakeToRoom) {// did they make it to a room
            Card initWhereGuessCard = askWhereCard();// where they guessed
            while (!confirmCard(initWhereGuessCard)) {
                initWhereGuessCard = askWhereCard();
            }
            Card initWhoGuessCard = askWhoCard();// who they guessed
            while (!confirmCard(initWhoGuessCard)) {
                initWhoGuessCard = askWhoCard();
            }
            Card initWhatGuessCard = askWhatCard();
            while (confirmCard(initWhatGuessCard)) {
                initWhatGuessCard = askWhatCard();
            }
            Player initwhoProvedWrongPlayer = askWhoProved();// who proved wrong
            while (!confirmWhoProved(initwhoProvedWrongPlayer)) {
                initwhoProvedWrongPlayer = askWhoProved();
            }
            Card initCardShown = null;
            // FIXME: add a card shown so that if its your turn.

            if (initCardShown != null) {
                initTurn = new Turn(initWhosTurnPlayer, initwhoProvedWrongPlayer, initWhoGuessCard, initWhatGuessCard,
                        initWhereGuessCard, initCardShown);
            } else {
                initTurn = new Turn(initWhosTurnPlayer, initwhoProvedWrongPlayer, initWhoGuessCard, initWhatGuessCard,
                        initWhereGuessCard);
            }
            finalConfirm(initTurn);
            MainClass.getLog().addTurn(initTurn);// add a confirm for if they didnt make it to a room
        } else {

        }

    }
}