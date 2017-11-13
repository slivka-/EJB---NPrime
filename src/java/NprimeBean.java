import javax.ejb.Stateless;
/**
 * EJB bean for finding prime numbers
 * @author Michał Śliwa
 */
@Stateless
public class NprimeBean implements NprimeRemote
{
    /**
     * Returns smallest prime number greater than given int in 4k+3 format
     * @param n int 
     * @return int in 4k+3 format
     */
    @Override
    public int prime(int n)
    {
        //increment until n is divisable by 4
        while (n % 4!=0) 
            n++;
        //divide n by 4
        int k = n/4;
        int output = (4*k)+3;
        while (!isPrime(output))
        {
            //while output is not a prime number increment k and recalculate
            k++;
            output = (4*k)+3;
        }
        return output;
    }
    
    /**
     * Checks if given int is a prime number
     * @param p int to check
     * @return true if is prime, otherwise false
     */
    private Boolean isPrime(int p)
    {
        //if p is divisable by 2, return false
        if(p%2!=0)
        {
            //check only half of the number
            int lim = (p+1)/2;
            for (int i = 3; i < lim; i++)
            {
                //if number can be divided without rest, return false
                if (p % i == 0)
                    return false;
            }
            return true;
        }
        else
        {
            return false;
        }
    }
}
