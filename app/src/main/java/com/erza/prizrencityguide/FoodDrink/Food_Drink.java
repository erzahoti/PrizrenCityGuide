package com.erza.prizrencityguide.FoodDrink;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.erza.prizrencityguide.MapsActivity;
import com.erza.prizrencityguide.R;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Donika on 12/24/2016.
 */

public class Food_Drink extends AppCompatActivity implements AsyncResponse {
    private ArrayList<Food_Drink_DB> productList;
    private ListView lvProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_drink_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Food_Drink.this, MapsActivity.class);
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);






        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(Food_Drink.this, this );
        taskRead.execute("http://www.regjisori.com/pcg/Food_drink/food_drink.php");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return  true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void processFinish(String s) {
        productList = new JsonConverter<Food_Drink_DB>().toArrayList(s, Food_Drink_DB.class);
        BindDictionary<Food_Drink_DB> dict = new BindDictionary<Food_Drink_DB>();
        dict.addStringField(R.id.tvName, new StringExtractor<Food_Drink_DB>() {
            @Override
            public String getStringValue(Food_Drink_DB FoodDrinkDB, int i) {
                return FoodDrinkDB.emri;
            }
        });
        dict.addStringField(R.id.tvPershkrimi, new StringExtractor<Food_Drink_DB>() {
            @Override
            public String getStringValue(Food_Drink_DB FoodDrinkDB, int i) {
                return "" + FoodDrinkDB.pershkrimi;
            }
        });
        dict.addStringField(R.id.tvLokacioni, new StringExtractor<Food_Drink_DB>() {
            @Override
            public String getStringValue(Food_Drink_DB FoodDrinkDB, int i) {
                return "" + FoodDrinkDB.lokacioni;
            }
        });
        dict.addStringField(R.id.tvLloji, new StringExtractor<Food_Drink_DB>() {
            @Override
            public String getStringValue(Food_Drink_DB FoodDrinkDB, int i) {
                return "" + FoodDrinkDB.lloji;
            }
        });
        dict.addStringField(R.id.tvKoordinatat, new StringExtractor<Food_Drink_DB>() {
            @Override
            public String getStringValue(Food_Drink_DB FoodDrinkDB, int i) {
                return "" + FoodDrinkDB.koordinantat;
            }
        });

        dict.addDynamicImageField(R.id.ivImazhi,
                new StringExtractor<Food_Drink_DB>() {
                    @Override
                    public String getStringValue(Food_Drink_DB FoodDrinkDB, int i) {
                        return FoodDrinkDB.imazhi_link;
                    }
                }, new DynamicImageLoader() {
                    @Override
                    public void loadImage(String link, ImageView imageView) {
                        Picasso.with(Food_Drink.this).load(link).into(imageView);
                    }
                });


        FunDapter<Food_Drink_DB> adapter = new FunDapter<>(Food_Drink.this, productList, R.layout.food_drink_layout_list,dict);
        lvProduct = (ListView)findViewById(R.id.lvProduct);
        lvProduct.setAdapter(adapter);

    }



}
