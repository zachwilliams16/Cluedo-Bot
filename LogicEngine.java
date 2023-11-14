public class LogicEngine {

    // TODO: if a player didnt prove wrong then they definitly dont have any of
    // these cards
    // TODO: if a player did prove wrong they they have to have at least 1 of those
    // cards
    // TODO: if a player couldnt have a card because someone else has that card
    // TODO: if a player is the only person who could have this card and we already
    // know what type of card of that type did (eg it was the weapon and this is a
    // weapon they have to have it);
    // TODO: if a player owns a card then nobody else does
    // TODO: reset all variables exept log so that there isnt any wrong things (if
    // player adds information)
    // TODO: find players who couldnt prove on a turn (private method)

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

                for (Card card : MainClass.getWhatCards()) {// for all who cards if not in hand add to not cards
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

                for (Card card : MainClass.getWhereCards()) {// for all who cards if not in hand add to not cards
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
}
