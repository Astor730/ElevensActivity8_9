import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard extends Board {

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 9;

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS =
            {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS =
            {"spades", "hearts", "diamonds", "clubs"};

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    /**
     * Flag used to control debugging print statements.
     */
    private static final boolean I_AM_DEBUGGING = false;


    /**
     * Creates a new <code>ElevensBoard</code> instance.
     */
    public ElevensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
    }

    /**
     * Determines if the selected cards form a valid group for removal.
     * In Elevens, the legal groups are (1) a pair of non-face cards
     * whose values add to 11, and (2) a group of three cards consisting of
     * a jack, a queen, and a king in some order.
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     *         false otherwise.
     */
    @Override
    public boolean isLegal(List<Integer> selectedCards)
    {
		if(selectedCards.size()>3|| selectedCards.size()<2)
        {
            return false;
        }
        if(selectedCards.size()== 3)
        {
            boolean re= containsJQK(selectedCards);
            return re;
        }
        if(selectedCards.size()==2)
        {
            boolean rere =containsPairSum11(selectedCards);
            return rere;
        }
       return false;
    }

    /**
     * Determine if there are any legal plays left on the board.
     * In Elevens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 11, or (2) a group
     * of three cards consisting of a jack, a queen, and a king in some order.
     * @return true if there is a legal play left on the board;
     *         false otherwise.
     */
    @Override
    public boolean anotherPlayIsPossible()
    {
        int j = 0;
        int q = 0;
        int k = 0;
        int move=0;
        for(int i=0; i<BOARD_SIZE;i++)
        {
            if(cardAt(i).pointValue()== 11)
            {
                j++;
            }
            if(cardAt(i).pointValue()==12)
            {
                q++;
            }
            if(cardAt(i).pointValue()==13)
            {
                k++;
            }
            for(int x=0;x<BOARD_SIZE;x++)
            {
                if(cardAt(i).pointValue()+cardAt(x).pointValue()==11)
                {
                    move++;
                }
            }
        }
        if(j>0&&q>0&&k>0)
        {
            return true;
        }
        else if(move>0)
        {
            return true;
        }

        return false;
    }

    /**
     * Check for an 11-pair in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 11-pair.
     * @return true if the board entries in selectedCards
     *              contain an 11-pair; false otherwise.
     */
    private boolean containsPairSum11(List<Integer> selectedCards)
    {
        Card card1 = cardAt(selectedCards.get(0));
        Card card2 = cardAt(selectedCards.get(1));
        if(card1.pointValue()+card2.pointValue()==11)
        {
            return true;
        }
        return false;
    }

    /**
     * Check for a JQK in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a JQK group.
     * @return true if the board entries in selectedCards
     *              include a jack, a queen, and a king; false otherwise.
     */
    private boolean containsJQK(List<Integer> selectedCards) {
        Card card1 = cardAt(selectedCards.get(0));
        Card card2 = cardAt(selectedCards.get(1));
        Card card3 = cardAt(selectedCards.get(2));
        if(card1.pointValue()+card2.pointValue()+card3.pointValue()== 36)
        {
            return true;
        }
        return false;
    }
}
