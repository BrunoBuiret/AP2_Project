import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @brief Utility class to ask the user for input.
 * @author
 */
public abstract class Input
{
    protected static BufferedReader reader = null;
    
    static
    {
        Input.reader = new BufferedReader(new InputStreamReader(System.in));
    }
    
    /**
     * @return
     * @throws IOException
     */
    public static String readLine() throws IOException
    {
        return Input.reader.readLine();
    }
    
    /**
     * @param possibleValues
     * @return
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