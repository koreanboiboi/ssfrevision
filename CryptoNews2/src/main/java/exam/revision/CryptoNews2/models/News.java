package exam.revision.CryptoNews2.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class News {
    
    private String id;
        private String title;
        private String body;
        private String imageurl;
    
        public String getImageurl() {
            return imageurl;
        }
        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }
        public String getId() {return id;}
        public void setId(String id) {this.id = id;}
    
        public String getTitle() {return title;}
        public void setTitle(String title) {this.title = title;}
    
        public String getBody() {return body;}
        public void setBody(String body) {this.body = body;}
    
        public static News create(String jsonStr){
            StringReader strReader = new StringReader(jsonStr);
            JsonReader reader = Json.createReader(strReader);
            return create(reader.readObject());
        }
    
        public static News create(JsonObject jo) {
            News news = new News();
            news.setId(jo.getString("id"));
            news.setTitle(jo.getString("title"));
            news.setBody(jo.getString("body"));
            news.setImageurl(jo.getString("imageurl"));
            
            return news;
        
        }
    
        public JsonObject toJson() {
            return Json.createObjectBuilder()
                        .add("id", id)
                        .add("title", title)
                        .add("body", body)
                        .add("imageurl", imageurl)
                        .build();
        }

}
