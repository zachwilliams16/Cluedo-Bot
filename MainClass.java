import java.util.ArrayList;
import java.util.Arrays;

public class MainClass {

    private static int numPlayers;
    private static ArrayList<Player> activePlayers = new ArrayList<Player>();

    private static ArrayList<Card> whoCards = new ArrayList<Card>();
    private static ArrayList<Card> whatCards = new ArrayList<Card>();
    private static ArrayList<Card> whereCards = new ArrayList<Card>();
    private static int CardsInHand;// number of cards in each persons hand

    private static Log gameLog = new Log();



    
    /**
     * 
     * @return the who cards
     */
    public static ArrayList<Card> getWhoCards() {
        return whoCards;
    }

    /**
     * 
     * @return the what cards
     */
    public static ArrayList<Card> getWhatCards() {
        return whatCards;
    }

    /**
     * 
     * @return the where cards
     */
    public static ArrayList<Card> getWhereCards() {
        return whereCards;
    }

    /**
     * 
     * @return the number of players
     */
    public static int getNumPlayers() {
        return numPlayers;
    }

    /**
     * makes all the cards.
     * change the cards based on who is playing
     */
    private static void makeCards() {
        ArrayList<String> who = new ArrayList<>(Arrays.asList("Miss Scarlett", "Colonel Mustard", "Mrs. White",
                "Reverend Green", "Mrs. Peacock", "Professor Plum"));

        ArrayList<String> what = new ArrayList<>(
                Arrays.asList("Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Wrench"));

        ArrayList<String> where = new ArrayList<>(Arrays.asList("Kitchen", "Ballroom", "Conservatory", "Dining Room",
                "Billiard Room", "Library", "Lounge", "Hall", "Study"));

        for (int i = 0; i < who.size(); i++) {
            whoCards.add(new Card(who.get(i), 0));
        }
        for (int i = 0; i < what.size(); i++) {
            whatCards.add(new Card(what.get(i), 1));
        }
        for (int i = 0; i < where.size(); i++) {
            whereCards.add(new Card(where.get(i), 2));
        }

    }

    /**
     * 
     * @return number of cards in each persons hand
     */
    public static int getNumCardsInHand() {
        return CardsInHand;
    }

    /**
     * creates all players and sets num players to number of players
     * 
     * @param initplayerNames names of the players to be made
     */
    private static void makePlayers(ArrayList<String> initplayerNames) {
        for (int i = 0; i < initplayerNames.size(); i++) {
            activePlayers.add(new Player(initplayerNames.get(i)));
        }

        numPlayers = initplayerNames.size();
    }

    /**
     * 
     * @return list of all active players
     */
    public static ArrayList<Player> getPlayers() {
        return activePlayers;
    }

    /**
     * players are in order according to id and letterID
     * 
     * @param idx index of player
     * @return the player at index idx,
     *         if player doesnt exist at that index null is returned
     */
    public static Player getPlayer(int idx) {
        try {
            return activePlayers.get(idx);
        } catch (Exception e) {
            return null;// TODO: test get player methods
        }
    }
       
    

    /**
     * 
     * @return the game log
     */
    public static Log getLog() {
        return gameLog;
    }

    public static void main(String[] args) {
        while (true) {// setup change variables as needed for game
            makePlayers(new ArrayList<String>(
                    Arrays.asList(
                            "Zach",
                            "Andrea",
                            "Josh",
                            "Katie",
                            "Kristy",
                            "Lucy"))); // change the names in list
                                       // to all active players

            CardsInHand = 3;// number of cards in hand
            makeCards();// makes the cards change the lists in the function to match what is in the game

            break;
        }

        ClueGUI mainGUI = new ClueGUI();

        whoCards.get(0).setOwner(activePlayers.get(0));
        whoCards.get(1).setOwner(activePlayers.get(0));
        whoCards.get(2).setOwner(activePlayers.get(0));
        mainGUI.printsheet();
    }
}
