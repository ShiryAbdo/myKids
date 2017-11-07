package memory.game.kids.memory.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import memory.game.kids.memory.model.Memory;

public class MemoryGridView extends GridView
{
    private static final int MARGINH = 15;
    private static final int MARGINM = 2;

    private Memory mMemory;
    private Context mContext;

    public MemoryGridView(Context context)
    {
        super(context);

        mContext = context;

        setOnItemClickListener(new OnItemClickListener()
        {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                mMemory.onPosition( position );
            }
        });

    }

    public MemoryGridView (Context context, AttributeSet attrs)
    {
        super( context , attrs );
        mContext = context;
        setOnItemClickListener(new OnItemClickListener()
        {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                mMemory.onPosition( position );
            }
        });
    }

    public MemoryGridView (Context context, AttributeSet attrs, int defStyle)
    {
        super( context , attrs , defStyle );
        mContext = context;
        setOnItemClickListener(new OnItemClickListener()
        {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                mMemory.onPosition( position );
            }
        });
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        update( );
    }

    public void update()
    {
    	if (getWidth()<480) {
    		setAdapter(new ImageAdapter( mContext, getWidth(), getHeight(), MARGINM , mMemory));
		}else{
			setAdapter(new ImageAdapter( mContext, getWidth(), getHeight(), MARGINH , mMemory));
		}
        
    }


    public void setMemory( Memory memory )
    {
        mMemory = memory;
    }

}
