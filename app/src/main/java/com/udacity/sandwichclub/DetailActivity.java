package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView nameTextView;
    private TextView alsoKnownAsTextView;
    private TextView placeOfOriginTextView;
    private TextView descriptionTextView;
    private TextView ingredientsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsImageView = findViewById(R.id.image_iv);
        nameTextView = findViewById(R.id.name_text_view);
        alsoKnownAsTextView = findViewById(R.id.also_known_as_text_view);
        placeOfOriginTextView = findViewById(R.id.place_of_origin_text_view);
        descriptionTextView = findViewById(R.id.description_text_view);
        ingredientsTextView = findViewById(R.id.ingredients_text_view);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        assert intent != null;
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.ic_wb_cloudy_black_24dp)
                .error(R.drawable.ic_error_black_24dp)
                .into(ingredientsImageView);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        nameTextView.setText(sandwich.getMainName());
        //need some way of looping through each list item so just using a for loop for now
        for (int i = 0; i < sandwich.getAlsoKnownAs().size(); i++) {
            if (i != sandwich.getAlsoKnownAs().size() - 1) {
                alsoKnownAsTextView.append(sandwich.getAlsoKnownAs().get(i) + ", ");
            } else {
                alsoKnownAsTextView.append(sandwich.getAlsoKnownAs().get(i));
            }
        }

        placeOfOriginTextView.setText(sandwich.getPlaceOfOrigin());
        descriptionTextView.setText(sandwich.getDescription());
        //need some way of looping through each list item so just using a for loop for now
        for (int i = 0; i < sandwich.getIngredients().size(); i++) {
            if (i != sandwich.getIngredients().size() - 1) {
                ingredientsTextView.append(sandwich.getIngredients().get(i) + ", ");
            } else {
                ingredientsTextView.append("and " + sandwich.getIngredients().get(i) + ".");
            }
        }
    }
}
