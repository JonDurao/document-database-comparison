package jdurao.kschool;

import jdurao.kschool.entities.Areas;
import jdurao.kschool.entities.Formats;
import jdurao.kschool.entities.Records;
import jdurao.kschool.util.TestDataGenerator;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx16G"})
public class PostgresOperations {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction entityTransaction;

    List<String> valuesSmall = new ArrayList<>();
    List<String> valuesMedium = new ArrayList<>();
    List<String> valuesLarge = new ArrayList<>();

    String inputSmall;
    String inputMedium;
    String inputLarge;
    String inputFormat;
    String inputLanguages;
    String inputMediums;
    String inputPlaces;
    String inputReleases;
    String inputRecords;
    String inputTracks;

    Boolean executed = false;
    Boolean executedSetup = false;

    public void main() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PostgresOperations.class.getSimpleName())
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
            valuesSmall.add("('" + TestDataGenerator.createAreaJson(i) + "')");
    }

    private void createArtist(int maxId, int maxAreaId) {
        for (int i = 0; i <= maxId; i++)
            valuesLarge.add("('" + TestDataGenerator.createArtistJson(i, maxAreaId) + "')");
    }

    private void createFormat(int maxId) {
        List<String> values = new ArrayList<>();

        for (int i = 0; i <= maxId; i++)
            values.add("('" + TestDataGenerator.createFormatJson(i) + "')");

        inputFormat = String.join(",", values);
    }

    private void createLabel(int maxId, int maxAreaId) {
        for (int i = 0; i <= maxId; i++)
            valuesMedium.add("('" + TestDataGenerator.createLabelJson(i, maxAreaId) + "')");
    }

    private void createLanguage(int maxId) {
        List<String> values = new ArrayList<>();

        for (int i = 0; i <= maxId; i++)
            values.add("('" + TestDataGenerator.createLanguageJson(i) + "')");

        inputLanguages = String.join(",", values);
    }

    private void createMediums(int maxId, int maxFormatId) {
        List<String> values = new ArrayList<>();

        for (int i = 0; i <= maxId; i++)
            values.add("('" + TestDataGenerator.createMediumJson(i, maxFormatId) + "')");

        inputMediums = String.join(",", values);
    }

    private void createPlaces(int maxId) {
        List<String> values = new ArrayList<>();

        for (int i = 0; i <= maxId; i++)
            values.add("('" + TestDataGenerator.createPlaceJson(i) + "')");

        inputPlaces = String.join(",", values);
    }

    private void createRecords(int maxId, int maxArtistId) {
        List<String> values = new ArrayList<>();

        for (int i = 0; i <= maxId; i++)
            values.add("('" + TestDataGenerator.createRecordJson(i, maxArtistId) + "')");

        inputRecords = String.join(",", values);
    }

    private void createReleases(int maxId, Integer maxRecordId, Integer maxLanguageId, Integer maxLabelId, Integer maxMediumId) {
        List<String> values = new ArrayList<>();

        for (int i = 0; i <= maxId; i++)
            values.add("('" + TestDataGenerator.createReleaseJson(i, maxRecordId, maxLanguageId, maxLabelId, maxMediumId) + "')");

        inputReleases = String.join(",", values);
    }

    private void createTracks(int maxId, Integer maxRecordId) {
        List<String> values = new ArrayList<>();

        for (int i = 0; i <= maxId; i++)
            values.add("('" + TestDataGenerator.createTrackJson(i, maxRecordId) + "')");

        inputTracks = String.join(",", values);
    }

    @Setup(Level.Invocation)
    public void setupSets() {
        if (!executedSetup) {
            entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            createArea(100);
            createLabel(1000, 100);
            createArtist(10000, 100);

            inputSmall = String.join(",", valuesSmall);
            inputMedium = String.join(",", valuesMedium);
            inputLarge = String.join(",", valuesLarge);

            executedSetup = true;
        }
    }

    @Benchmark
    public void aacleanup() {
        entityTransaction.begin();

        entityManager.createNativeQuery("DROP TABLE AREAS;\n" +
                "DROP TABLE ARTISTS;\n" +
                "DROP TABLE FORMATS;\n" +
                "DROP TABLE LABELS;\n" +
                "DROP TABLE LANGUAGES;\n" +
                "DROP TABLE MEDIUMS;\n" +
                "DROP TABLE PLACES;\n" +
                "DROP TABLE RECORDS;\n" +
                "DROP TABLE RELEASES;\n" +
                "DROP TABLE TRACKS;").executeUpdate();

        entityManager.createNativeQuery("CREATE TABLE AREAS (ID SERIAL NOT NULL, AREA JSONB, CONSTRAINT areas_pkey PRIMARY KEY (ID));\n" +
                "CREATE TABLE ARTISTS (ID SERIAL NOT NULL, ARTIST JSONB, CONSTRAINT artists_pkey PRIMARY KEY (ID));\n" +
                "CREATE TABLE FORMATS (ID SERIAL NOT NULL, FORMAT JSONB, CONSTRAINT formats_pkey PRIMARY KEY (ID));\n" +
                "CREATE TABLE LABELS (ID SERIAL NOT NULL, LABEL JSONB, CONSTRAINT labels_pkey PRIMARY KEY (ID));\n" +
                "CREATE TABLE LANGUAGES (ID SERIAL NOT NULL, LANGUAGE JSONB, CONSTRAINT languages_pkey PRIMARY KEY (ID));\n" +
                "CREATE TABLE MEDIUMS (ID SERIAL NOT NULL, MEDIUM JSONB, CONSTRAINT mediums_pkey PRIMARY KEY (ID));\n" +
                "CREATE TABLE PLACES (ID SERIAL NOT NULL, PLACE JSONB, CONSTRAINT places_pkey PRIMARY KEY (ID));\n" +
                "CREATE TABLE RECORDS (ID SERIAL NOT NULL, RECORD JSONB, CONSTRAINT records_pkey PRIMARY KEY (ID));\n" +
                "CREATE TABLE RELEASES (ID SERIAL NOT NULL, RELEASE JSONB, CONSTRAINT releases_pkey PRIMARY KEY (ID));\n" +
                "CREATE TABLE TRACKS (ID SERIAL NOT NULL, TRACK JSONB, CONSTRAINT tracks_pkey PRIMARY KEY (ID));").executeUpdate();

        entityTransaction.commit();
    }

    @Benchmark
    public void abCreationData() {
        if (!executed) {
            createLabel(100, 100);
            createFormat(10);
            createLanguage(100);
            createMediums(10, 100);
            createPlaces(100);
            createRecords(100, 10000);
            createReleases(200, 100, 100, 100, 100);
            createTracks(1000, 100);

            entityTransaction.begin();

            entityManager.createNativeQuery("INSERT INTO formats (format) VALUES " + inputFormat).executeUpdate();
            entityManager.createNativeQuery("INSERT INTO languages (language) VALUES " + inputLanguages).executeUpdate();
            entityManager.createNativeQuery("INSERT INTO mediums (medium) VALUES " + inputMediums).executeUpdate();
            entityManager.createNativeQuery("INSERT INTO places (place) VALUES " + inputPlaces).executeUpdate();
            entityManager.createNativeQuery("INSERT INTO releases (release) VALUES " + inputReleases).executeUpdate();
            entityManager.createNativeQuery("INSERT INTO records (record) VALUES " + inputRecords).executeUpdate();
            entityManager.createNativeQuery("INSERT INTO tracks (track) VALUES " + inputTracks).executeUpdate();

            entityTransaction.commit();
            executed = true;
        }
    }

    @Benchmark
    public void bInsertOneRecord() {
        entityTransaction.begin();

        entityManager.createNativeQuery("INSERT INTO areas (area) VALUES ('" + TestDataGenerator.createAreaJson(20000) + "')").executeUpdate();

        entityTransaction.commit();
    }

    @Benchmark
    public void cInsertMultipleRecordsSmall() {
        entityTransaction.begin();

        entityManager.createNativeQuery("INSERT INTO areas (area) VALUES " + inputSmall).executeUpdate();

        entityTransaction.commit();
    }

    @Benchmark
    public void dInsertMultipleRecordsMedium() {
        entityTransaction.begin();

        entityManager.createNativeQuery("INSERT INTO labels (label) VALUES " + inputMedium).executeUpdate();

        entityTransaction.commit();
    }

    @Benchmark
    public void eInsertMultipleRecordsLarge() {
        entityTransaction.begin();

        entityManager.createNativeQuery("INSERT INTO artists (artist) VALUES " + inputLarge).executeUpdate();

        entityTransaction.commit();
    }

    @Benchmark
    public void fUpdateFieldOne() {
        entityTransaction.begin();

        entityManager.createNativeQuery("UPDATE areas " +
                "SET area = area|| '{\"comment\": \"NewComment\" }' " +
                "WHERE area->'id' = '0'").executeUpdate();

        entityTransaction.commit();
    }

    @Benchmark
    public void gUpdateFieldMultiple() {
        entityTransaction.begin();

        entityManager.createNativeQuery("UPDATE artists " +
                "SET artist = artist || '{\"areaID\": " + 9999 + " }' " +
                "WHERE ARTIST->'id' " +
                "IN ('0','1', '2', '3', '4', '5', '6', '7', '8', '9')").executeUpdate();

        entityTransaction.commit();
    }

    @Benchmark
    public void hUpdateFieldLinked() {
        entityTransaction.begin();

        entityManager.createNativeQuery("UPDATE releases " +
                "SET release = jsonb_set(release, '{artistId}'::::text[], RECORD->'artistId',true) " +
                "FROM RECORDS " +
                "WHERE RECORD->'id' = release->'recordId' AND release->'id' < '50';").executeUpdate();

        entityTransaction.commit();
    }

    @Benchmark
    public void iDeleteKeyValuePair() {
        entityTransaction.begin();

        entityManager.createNativeQuery("UPDATE AREAS " +
                "SET AREA = AREA - 'comment' " +
                "WHERE AREA->'id' < '10';").executeUpdate();

        entityTransaction.commit();
    }

    @Benchmark
    public void jDeleteDocument() {
        entityTransaction.begin();

        entityManager.createNativeQuery("DELETE " +
                "FROM AREAS " +
                "WHERE AREA->'id' > '10' AND AREA->'id' <= '20';").executeUpdate();

        entityTransaction.commit();
    }

    @Benchmark
    public void kSelectSimple() {
        entityTransaction.begin();

        List<Formats> result = entityManager.createNativeQuery("SELECT * from formats", Formats.class).getResultList();

        entityTransaction.commit();
    }

    @Benchmark
    public void lSelectFiltered() {
        entityTransaction.begin();

        Records result = (Records) entityManager.createNativeQuery("SELECT * from records WHERE record->'id' = '1'", Records.class).getSingleResult();

        entityTransaction.commit();
    }

    @Benchmark
    public void mSelectJoined() {
        entityTransaction.begin();

        List<Formats> result = entityManager.createNativeQuery("SELECT FORMATS.* from RELEASES JOIN MEDIUMS ON release->'mediumId' = medium->'id' JOIN FORMATS ON FORMAT->'id' = MEDIUM->'formatId'", Formats.class).getResultList();

        entityTransaction.commit();
    }

    @Benchmark
    public void nSelectCount() {
        entityTransaction.begin();

        entityManager.createNativeQuery("SELECT COUNT(*) from formats").getSingleResult();

        entityTransaction.commit();
    }
}
