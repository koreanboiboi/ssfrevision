package exam.revision.day16weather.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Weather {

    private String main;
    private String description;
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Weather create(JsonObject jo) {

        Weather weather = new Weather();
        weather.setMain(jo.getString("main"));
        weather.setDescription(jo.getString("description"));
        weather.setIcon(jo.getString("icon"));

        return weather;

    }

    public JsonObject toJson(){

        return Json.createObjectBuilder()
                .add("main", main)
                .add("description", description)
                .add("icon", icon)
                .build();
    }

}
