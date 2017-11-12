import javax.ejb.Remote;
/**
 *
 * @author Michał Śliwa
 */
@Remote
public interface NprimeRemote
{
    public int prime(int n);
}
