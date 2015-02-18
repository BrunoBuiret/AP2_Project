import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @brief Represents an AI that uses an iterative algorithm.
 * @author Bruno Buiret, Thomas Arnaud, Mathieu Thouret, Sidney Adjou
 */
public class IterativeComputerPlayer extends ComputerPlayer
{
    /**
     * @brief Creates a new computer player which uses an iterative algorithm.
     * @param name Name of the new player.
     * @param representation Character representing the player on the board.
     * @param game Reference to the game of Tic Tac Toe.
     */
    public IterativeComputerPlayer(String name, char representation, TicTacToe game)
    {
        super(name, representation, game);
    }

    /**
     * @brief Determines the next best position to play using an iterative algorithm.
     * @param b Reference to the board.
     * @return Reference to the next position to play or `NULL` if it was unable to determine one.
     * @see Player#getNextPosition(Board)
     */
    public Position getNextPosition(Board b)
    {
        // Analyze the board
        // x <=> Column | y <=> Row
        // -------------+-----------------+------------------
        // 0 <=> Row #1 | 3 <=> Column #1 | 6 <=> Diagonal #1
        // 1 <=> Row #2 | 4 <=> Column #2 | 7 <=> Diagonal #2
        // 2 <=> Row #3 | 5 <=> Column #3 |
        int[] currentScores = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        ArrayList<Position> availableMoves = new ArrayList<Position>();
        
        for(int x = 0; x < 3; x++)
        {
            for(int y = 0; y < 3; y++)
            {
                if(b.getAt(x, y) != null)
                {
                    // Analyze rows
                    currentScores[y] = currentScores[y] + (b.getAt(x, y).equals(this) ? 1 : -1);
                            
                    // Analyze columns
                    currentScores[3 + x] = currentScores[3 + x] + (b.getAt(x, y).equals(this) ? 1 : -1);
                    
                    // Analyze diagonals
                    if(x == y)
                    {
                        currentScores[6] = currentScores[6] + (b.getAt(x, y).equals(this) ? 1 : -1);
                    }
                    
                    if(x == y || (x == 0 && y == 2) || (x == 2 && y == 0))
                    {
                        currentScores[7] = currentScores[7] + (b.getAt(x, y).equals(this) ? 1 : -1);
                    }
                }
                else
                {
                    availableMoves.add(new Position(x, y));
                }
            }
        }
        
        // Check if the computer can win
        for(int i = 0; i < 8; i++)
        {
            if(currentScores[i] == 2)
            {
                return this.determineNextPosition(b, i);
            }
        }
        
        // Check if the computer has to stop the other player from winning
        for(int i = 0; i < 8; i++)
        {
            if(currentScores[i] == -2)
            {
                return this.determineNextPosition(b, i);
            }
        }
        
        // Otherwise, randomly play
        if(availableMoves.size() > 0)
        {
            Random r = new Random();
            return availableMoves.get(r.nextInt(availableMoves.size()));
        }
        
        return null;
    }
    
    /**
     * @brief Utility method to determine the next position to play according to a line, column or diagonal.
     * @param b Reference to the board.
     * @param i Parameter to determine which line, column or diagonal to look through.
     * @return Reference to the next position to play or `NULL` if it was impossible to determine one.
     */
    protected Position determineNextPosition(Board b, int i)
    {
        switch(i)
        {
            // Determine the first empty cell in a row
            case 0:
            case 1:
            case 2:
                for(int j = 0; j < 3; j++)
                {
                    if(b.getAt(j, i) == null)
                    {
                        return new Position(j, i);
                    }
                }
            break;
            
            // Determine the first empty cell in a column
            case 3:
            case 4:
            case 5:
                for(int j = 0; j < 3; j++)
                {
                    if(b.getAt(i - 3, j) == null)
                    {
                        return new Position(i - 3, j);
                    }
                }
            break;
            
            // Determine the first empty cell in the first diagonal
            case 6:
                for(int j = 0; j < 3; j++)
                {
                    if(b.getAt(j, j) == null)
                    {
                        return new Position(j, j);
                    }
                }
            break;
            
            // Determine the first empty cell in the second diagonal
            case 7:
                for(int j = 0; j < 3; j++)
                {
                    if(b.getAt(2 - j, j) == null)
                    {
                        return new Position(2 - j, j);
                    }
                }
            break;
            
            default:
                throw new InvalidParameterException("i must be between 0 and 7.");
        }
        
        return null;
    }
}