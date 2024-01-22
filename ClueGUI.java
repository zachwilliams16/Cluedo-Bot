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
        ArrayList<Turn> initTurns = Log.getTurns();
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

    /**
     * 
     * @param initCard card to be confirmed
     * @return if that is the card they guessed (true if it is or false if it isn't)
     */
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

    /**
     * 
     * @param initCard the card that was shown
     * @return if that card was the card that was shown (true if it is or false if
     *         it isn't)
     */
    private boolean confirmCardShown(Card initCard) {
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println(initCard.getName() + " was shown?\n0 - yes\n1 - no");

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

    /**
     * 
     * @return the card that was shown
     */
    private Card getCardShown() {
        clearScreen();
        Scanner input = new Scanner(System.in);
        System.out.println("Were you shown a card?\n0 - yes\n1 - no");

        boolean shownCard = false;
        String userAnswerInputString = input.nextLine();
        userAnswerInputString.toLowerCase();
        input.close();
        try {// if they gave a number
            int userAnswerInputInt = Integer.parseInt(userAnswerInputString);
            if (userAnswerInputInt == 0) {
                shownCard = true;
            } else if (userAnswerInputInt == 1) {

            } else {
                System.out.println("Hmm...\nI couldn't find that answer");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {

                }
                return getCardShown();
            }
        } catch (Exception e) {// if they gave a string
            if (userAnswerInputString.equals("yes")) {
                shownCard = true;
            } else if (userAnswerInputString.equals("no")) {

            } else {
                System.out.println("Hmm...\nI couldn't find that answer");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException a) {

                }
                return getCardShown();
            }
        }

        if (!shownCard) {// if they were not shown a card
            input.close();
            return null;
        }

        clearScreen();
        for (int i = 0; i < MainClass.getAllCards().size(); i++) {
            System.out.println(i + " - " + MainClass.getOneCard(i));
        }

        System.out.println("Which card did you see?");
        userAnswerInputString = input.nextLine();
        userAnswerInputString.toLowerCase();
        input.close();

        try {// if they responded with a int
            int userAnswerInputInt = Integer.parseInt(userAnswerInputString);
            if (MainClass.getOneCard(userAnswerInputInt) != null) {
                return MainClass.getOneCard(userAnswerInputInt);
            } else {

                System.out.println("Hmm...\nI couldn't find that card.");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {

                }
                return getCardShown();
            }

        } catch (Exception e) {// if they responded with a string
            for (Card initCard : MainClass.getAllCards()) {
                if (initCard.getName().equals(userAnswerInputString)) {
                    return initCard;// finish else
                }
            }
            System.out.println("Hmm...\nI couldn't find that card.");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException a) {

            }
            return getCardShown();
        }
    }

    // TODO: make a edit log method
    // TODO: make method in case player is stabbed or bleeds
    public void logTurn() {

        Player initWhosTurnPlayer = askWhosTurn();// whos turn it is
        while (!comfirmWhosTurn(initWhosTurnPlayer)) {
            initWhosTurnPlayer = askWhosTurn();
        }

        boolean initDidTheyMakeToRoom = didTheyMakeToRoom();// did they make it to a room

        Turn initTurn = null;

        if (!initDidTheyMakeToRoom) {// did they make it to a room

            Card initWhereGuessCard = askWhereCard();// where they guessed
            while (!confirmCard(initWhereGuessCard)) {
                initWhereGuessCard = askWhereCard();
            }

            Card initWhoGuessCard = askWhoCard();// who they guessed
            while (!confirmCard(initWhoGuessCard)) {
                initWhoGuessCard = askWhoCard();
            }

            Card initWhatGuessCard = askWhatCard();// what they guessed
            while (confirmCard(initWhatGuessCard)) {
                initWhatGuessCard = askWhatCard();
            }

            Player initwhoProvedWrongPlayer = askWhoProved();// who proved wrong
            while (!confirmWhoProved(initwhoProvedWrongPlayer)) {
                initwhoProvedWrongPlayer = askWhoProved();
            }

            Card initCardShown = getCardShown();// what was the card shown
            while (confirmCardShown(initCardShown)) {
                initCardShown = getCardShown();
            }

            if (initCardShown != null) {// if they were shown a card
                initTurn = new Turn(initWhosTurnPlayer, initwhoProvedWrongPlayer, initWhoGuessCard, initWhatGuessCard,
                        initWhereGuessCard, initCardShown);
            } else {// if they weren't shown a card
                initTurn = new Turn(initWhosTurnPlayer, initwhoProvedWrongPlayer, initWhoGuessCard, initWhatGuessCard,
                        initWhereGuessCard);
            }

            Log.addTurn(initTurn);
        } else {
            Log.addTurn(new Turn(initWhosTurnPlayer));
        }

    }

    public void mainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("1 - Log a new turn");
        System.out.println("2 - Add peice of information - Not Working right now");
        System.out.println("3 - View log");
        System.out.println("Which number would you like to do?\n");
        int initUserInput = input.nextInt();

        if (initUserInput == 1) {
            clearScreen();
            System.out.println("You want to log a turn?\n0 - yes\n1 - no");

            String initInputLogTurnString = input.nextLine();
            initInputLogTurnString.toLowerCase();
            input.close();

            // This try-catch takes the input and finds what the user wanted to do
            try {
                int initInputLogTurnInt = Integer.parseInt(initInputLogTurnString);
                if (initInputLogTurnInt == 0) {
                    logTurn();
                } else if (initInputLogTurnInt == 1) {
                    return;
                }

            } catch (NumberFormatException e) {
                if (initInputLogTurnString.equals("yes")) {
                    logTurn();
                } else if (initInputLogTurnString.equals("no")) {
                    return;
                } else {
                    System.out.println("Hmm, I couldnt find a action for that answer.");
                }
            }
        } else if (initUserInput == 2) {
            System.out.println("Sorry this isnt working right now");
        } else if(initUserInput == 3){
            System.out.println("Sorry this isnt working right now");
        } else{
            System.out.println("I couldnt find a action for that");
        }
    }
}