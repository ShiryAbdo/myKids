package memory.game.kids.memory.game;

import java.util.ArrayList;
import java.util.List;

import memory.game.kids.memory.model.TileList;

/**
 * Created by falcon on 08/11/2017.
 */

public class images {
    private int[] mTiles;

    private TileList mList = new TileList();
    public images(int[] mTiles) {
        this.mTiles = mTiles;
    }

    public int getResId(int position)
    {
        return mList.get(position).getResId();
    }

//
//    private List<Integer> getTileSet()
//    {
//        List<Integer> list = new ArrayList<Integer>();
//
//        while (list.size() < SET_SIZE)
//        {
//            for (int i = 0 ; i <mTiles.length; i++){
//                int t = mTiles[i];
//                if (!list.contains(t))
//                {
//                    list.add(t);
//                }
//
//            }
//
//        }
//        return list;
//    }


}
