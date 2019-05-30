package jdurao.kschool;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import jdurao.kschool.enums.DataTablesEnum;
import jdurao.kschool.util.TestDataGenerator;
import org.bson.Document;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.mongodb.client.model.Filters.eq;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx16G"})
@Warmup(iterations = 0)
@Measurement(iterations = 4)
public class MongoOperations {
    @Param({"1"})
    public int iterations;

    MongoClient client;
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

    Integer minSmall = 0, minMedium = 0, minLarge = 0;

    Boolean executed = false;
    Boolean executedSetup = false;

    public void main() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(jdurao.kschool.MongoOperations.class.getSimpleName())
                .forks(1)
                .operationsPerInvocation(1)
                .build();

        new Runner(opt).run();
    }

    private void createArea(int maxId) {
        Integer newMaxId = maxId + minSmall;

        for (int i = minSmall; i <= newMaxId; i++)
            valuesSmall.add(TestDataGenerator.createAreaDocument((long)i));

        minSmall = minSmall + maxId;
    }
    private void createArtist(int maxId, int maxAreaId) {
        Integer newMaxId = maxId + minLarge;

        for (int i = minLarge; i <= newMaxId; i++)
            valuesLarge.add(TestDataGenerator.createArtistDocument((long)i, maxAreaId));

        minLarge = minLarge + maxId;
    }
    private void createFormat(int maxId) {
        for (int i = 0; i <= maxId; i++)
            valuesFormat.add(TestDataGenerator.createFormatDocument((long)i));
    }
    private void createLabel(int maxId, int maxAreaId) {
        Integer newMaxId = maxId + minMedium;

        for (int i = minMedium; i <= newMaxId; i++)
            valuesMedium.add(TestDataGenerator.createLabelDocument((long)i, maxAreaId));

        minMedium = minMedium + maxId;
    }
    private void createLanguage(int maxId) {
        for (int i = 0; i <= maxId; i++)
            valuesLanguages.add(TestDataGenerator.createLanguageDocument((long)i));
    }
    private void createMediums(int maxId, int maxFormatId) {
        for (int i = 0; i <= maxId; i++)
            valuesMediums.add(TestDataGenerator.createMediumDocument((long)i, maxFormatId));
    }
    private void createPlaces(int maxId) {
        for (int i = 0; i <= maxId; i++)
            valuesPlaces.add(TestDataGenerator.createPlaceDocument((long)i));
    }
    private void createRecords(int maxId, int maxArtistId) {
        for (int i = 0; i <= maxId; i++)
            valuesRecords.add(TestDataGenerator.createRecordDocument((long)i, maxArtistId));
    }
    private void createReleases(int maxId, Integer maxRecordId, Integer maxLanguageId, Integer maxLabelId, Integer maxMediumId) {
        for (int i = 0; i <= maxId; i++)
            valuesReleases.add(TestDataGenerator.createReleaseDocument((long) i, maxRecordId, maxLanguageId, maxLabelId, maxMediumId));
    }
    private void createTracks(int maxId, Integer maxRecordId) {
        for (int i = 0; i <= maxId; i++)
            valuesTracks.add(TestDataGenerator.createTrackDocument((long) i, maxRecordId));
    }

    @Setup(Level.Iteration)
    public void setupSets() {
        if (!executedSetup) {
            client = new MongoClient("localhost", 27017);
            database = client.getDatabase("mongoDatabase");
            executedSetup = true;
        }

        createArea(100);
        createLabel(1000, 100);
        createArtist(10000, 100);
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
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
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    public void abCreationData() {
        if (!executed) {
            createFormat(10);
            createLanguage(100);
            createMediums(10, 100);
            createPlaces(100);
            createRecords(100, 10000);
            createReleases(10, 100, 100, 100, 100);
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
    @Warmup(iterations = 0)
    @Measurement(iterations = 4)
    public void bInsertOneRecord() {
        database.getCollection(DataTablesEnum.AREAS.getName()).insertOne(TestDataGenerator.createAreaDocument(UUID.randomUUID().getMostSignificantBits()));
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 4)
    public void cInsertMultipleRecordsSmall() {
        database.getCollection(DataTablesEnum.AREAS.getName()).insertMany(valuesSmall);
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 4)
    public void dInsertMultipleRecordsMedium() {
        database.getCollection(DataTablesEnum.LABELS.getName()).insertMany(valuesMedium);
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 4)
    public void eInsertMultipleRecordsLarge() {
        database.getCollection(DataTablesEnum.ARTISTS.getName()).insertMany(valuesLarge);
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 4)
    public void fUpdateFieldOne() {
        database.getCollection(DataTablesEnum.AREAS.getName()).updateOne(eq("id", 99), new Document("$set", new Document("comment", "New Comment")));
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 4)
    public void gUpdateFieldMultiple() {
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 4)
    public void hUpdateFieldLinked() {
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 4)
    public void iDeleteKeyValuePair() {
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 4)
    public void jDeleteDocument() {
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 4)
    public void kSelectSimple() {
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 4)
    public void lSelectFiltered() {
    }

    @Benchmark
    @Warmup(iterations = 0)
    @Measurement(iterations = 4)
    public void mSelectJoined() {
    }
}
