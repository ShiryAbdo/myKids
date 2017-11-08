package memory.game.kids.memory.game;

import android.os.Bundle;
import android.view.View;

import memory.game.kids.memory.R;
import memory.game.kids.memory.ui.AbstractMainActivity;

public class ShowImages extends AbstractMainActivity implements Memory_two.OnMemoryListener {
    MemoryGridViewD mGridView;
    Memory_two mMemory ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_images2);
        mGridView = (MemoryGridViewD)findViewById(R.id.gridview);
        getdata(getmMemory());

    }
    public  void getdata (Memory_two mMemory){
        mGridView.setMemory(mMemory);

    }

    public Memory_two getmMemory() {
        return mMemory;
    }

    public void setmMemory(Memory_two mMemory) {
        this.mMemory = mMemory;
    }

    @Override
    public void onComplete(int moveCount) {

    }

    @Override
    public void onUpdateView() {
        mGridView.update();

    }

    @Override
    public View getGameView() {
        return mGridView;
    }

    @Override
    protected void newGame() {

    }

    @Override
    protected void preferences() {

    }

    @Override
    protected void dialoge() {

    }


}
