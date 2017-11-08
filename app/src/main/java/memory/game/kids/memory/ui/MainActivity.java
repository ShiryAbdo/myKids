package memory.game.kids.memory.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.View;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;

import  memory.game.kids.memory.PreferencesService;
import memory.game.kids.memory.R;
import memory.game.kids.memory.game.MemoryGridViewD;
import memory.game.kids.memory.game.Memory_two;
import memory.game.kids.memory.model.Memory;

/**
 * MainActivity
 */
public class MainActivity extends AbstractMainActivity implements Memory.OnMemoryListener
{

    private static final int[] tiles_fruits =
    {
        R.drawable.a1, R.drawable.a2,
        R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6,
        R.drawable.a7, R.drawable.a8, R.drawable.a9, R.drawable.a10,
        R.drawable.a11, R.drawable.a12, R.drawable.a13, R.drawable.a14,
        R.drawable.a15, R.drawable.a16, R.drawable.a17, R.drawable.a18,
        R.drawable.a19, R.drawable.a20, R.drawable.a21, R.drawable.a22
    };
    
    private static final int[] tiles_halloween =
    {
        R.drawable.b1, R.drawable.b2,
        R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6,
        R.drawable.b7, R.drawable.b8, R.drawable.b9, R.drawable.b10,
        R.drawable.b11, R.drawable.b12
    };
    
    private static final int[] tiles_sports =
    {
        R.drawable.c1, R.drawable.c2,
        R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6,
        R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.c10,
        R.drawable.c11, R.drawable.c12
    };
	
	private static final int[] tiles_foods =
    {
        R.drawable.d1, R.drawable.d2,
        R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6,
        R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10,
        R.drawable.d11, R.drawable.d12
    };
    
    private static final int[][] icons_set = { tiles_fruits , tiles_halloween, tiles_sports, tiles_foods };
    
    private static final int[] sounds = {
      R.raw.gupp, R.raw.winch, R.raw.chtoing, R.raw.kito, R.raw.kato, 
      R.raw.ding, R.raw.ding2, R.raw.ding3, R.raw.ding4, R.raw.ding5,
      R.raw.ding6, R.raw.dong, R.raw.swirlup, R.raw.swipp
    };


    private static final int[] not_found_tile_set =
    {
        R.drawable.not_found_fruits, R.drawable.not_found_halloween, R.drawable.not_found_sports, R.drawable.not_found_foods
    };

    int [] arraImagRtage ={0, 1, 2, 3, 4, 5, 6,7 ,8,9,
                           10, 11, 12, 13, 14, 15, 16,17 ,18,19 ,
                           20, 21, 22, 23, 24, 25, 26,27 ,28,29 ,
                           30, 31, 32, 33, 34, 35, 36,37 ,38,39 ,
                           40, 41, 42, 43, 44, 45, 46,47 ,48,49
    };
    int range = arraImagRtage.length;
    int rangee;
    int myImgCount = 0;
    ArrayList<Integer> numberArray =new ArrayList<>();
    Random random = new Random();
     private Memory memory ;
     private  Memory_two memory_two ;
     private MemoryGridView mGridView;
    MemoryGridViewD memoryGridViewDialoge ;
    Dialog okdialog ;

    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        int set = PreferencesService.instance().getIconsSet();
        rangee =icons_set[ set ].length;
         memory= new Memory( icons_set[ set ], sounds , not_found_tile_set[ set], this );
        memory.reset();
        memory_two= new Memory_two( icons_set[ set ], sounds , not_found_tile_set[ set ], this  );
        memory_two.reset();
          PreferencesService.init( this );



         newGame();

    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected View getGameView()
    {
        return mGridView;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void newGame()
    {
         mGridView = (MemoryGridView) findViewById(R.id.gridview);
         mGridView.setMemory(memory);
        drawGrid();
    }

   
    /**
     * {@inheritDoc }
     */
    @Override
    protected void preferences()
    {
        Intent intent = new Intent( this , PreferencesActivity.class );
        startActivity(intent);
    }

    @Override
    protected void dialoge() {


        okdialog = new Dialog(this, R.style.custom_dialog_theme);
        okdialog.setContentView(R.layout.activity_show_images);
        memoryGridViewDialoge =(MemoryGridViewD)okdialog.findViewById(R.id.gridview_Dialode);
        memoryGridViewDialoge.setMemory(memory_two);
        okdialog.show();

//                 new Handler().postDelayed(new Runnable() {
//                      @Override
//                     public void run() {
//                          okdialog.dismiss();
//                          okdialog.cancel();
//                }
//                }, 9000);
////
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//                     LayoutInflater inflater = this.getLayoutInflater();
//      View dialogView = inflater.inflate(R.layout.activity_show_images, null);
//           dialogBuilder.setView(dialogView);
//             alertDialog = dialogBuilder.create();
//
//               alertDialog.show();
//                new Handler().postDelayed(new Runnable() {
//                      @Override
//                     public void run() {
//                          alertDialog.cancel();
//                }
//                }, 9000);


    }






    @Override
    protected void onResume()
    {
        super.onResume();

        memory.onResume( PreferencesService.instance().getPrefs() );

        drawGrid();

    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected void onPause()
    {
        super.onPause();

        memory.onPause( PreferencesService.instance().getPrefs() , mQuit);

    }


    /**
     * {@inheritDoc }
     */
    public void onComplete(int countMove)
    {
        int nHighScore = PreferencesService.instance().getHiScore();
        String title = getString(R.string.success_title);
        Object[] args = { countMove, nHighScore };
        String message = MessageFormat.format(getString(R.string.success), args );
        int icon = R.drawable.win;
        if (countMove < nHighScore)
        {
            title = getString(R.string.hiscore_title);
            message = MessageFormat.format(getString(R.string.hiscore), args );
            icon = R.drawable.hiscore;

            PreferencesService.instance().saveHiScore(countMove);
        }
        this.showEndDialog(title, message, icon);
    }

    /**
     * {@inheritDoc }
     */
    public void onUpdateView()
    {

        drawGrid();
    }

    /**
     * Draw or redraw the grid
     */
    private void drawGrid()
    {
        mGridView.update();
     }


 }
