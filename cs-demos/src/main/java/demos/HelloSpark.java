package demos;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.List;

public class HelloSpark {
    public static void main(String[] args) {
       System.setProperty("hadoop.home.dir", "C:\\Java\\hadoop-2.9.2");
       System.setProperty("spark.master", "local");

        SparkSession session = SparkSession.builder()
                .appName("Hello Spark")
                .getOrCreate();

        JavaSparkContext spark = new JavaSparkContext(session.sparkContext());

        List<String> words = Arrays.asList("Hello", "World");
        List<String> processed = spark.parallelize(words).map(String::toUpperCase).collect();
        processed.forEach(System.out::println);

        spark.stop();
    }
}
