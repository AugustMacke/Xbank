package XbankService;

import java.util.Set;

/**
 * Created by user on 23.03.15.
 */
public class Customer
{
    private String userName = "User";
    private String password = "123";
    private Set<Account> myAccounts;

    public Customer login(String userName, String password) throws InvalidLoginException
    {
        if( this.userName == userName && this.password == password)
            return this;
        else
            throw new InvalidLoginException();
    }

    public void logout()
    {

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
