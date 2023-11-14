import java.util.ArrayList;

public class LogicEngine {
    // TODO: go through all players and see if they could only have one card from
    // cards tehy might have proved wrong when they did prove wrong. goes off of
    // playerdidprovewrong
    // TODO: if a player couldnt have a card because someone else has that card
    // TODO: if a player is the only person who could have this card and we already
    // know what type of card of that type did (eg it was the weapon and this is a
    // weapon they have to have it);
    // TODO: if a player owns a card then nobody else does
    // TODO: reset all variables exept log so that there isnt any wrong things (if
    // player adds information)
    // TODO: find players who couldnt prove on a turn (private method)

    private ArrayList<Player> getDidntProve(Turn initTurn) {
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
     * - goes through all players and will find the ones that have all their cards
     * and
     * will make all other cards not have that player on the defdoesnt list
     */
    public void playerHasAllCards() {

        for (Player player : MainClass.getPlayers()) {// for all players
            if (player.getHandSize() >= MainClass.getNumCardsInHand()) {// if player has full hand

                for (Card card : MainClass.getWhoCards()) {// for all who cards if not in hand add to not cards
                    boolean cardInHand = false;
                    for (Card myCard : player.getHand()) {
                        if (myCard.equals(card)) {
                            cardInHand = true;
                            break;
                        }
                    }
                    if (!cardInHand) {
                        card.addDefDoesnt(player);
                    }
                }

                for (Card card : MainClass.getWhatCards()) {// for all what cards if not in hand add to not cards
                    boolean cardInHand = false;
                    for (Card myCard : player.getHand()) {
                        if (myCard.equals(card)) {
                            cardInHand = true;
                            break;
                        }
                    }
                    if (!cardInHand) {
                        card.addDefDoesnt(player);
                    }
                }

                for (Card card : MainClass.getWhereCards()) {// for all where cards if not in hand add to not cards
                    boolean cardInHand = false;
                    for (Card myCard : player.getHand()) {
                        if (myCard.equals(card)) {
                            cardInHand = true;
                            break;
                        }
                    }
                    if (!cardInHand) {
                        card.addDefDoesnt(player);
                    }
                }
            }
        }
    }

    /**
     * goes through entire log and every time someone didnt prove wrong then they
     */
    public void playerDidntProveWrong() {
        for (Turn turn : MainClass.getLog().getTurns()) {

            if (turn.getMadeToRoom()) {
                for (Player player : getDidntProve(turn)) {
                    turn.getWhoGuess().addDefDoesnt(player);
                    turn.getWhatGuess().addDefDoesnt(player);
                    turn.getWhereGuess().addDefDoesnt(player);
                }
            }
        }
    }

    /**
     * adds cards that player might of had
     */
    public void playerDidProveWrong() {
        for (Turn turn : MainClass.getLog().getTurns()) {
            if (turn.getMadeToRoom()) {
                if (turn.getWhoProved() != null) {
                    ArrayList<Card> initCards = new ArrayList<Card>();
                    initCards.add(turn.getWhoGuess());
                    initCards.add(turn.getWhereGuess());
                    initCards.add(turn.getCardShown());
                    turn.getWhoProved().addMaybeCards(initCards);
                }

            }

        }
    }
}
