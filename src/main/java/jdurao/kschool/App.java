package jdurao.kschool;

import org.openjdk.jmh.runner.RunnerException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

public class App {
    public static void main(String[] args) throws RunnerException, FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream("results-" + new Date().getTime() + ".txt"));
        System.setOut(out);

        /*PostgresOperations postgresOperations = new PostgresOperations();
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
        postgresOperations.mSelectJoined();*/

        MongoOperations mongoOperations = new MongoOperations();
        mongoOperations.main();
        mongoOperations.setupSets();
        mongoOperations.aaCleanup();
        mongoOperations.abCreationData();
        //mongoOperations.bInsertOneRecord();
        mongoOperations.cInsertMultipleRecordsSmall();
        mongoOperations.dInsertMultipleRecordsMedium();
        mongoOperations.eInsertMultipleRecordsLarge();
        mongoOperations.fUpdateFieldOne();
    }
}
