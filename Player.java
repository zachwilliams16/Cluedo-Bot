import java.util.ArrayList;

public class Player {
    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static int totalIDs = 0; // count of all active players

    int id;
    String letterID;
    String name;

    ArrayList<Card> myHand = new ArrayList<Card>();
    ArrayList<ArrayList<Card>> maybeCards = new ArrayList<ArrayList<Card>>();// stores in list of three because if they
                                                                             // proved wrong then they have at least one
                                                                             // of these cards they have

    /**
     * 
     * @param initName the name of the player
     */
    public Player(String initName) {
        id = totalIDs;
        totalIDs++;
        letterID = String.valueOf(letters.charAt(id));
        name = initName;
    }

    /**
     * 
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return the letter id of this player
     */
    public String getletterID() {
        return letterID;
    }

    /**
     * 
     * @return the int id of the player
     */
    public int getNumID() {
        return id;
    }

    /**
     * 
     * @return Number of active players
     */
    public int getNumPlayers() {
        return totalIDs;
    }

    /**
     * 
     * @param initplayer player to be tested against self
     * @return if initplayer equals this player
     */
    public boolean equals(Player initplayer) {
        if (letterID.equals(initplayer.getletterID())) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @return the players hand
     */
    public ArrayList<Card> getHand() {
        return myHand;
    }

    /**
     * 
     * @return size of players hand
     */
    public int getHandSize() {
        return myHand.size();
    }

    /**
     * adds card to hand if it is not already in hand
     * 
     * @param initCard the card being added to players hand
     */
    public void addCard(Card initCard) {
        for (int i = 0; i < myHand.size(); i++) {
            if (initCard.getID() == myHand.get(i).getID()) {
                return;
            }
        }
        myHand.add(initCard);
    }

    public void removeCard(Card initCard) {
        for (int i = 0; i < myHand.size(); i++) {
            if (myHand.get(i).getID() == initCard.getID()) {
                myHand.remove(i);
            }
        }
    }

}
