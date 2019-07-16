package demos;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import static org.apache.spark.sql.types.DataTypes.IntegerType;
import static org.apache.spark.sql.types.DataTypes.StringType;

public class DatasetWithSql {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "C:\\Java\\hadoop-2.9.2");
        System.setProperty("spark.master", "local");

        SparkSession session = SparkSession.builder()
                .appName("Dataset with SQL")
                .getOrCreate();

        //optionally specify the schema explicitly
        StructType schema =
                new StructType(new StructField[] {
                        new StructField("name", StringType, false, Metadata.empty()),
                        new StructField("age", IntegerType, true, Metadata.empty())
                });

        //load the data
        Dataset<Row> ds = session.read().schema(schema).json("src/main/resources/people.json");

        //check the schema
        ds.printSchema();

        //use the SQL API to select the rows
        ds.filter(ds.col("age").isNotNull()).sort(ds.col("age").desc()).show();

        //or register the dataset as a table
        ds.createOrReplaceTempView("people");

        //and use SQL queries
        session.sql("SELECT name, age FROM people WHERE age IS NOT NULL SORT BY age ASC").show();

        //perform an aggregate operation using SQL
        Dataset<Row> sumQuery = session.sql("SELECT sum(age) FROM people");
        long sumOfAges = sumQuery.first().getLong(0);
        System.out.println("Sum of ages: " + sumOfAges);

        session.stop();
    }
}
