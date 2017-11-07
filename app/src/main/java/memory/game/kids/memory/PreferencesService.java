package memory.game.kids.memory;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Preference Service
 */
public class PreferencesService
{

    public static final int ICONS_SET_FRUITS = 0;
    public static final int ICONS_SET_HALLOWEEN = 1;
    public static final int ICONS_SET_SPORTS = 2;
	public static final int ICONS_SET_FOODS = 3;
    public static final int HISCORE_DEFAULT = 200;
    private static final String PREFS_NAME = "MemoryPrefsFile";
    private static final String PREF_BEST_MOVE_COUNT = "best_move_count";
    private static final String PREF_SOUND_ENABLED = "sound_enabled";
    private static final String PREF_ICONS_SET = "icons_set";
    private static PreferencesService mSingleton = new PreferencesService();
    private static Context mContext;

    private PreferencesService()
    {
    }

    public static PreferencesService instance()
    {
        return mSingleton;
    }

    public static void init(Context context)
    {
        mContext = context;
    }

    public SharedPreferences getPrefs()
    {
        return mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveHiScore(int hiscore)
    {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putInt(PREF_BEST_MOVE_COUNT, hiscore);
        editor.commit();

    }

    public int getHiScore()
    {
        return getPrefs().getInt(PREF_BEST_MOVE_COUNT, HISCORE_DEFAULT);

    }

    public void resetHiScore()
    {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.remove(PREF_BEST_MOVE_COUNT);
        editor.commit();
    }

    public boolean isSoundEnabled()
    {
        return getPrefs().getBoolean(PREF_SOUND_ENABLED, true);
    }

    public void saveSoundEnabled(boolean enabled)
    {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putBoolean(PREF_SOUND_ENABLED, enabled);
        editor.commit();
    }

    public void saveIconsSet(int set)
    {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putInt(PREF_ICONS_SET, set);
        editor.commit();

    }

    public int getIconsSet()
    {
        return getPrefs().getInt(PREF_ICONS_SET, ICONS_SET_FRUITS);

    }
}
