package jdurao.kschool.util;

import com.google.gson.JsonObject;

import java.util.Date;
import java.util.UUID;

public class TestDataGenerator {
    public static String createAreaJson(Long id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", "area" + id);
        jsonObject.addProperty("sortName", id + "area");
        jsonObject.addProperty("comment", UUID.randomUUID().toString());
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }

    public static String createArtistJson(Long id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", "artist" + id);
        jsonObject.addProperty("sortName", id + "artist");
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }

    public static String createLabelJson(Long id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", "label" + id);
        jsonObject.addProperty("code", new Date().getTime());
        jsonObject.addProperty("sortName", id + "label");
        jsonObject.addProperty("comment", UUID.randomUUID().toString());
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }

    public static String createLanguageJson(Long id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", "name " + id);
        jsonObject.addProperty("sortName", id + "name");
        jsonObject.addProperty("comment", UUID.randomUUID().toString());
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }
}
