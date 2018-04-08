package com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_details;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.widget.Toast;

import com.helpmeproductions.willus08.kohlsdeliverable.R;
import com.helpmeproductions.willus08.kohlsdeliverable.injection.item_details.DaggerItemDetailsComponent;
import com.helpmeproductions.willus08.kohlsdeliverable.model.Item;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemDetails extends AppCompatActivity implements ItemDetailsContract.View{
    @Inject ItemDetailsPresenter presenter;

    @BindView(R.id.rvDetailedItemList)
    RecyclerView detailedListOfItems;

    ItemDetailsAdapter adapter;
    DefaultItemAnimator animator;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.OnScrollListener scrollListener;

    private boolean loading = false;
    private boolean firstRun = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        ButterKnife.bind(this);
        setupDagger();
        presenter.addView(this);
        presenter.extractIntent(getIntent());
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupDagger() {
        DaggerItemDetailsComponent.create().inject(this);
    }

    @Override
    public void setupDetailedList(final List<Item> itemList, int position) {


        loading=false;
        // sets up the list to the recycler view
        adapter = new ItemDetailsAdapter(itemList);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        // moves the user to the position after loading the new list
        layoutManager.scrollToPosition(position);
        animator = new DefaultItemAnimator();
        scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // checks to see if the user cant scroll anymore to begin lazy loading
                if(!recyclerView.canScrollHorizontally(1) && !loading){

                    // makes sure the list dose not excede 100 children to save on space
                    if(itemList.size()+1 < 100){
                        presenter.loadItems(itemList.size()+1);

                    }else {
                        // lets the user know that they have reached the limit of the list
                        Toast.makeText(ItemDetails.this, R.string.lazy_load_limit_message, Toast.LENGTH_SHORT).show();
                    }
                    // prevents the user from accidentally Lazy loading multple times at once
                    loading=true;
                }
            }
        };
        detailedListOfItems.addOnScrollListener(scrollListener);
        detailedListOfItems.setAdapter(adapter);
        detailedListOfItems.setLayoutManager(layoutManager);
        detailedListOfItems.setItemAnimator(animator);

        // prevents a crash from seting this up each time the list is lazy loaded
        if(firstRun){
            // snaps the view to go one item at a time
            SnapHelper helper = new PagerSnapHelper();
            helper.attachToRecyclerView(detailedListOfItems);
            firstRun =false;
        }
    }

}
