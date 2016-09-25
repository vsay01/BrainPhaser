package com.questions.game.activities.aboutscreen;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.questions.game.BrainPhaserComponent;
import com.questions.game.R;
import com.questions.game.activities.BrainPhaserActivity;
import com.questions.game.utility.FileUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by thomasstuckel on 07/03/2016.
 * <p>
 * opens and reads the license from a .txt file and shows on the about screen
 */
public class AboutActivity extends BrainPhaserActivity {
    private TextView mAboutText;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void injectComponent(BrainPhaserComponent component) {
        component.inject(this);
    }

    /**
     * This method is called when the activity is created
     *
     * @param savedInstanceState handed over to super constructor
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);

        mAboutText = (TextView) findViewById(R.id.action_about);

        try {
            String contents = getStringFromRawFile();
            mAboutText.setText(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //load the toolbar from layout into the toolbar varible
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Set as Actionbar
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Resource: Pro Android by Syes Y. Hashimi and Satya Komatineni (2009) p.59
     * opens the textfile from credits.txt and reads and converts with convertStreamToString() into a String
     *
     * @return string from .txt file
     * @throws IOException
     */
    String getStringFromRawFile() throws IOException {
        Resources r = getResources();
        InputStream is = r.openRawResource(R.raw.credits);
        String myText = FileUtils.convertStreamToString(is);
        is.close();
        return myText;
    }
}
