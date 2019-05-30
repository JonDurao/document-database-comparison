package jdurao.kschool.util;

import com.google.gson.JsonObject;

import java.util.Date;
import java.util.Random;
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

    public static String createArtistJson(Long id, Integer maxAreaId) {
        Random random = new Random();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("areaId", random.nextInt(maxAreaId));
        jsonObject.addProperty("name", "artist" + id);
        jsonObject.addProperty("sortName", id + "artist");
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }

    public static String createRecordJson(Long id, Integer maxArtistId) {
        Random random = new Random();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("artistId", random.nextInt(maxArtistId));
        jsonObject.addProperty("name", "record" + id);
        jsonObject.addProperty("length", random.nextInt(2));
        jsonObject.addProperty("comment", UUID.randomUUID().toString());
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }

    public static String createTrackJson(Long id, Integer maxRecordId) {
        Random random = new Random();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("recordId", random.nextInt(maxRecordId));
        jsonObject.addProperty("name", "track" + id);
        jsonObject.addProperty("length", random.nextInt(75));
        jsonObject.addProperty("number", random.nextInt(12));
        jsonObject.addProperty("comment", UUID.randomUUID().toString());
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }

    public static String createLabelJson(Long id, Integer maxAreaId) {
        Random random = new Random();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("areaId", random.nextInt(maxAreaId));
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
        jsonObject.addProperty("name", "language " + id);

        return jsonObject.toString();
    }

    public static String createFormatJson(Long id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", "format " + id);

        return jsonObject.toString();
    }

    public static String createMediumJson(Long id, Integer maxFormatId) {
        Random random = new Random();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("formatId", random.nextInt(maxFormatId));
        jsonObject.addProperty("name", "medium " + id);
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }

    public static String createPlaceJson(Long id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", "place " + id);
        jsonObject.addProperty("address", "address at  " + id);
        jsonObject.addProperty("coordinates", id + "x" + id);
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }

    public static String createReleaseJson(Long id, Integer maxRecordId, Integer maxLanguageId, Integer maxLabelId, Integer maxMediumId) {
        Random random = new Random();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("recordId", random.nextInt(maxRecordId));
        jsonObject.addProperty("languageId", random.nextInt(maxLanguageId));
        jsonObject.addProperty("labelId", random.nextInt(maxLabelId));
        jsonObject.addProperty("mediumId", random.nextInt(maxMediumId));
        jsonObject.addProperty("barcode", (long) random.nextInt(1000));
        jsonObject.addProperty("name", "place " + id);
        jsonObject.addProperty("address", "address at  " + id);
        jsonObject.addProperty("coordinates", id + "x" + id);
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }
}
