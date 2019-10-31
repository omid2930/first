package app.mma.jsonxml2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FlowerJsonParser {
    public static List<Flower> parseJson(InputStream input){
        String content = Utils.convertInputStreamToString(input);
        return parseJson(content);
    }

    public static List<Flower> parseJson(String jsonString){
        List<Flower> flowerList = new ArrayList<>();
        try {
            JSONArray json = new JSONArray(jsonString);
            for (int i = 0; i < json.length() ; i++){
                JSONObject jsonobject = json.getJSONObject(i);
                Flower flower = new Flower();
                flower.setName(jsonobject.getString("name"));
                flower.setCategory(jsonobject.getString("category"));
                flower.setPhoto(jsonobject.getString("photo"));
                flower.setInstructions(jsonobject.getString("instructions"));
                flower.setProductId((int) jsonobject.getLong("productId"));
                flower.setPrice(jsonobject.getDouble("price"));
                flowerList.add(flower);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return flowerList;
    }

}
