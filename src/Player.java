/**
 * @brief Represents an abstract player.
 * @author 
 */
public abstract class Player
{
    /**
     * @brief Holds the player's name.
     */
    protected String name;
    
    /**
     * @brief Holds the player's representation.
     */
    protected char representation;
    
    /**
     * @brief Creates a new player.
     * @param name Name of the new player.
     * @param representation Character representing the player on the board.
     */
    public Player(String name, char representation)
    {
        this.name = name;
        this.representation = representation;
    }
    
    /**
     * @brief Gets a player's name.
     * @return Player's name.
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * @brief Gets a player's representation.
     * @return Player's representation.
     */
    public char getRepresentation()
    {
        return this.representation;
    }
    
    /**
     * @brief Tests if another player is equal to this one.
     * @param o Reference to the other object representing the player.
     * @return `true` if the two players are equals, `false` otherwise.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o)
    {
        if(o instanceof Player)
        {
            return this.name.equals(((Player) o).getName()) && this.representation == ((Player) o).getRepresentation();
        }
        
        return false;
    }
    
    /**
     * @brief Gets a player's string representation.
     * @return Player's string representation.
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return String.format("%s (%c)", this.name, this.representation);
    }
}