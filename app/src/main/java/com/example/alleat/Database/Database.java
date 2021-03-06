package com.example.alleat.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.alleat.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME="AllEatDB.db";
    private static final int DB_VERSION=1;

    public Database(Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @SuppressLint("Range")
    public List<Order> getCarts(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect={"ProductID","ProductName","Quantity","Price"};
        String sqlTable="OrderDeatails";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Order> result = new ArrayList<>();
        if(c.moveToFirst()){
            do {
                result.add(new Order(c.getString(c.getColumnIndex("ProductID")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price"))));
            } while (c.moveToNext());
        }
        return result;
    }

public void addToCart(Order order){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDeatails (ProductID,ProductName,Quantity,Price) VALUES ('%s' , '%s' , '%s' , '%s');",
        order.getProductID(),
        order.getProductName(),
        order.getQuantity(),
        order.getPrice());
        db.execSQL(query);
}

    public void cleanCart(){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDeatails");
        db.execSQL(query);
    }

}
