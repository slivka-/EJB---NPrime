import javax.ejb.Stateless;
/**
 *
 * @author Michał Śliwa
 */
@Stateless
public class NprimeBean implements NprimeRemote
{
    @Override
    public int prime(int n)
    {
        while(n % 4!=0) 
            n++;
        int k = n/4;
        int output = (4*k)+3;
        while(!isPrime(output))
        {
            k++;
            output = (4*k)+3;
        }
        return output;
    }
    
    private Boolean isPrime(int p)
    {
        for (int i = 2; i < p; i++)
        {
            if (p % i == 0)
                return false;
        }
        return true;
    }
}
