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
        return n;
    }
}
