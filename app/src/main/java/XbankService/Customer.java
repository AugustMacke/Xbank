package XbankService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by user on 23.03.15.
 */
public class Customer
{
    private String userName = "User";
    private String password = "123";
    private Set<Account> myAccounts = new HashSet<Account>();
    private boolean isLoggedIn = false;

    public Customer login(String userName, String password) throws InvalidLoginException
    {
        if( this.userName.equals(userName) && this.password.equals(password) )
        {
            isLoggedIn = true;

            Account acc1 = new Account();
            acc1.increase( new BigDecimal("100") );
            Account acc2 = new Account();
            acc2.increase( new BigDecimal("200") );
            myAccounts.add(acc1);
            myAccounts.add(acc2);

            return this;
        }

        else
            throw new InvalidLoginException();
    }

    public void logout() throws NoSessionException
    {
        if(!isLoggedIn)
            throw new NoSessionException();
        else
            isLoggedIn = false;
    }

    public void addNewAccount(Account account)
    {
        myAccounts.add(account);
    }

    public Account getAccountById(int id)
    {
        for(Account acc : myAccounts)
        {
            if(acc.getId() == id)
                return acc;
        }
        return null;
    }

    public Set<Account> getMyAccounts()
    {
        return myAccounts;
    }

    @Override
    public String toString()
    {
        return userName;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }
}
