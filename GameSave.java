import java.util.ArrayList;

public class GameSave {
    private ArrayList<Turn> gameLog;
    ArrayList<Card> whoCards;
    ArrayList<Card> whatCards;
    ArrayList<Card> whereCards;
    ArrayList<Player> activePlayers;

    public GameSave() {
        gameLog = Log.getTurns();
        whoCards = MainClass.getWhoCards();
        whatCards = MainClass.getWhatCards();
        whereCards = MainClass.getWhereCards();
        activePlayers = MainClass.getPlayers();
        // save game here
    }

    public void loadGame() {
        Log.setGameLog(gameLog);
        MainClass.setWhoCards(whoCards);
        MainClass.setWhatCards(whatCards);
        MainClass.setWhereCards(whereCards);
        MainClass.setActivePlayers(activePlayers);
    }
}