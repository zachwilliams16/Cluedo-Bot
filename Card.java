import java.util.ArrayList;

public class Card {
    static int numCards = 0;

    int ID;

    int type; // 0, 1, 2 for who, what, where
    String name; // The name of the card

    String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";// list of of possible player id's

    Player owner = null;// a player definitly does have this card represetned with O
    ArrayList<Player> defDoesnt = new ArrayList<Player>();// A player definitly doesnt have this card represented with /
    ArrayList<Player> maybe = new ArrayList<Player>();// A player might have this card represented with ?

    /**
     * Constructor
     * 
     * @param initName       Name of the card
     * @param initType       Type of the card - 0, 1, 2 for who, what, where
     * @param initNumPlayers Number of players
     */
    public Card(String initName, int initType) {
        type = initType;
        name = initName;

        ID = numCards;
        numCards++;
    }

    /**
     * 
     * @return the ID of this card
     */
    public int getID() {
        return ID;
    }

    public String toString() {
        String toString = "";
        // if a player has this card
        if (owner == null) {
            toString += "|";
        } else {
            toString += owner.getletterID();
        }

        // adds the card name
        if (name.length() % 2 == 1) {
            int length = name.length() - 1;
            length = 24 - length;
            length /= 2;
            for (int i = 0; i < length; i++) {
                toString += " ";
            }
            toString += name;
            for (int i = 0; i < length - 1; i++) {
                toString += " ";
            }
        } else {
            int length = name.length();
            length = 24 - length;
            length /= 2;
            for (int i = 0; i < length; i++) {
                toString += " ";
            }
            toString += name;
            for (int i = 0; i < length; i++) {
                toString += " ";
            }
        }

        toString += "| ";
        toString += findPlayerSymbols();
        toString += "|";
        return toString;
    }

    /**
     * @return all the player symbols as a string for the toString method
     * 
     */
    private String findPlayerSymbols() {
        String returnString = "";

        for (int i = 0; i < MainClass.getNumPlayers(); i++) {// player symbols
            boolean haveinfo = false;

            if (owner != null) {// if a player does have this card all other players dont have this card
                for (int a = 0; a < MainClass.getNumPlayers(); a++) {
                    if (String.valueOf(letters.charAt(a)).equals(owner.getletterID())) {
                        returnString += "O ";
                    } else {
                        returnString += "X ";
                    }
                }
                break;
            }

            for (int a = 0; a < defDoesnt.size(); a++) {// goes through defdoesnt and sees if player i is there
                if (String.valueOf(letters.charAt(i)).equals(defDoesnt.get(a).getletterID())) {
                    returnString += "X ";
                    haveinfo = true;
                    break;
                }
            }

            for (int a = 0; a < maybe.size(); a++) {// goes through maybe and sees if player i is there
                if (String.valueOf(letters.charAt(i)).equals(maybe.get(a).getletterID())) {
                    returnString += "? ";
                    haveinfo = true;
                    break;
                }
            }

            if (!haveinfo) {// if there isnt any information on a player
                returnString += ". ";
            }
        }

        int temp = 6 - MainClass.getNumPlayers();
        for (int i = 0; i < temp; i++) {// players who arent here get a blank space
            returnString += "  ";
        }

        return returnString;
    }

    /**
     * 
     * @return the list of players who dont have this
     */
    public ArrayList<Player> getDefDoesnt() {
        return defDoesnt;
    }

    /**
     * 
     * @return the players who might have this card
     */
    public ArrayList<Player> getMaybe() {
        return maybe;
    }

    /**
     * 
     * @return name of the card
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param initOwner The owner of a card is set to this player
     */
    public void setOwner(Player initOwner) {
        owner = initOwner;
        initOwner.addCard(this);
        for (int i = 0; i < defDoesnt.size(); i++) {// removes if it is in defdoesnt
            if (defDoesnt.get(i).getletterID().equals(initOwner.getletterID())) {
                defDoesnt.remove(i);
                break;
            }
        }
        for (int i = 0; i < maybe.size(); i++) {
            if (maybe.get(i).getletterID().equals(initOwner.getletterID())) {
                maybe.remove(i);
                break;
            }
        }

    }

    public Player getOwner() {
        return owner;
    }

    /**
     * adds a player to defdoesnt which means they dont own this card, also removes
     * them from maybe and defdeos if they are in those
     * 
     * @param initNotOwner the player who definetly doesnt own this card
     */
    public void addDefDoesnt(Player initNotOwner) {

        if (owner != null) {// if initNotOwner owns it they now dont own it
            if (owner.getletterID().equals(initNotOwner.getletterID())) {
                owner.removeCard(this);
                owner = null;
            }
        }

        for (int i = 0; i < maybe.size(); i++) {// removes it if it is in maybe
            if (maybe.get(i).getletterID().equals(initNotOwner.getletterID())) {
                maybe.remove(i);
                break;
            }
        }

        boolean init = false;
        for (int i = 0; i < defDoesnt.size(); i++) {// checks to see if it is allready in defdoesnt (prevents
                                                    // duplicates)
            if (defDoesnt.get(i).getletterID().equals(initNotOwner.getletterID())) {
                init = true;
            }
        }
        if (!init) {
            defDoesnt.add(initNotOwner);
        }

    }

    /**
     * adds a player to maybe having this card
     * 
     * @param initMaybe the player who might have this card
     */
    public void addMaybe(Player initMaybe) {
        if (owner != null) {// if owneris not null
            if (initMaybe.getletterID().equals(owner.getletterID())) {// checks if it is in defDoes
                return;
            }
        }

        for (int i = 0; i < defDoesnt.size(); i++) {// checks if it is already in defdoesnt
            if (defDoesnt.get(i).getletterID().equals(initMaybe.getletterID())) {
                return;
            }
        }
        for (int i = 0; i < maybe.size(); i++) {// checks if it is already in maybe
            if (maybe.get(i).getletterID().equals(initMaybe.getletterID())) {
                return;
            }
        }
        maybe.add(initMaybe);
    }

    /**
     * 
     * @param initCard other card being tested
     * @return true if this card equals initcard false if it doesnt
     */
    public boolean equals(Card initCard) {
        if (initCard.getID() == ID) {
            return true;
        }
        return false;
    }
}
