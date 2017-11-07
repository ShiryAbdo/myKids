package memory.game.kids.memory.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import memory.game.kids.memory.R;
import memory.game.kids.memory.model.Memory;
import memory.game.kids.memory.ui.MemoryGridView;

public class ShowImages extends AppCompatActivity  implements Memory.OnMemoryListener{
     MemoryGridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);
        mGridView = (MemoryGridView) findViewById(R.id.gridview);
    }

     public  void getdata (Memory mMemory){
          mGridView.setMemory(mMemory);

     }

    @Override
    public void onComplete(int moveCount) {

    }

    @Override
    public void onUpdateView() {
        mGridView.update();

    }
}
