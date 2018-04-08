package com.helpmeproductions.willus08.kohlsdeliverable.data.local;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;
import com.helpmeproductions.willus08.kohlsdeliverable.model.CartItems;
import com.helpmeproductions.willus08.kohlsdeliverable.model.Item;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SQLHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME="ITEMS";
    private static final int VERSION = 1;

    private static final String TABLE_NAME="CART_ITEMS";
    private static final String KEY = "ID_KEY";
    private static final String ITEM_INFO = "ITEM_INFO";

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String table ="CREATE TABLE " + TABLE_NAME +
                "(" + KEY + " text primary key, "
                + ITEM_INFO + " text" + ")";
        sqLiteDatabase.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void clear(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.close();
    }

    public void addItem(Item item, int amount){
        SQLiteDatabase db = this.getWritableDatabase();
        Gson gson = new Gson();
        Log.d(TAG, "addItem: begin adding item");
        if(checkIfExists(item.getItemId())){
            Log.d(TAG, "addItem: item exists addding extra one");
            CartItems items = getItem(item.getItemId());
//            Log.d(TAG, "addItem: "+ items.getAmount());
            items.setAmount(items.getAmount() + amount);
            String cartInfo = gson.toJson(items);
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY, item.getItemId());
            contentValues.put(ITEM_INFO,cartInfo);
            removeItem(item.getItemId());
            db.insert(TABLE_NAME, null, contentValues);
            db.close();
        } else {
            Log.d(TAG, "addItem: adding new item");
            CartItems cartItem = new CartItems(item,amount);
            String cartInfo = gson.toJson(cartItem);
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY, item.getItemId());
            contentValues.put(ITEM_INFO,cartInfo);
            db.insert(TABLE_NAME, null, contentValues);
            db.close();
        }
    }

    private void removeItem(String itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,
                KEY+" = ? ",
                new String[] { itemId });
    }

    private CartItems getItem(String itemId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME,null);
        CartItems item ;
        res.moveToFirst();
        while(res.moveToNext()){
            if(res.getString(res.getColumnIndex(KEY)).equals(itemId)){
                Gson gson = new Gson();
                item =( gson.fromJson(res.getString(res.getColumnIndex(ITEM_INFO)),CartItems.class));
                res.close();
                Log.d(TAG, "getItem: item found");
                return  item;
            }
        }
        res.close();
        Log.d(TAG, "getItem: no item");
        return null;
    }

    private boolean checkIfExists(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d(TAG, "checkIfExists: trying to find items");
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME,null);
        res.moveToFirst();
        while(!res.isAfterLast()){
            Log.d(TAG, "checkIfExists: checking items in data base");
            if(res.getString(res.getColumnIndex(KEY)).equals(id)){
                Log.d(TAG, "checkIfExists: it exists");
                return true;
            }
            res.moveToNext();
        }
        res.close();
        Log.d(TAG, "checkIfExists: it dosent ");
        return false;

    }

    public List<CartItems> getCartItems(){
        List<CartItems> cartItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME,null);
        res.moveToFirst();

        while(!res.isAfterLast()){
            Gson gson = new Gson();
            cartItems.add( gson.fromJson(res.getString(res.getColumnIndex(ITEM_INFO)),CartItems.class));
            res.moveToNext();
        }
        res.close();

        return cartItems;
    }
}
