package jdurao.kschool;

import jdurao.kschool.entities.Areas;
import jdurao.kschool.entities.Artists;
import jdurao.kschool.entities.Records;
import jdurao.kschool.entities.Tracks;
import jdurao.kschool.pojo.AreaJson;
import jdurao.kschool.util.HibernateUtil;
import jdurao.kschool.util.TestDataGenerator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx16G"})
@Warmup(iterations = 0)
@Measurement(iterations = 4)
public class PostgresOperations {
    List<String> valuesSmall = new ArrayList<>();
    List<String> valuesMedium = new ArrayList<>();
    List<String> valuesLarge = new ArrayList<>();
    String inputSmall;
    String inputMedium;
    String inputLarge;

    @Param({"1"})
    public int iterations;

    public void main() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PostgresOperations.class.getSimpleName())
                .forks(1)
                .operationsPerInvocation(1)
                .build();

        new Runner(opt).run();
    }

    @Setup(Level.Invocation)
    public void aCreationData() {
        List<String> valuesRecords = new ArrayList<>();
        List<String> valuesTracks = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            valuesSmall.add("('" + TestDataGenerator.createAreaJson((long) i) + "')");
            valuesRecords.add("('" + TestDataGenerator.createRecordJson((long) i) + "')");
            valuesTracks.add("('" + TestDataGenerator.createTrackJson((long) i) + "')");
        }
        inputSmall = String.join(",", valuesSmall);
        for (int i = 0; i < 1000; i++) {
            valuesMedium.add("('" + TestDataGenerator.createLabelJson((long) i) + "')");
        }
        inputMedium = String.join(",", valuesMedium);
        for (int i = 0; i < 10000; i++) {
            valuesLarge.add("('" + TestDataGenerator.createArtistJson((long) i) + "')");
        }
        inputLarge = String.join(",", valuesLarge);

        String inputRecords = String.join(",", valuesRecords);
        String inputTracks = String.join(",", valuesTracks);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.createNativeQuery("INSERT INTO records (record) VALUES " + inputRecords).executeUpdate();
        entityManager.createNativeQuery("INSERT INTO tracks (track) VALUES " + inputTracks).executeUpdate();

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Benchmark
    public void bInsertOneRecord() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.createNativeQuery("INSERT INTO areas (area) VALUES ('" + TestDataGenerator.createAreaJson(UUID.randomUUID().getMostSignificantBits()) + "')").executeUpdate();
        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Benchmark
    public void cInsertMultipleRecordsSmall() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.createNativeQuery("INSERT INTO areas (area) VALUES " + inputSmall).executeUpdate();

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Benchmark
    public void dInsertMultipleRecordsMedium() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.createNativeQuery("INSERT INTO labels (label) VALUES " + inputMedium).executeUpdate();

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Benchmark
    public void eInsertMultipleRecordsLarge() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        entityManager.createNativeQuery("INSERT INTO artists (artist) VALUES " + inputLarge).executeUpdate();

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Benchmark
    public void fUpdateFieldOne() {
        for (int i = 0; i < iterations; i++){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();

            Areas area = (Areas) entityManager.createNativeQuery("SELECT  * FROM areas a WHERE a.area->'id' = '99' LIMIT 1", Areas.class).getSingleResult();

            area.getArea().setComment("New Comment");

            entityManager.persist(area);

            entityTransaction.commit();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Benchmark
    public void gUpdateFieldMultiple() {
        for (int i = 0; i < iterations; i++){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();

            List<Artists> artists
                    = (List<Artists>) entityManager
                    .createNativeQuery("SELECT  * " +
                            "FROM artists a " +
                            "WHERE a.artist->'id' " +
                            "IN ('1', '2', '3', '4', '5', '6', '7', '8', '9'," +
                            "'10', '20', '30', '40', '50', '60', '70', '80', '90'," +
                            "'11', '21', '31', '41', '51', '61', '71', '81', '91'," +
                            "'12', '22', '32', '42', '52', '62', '72', '82', '92'," +
                            "'13', '23', '33', '43', '53', '63', '73', '83', '93')"
                            , Artists.class).getResultList();

            artists.stream().forEach(e -> {
                Random random = new Random();
                e.getArtist().setAreaId((long) random.nextInt(100));
            });

            entityManager.persist(artists);

            entityTransaction.commit();
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Benchmark
    public void hUpdateFieldLinked() {
        for (int i = 0; i < iterations; i++){
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();

            List<Tracks> tracks
                    = (List<Tracks>) entityManager
                    .createNativeQuery("SELECT * " +
                                    "FROM tracks t " +
                                    "JOIN records r ON t.track->'recordId'=r.record->'id'"
                            , Tracks.class).getResultList();

            tracks.stream().forEach(e -> {
                Records records =  entityManager.find(Records.class, e.getTrack().getRecordId());
                e.getTrack().setArtistId(records.getRecord().getArtistId());
            });

            entityManager.persist(tracks);

            entityTransaction.commit();
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
