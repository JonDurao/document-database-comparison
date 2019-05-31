package jdurao.kschool.util;

import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import org.bson.Document;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class TestDataGenerator {
    public static String createAreaJson(Integer id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", "area" + id);
        jsonObject.addProperty("sortName", id + "area");
        jsonObject.addProperty("comment", UUID.randomUUID().toString());
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }
    public static Document createAreaDocument(Integer id) {
        return new Document()
                .append("_id", id)
                .append("name", "area" + id)
                .append("sortName", id + "area")
                .append("comment", UUID.randomUUID().toString())
                .append("updatedDate", new Date().toString());
    }

    public static String createArtistJson(Integer id, Integer maxAreaId) {
        Random random = new Random();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("areaId", random.nextInt(maxAreaId));
        jsonObject.addProperty("name", "artist" + id);
        jsonObject.addProperty("sortName", id + "artist");
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }
    public static Document createArtistDocument(Integer id, Integer maxAreaId) {
        Random random = new Random();

        return new Document()
                .append("_id", id)
                .append("areaId", random.nextInt(maxAreaId))
                .append("name", "artist" + id)
                .append("sortName", id + "artist")
                .append("updatedDate", new Date().toString());
    }

    public static String createRecordJson(Integer id, Integer maxArtistId) {
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
    public static Document createRecordDocument(Integer id, Integer maxArtistId) {
        Random random = new Random();

        return new Document()
                .append("_id", id)
                .append("artistId", random.nextInt(maxArtistId))
                .append("name", "record" + id)
                .append("length", random.nextInt(2))
                .append("comment", UUID.randomUUID().toString())
                .append("updatedDate", new Date().toString());
    }

    public static String createTrackJson(Integer id, Integer maxRecordId) {
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
    public static Document createTrackDocument(Integer id, Integer maxRecordId) {
        Random random = new Random();

        return new Document()
                .append("_id", id)
                .append("recordId", random.nextInt(maxRecordId))
                .append("name", "track" + id)
                .append("length", random.nextInt(75))
                .append("number", random.nextInt(12))
                .append("comment", UUID.randomUUID().toString())
                .append("updatedDate", new Date().toString());
    }

    public static String createLabelJson(Integer id, Integer maxAreaId) {
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
    public static Document createLabelDocument(Integer id, Integer maxAreaId) {
        Random random = new Random();

        return new Document()
                .append("_id", id)
                .append("areaId", random.nextInt(maxAreaId))
                .append("name", "label" + id)
                .append("code", new Date().getTime())
                .append("sortName", id + "label")
                .append("comment", UUID.randomUUID().toString())
                .append("updatedDate", new Date().toString());
    }

    public static String createLanguageJson(Integer id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", "language " + id);

        return jsonObject.toString();
    }
    public static Document createLanguageDocument(Integer id) {
        return new Document()
                .append("_id", id)
                .append("name", "language " + id);
    }

    public static String createFormatJson(Integer id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", "format " + id);

        return jsonObject.toString();
    }
    public static Document createFormatDocument(Integer id) {
        return new Document()
                .append("_id", id)
                .append("name", "format " + id);
    }

    public static String createMediumJson(Integer id, Integer maxFormatId) {
        Random random = new Random();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("formatId", random.nextInt(maxFormatId));
        jsonObject.addProperty("name", "medium " + id);
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }
    public static Document createMediumDocument(Integer id, Integer maxFormatId) {
        Random random = new Random();

        return new Document()
                .append("_id", id)
                .append("formatId", random.nextInt(maxFormatId))
                .append("name", "medium " + id)
                .append("updatedDate", new Date().toString());
    }

    public static String createPlaceJson(Integer id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("name", "place " + id);
        jsonObject.addProperty("address", "address at  " + id);
        jsonObject.addProperty("coordinates", id + "x" + id);
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }
    public static Document createPlaceDocument(Integer id) {
        return new Document()
                .append("_id", id)
                .append("name", "place " + id)
                .append("address", "address at  " + id)
                .append("coordinates", id + "x" + id)
                .append("updatedDate", new Date().toString());
    }

    public static String createReleaseJson(Integer id, Integer maxRecordId, Integer maxLanguageId, Integer maxLabelId, Integer maxMediumId) {
        Random random = new Random();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("recordId", random.nextInt(2));
        jsonObject.addProperty("languageId", random.nextInt(maxLanguageId));
        jsonObject.addProperty("labelId", random.nextInt(maxLabelId));
        jsonObject.addProperty("mediumId", random.nextInt(maxMediumId));
        jsonObject.addProperty("barcode", random.nextInt(1000));
        jsonObject.addProperty("status", "status " + id);
        jsonObject.addProperty("quality", random.nextInt(100));
        jsonObject.addProperty("comment", UUID.randomUUID().toString());
        jsonObject.addProperty("updatedDate", new Date().toString());

        return jsonObject.toString();
    }
    public static Document createReleaseDocument(Integer id, Integer maxRecordId, Integer maxLanguageId, Integer maxLabelId, Integer maxMediumId) {
        Random random = new Random();

        return new Document()
                .append("_id", id)
                .append("recordId", random.nextInt(2))
                .append("languageId", random.nextInt(maxLanguageId))
                .append("labelId", random.nextInt(maxLabelId))
                .append("mediumId", random.nextInt(maxMediumId))
                .append("barcode", random.nextInt(1000))
                .append("status", "status " + id)
                .append("quality", random.nextInt(100))
                .append("comment", UUID.randomUUID().toString())
                .append("updatedDate", new Date().toString());
    }
}
