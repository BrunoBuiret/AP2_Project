import java.security.InvalidParameterException;

/**
 * @brief Represents a simple two-dimensional board.
 * @author 
 */
public class Board
{
    /**
     * @brief Holds the board's width.
     */
    protected int width;
    
    /**
     * @brief Holds the board's height.
     */
    protected int height;
    
    /**
     * @brief Holds the actual board.
     */
    protected Player[][] board;
    
    /**
     * @brief Creates a new board.
     * @param width Board's width.
     * @param height Board's height.
     */
    public Board(int width, int height)
    {
        // Is the width valid?
        if(width > 0)
        {
            this.width = width;
        }
        else   
        {
            throw new InvalidParameterException("A board's width cannot be negative or null.");
        }
        
        // Is the height valid?
        if(height > 0)
        {
            this.height = height;
        }
        else   
        {
            throw new InvalidParameterException("A board's height cannot be negative or null.");
        }
        
        // Create the board
        this.board = new Player[this.width][this.height];
        
        // Then, initialize it
        for(int x = 0; x < this.width; x++)
        {
            for(int y = 0; y < this.height; y++)
            {
                this.board[x][y] = null;
            }
        }
    }
    
    /**
     * @brief Places a pawn on the board.
     * @param x Pawn's position's abscissa.
     * @param y Pawn's position's ordinate.
     * @param p Reference to the pawn's owner.
     */
    public void setAt(int x, int y, Player p)
    {
        if(x >= 0)
        {
            if(x < this.width)
            {
                if(y >= 0)
                {
                    if(y < this.height)
                    {
                        this.board[x][y] = p;
                    }
                    else
                    {
                        throw new InvalidParameterException("A position's ordinate cannot be outside the board.");
                    }
                }
                else
                {
                    throw new InvalidParameterException("A position's ordinate cannot be negative.");
                }
            }
            else
            {
                throw new InvalidParameterException("A position's abscissa cannot be outside the board.");
            }
        }
        else
        {
            throw new InvalidParameterException("A position's abscissa cannot be negative.");
        }
    }
    
    /**
     * @brief Places a pawn on the board.
     * @param position Pawn's position.
     * @param player Reference to the pawn's owner.
     * @see Board#setAt(int, int, Player)
     */
    public void setAt(Position position, Player player)
    {
        this.setAt(position.getX(), position.getY(), player);
    }
    
    /**
     * @brief Gets a board's string representation.
     * @return Board's string representation.
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        int x, y;
        
        // Build the board's header
        s.append('+');
        
        for(x = 0; x < this.width; x++)
        {
            s.append('-');
        }
        
        s.append("+\n");
        
        // Build the board's body
        for(y = 0; y < this.height; y++)
        {
            for(x = 0; x < this.width; x++)
            {
                s.append(this.board[x][y] != null ? this.board[x][y].getRepresentation() : ' ');
            }
            
            s.append("|\n");
        }
        
        // Build the board's footer
        s.append('+');
        
        for(x = 0; x < this.width; x++)
        {
            s.append('-');
        }
        
        s.append("+\n");
        
        return s.toString();
    }
}