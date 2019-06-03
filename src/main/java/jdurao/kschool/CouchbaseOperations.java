package jdurao.kschool;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import jdurao.kschool.enums.DataTablesEnum;
import jdurao.kschool.util.TestDataGenerator;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import rx.Observable;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx16G"})
public class CouchbaseOperations {
    Cluster cluster;

    List<JsonDocument> valuesSmall = new ArrayList<>();
    List<JsonDocument> valuesMedium = new ArrayList<>();
    List<JsonDocument> valuesLarge = new ArrayList<>();

    List<JsonDocument> valuesFormat = new ArrayList<>();
    List<JsonDocument> valuesLanguages = new ArrayList<>();
    List<JsonDocument> valuesMediums = new ArrayList<>();
    List<JsonDocument> valuesPlaces = new ArrayList<>();
    List<JsonDocument> valuesRecords = new ArrayList<>();
    List<JsonDocument> valuesReleases = new ArrayList<>();
    List<JsonDocument> valuesTracks = new ArrayList<>();

    Boolean executed = false;
    Boolean executedLoop = false;
    Boolean executedSetup = false;

    public void main() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(jdurao.kschool.CouchbaseOperations.class.getSimpleName())
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
            valuesSmall.add(TestDataGenerator.createAreaJsonDocument(i));
    }

    private void createArtist(int maxId, int maxAreaId) {
        for (int i = 0; i <= maxId; i++)
            valuesLarge.add(TestDataGenerator.createArtistJsonDocument(i, maxAreaId));
    }

    private void createFormat(int maxId) {
        for (int i = 0; i <= maxId; i++)
            valuesFormat.add(TestDataGenerator.createFormatJsonDocument(i));
    }

    private void createLabel(int maxId, int maxAreaId) {
        for (int i = 0; i <= maxId; i++)
            valuesMedium.add(TestDataGenerator.createLabelJsonDocument(i, maxAreaId));
    }

    private void createLanguage(int maxId) {
        for (int i = 0; i <= maxId; i++)
            valuesLanguages.add(TestDataGenerator.createLanguageJsonDocument(i));
    }

    private void createMediums(int maxId, int maxFormatId) {
        for (int i = 0; i <= maxId; i++)
            valuesMediums.add(TestDataGenerator.createMediumJsonDocument(i, maxFormatId));
    }

    private void createPlaces(int maxId) {
        for (int i = 0; i <= maxId; i++)
            valuesPlaces.add(TestDataGenerator.createPlaceJsonDocument(i));
    }

    private void createRecords(int maxId, int maxArtistId) {
        for (int i = 0; i <= maxId; i++)
            valuesRecords.add(TestDataGenerator.createRecordJsonDocument(i, maxArtistId));
    }

    private void createReleases(int maxId, Integer maxRecordId, Integer maxLanguageId, Integer maxLabelId, Integer maxMediumId) {
        for (int i = 0; i <= maxId; i++)
            valuesReleases.add(TestDataGenerator.createReleaseJsonDocument(i, maxRecordId, maxLanguageId, maxLabelId, maxMediumId));
    }

    private void createTracks(int maxId, Integer maxRecordId) {
        for (int i = 0; i <= maxId; i++)
            valuesTracks.add(TestDataGenerator.createTrackJsonDocument(i, maxRecordId));
    }

    @Setup(Level.Invocation)
    public void setupSets() {
        if (!executedSetup) {
            cluster = CouchbaseCluster.create("localhost");
            cluster.authenticate("Administrator", "ksPass");

            executedSetup = true;
            createArea(100);
            createLabel(1000, 100);
            createArtist(10000, 100);
        }
    }

    @Benchmark
    public void aaCleanup() {
        cluster.openBucket(DataTablesEnum.AREAS.getName()).bucketManager().flush();
        cluster.openBucket(DataTablesEnum.ARTISTS.getName()).bucketManager().flush();
        cluster.openBucket(DataTablesEnum.FORMATS.getName()).bucketManager().flush();
        cluster.openBucket(DataTablesEnum.LABELS.getName()).bucketManager().flush();
        cluster.openBucket(DataTablesEnum.LANGUAGES.getName()).bucketManager().flush();
        cluster.openBucket(DataTablesEnum.MEDIUMS.getName()).bucketManager().flush();
        cluster.openBucket(DataTablesEnum.PLACES.getName()).bucketManager().flush();
        cluster.openBucket(DataTablesEnum.RECORDS.getName()).bucketManager().flush();
        cluster.openBucket(DataTablesEnum.RELEASES.getName()).bucketManager().flush();
        cluster.openBucket(DataTablesEnum.TRACKS.getName()).bucketManager().flush();
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

            Observable.from(valuesFormat)
                    .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
                        @Override
                        public Observable<JsonDocument> call(final JsonDocument docToInsert) {
                            return cluster.openBucket(DataTablesEnum.FORMATS.getName()).async().insert(docToInsert);
                        }
                    })
                    .last()
                    .toBlocking()
                    .single();

            Observable.from(valuesLanguages)
                    .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
                        @Override
                        public Observable<JsonDocument> call(final JsonDocument docToInsert) {
                            return cluster.openBucket(DataTablesEnum.LANGUAGES.getName()).async().insert(docToInsert);
                        }
                    })
                    .last()
                    .toBlocking()
                    .single();

            Observable.from(valuesMediums)
                    .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
                        @Override
                        public Observable<JsonDocument> call(final JsonDocument docToInsert) {
                            return cluster.openBucket(DataTablesEnum.MEDIUMS.getName()).async().insert(docToInsert);
                        }
                    })
                    .last()
                    .toBlocking()
                    .single();

            Observable.from(valuesPlaces)
                    .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
                        @Override
                        public Observable<JsonDocument> call(final JsonDocument docToInsert) {
                            return cluster.openBucket(DataTablesEnum.PLACES.getName()).async().insert(docToInsert);
                        }
                    })
                    .last()
                    .toBlocking()
                    .single();

            Observable.from(valuesRecords)
                    .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
                        @Override
                        public Observable<JsonDocument> call(final JsonDocument docToInsert) {
                            return cluster.openBucket(DataTablesEnum.RECORDS.getName()).async().insert(docToInsert);
                        }
                    })
                    .last()
                    .toBlocking()
                    .single();

            Observable.from(valuesReleases)
                    .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
                        @Override
                        public Observable<JsonDocument> call(final JsonDocument docToInsert) {
                            return cluster.openBucket(DataTablesEnum.RELEASES.getName()).async().insert(docToInsert);
                        }
                    })
                    .last()
                    .toBlocking()
                    .single();

            Observable.from(valuesTracks)
                    .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
                        @Override
                        public Observable<JsonDocument> call(final JsonDocument docToInsert) {
                            return cluster.openBucket(DataTablesEnum.TRACKS.getName()).async().insert(docToInsert);
                        }
                    })
                    .last()
                    .toBlocking()
                    .single();

            executed = true;
        }
    }

    @Benchmark
    public void bInsertOneRecord() {
        cluster.openBucket(DataTablesEnum.AREAS.getName()).insert(TestDataGenerator.createAreaJsonDocument(20000));
    }

    @Benchmark
    public void cInsertMultipleRecordsSmall() {
        Observable.from(valuesSmall)
                .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
                    @Override
                    public Observable<JsonDocument> call(final JsonDocument docToInsert) {
                        return cluster.openBucket(DataTablesEnum.AREAS.getName()).async().insert(docToInsert);
                    }
                })
                .last()
                .toBlocking()
                .single();
    }

    @Benchmark
    public void dInsertMultipleRecordsMedium() {
        Observable.from(valuesMedium)
                .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
                    @Override
                    public Observable<JsonDocument> call(final JsonDocument docToInsert) {
                        return cluster.openBucket(DataTablesEnum.LABELS.getName()).async().insert(docToInsert);
                    }
                })
                .last()
                .toBlocking()
                .single();
    }

    @Benchmark
    public void eInsertMultipleRecordsLarge() {
        Observable.from(valuesLarge)
                .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
                    @Override
                    public Observable<JsonDocument> call(final JsonDocument docToInsert) {
                        return cluster.openBucket(DataTablesEnum.ARTISTS.getName()).async().insert(docToInsert);
                    }
                })
                .last()
                .toBlocking()
                .single();
    }

    @Benchmark
    public void fUpdateFieldOne() {
        JsonDocument area = cluster.openBucket(DataTablesEnum.AREAS.getName()).get("0");
        cluster.openBucket(DataTablesEnum.AREAS.getName()).upsert(JsonDocument.create(area.id(), area.content().put("comment", "NEW COMMENT")));
    }

    @Benchmark
    public void gUpdateFieldMultiple() {
        List<JsonDocument> newArtists = new ArrayList<>();
        List<JsonDocument> artists = Observable
                .range(0, 10)
                .flatMap(new Func1<Integer, Observable<JsonDocument>>() {
                    @Override
                    public Observable<JsonDocument> call(Integer id) {
                        return cluster.openBucket(DataTablesEnum.ARTISTS.getName()).async().get(String.valueOf(id));
                    }
                })
                .toList()
                .toBlocking()
                .single();

        for (JsonDocument artist : artists) {
            newArtists.add(JsonDocument.create(artist.id(), artist.content().put("areaID", 9999)));
        }

        Observable.from(newArtists)
                .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
                    @Override
                    public Observable<JsonDocument> call(final JsonDocument docToInsert) {
                        return cluster.openBucket(DataTablesEnum.ARTISTS.getName()).async().upsert(docToInsert);
                    }
                })
                .last()
                .toBlocking()
                .single();
    }

    @Benchmark
    public void hUpdateFieldLinked() {
        List<JsonDocument> newReleases = new ArrayList<>();
        List<JsonDocument> releases = Observable
                .range(0, 50)
                .flatMap(new Func1<Integer, Observable<JsonDocument>>() {
                    @Override
                    public Observable<JsonDocument> call(Integer id) {
                        return cluster.openBucket(DataTablesEnum.RELEASES.getName()).async().get(String.valueOf(id));
                    }
                })
                .toList()
                .toBlocking()
                .single();

        for (JsonDocument release : releases) {
            JsonDocument record = cluster.openBucket(DataTablesEnum.RECORDS.getName()).get(release.id());
            newReleases.add(JsonDocument.create(release.id(), release.content().put("artistId", record.id())));
        }

        Observable.from(newReleases)
                .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
                    @Override
                    public Observable<JsonDocument> call(final JsonDocument docToInsert) {
                        return cluster.openBucket(DataTablesEnum.ARTISTS.getName()).async().upsert(docToInsert);
                    }
                })
                .last()
                .toBlocking()
                .single();
    }

    @Benchmark
    public void iDeleteKeyValuePair() {
        List<JsonDocument> newAreas = new ArrayList<>();
        List<JsonDocument> areas = Observable
                .range(0, 9)
                .flatMap(new Func1<Integer, Observable<JsonDocument>>() {
                    @Override
                    public Observable<JsonDocument> call(Integer id) {
                        return cluster.openBucket(DataTablesEnum.AREAS.getName()).async().get(String.valueOf(id));
                    }
                })
                .toList()
                .toBlocking()
                .single();

        for (JsonDocument area : areas) {
            newAreas.add(JsonDocument.create(area.id(), area.content().removeKey("comment")));
        }

        Observable.from(newAreas)
                .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
                    @Override
                    public Observable<JsonDocument> call(final JsonDocument docToInsert) {
                        return cluster.openBucket(DataTablesEnum.ARTISTS.getName()).async().upsert(docToInsert);
                    }
                })
                .last()
                .toBlocking()
                .single();
    }

    @Benchmark
    public void jDeleteDocument() {
        Observable
                .range(0, 9)
                .flatMap(new Func1<Integer, Observable<JsonDocument>>() {
                    @Override
                    public Observable<JsonDocument> call(Integer id) {
                        return cluster.openBucket(DataTablesEnum.AREAS.getName()).async().remove(String.valueOf(id));
                    }
                })
                .toList()
                .toBlocking()
                .single();
    }

    @Benchmark
    public void kSelectSimple() {
        N1qlQueryResult result = cluster.openBucket(DataTablesEnum.FORMATS.getName()).query(
                N1qlQuery.simple("SELECT * FROM `formats`"));
    }

    @Benchmark
    public void lSelectFiltered() {
        cluster.openBucket(DataTablesEnum.AREAS.getName()).async().get("1");
    }

    @Benchmark
    public void mSelectJoined() {
        List<JsonDocument> formats = new ArrayList<>();

        N1qlQueryResult result = cluster.openBucket(DataTablesEnum.RELEASES.getName()).query(
                N1qlQuery.simple("SELECT * FROM `releases`"));

        for (N1qlQueryRow row : result.allRows()) {
            JsonDocument medium = cluster.openBucket(DataTablesEnum.MEDIUMS.getName()).get(String.valueOf(row.value().get("mediumId")));
            formats.add(cluster.openBucket(DataTablesEnum.FORMATS.getName()).get(String.valueOf(medium.content().get("formatId"))));
        }
    }

    @Benchmark
    public void nSelectCount() {
        N1qlQueryResult result = cluster.openBucket(
                DataTablesEnum.FORMATS.getName()).query(N1qlQuery.simple("SELECT COUNTS(*) FROM `formats`")
        );
    }
}
