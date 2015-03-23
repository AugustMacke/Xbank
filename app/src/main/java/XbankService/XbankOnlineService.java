package XbankService;

/**
 * Created by user on 23.03.15.
 */
import java.math.BigDecimal;
import java.util.Set;

public interface XbankOnlineService
{
    public Customer login(String username, String password)
            throws InvalidLoginException;

    public void logout()
            throws NoSessionException;

    public Set<Account> getMyAccounts()
            throws NoSessionException;

    public BigDecimal getBalance(int accountID)
            throws NoSessionException;

    public BigDecimal transfer(int fromAccount,
                               int toAccount,
                               BigDecimal amount)
            throws NoSessionException;
}
