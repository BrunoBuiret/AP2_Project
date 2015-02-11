import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Represents a game of tic tac toe.
 * @author 
 */
public class TicTacToe
{
    /**
     * @brief Holds a reference to the board.
     */
    protected Board board;
    
    /**
     * @brief Holds a reference to the list of players.
     */
    protected List<Player> players;
    
    /**
     * @brief Creates a new Tic Tac Toe game.
     */
    public TicTacToe()
    {
        this.board = new Board(3,  3);
        this.players = new ArrayList<Player>();
    }
    
    /**
     * @param p 
     */
    public void addPlayer(Player p)
    {
        if(this.players.size() < 2)
        {
            this.players.add(p);
        }
        else
        {
            throw new IllegalStateException("The players' list is already full.");
        }
    }
    
    /**
     * @brief Places a pawn on the board.
     * @param player Reference to the player who's playing.
     * @param position Reference to the position which is being played.
     * @throws InvalidParameterException Thrown when somebody already played there.
     */
    protected void play(Player player, Position position)
    {
        if(this.board.getAt(position) == null)
        {
            this.board.setAt(position, player);
        }
        else
        {
            throw new InvalidParameterException(String.format(
                "%s already played at %s.",
                this.board.getAt(position),
                position
            ));
        }
    }
    
    /**
     * 
     */
    public void run()
    {
        // Check if there is the required number of players
        if(this.players.size() == 2)
        {
            boolean keepLooping = false;
            int playerIndex = this.players.get(0) instanceof HumanPlayer ? 0 : 1;
            
            do
            {
                System.out.println(this.board);
                
                if(this.players.get(playerIndex) instanceof HumanPlayer)
                {
                    // Display what the user can do
                    System.out.println(String.format(
                            "%s: What do you want to do?",
                            this.players.get(playerIndex)
                        ));
                    System.out.println(" Play: <column> <row>");
                    System.out.println(" Quit: q");
                    
                    // Ask the user what he wants to do
                    boolean keepScanning = true;
                    String stringToParse;
                    
                    do
                    {
                        try
                        {
                            System.out.print(" > ");
                            stringToParse = Input.readLine();
                            keepScanning = !(Position.matches(stringToParse) || stringToParse.compareTo("q") == 0);
                        }
                        catch(IOException e)
                        {
                            keepScanning = true;
                        }
                    }
                    while(keepScanning);
                }
                else
                {
                    
                }
            }
            while(keepLooping);
        }
        else
        {
            throw new IllegalStateException(String.format("The players' list isn't full : %d out of 2.", this.players.size()));
        }
    }
}