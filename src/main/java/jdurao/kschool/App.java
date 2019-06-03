package jdurao.kschool;

import org.openjdk.jmh.runner.RunnerException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

public class App {
    public static void main(String[] args) throws RunnerException, FileNotFoundException {
         int iterations = 10;
        PrintStream out = new PrintStream(new FileOutputStream("results-" + new Date().getTime() + ".txt"));
        System.setOut(out);

        for (int i = 0;i < iterations; i++) {
            System.out.println("****************************************************************************************");
            System.out.println("****************************************************************************************");
            System.out.println("****************************************************************************************");
            System.out.println("***********************************      POSTGRES     **********************************");
            System.out.println("****************************************************************************************");
            System.out.println("***********************************         " + i + "         **********************************");
            System.out.println("****************************************************************************************");
            System.out.println("****************************************************************************************");
            System.out.println("****************************************************************************************");

            PostgresOperations postgresOperations = new PostgresOperations();
            postgresOperations.main();
            postgresOperations.setupSets();
            postgresOperations.aacleanup();
            postgresOperations.abCreationData();
            postgresOperations.bInsertOneRecord();
            postgresOperations.cInsertMultipleRecordsSmall();
            postgresOperations.dInsertMultipleRecordsMedium();
            postgresOperations.eInsertMultipleRecordsLarge();
            postgresOperations.fUpdateFieldOne();
            postgresOperations.gUpdateFieldMultiple();
            postgresOperations.hUpdateFieldLinked();
            postgresOperations.iDeleteKeyValuePair();
            postgresOperations.jDeleteDocument();
            postgresOperations.kSelectSimple();
            postgresOperations.lSelectFiltered();
            postgresOperations.mSelectJoined();
            postgresOperations.nSelectCount();
        }

        for (int j = 0; j < iterations; j++) {
            System.out.println("****************************************************************************************");
            System.out.println("****************************************************************************************");
            System.out.println("****************************************************************************************");
            System.out.println("***********************************       MONGODB     **********************************");
            System.out.println("****************************************************************************************");
            System.out.println("***********************************         " + j + "         **********************************");
            System.out.println("****************************************************************************************");
            System.out.println("****************************************************************************************");
            System.out.println("****************************************************************************************");

            MongoOperations mongoOperations = new MongoOperations();
            mongoOperations.main();
            mongoOperations.setupSets();
            mongoOperations.aaCleanup();
            mongoOperations.abCreationData();
            mongoOperations.bInsertOneRecord();
            mongoOperations.cInsertMultipleRecordsSmall();
            mongoOperations.dInsertMultipleRecordsMedium();
            mongoOperations.eInsertMultipleRecordsLarge();
            mongoOperations.fUpdateFieldOne();
            mongoOperations.gUpdateFieldMultiple();
            mongoOperations.hUpdateFieldLinked();
            mongoOperations.iDeleteKeyValuePair();
            mongoOperations.jDeleteDocument();
            mongoOperations.kSelectSimple();
            mongoOperations.lSelectFiltered();
            mongoOperations.mSelectJoined();
            mongoOperations.nSelectCount();
        }

        for (int k = 0; k < iterations; k++) {
            System.out.println("****************************************************************************************");
            System.out.println("****************************************************************************************");
            System.out.println("****************************************************************************************");
            System.out.println("**********************************       COUCHBASE     *********************************");
            System.out.println("****************************************************************************************");
            System.out.println("***********************************         " + k + "         **********************************");
            System.out.println("****************************************************************************************");
            System.out.println("****************************************************************************************");
            System.out.println("****************************************************************************************");

            CouchbaseOperations couchbaseOperations = new CouchbaseOperations();
            couchbaseOperations.main();
            couchbaseOperations.setupSets();
            couchbaseOperations.aaCleanup();
            couchbaseOperations.abCreationData();
            couchbaseOperations.bInsertOneRecord();
            couchbaseOperations.cInsertMultipleRecordsSmall();
            couchbaseOperations.dInsertMultipleRecordsMedium();
            couchbaseOperations.eInsertMultipleRecordsLarge();
            couchbaseOperations.fUpdateFieldOne();
            couchbaseOperations.gUpdateFieldMultiple();
            couchbaseOperations.hUpdateFieldLinked();
            couchbaseOperations.iDeleteKeyValuePair();
            couchbaseOperations.jDeleteDocument();
            couchbaseOperations.kSelectSimple();
            couchbaseOperations.lSelectFiltered();
            couchbaseOperations.mSelectJoined();
            couchbaseOperations.nSelectCount();
        }
    }
}
