package com.helpmeproductions.willus08.kohlsdeliverable.view.activities.item_cart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.helpmeproductions.willus08.kohlsdeliverable.R;
import com.helpmeproductions.willus08.kohlsdeliverable.injection.item_cart.DaggerItemCartComponent;
import com.helpmeproductions.willus08.kohlsdeliverable.model.CartItems;
import com.helpmeproductions.willus08.kohlsdeliverable.model.Item;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemCart extends AppCompatActivity implements ItemCartContract.View{

    @Inject ItemCartPresenter presenter;

    @BindView(R.id.rvCartItems)
    RecyclerView itemsListView;

    @BindView(R.id.tvTotalCost)
    TextView costBox;

    ItemCartAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    DefaultItemAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_cart_view);
        setupDagger();
        presenter.addView(this);
        ButterKnife.bind(this);
        setupAdapter();

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setupDagger() {
        DaggerItemCartComponent.create().inject(this);
    }

    public void buyItems(View view) {
        presenter.clearCart(this);
        setupAdapter();
    }

    @Override
    public void setupAdapter() {
        List<CartItems> items = presenter.getCartItemsFromDatabase(this);
        adapter = new ItemCartAdapter(items);
        layoutManager = new LinearLayoutManager(this);
        animator = new DefaultItemAnimator();

        itemsListView.setAdapter(adapter);
        itemsListView.setLayoutManager(layoutManager);
        itemsListView.setItemAnimator(animator);

        if(items.size() == 0){
            showError("No items In cart");
            costBox.setText("Total cost is: $0.00");
        }else {
            costBox.setText(presenter.calculateCost(items));
        }
    }
}
