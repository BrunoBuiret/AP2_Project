import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @brief 
 * @author
 */
public abstract class Input
{
    protected static BufferedReader reader = null;
    
    static
    {
        Input.reader = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public String readLine() throws IOException
    {
        return Input.reader.readLine();
    }
}