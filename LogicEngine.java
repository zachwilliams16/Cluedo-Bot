import java.util.ArrayList;

public class LogicEngine {
    // TODO: go through all players and see if they could only have one card from
    // cards tehy might have proved wrong when they did prove wrong with that card.
    // goes off of
    // playerdidprovewrong

    /**
     * 
     * @param initTurn the turn that this method is finding all teh players who
     *                 couldnt prove
     * @return the players who didnt prove
     */
    private static ArrayList<Player> getDidntProve(Turn initTurn) {
        ArrayList<Player> initplayers = new ArrayList<Player>();

        int temp = initTurn.getWhosTurn().getNumID();
        while (true) {
            temp++;
            if (temp >= MainClass.getNumPlayers()) {
                temp = 0;
            }
            if (temp == initTurn.getWhosTurn().getNumID()) {
                break;
            }
            initplayers.add(MainClass.getPlayer(temp));

        }

        return initplayers;
    }

    /**
     * goes through all players and sees if any players have a full hand and will
     * add all other cards not in hand to cards they cant have
     * 
     * @return true if something changed or false if something didnt
     */
    public static boolean playerHasAllCards() {
        boolean initSomethingChanged = false;
        for (Player initPlayer : MainClass.getPlayers()) {// for all players
            if (initPlayer.getHandSize() >= MainClass.getNumCardsInHand()) {// if player has full hand

                for (Card initCard : MainClass.getWhoCards()) {// for all who cards if not in hand add to not cards
                    boolean cardInHand = false;
                    for (Card myCard : initPlayer.getHand()) {
                        if (myCard.equals(initCard)) {
                            cardInHand = true;
                            break;
                        }
                    }
                    if (!cardInHand) {
                        initCard.addDefDoesnt(initPlayer);
                        initSomethingChanged = true;
                    }
                }

                for (Card initCard : MainClass.getWhatCards()) {// for all what cards if not in hand add to not cards
                    boolean cardInHand = false;
                    for (Card myCard : initPlayer.getHand()) {
                        if (myCard.equals(initCard)) {
                            cardInHand = true;
                            break;
                        }
                    }
                    if (!cardInHand) {
                        initCard.addDefDoesnt(initPlayer);
                        initSomethingChanged = true;
                    }
                }

                for (Card initCard : MainClass.getWhereCards()) {// for all where cards if not in hand add to not cards
                    boolean cardInHand = false;
                    for (Card myCard : initPlayer.getHand()) {
                        if (myCard.equals(initCard)) {
                            cardInHand = true;
                            break;
                        }
                    }
                    if (!cardInHand) {
                        initCard.addDefDoesnt(initPlayer);
                        initSomethingChanged = true;
                    }
                }
            }
        }
        return initSomethingChanged;
    }

    /**
     * if a player didnt prove wrong then those cards are added to the cards they
     * cant have
     * 
     * @return true if something chnaged or false if something wasnt changed
     */
    public static boolean playerDidntProveWrong() {
        boolean initSomethingChanged = false;
        for (Turn initTurn : Log.getTurns()) {

            if (initTurn.getMadeToRoom()) {
                for (Player initPlayer : getDidntProve(initTurn)) {
                    initTurn.getWhoGuess().addDefDoesnt(initPlayer);
                    initTurn.getWhatGuess().addDefDoesnt(initPlayer);
                    initTurn.getWhereGuess().addDefDoesnt(initPlayer);
                    initSomethingChanged = true;
                }
            }
        }
        return initSomethingChanged;
    }

    /**
     * if a player did prove wrong that set of cards are added to their maybe cards
     * list
     * 
     * @return true if something changed
     */
    public static boolean playerDidProveWrong() {
        boolean initSomethingChanged = false;
        for (Turn initTurn : Log.getTurns()) {
            if (initTurn.getMadeToRoom()) {
                if (initTurn.getWhoProved() != null) {
                    ArrayList<Card> initCards = new ArrayList<Card>();
                    initCards.add(initTurn.getWhoGuess());
                    initCards.add(initTurn.getWhereGuess());
                    initCards.add(initTurn.getCardShown());
                    initTurn.getWhoProved().addMaybeCards(initCards);
                    initSomethingChanged = true;
                }

            }

        }
        return initSomethingChanged;
    }

    /**
     * if a player owns this card then no one else can own this card for all turns
     * 
     * @return true if something changed or false if nothing changed
     */
    public static boolean playerOwnsCard() {
        boolean initSomethingChanged = false;
        for (Card initCard : MainClass.getWhoCards()) {
            if (initCard.getOwner() != null) {
                for (Player initPlayer : MainClass.getPlayers()) {
                    if (!initPlayer.equals(initCard.getOwner())) {
                        initCard.addDefDoesnt(initPlayer);
                        initSomethingChanged = true;
                    }
                }
            }
        }
        return initSomethingChanged;
    }

    /**
     * if a final guess if known (eg: we know the person, place or thing) then if a
     * person is the only person who could have it (eg: only player who might have
     * it (maybe list) and all other players dont have this card (defDoesnt list))
     * then that player has to have that card
     * 
     * @return true if something changed false if nothing changed
     */
    public static boolean onlyPlayerPossible() {
        boolean initSomethingChanged = false;

        boolean whoCardGuess = false;
        ArrayList<Card> initWhoCards = new ArrayList<Card>();

        boolean whatCardGuess = false;
        ArrayList<Card> initWhatCards = new ArrayList<Card>();

        boolean whereCardGuess = false;
        ArrayList<Card> initWhereCards = new ArrayList<Card>();

        for (Card initCard : MainClass.getWhoCards()) {
            if (initCard.getDefDoesnt().size() == MainClass.getNumPlayers()) {
                whoCardGuess = true;
            } else if (initCard.getDefDoesnt().size() == MainClass.getNumPlayers() - 1) {
                initWhoCards.add(initCard);
                initSomethingChanged = true;
            }
        }
        if (whoCardGuess) {
            for (Card initCard : initWhoCards) {
                initCard.setOwner(initCard.getMaybe().get(0));
            }
        }

        for (Card initCard : MainClass.getWhatCards()) {
            if (initCard.getDefDoesnt().size() == MainClass.getNumPlayers()) {
                whatCardGuess = true;
            } else if (initCard.getDefDoesnt().size() == MainClass.getNumPlayers() - 1) {
                initWhatCards.add(initCard);
                initSomethingChanged = true;
            }
        }
        if (whatCardGuess) {
            for (Card initCard : initWhatCards) {
                initCard.setOwner(initCard.getMaybe().get(0));
            }
        }

        for (Card initCard : MainClass.getWhereCards()) {
            if (initCard.getDefDoesnt().size() == MainClass.getNumPlayers()) {
                whereCardGuess = true;
            } else if (initCard.getDefDoesnt().size() == MainClass.getNumPlayers() - 1) {
                initWhereCards.add(initCard);
                initSomethingChanged = true;
            }
        }
        if (whereCardGuess) {
            for (Card initCard : initWhereCards) {
                initCard.setOwner(initCard.getMaybe().get(0));
            }
        }

        return initSomethingChanged;
    }

}
