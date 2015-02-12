import java.util.Arrays;

/**
 * @brief Main entry point of the program.
 * @author 
 */
public class Program
{
    public static void main(String[] args)
    {
        TicTacToe t = new TicTacToe();
        t.addPlayer(new HumanPlayer("Human",  'x', t));
        
        // Display the AI kinds
        System.out.println("==[Tic Tac Toe]====");
        System.out.println("Select which kind of algorithm the computer should use.");
        System.out.println(" 1. Recursive algorithm");
        System.out.println(" 2. Iterative algorithm");
        System.out.println(" 3. Quit");
        
        // Select the kind of AI
        int menuItemId = Input.selectInteger(Arrays.asList(1, 2, 3));
        
        switch(menuItemId)
        {
            case 1:
                t.addPlayer(new RecursiveComputerPlayer("RecursiveComputer",  'o', t));
            break;
                
            case 2:
                t.addPlayer(new RecursiveComputerPlayer("IterativeComputer",  'o', t));
            break;
                
            case 3:
                System.exit(0);
            break;
        }
        
        // Run the game
        t.run();
    }
}
