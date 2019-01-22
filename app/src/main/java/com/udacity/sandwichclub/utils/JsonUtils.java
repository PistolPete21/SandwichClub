package com.udacity.sandwichclub.utils;

import android.net.Uri;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        if (json != null) {
            try {
                JSONObject jsonObject = new JSONObject(json);

                JSONObject name = jsonObject.getJSONObject("name");

                String mainName = name.getString("mainName");
                String alsoKnownAs = name.getString("alsoKnownAs");
                List<String> alsoKnownAsList = Arrays.asList(alsoKnownAs.replaceAll("\\[|\\]", "").replace("\"", "").split("\\s*,\\s*"));
                String placeOfOrigin = jsonObject.getString("placeOfOrigin");
                String description = jsonObject.getString("description");
                String image = jsonObject.getString("image");
                String ingredients = jsonObject.getString("ingredients");
                List<String> ingredientsList = Arrays.asList(ingredients.replaceAll("\\[|\\]", "").replace("\"", "").split("\\s*,\\s*"));

                Sandwich sandwich = new Sandwich();

                sandwich.setMainName(mainName);
                sandwich.setAlsoKnownAs(alsoKnownAsList);
                sandwich.setPlaceOfOrigin(placeOfOrigin);
                sandwich.setDescription(description);
                sandwich.setImage(image);
                sandwich.setIngredients(ingredientsList);

                return sandwich;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }
}
