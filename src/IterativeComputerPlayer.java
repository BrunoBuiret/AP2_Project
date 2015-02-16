import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @brief Represents an AI that uses an iterative algorithm.
 * @author 
 */
public class IterativeComputerPlayer extends ComputerPlayer
{
    /**
     * @param name
     * @param representation
     * @param game
     */
    public IterativeComputerPlayer(String name, char representation, TicTacToe game)
    {
        super(name, representation, game);
    }

    /**
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
     * @param b
     * @param i
     * @return
     */
    protected Position determineNextPosition(Board b, int i)
    {
        if(i >= 0 && i <= 7)
        {
            // Determine the first empty cell in a row
            if(i >= 0 && i <= 2)
            {
                for(int j = 0; j < 3; j++)
                {
                    if(b.getAt(j, i) == null)
                    {
                        return new Position(j, i);
                    }
                }
            }
            // Determine the first empty cell in a column
            else if(i >= 3 && i <= 5)
            {
                for(int j = 0; j < 3; j++)
                {
                    if(b.getAt(i - 3, j) == null)
                    {
                        return new Position(i - 3, j);
                    }
                }
            }
            // Determine the first empty cell in the first diagonal
            else if(i == 6)
            {
                for(int j = 0; j < 3; j++)
                {
                    if(b.getAt(j, j) == null)
                    {
                        return new Position(j, j);
                    }
                }
            }
         // Determine the first empty cell in the second diagonal
            else
            {
                for(int j = 0; j < 3; j++)
                {
                    if(b.getAt(2 - j, j) == null)
                    {
                        return new Position(2 - j, j);
                    }
                }
            }
            
            return null;
        }
        else
        {
            throw new InvalidParameterException("i must be between 0 and 7.");
        }
    }
}