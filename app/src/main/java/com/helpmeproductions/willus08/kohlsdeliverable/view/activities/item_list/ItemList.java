package com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_list;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.os.Bundle;
import android.os.Parcelable;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.helpmeproductions.willus08.kohlsdeliverable.R;
import com.helpmeproductions.willus08.kohlsdeliverable.injection.item_list.DaggerItemListComponent;
import com.helpmeproductions.willus08.kohlsdeliverable.model.Item;
import com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_cart.ItemCart;
import com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_details.ItemDetails;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemList extends AppCompatActivity implements ItemListContract.View {
    private static final String TAG = "test";
    @Inject ItemListPresenter presenter;

    @BindView(R.id.etSearchBox)
    EditText searchBar;

    @BindView(R.id.rvListOfItems)
    RecyclerView listOfItems;

    ItemListAdapter adapter;
    DefaultItemAnimator animator;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.OnScrollListener scrollListener;

    ItemReceiver receiver = new ItemReceiver();
    ScanReceiver scanReceiver = new ScanReceiver();
    IntentFilter filter = new IntentFilter();
    public String currentItem = "";
    public int currentSize = 0;
    boolean loading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        setupDagger();
        ButterKnife.bind(this);
        presenter.addView(this);
        Toolbar toolbar = findViewById(R.id.tbMainToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // loads the currently searched item when the screen rotates
        if(savedInstanceState != null){

            currentItem =savedInstanceState.getString("currentItem");

        }else {
            // gives a default value for when the app first starts
            currentItem = "apple";
        }
        presenter.getItems(currentItem,1);



    }


    @Override
    public void setupDagger() {
        DaggerItemListComponent.create().inject(this);
    }


    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupList(final List<Item> itemList, final int position) {

        layoutManager = new LinearLayoutManager(this);
        animator = new DefaultItemAnimator();
       // this will hopefully keep it from re defaulting once you do a scan item
        if(position == -1){
            adapter = new ItemListAdapter(itemList);
            currentItem = itemList.get(0).getName();
        }else{
        loading=false;
        currentSize = itemList.size();
        // sets the list to the recycler view
        adapter = new ItemListAdapter(itemList);
        // saves the user's spot when lazy loading happens
        layoutManager.scrollToPosition(position);
        scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // begins lazy loading when the user can not scroll down anymore
                if(!recyclerView.canScrollVertically(1) && !loading){
                    // prevents the list from having more than 100 children to save on memory
                   if(currentSize+1 < 100){
                        presenter.getItems(currentItem,currentSize+1);

                   }else {
                       // lets the user know when they can not load anymore
                       Toast.makeText(ItemList.this, R.string.lazy_load_limit_message, Toast.LENGTH_SHORT).show();
                   }
                    loading=true;
                }
            }
        };
        }
        listOfItems.addOnScrollListener(scrollListener);
        listOfItems.setAdapter(adapter);
        listOfItems.setLayoutManager(layoutManager);
        listOfItems.setItemAnimator(animator);

    }

    @Override
    public void goToDetailedItems(int position, List<Item> itemList) {
        // passes the list and the selected position to the next activity
        Intent intent = new Intent(this, ItemDetails.class);
        intent.putExtra("position",position);
        intent.putExtra("item",currentItem);
        intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) itemList);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // sets up the receiver to get information on what item was clicked from the adapter
        filter.addAction("items");
        registerReceiver(receiver,filter);
        IntentFilter scanFilter = new IntentFilter();
        scanFilter.addAction("com.dwexample.ACTION");
        scanFilter.addCategory("android.intent.category.DEFAULT");
        registerReceiver(scanReceiver, scanFilter);
    }


    @Override
    protected void onStop() {
        super.onStop();
        // unregisters the reciver to save on memory
        unregisterReceiver(receiver);
        unregisterReceiver(scanReceiver);
    }

    public void Search(View view) {
        // checks to make sure the search bar has anything in it before attempting to do a new search
        if(!searchBar.getText().toString().equals("")){
        currentItem = searchBar.getText().toString();
        presenter.getItems(currentItem,1);
        }else {
            //warns the user that they have nothing in the search bar
            Toast.makeText(this, R.string.search_warning_toast, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // saves the current item when the screen is rotated
        savedInstanceState.putString("currentItem",currentItem);

    }

    public void Scan(View view) {
        Intent i = new Intent();
        i.setAction("com.dwexample.ACTION");
        i.putExtra("com.symbol.datawedge.data_string",presenter.generateRandomID());
        sendBroadcast(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: I should only see this once");
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.directory,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_cart:
                Intent intent = new Intent(this, ItemCart.class);
                startActivity(intent);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }


    class ItemReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // gets information from the adaptor and passes it on to be sent to the next activity
              List<Item> items = intent.getParcelableArrayListExtra("list");
              int position = intent.getIntExtra("position" , 0);
              goToDetailedItems(position,items);
        }
    }

    class ScanReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            String a = intent.getAction();
            if(a.equals("com.dwexample.ACTION")){
                presenter.scannedCall(intent.getStringExtra("com.symbol.datawedge.data_string"));
            }
        }
    }
}
