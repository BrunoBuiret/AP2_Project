import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @brief Utility class to ask the user for input.
 * @author Bruno Buiret, Thomas Arnaud, Mathieu Thouret, Sidney Adjou
 */
public abstract class Input
{
    /**
     * @brief Holds a reference to a buffered reader used for user input.
     */
    protected static BufferedReader reader = null;
    
    static
    {
        Input.reader = new BufferedReader(new InputStreamReader(System.in));
    }
    
    /**
     * @brief Reads a line of user input.
     * @return Reference to the string read from user input.
     * @throws IOException Thrown when an input error occurs.
     */
    public static String readLine() throws IOException
    {
        return Input.reader.readLine();
    }
    
    /**
     * @brief Asks the user for a value from a set.
     * @param possibleValues List of possible integer values.
     * @return Selected value.
     */
    public static Integer selectInteger(List<Integer> possibleValues)
    {
        boolean keepScanning = false;
        int readValue = -1;
        
        do
        {
            try
            {
                System.out.print("> ");
                readValue = Integer.parseInt(Input.readLine());
                keepScanning = !possibleValues.contains(readValue);
            }
            catch(NumberFormatException e)
            {
                keepScanning = true;
            }
            catch(IOException e)
            {
                keepScanning = true;
            }
        }
        while(keepScanning);
        
        return readValue;
    }
}