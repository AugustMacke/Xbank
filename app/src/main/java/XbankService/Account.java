package XbankService;

import java.math.BigDecimal;

/**
 * Created by user on 23.03.15.
 */
public class Account
{
    int id;
    BigDecimal amount;

    public int getId()
    {
        return id;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void increase(BigDecimal amount)
    {
        this.amount = this.amount.add(amount);
    }

    public void decrease(BigDecimal amount)
    {
        this.amount = this.amount.subtract(amount);
    }

    @Override
    public String toString()
    {
        //TODO
        return "Id: " + id + ", Kontostand: " + amount;
    }
}
