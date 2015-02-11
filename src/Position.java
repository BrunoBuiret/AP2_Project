import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    
    protected final static Pattern positionPattern = Pattern.compile("([0-9]+)\\s+([0-9]+)");
    
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
     * @brief Parses a string to create a new position.
     * @param s Reference to the string to parse.
     * @return Reference to the new position or `NULL`.
     */
    public static Position parse(String s)
    {
        Matcher m = Position.positionPattern.matcher(s);
        return m.matches() ?  new Position(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))) : null;
    }
    
    /**
     * @brief Tests if a string can build a position.
     * @param s Reference to the string to parse.
     * @return `TRUE` if the string matches, `FALSE` otherwise.
     */
    public static boolean matches(String s)
    {
        return Position.positionPattern.matcher(s).matches();
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