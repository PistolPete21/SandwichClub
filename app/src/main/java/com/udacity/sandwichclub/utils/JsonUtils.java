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
                //Using jsonObject to help me do the parsing even though the json is really just a big long string file
                JSONObject jsonObject = new JSONObject(json);
                JSONObject name = jsonObject.getJSONObject("name");

                //Pull out each individual property that we want
                String mainName = name.getString("mainName");
                String alsoKnownAs = name.getString("alsoKnownAs");
                //Had to find a way to convert the string to a list to work with the Sandwich class
                List<String> alsoKnownAsList = Arrays.asList(alsoKnownAs.replaceAll("\\[|\\]", "").replace("\"", "").split("\\s*,\\s*"));
                String placeOfOrigin = jsonObject.getString("placeOfOrigin");
                String description = jsonObject.getString("description");
                String image = jsonObject.getString("image");
                String ingredients = jsonObject.getString("ingredients");
                //Had to find a way to convert the string to a list to work with the Sandwich class
                List<String> ingredientsList = Arrays.asList(ingredients.replaceAll("\\[|\\]", "").replace("\"", "").split("\\s*,\\s*"));

                //Set each property that we pulled out onto the sandwich object for use in the UI
                Sandwich sandwich = new Sandwich();
                sandwich.setMainName(mainName);
                sandwich.setAlsoKnownAs(alsoKnownAsList);
                sandwich.setPlaceOfOrigin(placeOfOrigin);
                sandwich.setDescription(description);
                sandwich.setImage(image);
                sandwich.setIngredients(ingredientsList);

                //Finally just return a sandwich object with our new properties that came from the json
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
