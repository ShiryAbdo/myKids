package memory.game.kids.memory.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import memory.game.kids.memory.model.Memory;

public class ImageAdapter extends BaseAdapter
{

    private Context mContext;
    private int mTileSize;
    private Memory mMemory;
    private int screnw;

    public ImageAdapter(Context c, int width, int height, int margin , Memory memory )
    {
        mContext = c;
        mMemory = memory;

        if (width > height)
        {
        	this.screnw = height;
            mTileSize = getTileSize(width, height, memory.getMaxTilesPerRow(), memory.getMinTilesPerRow(), margin);
        } else
        {
        	this.screnw = width;
            mTileSize = getTileSize(height, width, memory.getMaxTilesPerRow(), memory.getMinTilesPerRow(), margin);

        }

    }

    private int getTileSize(int max, int min, int countMax, int countMin, int margin)
    {
        int a = max / countMax;
        int b = min / countMin;
        
        
        return ((a < b) ? a : b ) - margin;
    }

    public int getCount()
    {
        return mMemory.getCount();
    }

    public Object getItem(int position)
    {
        return null;
    }

    public long getItemId(int position)
    {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;
        if (convertView == null)
        {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            if (this.screnw<480) {
            	 imageView.setLayoutParams(new GridView.LayoutParams(mTileSize-6, mTileSize-6));
//            	 imageView.setPadding(1, 1, 1, 1);
			}else{
	            imageView.setLayoutParams(new GridView.LayoutParams(mTileSize, mTileSize));
	            imageView.setPadding(2, 2, 2, 2);
			}
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            
        } else
        {
            imageView = (ImageView) convertView;
        }


        imageView.setImageResource( mMemory.getResId( position ));
        return imageView;
    }

}
