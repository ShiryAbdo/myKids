package memory.game.kids.memory.game;

import android.content.SharedPreferences;

import org.androidsoft.utils.sound.SoundManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import memory.game.kids.memory.PreferencesService;
import memory.game.kids.memory.R;
import memory.game.kids.memory.model.Memory;
import memory.game.kids.memory.model.Tile;
import memory.game.kids.memory.model.TileList;

public class Memory_two
{
     private static final int SOUND_FAILED = 2000;
    private static final int SOUND_SUCCEED = 2001;
    private static final String PREF_LIST = "list";
    private static final String PREF_MOVE_COUNT = "move_count";
    private static final String PREF_SELECTED_COUNT = "seleted_count";
    private static final String PREF_FOUND_COUNT = "found_count";
    private static final String PREF_LAST_POSITION = "last_position";
    private static final String PREF_TILE_VERSO = "tile_verso";
    private static final int MAX_TILES_PER_ROW = 6;
    private static final int MIN_TILES_PER_ROW = 4;
    private static final int SET_SIZE = (MAX_TILES_PER_ROW * MIN_TILES_PER_ROW) / 2;
    private int mSelectedCount;
    private int mMoveCount;
    private int mFoundCount;
    private int mLastPosition = -1;
    private Tile mT1;
    private Tile mT2;
    private TileList mList = new TileList();
    private int[] mTiles;
    private Memory.OnMemoryListener mListener;
    private static int[] mSounds;
    private int mTileVerso;
     int [] arraImagRtage ={0, 1, 2, 3, 4, 5, 6,7 ,8,9,
            10, 11, 12, 13, 14, 15, 16,17 ,18,19 ,
            20, 21, 22, 23, 24, 25, 26,27 ,28,29 ,
            30, 31, 32, 33, 34, 35, 36,37 ,38,39 ,
            40, 41, 42, 43, 44, 45, 46,47 ,48,49
    };
    int range = arraImagRtage.length;
    int myImgCount = 0;
     Random random = new Random();

    public Memory_two(int[] tiles, int[] sounds, int tile_verso, Memory.OnMemoryListener listener  )
    {
        mTiles = tiles;
        mSounds = sounds;
        mListener = listener;
        mTileVerso = tile_verso;
        Tile.setNotFoundResId(mTileVerso);


    }

    public Memory_two() {
    }

    public void onResume(SharedPreferences prefs)
    {
        String serialized = prefs.getString(PREF_LIST, null);
        if (serialized != null)
        {
            mList = new TileList(serialized);
            mMoveCount = prefs.getInt(PREF_MOVE_COUNT, 0);
            ArrayList<Tile> list = mList.getSelected();
            mSelectedCount = list.size();
            mT1 = (mSelectedCount > 0) ? list.get(0) : null;
            mT2 = (mSelectedCount > 1) ? list.get(1) : null;
            mFoundCount = prefs.getInt(PREF_FOUND_COUNT, 0);
            mLastPosition = prefs.getInt(PREF_LAST_POSITION, -1);
            mTileVerso = prefs.getInt(PREF_TILE_VERSO, R.drawable.not_found_fruits);
            Tile.setNotFoundResId(mTileVerso);
        }

        initSounds();
    }

    public void onPause(SharedPreferences preferences, boolean quit)
    {
        SharedPreferences.Editor editor = preferences.edit();
        if (!quit)
        {
            // Paused without quit - save state
            editor.putString(PREF_LIST, mList.serialize());
            editor.putInt(PREF_MOVE_COUNT, mMoveCount);
            editor.putInt(PREF_SELECTED_COUNT, mSelectedCount);
            editor.putInt(PREF_FOUND_COUNT, mFoundCount);
            editor.putInt(PREF_LAST_POSITION, mLastPosition);
            editor.putInt(PREF_TILE_VERSO, mTileVerso);
        }
        else
        {
            editor.remove(PREF_LIST);
            editor.remove(PREF_MOVE_COUNT);
            editor.remove(PREF_SELECTED_COUNT);
            editor.remove(PREF_FOUND_COUNT);
            editor.remove(PREF_LAST_POSITION);
            editor.remove(PREF_TILE_VERSO);
        }
        editor.commit();
    }

    public int getCount()
    {
        return mList.size();
    }

    public int getMaxTilesPerRow()
    {
        return MAX_TILES_PER_ROW;
    }

    public int getMinTilesPerRow()
    {
        return MIN_TILES_PER_ROW;
    }

    public int getResId(int position)
    {
        return mList.get(position).getResId();
    }

    public void reset()
    {

        mFoundCount =mList.size();
        mMoveCount = 0;
        mList.clear();
        getTileSet();
        for (Integer tile : getTileSet())
        {
            addRandomly(tile);
        }
        for(int i = 0 ; i <mList.size(); i ++){
            mList.get(i).select();
        }
    }

    private void addRandomly(int nResId)
    {
        mList.add(0, new Tile(nResId));
        myImgCount = random.nextInt(mList.size());
        mList.add(myImgCount, new Tile(nResId));



    }
    private void initSounds()
    {
        SoundManager.instance().addSound(SOUND_FAILED, R.raw.youlose);
        SoundManager.instance().addSound(SOUND_SUCCEED, R.raw.youwin);
        for (int i = 0; i < mSounds.length; i++)
        {
            SoundManager.instance().addSound(i, mSounds[i]);
        }
    }

    public interface OnMemoryListener
    {

        void onComplete(int moveCount);

        void onUpdateView();
    }

    public void onPosition(int position)
    {

        if (position == mLastPosition)
        {
            // Same item clicked
            return;
        }

        mLastPosition = position;
        Tile tile = mList.get(position);
        tile.select();
        int sound = tile.mResId % mSounds.length;
        playSound(sound);

        switch (mSelectedCount)
        {
            case 0:
                mT1 = tile;
                break;

            case 1:
                mT2 = tile;
                if (mT1.getResId() == mT2.getResId())
                {
                    mT1.setFound(true);
                    mT2.setFound(true);
                    mFoundCount += 2;
                    playSound(SOUND_SUCCEED);
                }
                else
                {
//                    playSound( SOUND_FAILED );
                }
                break;

            case 2:
                if (mT1.getResId() != mT2.getResId())
                {
                    mT1.unselect();
                    mT2.unselect();
                }
                mSelectedCount = 0;
                mT1 = tile;
                break;
        }
        mSelectedCount++;
        mMoveCount++;
        updateView();
        checkComplete();
    }

    private void updateView()
    {
        mListener.onUpdateView();
    }

    private void checkComplete()
    {
        if (mFoundCount == mList.size())
        {
//            mListener.onComplete(mMoveCount);
        }
    }



    private int rand(int nSize)
    {
        double dPos = Math.random() * nSize;
        return (int) dPos;
    }

    private List<Integer> getTileSet()
    {
        List<Integer> list = new ArrayList<Integer>();

        while (list.size() < SET_SIZE)
        {
            for (int m = 0 ; m <mTiles.length; m++){
                int t = mTiles[m];
                if (!list.contains(t))
                {
                    list.add(t);
                }
            }

        }
        return list;
    }
    private void playSound(int index)
    {
        if (PreferencesService.instance().isSoundEnabled())
        {
            SoundManager.instance().playSound(index);
        }
    }




}
