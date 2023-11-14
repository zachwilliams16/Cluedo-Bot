import java.util.ArrayList;

public class Log {

    private ArrayList<Turn> gameLog = new ArrayList<Turn>();

    public Log() {

    }

    /**
     * 
     * @return the list of turns that have happened in the game
     */
    public ArrayList<Turn> getTurns() {
        return gameLog;
    }

    /**
     * For if they did make it to a room
     * give null for whoproved if noboody did
     * 
     * @param initWhoTurn        whos turn it is
     * @param initwhoProvedWrong who proved them wrong. null if none did
     * @param initWhoGuess       who they guessed
     * @param initWhatGuess      what they guessed
     * @param initWhereGuess     where they guessed
     */
    public void logTurn(Player initWhoTurn, Player initwhoProvedWrong, Card initWhoGuess, Card initWhatGuess,
            Card initWhereGuess) {
        gameLog.add(new Turn(initWhoTurn, initwhoProvedWrong, initWhoGuess, initWhatGuess, initWhereGuess));
    }

    public void logTurn(Player initWhoTurn, Player initwhoProvedWrong, Card initWhoGuess, Card initWhatGuess,
            Card initWhereGuess, Card cardShown) {
        gameLog.add(new Turn(initWhoTurn, initwhoProvedWrong, initWhoGuess, initWhatGuess, initWhereGuess));
    }

    /**
     * for if they didnt make it to room
     * 
     * @param initWhoTurn whos turn it is
     */
    public void logTurn(Player initWhoTurn) {
        gameLog.add(new Turn(initWhoTurn));
    }

    /**
     * 
     * @return the full game log
     */
    public ArrayList<Turn> returnGameLog() {
        return gameLog;
    }

}

class Turn {
    private Player whosTurn;
    private Player whoProvedWrong;

    private Card whoGuess;
    private Card whatGuess;
    private Card whereGuess;

    private boolean madeToRoom;

    boolean cardShownIsKnown;
    private Card cardShown;

    /**
     * For if they did make it to a room
     * give null for whoproved if noboody did
     * 
     * @param initWhoTurn        whos turn it is
     * @param initwhoProvedWrong who proved them wrong. null if none did
     * @param initWhoGuess       who they guessed
     * @param initWhatGuess      what they guessed
     * @param initWhereGuess     where they guessed
     */
    public Turn(Player initWhoTurn, Player initwhoProvedWrong, Card initWhoGuess, Card initWhatGuess,
            Card initWhereGuess) {
        whosTurn = initWhoTurn;
        whoProvedWrong = initwhoProvedWrong;
        whoGuess = initWhoGuess;
        whatGuess = initWhatGuess;
        whereGuess = initWhereGuess;
        madeToRoom = true;
        cardShownIsKnown = false;

    }

    /**
     * for if they didnt make it to room
     * 
     * @param initWhoTurn whos turn it is
     */
    public Turn(Player initWhoTurn) {
        whosTurn = initWhoTurn;
        madeToRoom = false;
    }

    /**
     * For if they did make it to a room and card shown is known (eg: they know what
     * card was shown/card shown was flashed)
     * give null for whoproved if noboody did
     * 
     * @param initWhoTurn        whos turn it is
     * @param initwhoProvedWrong who proved them wrong. null if none did
     * @param initWhoGuess       who they guessed
     * @param initWhatGuess      what they guessed
     * @param initWhereGuess     where they guessed
     * @param initcardShown      the card shown to prove wrong
     */
    public Turn(Player initWhoTurn, Player initwhoProvedWrong, Card initWhoGuess, Card initWhatGuess,
            Card initWhereGuess, Card initcardShown) {
        whosTurn = initWhoTurn;
        whoProvedWrong = initwhoProvedWrong;
        whoGuess = initWhoGuess;
        whatGuess = initWhatGuess;
        whereGuess = initWhereGuess;
        madeToRoom = true;

        cardShown = initcardShown;
        cardShownIsKnown = true;
    }

    /**
     * 
     * @return the card that was shown or null if card shown is not known
     */
    public Card getCardShown() {
        if (cardShownIsKnown) {
            return cardShown;
        }
        return null;
    }

    /**
     * 
     * @return if the shown card is known
     */
    public boolean getCardShownIsKnown() {
        return cardShownIsKnown;

    }

    /**
     * 
     * @return whos turn it was this turn
     */
    public Player getWhosTurn() {
        return whosTurn;
    }

    /**
     * 
     * @return who proved who evers turn it was wrong or null if nobody did
     */
    public Player getWhoProved() {
        return whoProvedWrong;
    }

    /**
     * 
     * @return who they guessed
     */
    public Card getWhoGuess() {
        return whoGuess;
    }

    /**
     * 
     * @return what they guessed
     */
    public Card getWhatGuess() {
        return whatGuess;
    }

    /**
     * 
     * @return where they guessed
     */
    public Card getWhereGuess() {
        return whereGuess;
    }

    /**
     * 
     * @return if they made it to a room or not
     */
    public boolean getMadeToRoom() {
        return madeToRoom;
    }

    public String toString() {
        String returnString = "";
        if (madeToRoom) {
            returnString += "It was " + whosTurn + "'s and they guessed " + whoGuess.getName() + " with the "
                    + whatGuess + " in the " + whereGuess + ".";
        } else {
            returnString += whosTurn + " didn't make it to a room.";
        }

        return returnString;
    }

    /**
     * sets whos turn it was to a new player
     * 
     * @param initPlayer the new players turn for this turn
     */
    public void setWhosTurn(Player initPlayer) {
        whosTurn = initPlayer;
    }

    /**
     * sets who proved them wrong
     * 
     * @param initPlayer the player who proved them wrong
     */
    public void setWhoProved(Player initPlayer) {
        whoProvedWrong = initPlayer;
    }

    /**
     * nobody proved wrong this turn so it is set to null
     */
    public void setWhoProved() {
        whoProvedWrong = null;
    }

    /**
     * sets who they guessed
     * 
     * @param initCard who they guessed that turn
     */
    public void setWhoGuess(Card initCard) {
        whoGuess = initCard;
    }

    /**
     * sets what they guessed
     * 
     * @param initCard what they guessed that turn
     */
    public void setWhatGuess(Card initCard) {
        whatGuess = initCard;
    }

    /**
     * sets where they guessed
     * 
     * @param initCard where they guessed that turn
     */
    public void setWhereGuess(Card initCard) {
        whereGuess = initCard;
    }
}
