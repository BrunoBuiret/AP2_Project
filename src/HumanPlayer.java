import java.io.IOException;

/**
 * @brief Represents a human player.
 * @author Bruno Buiret, Thomas Arnaud, Mathieu Thouret, Sydney Adjou
 */
public class HumanPlayer extends Player
{
    /**
     * @brief Creates a new human player.
     * @param name Player's name.
     * @param representation Player's representation on the board.
     * @param game Reference to the game of Tic Tac Toe.
     */
    public HumanPlayer(String name, char representation, TicTacToe game)
    {
        super(name, representation, game);
    }

    /**
     * @brief Let the user type which position they want to play.
     * @param b Reference to the board which isn't used by this method.
     * @return Reference to the next position to play.
     * @see Player#getNextPosition(Board)
     */
    public Position getNextPosition(Board b)
    {
        Position p = null;
        
        try
        {
            System.out.print(" > ");
            p = Position.parse(Input.readLine());
        }
        catch(IOException e)
        {
            return null;
        }
        
        return p;
    }
}