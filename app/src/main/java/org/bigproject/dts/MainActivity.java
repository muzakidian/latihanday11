package org.bigproject.dts;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import swipe.gestures.GestureManager;
import willi.boelke.dts.R;

import org.bigproject.dts.adapter.RecyclerViewAdapterWithOnClick;


/**
 * Here an example implementation af a RecyclerView using the RecyclerAdapterSwipeGestures
 */
public class MainActivity extends AppCompatActivity
{
    private ArrayList<String> list;
    private RecyclerViewAdapterWithOnClick adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillList();
        setupRecyclerView();
    }

    /**
     * Fills the list with a few example strings
     */
    private void fillList()
    {
        list = new ArrayList<>();
        list.add("Item 1");
        list.add("Item 2");
        list.add("Item 3");
        list.add("Item 4");
        list.add("Item 5");
        list.add("Item 6");
        list.add("Item 7");
    }


    /**
     * Setup the RecyclerView, attaches the adapter
     * and the swipe Gestures
     */
    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.demo_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getApplicationContext());
        adapter = new RecyclerViewAdapterWithOnClick(list);


        GestureManager recyclerAdapterSwipeGestures = new GestureManager(rightCallback, leftCallback);

        // Setting background colours
        recyclerAdapterSwipeGestures.setBackgroundColorLeft(new ColorDrawable(Color.RED));
        recyclerAdapterSwipeGestures.setBackgroundColorRight(new ColorDrawable(Color.GREEN));

        // Setting Icons
        recyclerAdapterSwipeGestures.setIconRight(ContextCompat.getDrawable(this, R.drawable.icon));
        recyclerAdapterSwipeGestures.setIconLeft(ContextCompat.getDrawable(this, R.drawable.icon2));
        recyclerAdapterSwipeGestures.setIconSizeMultiplier(2);

        //Set Text
        recyclerAdapterSwipeGestures.setTextLeft("LEFT");
        recyclerAdapterSwipeGestures.setTextRight("RIGHT");

        //Set text size
        recyclerAdapterSwipeGestures.setTextSize(60);

        //Set text colour
        recyclerAdapterSwipeGestures.setTextColor(Color.BLACK);

        // Attach to the Recycler View Adapter
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(recyclerAdapterSwipeGestures);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        recyclerView.setLayoutManager(layoutManager);
        adapter.setOnItemClickListener(onItemClickListener);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }




    private GestureManager.SwipeCallbackLeft leftCallback = new GestureManager.SwipeCallbackLeft()
    {
        @Override
        public void onLeftSwipe(int position)
        {
            Toast.makeText(getApplicationContext(), "You swiped to the Left on the item " + list.get(position), Toast.LENGTH_LONG).show();
            adapter.notifyDataSetChanged();
        }
    };


    private GestureManager.SwipeCallbackRight rightCallback = new GestureManager.SwipeCallbackRight()
    {
        @Override
        public void onRightSwipe(int position)
        {
            Toast.makeText(getApplicationContext(), "You swiped to the Right on the item " + list.get(position), Toast.LENGTH_LONG).show();
            adapter.notifyDataSetChanged();
        }
    };


    private RecyclerViewAdapterWithOnClick.OnItemClickListener onItemClickListener = new RecyclerViewAdapterWithOnClick.OnItemClickListener()
    {
        @Override
        public void onItemClick(int position)
        {
            Toast.makeText(getApplicationContext(), "You clicked on the item " + list.get(position), Toast.LENGTH_LONG).show();
            adapter.notifyDataSetChanged();
        }
    };


}