package memory.game.kids.memory.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import org.androidsoft.utils.ui.BasicActivity;

import memory.game.kids.memory.PreferencesService;
import memory.game.kids.memory.R;
import memory.game.kids.memory.ui.MainActivity;

public class ChoesActivity extends BasicActivity implements View.OnClickListener
{
    private CompoundButton mCbSoundEnabled;
    private RadioButton mRbFruits;
    private RadioButton mRbHalloween;
    private RadioButton mRbSports;
    private RadioButton mRbFoods;
private  Button start  ;

    /**
     * {@inheritDoc }
     */
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);

        setContentView( R.layout.activity_choes);

         PreferencesService.init( this );
        PreferencesService.instance().getPrefs();

        start =(Button)findViewById(R.id.start);
        mRbFruits = (RadioButton) findViewById(R.id.radio_mode_fruits);
        mRbFruits.setOnClickListener(this);
        mRbHalloween = (RadioButton) findViewById(R.id.radio_mode_halloween);
        mRbHalloween.setOnClickListener(this);
        mRbSports = (RadioButton) findViewById(R.id.radio_mode_sports);
        mRbSports.setOnClickListener(this);
        mRbFoods = (RadioButton) findViewById(R.id.radio_mode_foods);
        mRbFoods.setOnClickListener(this);
        int iconSet = PreferencesService.instance().getIconsSet();
        if( iconSet == PreferencesService.ICONS_SET_FRUITS )
        {
            mRbFruits.setChecked(true);
            mRbHalloween.setChecked(false);
            mRbSports.setChecked(false);
            mRbFoods.setChecked(false);
        }
        else if ( iconSet == PreferencesService.ICONS_SET_HALLOWEEN )
        {
            mRbFruits.setChecked(false);
            mRbHalloween.setChecked(true);
            mRbSports.setChecked(false);
            mRbFoods.setChecked(false);
        }
        else if ( iconSet == PreferencesService.ICONS_SET_SPORTS )
        {
            mRbFruits.setChecked(false);
            mRbHalloween.setChecked(false);
            mRbSports.setChecked(true);
            mRbFoods.setChecked(false);
        }
        else if ( iconSet == PreferencesService.ICONS_SET_FOODS )
        {
            mRbFruits.setChecked(false);
            mRbHalloween.setChecked(false);
            mRbSports.setChecked(false);
            mRbFoods.setChecked(true);
        }

        mCbSoundEnabled = (CompoundButton) findViewById(R.id.checkbox_sound);
        mCbSoundEnabled.setOnClickListener(this);
        mCbSoundEnabled.setChecked( PreferencesService.instance().isSoundEnabled() );
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



    /**
     * {@inheritDoc }
     */
    @Override
    public int getMenuResource()
    {
        return R.menu.menu_close;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int getMenuCloseId()
    {
        return R.id.menu_close;
    }

    /**
     * {@inheritDoc }
     */
    public void onClick(View view)
    {

         if ( view == mCbSoundEnabled )
        {
            PreferencesService.instance().saveSoundEnabled( mCbSoundEnabled.isChecked());
        }
        else if ( view == mRbFruits )
        {
            PreferencesService.instance().saveIconsSet( PreferencesService.ICONS_SET_FRUITS );
        }
        else if ( view == mRbHalloween )
        {
            PreferencesService.instance().saveIconsSet( PreferencesService.ICONS_SET_HALLOWEEN );
        }
        else if ( view == mRbSports )
        {
            PreferencesService.instance().saveIconsSet( PreferencesService.ICONS_SET_SPORTS );
        }
        else if ( view == mRbFoods )
        {
            PreferencesService.instance().saveIconsSet( PreferencesService.ICONS_SET_FOODS );
        }
    }




}
