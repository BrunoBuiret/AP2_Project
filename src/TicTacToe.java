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
     * @brief Holds the number of moves that have been played.
     */
    protected int movesNumber;
    
    /**
     * @brief Creates a new Tic Tac Toe game.
     */
    public TicTacToe()
    {
        this.board = new Board(3,  3);
        this.players = new ArrayList<Player>();
        this.movesNumber = 0;
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
     * @return
     */
    public List<Player> getPlayers()
    {
        return this.players;
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
     * @brief Runs the game.
     */
    public void run()
    {
        // Check if there is the required number of players
        if(this.players.size() == 2)
        {
            boolean keepLooping = true;
            int playerIndex = this.players.get(0) instanceof HumanPlayer ? 0 : 1;
            
            while(keepLooping)
            {
                // Display the board
                System.out.print(this.board);
                
                // Let the current user do something
                boolean keepPlaying = false;
                
                do
                {
                    if(this.players.get(playerIndex) instanceof HumanPlayer)
                    {
                        Position p = this.players.get(playerIndex).getNextPosition(this.board);
                        
                        if(p != null)
                        {
                            try
                            {
                                // Try playing this position
                                this.play(this.players.get(playerIndex), p);
                                this.movesNumber++;
                                
                                // Tell the user what position was played
                                System.out.println(String.format("You played %s.", p));
                                System.out.println();
                                
                                // Stop playing
                                keepPlaying = false;
                            }
                            catch(InvalidParameterException e)
                            {
                                System.err.println(e.getMessage());
                                keepPlaying = true;
                            }
                        }
                        else
                        {
                            System.err.println("Invalid move, try another one.");
                            keepPlaying = true;
                        }
                    }
                    else
                    {
                        Position p = this.players.get(playerIndex).getNextPosition(this.board);
                        
                        if(p != null)
                        {
                            try
                            {
                                // Try playing this position
                                this.play(this.players.get(playerIndex), p);
                                this.movesNumber++;
                                
                                // Tell the user what position was played
                                System.out.println(String.format("%s played %s.", this.players.get(playerIndex), p));
                                System.out.println();
                                
                                // Stop looping
                                keepPlaying = false;
                            }
                            catch(InvalidParameterException e)
                            {
                                keepPlaying = true;
                            }
                        }
                        else
                        {
                            keepPlaying = true;
                        }
                    }
                }
                while(keepPlaying);
                
                // Check if the player won the game
                if(this.check(this.players.get(playerIndex)))
                {
                    System.out.print(this.board);
                    System.out.println(String.format("%s has won.", this.players.get(playerIndex)));
                    System.out.println();
                    keepLooping = false;
                }
                // Or if the game is a draw
                else if(this.movesNumber == 9)
                {
                    System.out.print(this.board);
                    System.out.println("Nobody won.");
                    System.out.println();
                    keepLooping = false;
                }
                
                playerIndex = ++playerIndex % 2;
            }
        }
        else
        {
            throw new IllegalStateException(String.format("The players' list isn't full : %d out of 2.", this.players.size()));
        }
    }
    
    /**
     * @brief Checks if a player has won.
     * @param p Reference to the player to test for.
     * @return `TRUE` if the player has won, `FALSE` otherwise.
     */
    protected boolean check(Player p)
    {
        // Check horizontals and verticals
        for(int i = 0; i < 3; i++)
        {
            if(
                (
                    this.board.getAt(i, 0) != null && this.board.getAt(i, 0).equals(p)
                    &&
                    this.board.getAt(i, 1) != null && this.board.getAt(i, 1).equals(p)
                    &&
                    this.board.getAt(i, 2) != null && this.board.getAt(i, 2).equals(p)
                )
                ||
                (
                    this.board.getAt(0, i) != null && this.board.getAt(0, i).equals(p)
                    &&
                    this.board.getAt(1, i) != null && this.board.getAt(1, i).equals(p)
                    &&
                    this.board.getAt(2, i) != null && this.board.getAt(2, i).equals(p)
                )
            )
            {
                return true;
            }
        }
        
        // Check diagonals
        if(
            (
                this.board.getAt(0, 0) != null && this.board.getAt(0, 0).equals(p)
                &&
                this.board.getAt(1, 1) != null && this.board.getAt(1, 1).equals(p)
                &&
                this.board.getAt(2, 2) != null && this.board.getAt(2, 2).equals(p)
            )
            ||
            (
                this.board.getAt(2, 0) != null && this.board.getAt(2, 0).equals(p)
                &&
                this.board.getAt(1, 1) != null && this.board.getAt(1, 1).equals(p)
                &&
                this.board.getAt(0, 2) != null && this.board.getAt(0, 2).equals(p)
            )
        )
        {
            return true;
        }
        
        return false;
    }
}