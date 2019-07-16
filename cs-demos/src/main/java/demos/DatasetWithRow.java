package demos;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.sum;

public class DatasetWithRow {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "C:\\Java\\hadoop-2.9.2");
        System.setProperty("spark.master", "local");

        SparkSession session = SparkSession.builder()
                .appName("Dataset with Row")
                .getOrCreate();

        Dataset<Row> ds = session.read().json("src/main/resources/people.json");
        ds.show();

        // Select only the "name" column
        ds.select("name").show();

        // Select everybody, but increment the age by 1
        Dataset<Row> older = ds.select(col("name"), col("age").plus(1).as("age"));
        older.show();

        // perform an aggregating operation
        ds.select(sum("age"));

        session.stop();
    }
}
