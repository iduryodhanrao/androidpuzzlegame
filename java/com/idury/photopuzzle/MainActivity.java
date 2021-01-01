package com.idury.photopuzzle;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private ConstraintLayout mRelativeLayout;
    private Button mButton;

    private PopupWindow mPopupWindow;
    ArrayList<Integer> img = new ArrayList<Integer>();
    Properties butImagPair = new Properties();
    int prevClickedValue = 0;
    int currClickedValue = 0;
    int prevClickedButton = 0;
    int currClickedButton = 0;

    Integer imglist[];

    //ArrayList<Integer> imgOrder=new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        mRelativeLayout = (ConstraintLayout) findViewById(R.id.rl);
        getImages("Animals");
        restartGame();
        //popupDisplay();
    }

    public void getImages(String a) {

        if (a == "Animals") {
            imglist = new Integer[]{R.drawable.cat, R.drawable.cow, R.drawable.dog, R.drawable.duck, R.drawable.eliphant, R.drawable.frog,
                    R.drawable.horse, R.drawable.lion, R.drawable.panda, R.drawable.parrot, R.drawable.penguin, R.drawable.pig, R.drawable.rooster,
                    R.drawable.sheep};
        } else if (a == "Family") {
            imglist = new Integer[]{R.drawable.ash11, R.drawable.aishini1, R.drawable.aishini2, R.drawable.aishini3, R.drawable.nanamma,
                    R.drawable.mom, R.drawable.mom1, R.drawable.nani1, R.drawable.papa1, R.drawable.saira1, R.drawable.tata1};
        } else if (a == "Fruits") {
            imglist = new Integer[]{R.drawable.banana, R.drawable.cherry, R.drawable.grapes, R.drawable.lemon, R.drawable.mango,
                    R.drawable.orange, R.drawable.papaya, R.drawable.pears, R.drawable.pineapple, R.drawable.strawberry, R.drawable.watermelon};
        } else if (a == "Transports") {
            imglist = new Integer[]{R.drawable.trn_airplane, R.drawable.trn_bicycle, R.drawable.trn_bike, R.drawable.trn_bus, R.drawable.trn_car,
                    R.drawable.trn_heli, R.drawable.trn_pickup, R.drawable.trn_ship, R.drawable.trn_train, R.drawable.trn_truck};
        } else if (a == "Cartoons") {
            imglist = new Integer[]{R.drawable.sh_batman, R.drawable.sh_captainamerica, R.drawable.sh_donaldduck, R.drawable.sh_flash,
                    R.drawable.sh_ironman, R.drawable.sh_mickey, R.drawable.sh_minie, R.drawable.sh_mona, R.drawable.sh_peppa,
                    R.drawable.sh_simpson, R.drawable.sh_spiderman, R.drawable.sh_superman};
        } else if (a == "Shapes") {
            imglist = new Integer[]{R.drawable.shp_circle, R.drawable.shp_diamond, R.drawable.shp_heart, R.drawable.shp_oval,
                    R.drawable.shp_rectangle, R.drawable.shp_square, R.drawable.shp_star, R.drawable.shp_triangle};
        }
    }

    public void restartGame() {
        img.clear();
        int i = 1;
        int j = 0;

        List imglist1 = Arrays.asList(imglist);
        Collections.shuffle(imglist1, new Random());
        while (j < 6) {
            img.add((Integer) imglist1.get(j));
            img.add((Integer) imglist1.get(j));
            j = j + 1;
        }
        Collections.shuffle(img);
        while (i < 13) {
            String buttonID = "button" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            ImageButton b = (ImageButton) findViewById(resID);
            b.setImageResource(img.get(i - 1));
            b.setVisibility(View.VISIBLE);
            butImagPair.setProperty(String.valueOf(resID), String.valueOf(img.get(i - 1)));
            i = i + 1;
        }
    }

    public void playSound()
    {
        MediaPlayer audiotoplay= MediaPlayer.create(MainActivity.this,R.raw.nicejob1);
        audiotoplay.start();
        audiotoplay.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                MediaPlayer audiotoplay2 = MediaPlayer.create(MainActivity.this, R.raw.yeahkidsvoice);
                audiotoplay2.start();

            }
        });

    }

    void popupDisplay() {
        // Initialize a new instance of LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.custom_layout, null);
        mPopupWindow = new PopupWindow(
                customView, 1500, 1500
        );
        // Get a reference for the custom view close but    ton
        ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

        // Set a click listener for the popup window close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                mPopupWindow.dismiss();
                System.exit(0);
                //restartGame();
            }
        });
        //get what game to play
        //family picture game button clicked
        ImageButton familyButton = (ImageButton) customView.findViewById(R.id.ib_familypic);
        familyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                mPopupWindow.dismiss();
                getImages("Family");//img.addAll(Arrays.asList(imglist));
                restartGame();
            }
        });
        //animal picture game button clicked
        ImageButton animalButton = (ImageButton) customView.findViewById(R.id.ib_animalpic);
        animalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                mPopupWindow.dismiss();
                getImages("Animals");//img.addAll(Arrays.asList(imglist));
                restartGame();
            }
        });
        //shape picture game button clicked
        ImageButton shapeButton = (ImageButton) customView.findViewById(R.id.ib_shapepic);
        shapeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                mPopupWindow.dismiss();
                getImages("Shapes");//img.addAll(Arrays.asList(imglist));
                restartGame();
            }
        });
        //transport picture game button clicked
        ImageButton transportButton = (ImageButton) customView.findViewById(R.id.ib_transport);
        transportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                mPopupWindow.dismiss();
                getImages("Transports");//img.addAll(Arrays.asList(imglist));
                restartGame();
            }
        });
        //superhero picture game button clicked
        ImageButton cartoonButton = (ImageButton) customView.findViewById(R.id.ib_superheropic);
        cartoonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                mPopupWindow.dismiss();
                getImages("Cartoons");//img.addAll(Arrays.asList(imglist));
                restartGame();
            }
        });
        //fruits picture game button clicked
        ImageButton fruitButton = (ImageButton) customView.findViewById(R.id.ib_fruitpic);
        fruitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                mPopupWindow.dismiss();
                getImages("Fruits");//img.addAll(Arrays.asList(imglist));
                restartGame();
            }
        });
        // Finally, show the popup window at the center location of root relative layout
        mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER, 0, 0);
        playSound();
    }
    public void buttonClicked(View v) {
        //TextView textView=(TextView)findViewById(R.id.T1);
        prevClickedValue = currClickedValue;
        prevClickedButton = currClickedButton;
        ImageButton button = (ImageButton) v;
        Log.i("button clicked", butImagPair.getProperty(String.valueOf(button.getId())));
        currClickedValue = Integer.parseInt(butImagPair.getProperty(String.valueOf(button.getId())));
        currClickedButton = button.getId();
        button.setVisibility(View.INVISIBLE);
        if (prevClickedValue != 0) {
            if (prevClickedValue != currClickedValue) {
                ImageButton prevButton = (ImageButton) findViewById(prevClickedButton);
                prevButton.setVisibility(View.VISIBLE);
            } else {
                butImagPair.remove(String.valueOf(currClickedButton));
                butImagPair.remove(String.valueOf(prevClickedButton));
                currClickedValue = 0;
                currClickedButton = 0;
            }
        }
        Log.i("total pair count", String.valueOf(butImagPair.size()));
        //if (currClickedButton == 0)
        if (butImagPair.size()==0) {
            popupDisplay();

        }

    }
    }

