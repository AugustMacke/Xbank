package XbankService;

import java.math.BigDecimal;

/**
 * Created by user on 23.03.15.
 */
public class Account
{
    static int idCounter = 0;
    int id;
    BigDecimal amount = new BigDecimal("100");

    public Account()
    {
        idCounter++;
        this.id = idCounter;
    }

    public int getId()
    {
        return id;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void increase(BigDecimal add)
    {
        this.amount = this.amount.add(add);
    }

    public void decrease(BigDecimal amount)
    {
        this.amount = this.amount.subtract(amount);
    }

    @Override
    public String toString()
    {
        return "Id: " + id + ", Kontostand: " + amount;
    }
}
