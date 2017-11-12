import javax.ejb.Remote;
/**
 * Interface for finding prime numbers
 * @author Michał Śliwa
 */
@Remote
public interface NprimeRemote
{
    /**
     * Returns smallest prime number greater than given int in 4k+3 format
     * @param n int 
     * @return int in 4k+3 format
     */
    public int prime(int n);
}
