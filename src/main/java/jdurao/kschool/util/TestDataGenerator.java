package jdurao.kschool.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jdurao.kschool.pojo.AreaJson;

import java.util.Date;
import java.util.UUID;

public class TestDataGenerator {
    public static String createAreaJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", UUID.randomUUID().toString());
        jsonObject.addProperty("placeId", UUID.randomUUID().toString());
        jsonObject.addProperty("name", UUID.randomUUID().toString());
        jsonObject.addProperty("sortName", UUID.randomUUID().toString());
        jsonObject.addProperty("comment", UUID.randomUUID().toString());
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }
}
