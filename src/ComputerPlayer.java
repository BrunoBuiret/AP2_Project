/**
 * @brief Represents an AI.
 * @author Bruno Buiret, Thomas Arnaud, Mathieu Thouret, Sidney Adjou
 */
public abstract class ComputerPlayer extends Player
{
    /**
     * @brief Creates a new computer player.
     * @param name Computer's name.
     * @param representation Computer's representation on the board.
     * @param game Reference to the game of Tic Tac Toe.
     */
    public ComputerPlayer(String name, char representation, TicTacToe game)
    {
        super(name, representation, game);
    }
}