import java.security.InvalidParameterException;

/**
 * @brief Represents a two-dimensional position.
 * @author 
 */
public class Position
{
    /**
     * @brief Holds the position's abscissa.
     */
    protected final int x;
    
    /**
     * @brief Holds the position's ordinate.
     */
    protected final int y;
    
    /**
     * @brief Creates a new position.
     * @param x Position's abscissa.
     * @param y Position's ordinate.
     * @throws InvalidParameterException
     */
    public Position(int x, int y) throws InvalidParameterException
    {
        // Is the abscissa valid?
        if(x >= 0)
        {
            this.x = x;
        }
        else
        {
            throw new InvalidParameterException("A position's abscissa cannot be negative.");
        }
        
        // Is the ordinate valid?
        if(y >= 0)
        {
            this.y = y;
        }
        else
        {
            throw new InvalidParameterException("A position's ordinate cannot be negative.");
        }
    }
    
    /**
     * @brief Gets a position's abscissa.
     * @return Position's abscissa.
     */
    public int getX()
    {
        return this.x;
    }
    
    /**
     * @brief Gets a position's ordinate.
     * @return Position's ordinate.
     */
    public int getY()
    {
        return this.y;
    }
    
    /**
     * @brief Tests if another position is equals to this one.
     * @param o Reference to the other object representing the position.
     * @return `true` if the two positions are equals, `false` otherwise.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o)
    {
        if(o instanceof Position)
        {
            return ((Position) o).getX() == this.x && ((Position) o).getY() == this.y;
        }
        
        return false;
    }
}