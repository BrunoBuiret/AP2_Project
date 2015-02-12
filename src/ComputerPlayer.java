/**
 * @brief Represents an AI.
 * @author 
 */
public abstract class ComputerPlayer extends Player
{
    /**
     * @brief Creates a new computer player.
     * @param name Computer's name.
     * @param representation Computer's representation on the board.
     */
    public ComputerPlayer(String name, char representation, TicTacToe game)
    {
        super(name, representation, game);
    }
}