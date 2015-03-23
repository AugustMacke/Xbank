package de.fh_muenster.xbank;

/**
 * Created by user on 23.03.15.
 */

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by user on 18.03.15.
 */
public class SettingsActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
