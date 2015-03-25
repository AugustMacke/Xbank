package de.fh_muenster.xbank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Set;

import XbankService.Account;
import XbankService.Customer;
import XbankService.InvalidLoginException;
import XbankService.NoSessionException;


public class MainActivity extends ActionBarActivity
{
    private Customer c;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            openSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openSettings()
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void login(View view)
    {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String user = sharedPref.getString("pref_user", "");
        String pw = sharedPref.getString("pref_password", "");

        Button btn = (Button) findViewById(R.id.btn_login);
        String btnMessage = btn.getText().toString();

        new MyTask().execute( new String[]{user, pw, btnMessage} );
    }



    public class MyTask extends AsyncTask<String, Integer, String>
    {
        protected String doInBackground(String[] params)
        {
            if(params[2].equals("Login"))
            {
                c = new Customer();
                try
                {
                    c.login(params[0], params[1]);

                    //return (R.string.info_welcome + " " + c.getUserName());
                    return "login_ok";
                }
                catch (InvalidLoginException ex)
                {
                    //return "" + (R.string.error_login);
                    return "login_fail";
                }
            }
            else
            {
                try
                {
                    if(c == null)
                        throw new NoSessionException();

                    c.logout();

                    //return "" + (R.string.info_logout);
                    return "logout_ok";
                }
                catch (NoSessionException ex)
                {
                    //return "" + R.string.error_logout;
                    return "logout_fail";
                }
            }
        }

        protected void onProgressUpdate(Integer values)
        {

        }

        protected void onPostExecute(String result)
        {
            Button btn = (Button) findViewById(R.id.btn_login);
            TextView info = (TextView) findViewById(R.id.textView);

            switch(result)
            {
                case "login_ok":
                    btn.setText("Logout");
                    if(c != null)
                    {
                        info.setText(getResources().getString(R.string.info_welcome) + " " + c.getUserName());
                        Set<Account> accs_ = c.getMyAccounts();
                        //Account[] accs = (Account[]) accs_.toArray();

                        TextView accs_view = (TextView) findViewById(R.id.textViewAccs);
                        accs_view.setText("");

                        for(Iterator<Account> i = accs_.iterator(); i.hasNext(); )
                        {
                            Account acc = i.next();
                            accs_view.append(acc.toString() + "\n");
                        }
                    }
                    else
                        info.setText(getResources().getString(R.string.error_fatal));
                    break;
                case "login_fail":
                    btn.setText("Login");
                    info.setText(getResources().getString(R.string.error_login));
                    break;
                case "logout_ok":
                    btn.setText("Login");
                    info.setText(getResources().getString(R.string.info_logout));
                    break;
                case "logout_fail":
                    btn.setText("Login");
                    info.setText(getResources().getString(R.string.error_logout));
                    break;
            }
        }
    }
}
