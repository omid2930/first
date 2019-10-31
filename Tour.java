package app.mma.jsonxml2;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Tour {
    private int tourId;
    private String tourTitle;
    private String packageTitle;
    private String description;
    private double price;
    private String difficulty;
    private int length;
    private String image;
    private String link;

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getTourTitle() {
        return tourTitle;
    }

    public void setTourTitle(String tourTitle) {
        this.tourTitle = tourTitle;
    }

    public String getPackageTitle() {
        return packageTitle;
    }

    public void setPackageTitle(String packageTitle) {
        this.packageTitle = packageTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @NonNull
    @Override
    public String toString() {
        return tourTitle+"\n $ "+price;
    }
    public static JSONArray TourListToJsonarray(List<Tour> tours){

        JSONArray json=new JSONArray();
        try {
            for (Tour tour:tours){
                JSONObject jsonObject=new JSONObject();

                jsonObject.put("tourId",tour.getTourId());
                jsonObject.put("tourTitle",tour.getTourTitle());
                jsonObject.put("packageTitle",tour.getPackageTitle());
                jsonObject.put("description",tour.getDescription());
                jsonObject.put("price",tour.getPrice());
                jsonObject.put("difficulty",tour.getDifficulty());
                jsonObject.put("length",tour.getLength());
                jsonObject.put("image",tour.getImage());
                jsonObject.put("link",tour.getLink());

                json.put(jsonObject);
            }


        }catch (JSONException e){
            e.printStackTrace();
        }
        return  json;
    }
}
