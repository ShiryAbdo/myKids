package memory.game.kids.memory.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import org.androidsoft.utils.ui.BasicActivity;

import memory.game.kids.memory.PreferencesService;
import memory.game.kids.memory.R;

public class PreferencesActivity extends BasicActivity implements OnClickListener
{
    private TextView mTvHiScore;
    private Button mButtonResetHiScore;
    private CompoundButton mCbSoundEnabled;
    private RadioButton mRbFruits;
    private RadioButton mRbHalloween;
    private RadioButton mRbSports;
	private RadioButton mRbFoods;
            
    
    /**
     * {@inheritDoc }
     */
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);

        setContentView( R.layout.settings);
        
        mTvHiScore = (TextView) findViewById( R.id.hiscore );
        updateHiScore();

        mButtonResetHiScore = (Button) findViewById(R.id.button_reset_hiscore);
        mButtonResetHiScore.setOnClickListener(this);
        
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
        if( view == mButtonResetHiScore )
        {
            PreferencesService.instance().resetHiScore();
            updateHiScore();
        } 
        else if ( view == mCbSoundEnabled )
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

    private void updateHiScore()
    {
        int hiscore = PreferencesService.instance().getHiScore();
        if( hiscore == PreferencesService.HISCORE_DEFAULT )
        {
            mTvHiScore.setText(" - ");
        }
        else
        {
            mTvHiScore.setText(" " + hiscore );
        }
    }

    
  
}
