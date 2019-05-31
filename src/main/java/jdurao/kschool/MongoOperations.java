package jdurao.kschool;

import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import jdurao.kschool.enums.DataTablesEnum;
import jdurao.kschool.util.TestDataGenerator;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import javax.print.Doc;
import javax.xml.crypto.Data;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx16G"})

public class MongoOperations {
    /*MongoClient client;
    MongoDatabase database;

    List<Document> valuesSmall = new ArrayList<>();
    List<Document> valuesMedium = new ArrayList<>();
    List<Document> valuesLarge = new ArrayList<>();

    List<Document> valuesFormat = new ArrayList<>();
    List<Document> valuesLanguages = new ArrayList<>();
    List<Document> valuesMediums = new ArrayList<>();
    List<Document> valuesPlaces = new ArrayList<>();
    List<Document> valuesRecords = new ArrayList<>();
    List<Document> valuesReleases = new ArrayList<>();
    List<Document> valuesTracks = new ArrayList<>();

    Boolean executed = false;
    Boolean executedLoop = false;
    Boolean executedSetup = false;

    public void main() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(jdurao.kschool.MongoOperations.class.getSimpleName())
                .forks(1)
                .mode(Mode.AverageTime)
                .measurementTime(TimeValue.milliseconds(1))
                .measurementIterations(1)
                .warmupIterations(0)
                .build();

        new Runner(opt).run();
    }

    private void createArea(int maxId) {
        for (int i = 0; i <= maxId; i++)
            valuesSmall.add(TestDataGenerator.createAreaDocument( i));
    }
    private void createArtist(int maxId, int maxAreaId) {
        for (int i = 0; i <= maxId; i++)
            valuesLarge.add(TestDataGenerator.createArtistDocument(i, maxAreaId));
    }
    private void createFormat(int maxId) {
        for (int i = 0; i <= maxId; i++)
            valuesFormat.add(TestDataGenerator.createFormatDocument(i));
    }
    private void createLabel(int maxId, int maxAreaId) {
        for (int i = 0; i <= maxId; i++)
            valuesMedium.add(TestDataGenerator.createLabelDocument(i, maxAreaId));
    }
    private void createLanguage(int maxId) {
        for (int i = 0; i <= maxId; i++)
            valuesLanguages.add(TestDataGenerator.createLanguageDocument(i));
    }
    private void createMediums(int maxId, int maxFormatId) {
        for (int i = 0; i <= maxId; i++)
            valuesMediums.add(TestDataGenerator.createMediumDocument(i, maxFormatId));
    }
    private void createPlaces(int maxId) {
        for (int i = 0; i <= maxId; i++)
            valuesPlaces.add(TestDataGenerator.createPlaceDocument(i));
    }
    private void createRecords(int maxId, int maxArtistId) {
        for (int i = 0; i <= maxId; i++)
            valuesRecords.add(TestDataGenerator.createRecordDocument(i, maxArtistId));
    }
    private void createReleases(int maxId, Integer maxRecordId, Integer maxLanguageId, Integer maxLabelId, Integer maxMediumId) {
        for (int i = 0; i <= maxId; i++)
            valuesReleases.add(TestDataGenerator.createReleaseDocument( i, maxRecordId, maxLanguageId, maxLabelId, maxMediumId));
    }
    private void createTracks(int maxId, Integer maxRecordId) {
        for (int i = 0; i <= maxId; i++)
            valuesTracks.add(TestDataGenerator.createTrackDocument( i, maxRecordId));
    }

    @Setup(Level.Invocation)
    public void setupSets() {
        if (!executedSetup) {
            client = new MongoClient("localhost", 27017);
            database = client.getDatabase("mongoDatabase");
            executedSetup = true;
            createArea(100);
            createLabel(1000, 100);
            createArtist(10000, 100);
        }
    }

    @Benchmark
    public void aaCleanup() {
        database.getCollection(DataTablesEnum.AREAS.getName()).drop();
        database.getCollection(DataTablesEnum.ARTISTS.getName()).drop();
        database.getCollection(DataTablesEnum.FORMATS.getName()).drop();
        database.getCollection(DataTablesEnum.LABELS.getName()).drop();
        database.getCollection(DataTablesEnum.LANGUAGES.getName()).drop();
        database.getCollection(DataTablesEnum.MEDIUMS.getName()).drop();
        database.getCollection(DataTablesEnum.PLACES.getName()).drop();
        database.getCollection(DataTablesEnum.RECORDS.getName()).drop();
        database.getCollection(DataTablesEnum.RELEASES.getName()).drop();
        database.getCollection(DataTablesEnum.TRACKS.getName()).drop();
    }

    @Benchmark
    public void abCreationData() {
        if (!executed) {
            createFormat(10);
            createLanguage(100);
            createMediums(10, 100);
            createPlaces(100);
            createRecords(100, 10000);
            createReleases(200, 100, 100, 100, 100);
            createTracks(1000, 100);

            database.createCollection(DataTablesEnum.AREAS.getName());
            database.createCollection(DataTablesEnum.ARTISTS.getName());
            database.createCollection(DataTablesEnum.FORMATS.getName());
            database.createCollection(DataTablesEnum.LABELS.getName());
            database.createCollection(DataTablesEnum.LANGUAGES.getName());
            database.createCollection(DataTablesEnum.MEDIUMS.getName());
            database.createCollection(DataTablesEnum.PLACES.getName());
            database.createCollection(DataTablesEnum.RECORDS.getName());
            database.createCollection(DataTablesEnum.RELEASES.getName());
            database.createCollection(DataTablesEnum.TRACKS.getName());

            database.getCollection(DataTablesEnum.FORMATS.getName()).insertMany(valuesFormat);
            database.getCollection(DataTablesEnum.LANGUAGES.getName()).insertMany(valuesLanguages);
            database.getCollection(DataTablesEnum.MEDIUMS.getName()).insertMany(valuesMediums);
            database.getCollection(DataTablesEnum.PLACES.getName()).insertMany(valuesPlaces);
            database.getCollection(DataTablesEnum.RECORDS.getName()).insertMany(valuesRecords);
            database.getCollection(DataTablesEnum.RELEASES.getName()).insertMany(valuesReleases);
            database.getCollection(DataTablesEnum.TRACKS.getName()).insertMany(valuesTracks);

            executed = true;
        }
    }

    @Benchmark
    public void bInsertOneRecord() {
        database.getCollection(DataTablesEnum.AREAS.getName()).insertOne(TestDataGenerator.createAreaDocument(200000));
    }

    @Benchmark
    public void cInsertMultipleRecordsSmall() {
        database.getCollection(DataTablesEnum.AREAS.getName()).insertMany(valuesSmall);
    }

    @Benchmark
    public void dInsertMultipleRecordsMedium() {
        database.getCollection(DataTablesEnum.LABELS.getName()).insertMany(valuesMedium);
    }

    @Benchmark
    public void eInsertMultipleRecordsLarge() {
        database.getCollection(DataTablesEnum.ARTISTS.getName()).insertMany(valuesLarge);
    }

    @Benchmark
    public void fUpdateFieldOne() {
        MongoCollection<Document> areas = database.getCollection(DataTablesEnum.AREAS.getName());
        Bson filter = null;
        Bson query = null;

        filter = eq("_id", 0);
        query = combine(set("comment", "comment"));
        UpdateResult result = areas.updateMany(filter, query);
        System.out.println(result.getModifiedCount());

        UpdateResult updateResult =
                database.getCollection(DataTablesEnum.AREAS.getName())
                        .updateOne(filter, query);
        System.out.println(updateResult.getModifiedCount());
    }

    @Benchmark
    public void gUpdateFieldMultiple() {
        MongoCollection<Document> artists = database.getCollection(DataTablesEnum.ARTISTS.getName());
        Bson filter = null;
        Bson query = null;

        filter = lte("_id", 10);
        query = combine(set("areaId", 9999));
        UpdateResult result = artists.updateMany(filter, query);
        System.out.println(result.getModifiedCount());
    }

    @Benchmark
    public void hUpdateFieldLinked() {
        MongoCollection<Document> releases = database.getCollection(DataTablesEnum.RELEASES.getName());
        MongoCollection<Document> records = database.getCollection(DataTablesEnum.RECORDS.getName());
        MongoCursor<Document> valuesReleases = releases.find(lt("_id", 50)).iterator();
        MongoCursor<Document> valuesRecords = records.find().iterator();

        UpdateOptions options = new UpdateOptions().upsert(true);

        try {
            while (valuesReleases.hasNext()) {
                Integer artistId = null;
                Document release = valuesReleases.next();

                System.out.println(release.get("_id"));

                secondWhile:
                while (valuesRecords.hasNext()) {
                    Document record = valuesRecords.next();

                    System.out.println("RECORD " + record.get("_id"));

                    Integer id = (Integer) record.get("_id");
                    Integer recordId = (Integer) release.get("recordId");

                    if (Math.toIntExact(id) == recordId) {
                        artistId = (Integer) record.get("artistId");
                        System.out.println("ARTIST ID " + artistId);
                        break secondWhile;
                    }
                }

                if (artistId != null) {
                    Bson filter = null;
                    Bson query = null;

                    filter = eq("_id", release.get("_id"));
                    query = combine(set("comment", "New Comment"), setOnInsert("artistId", artistId));

                    releases.updateOne(filter, new Document("$set", query));
                }
            }
        } finally {
            valuesReleases.close();
            valuesRecords.close();
        }
    }

    @Benchmark
    public void iDeleteKeyValuePair() {
        MongoCollection<Document> areas = database.getCollection(DataTablesEnum.AREAS.getName());
        Bson filter = null;
        Bson query = null;

        filter = lt("_id", 10);
        query = combine(unset("comment"));
        UpdateResult result = areas.updateMany(filter, query);
        System.out.println(result.getModifiedCount());
    }

    @Benchmark
    public void jDeleteDocument() {
        MongoCollection<Document> areas = database.getCollection(DataTablesEnum.AREAS.getName());
        Bson filter = null;
        Bson query = null;

        filter = lt("_id", 10);
        DeleteResult result = areas.deleteMany(filter);
        System.out.println(result.getDeletedCount());
    }

    @Benchmark
    public void kSelectSimple() {
        MongoCollection<Document> formats = database.getCollection(DataTablesEnum.FORMATS.getName());
        formats.find();
    }

    @Benchmark
    public void lSelectFiltered() {
        MongoCollection<Document> records = database.getCollection(DataTablesEnum.RECORDS.getName());
        Bson filter = null;

        filter = eq("_id", 1);
        records.find(filter);
    }

    @Benchmark
    public void mSelectJoined() {
        MongoCollection<Document> mediums = database.getCollection(DataTablesEnum.RECORDS.getName());
        MongoCollection<Document> releases = database.getCollection(DataTablesEnum.RECORDS.getName());
        MongoCollection<Document> formats = database.getCollection(DataTablesEnum.RECORDS.getName());
        Bson filterMedium = null;
        Bson filterRelease = null;

        Iterator<Document> iteratorReleases = releases.find().iterator();

        while (iteratorReleases.hasNext()) {
            Document release = iteratorReleases.next();

            filterRelease = eq("_id", release.get("mediumId"));

            Iterator<Document> iteratorMediums = mediums.find(filterRelease).iterator();

            while (iteratorMediums.hasNext()) {
                Document medium = iteratorMediums.next();

                filterMedium = eq("_id", medium.get("formatId"));

                Iterator<Document> iteratorFormats = formats.find(filterMedium).iterator();

                while (iteratorFormats.hasNext()) {
                    Document format = iteratorFormats.next();

                    System.out.println("FORMAT " + format.get("_id"));
                }
            }
        }
    }*/
}
