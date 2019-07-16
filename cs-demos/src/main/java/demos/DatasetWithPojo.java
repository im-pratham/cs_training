package demos;

import demos.pojo.Person;
import org.apache.spark.sql.*;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatasetWithPojo {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "C:\\Java\\hadoop-2.9.2");
        System.setProperty("spark.master", "local");

        SparkSession session = SparkSession.builder()
                .appName("Dataset with Pojo")
                .getOrCreate();

        // Create an instance of a Bean class
        Person person = new Person();
        person.setName("Bob");
        person.setAge(31);

        // Encoders are created for Java beans
        Encoder<Person> personEncoder = Encoders.bean(Person.class);
        Dataset<Person> javaBeanDS = session.createDataset(
                Collections.singletonList(person),
                personEncoder
        );
        javaBeanDS.show();

        //load an untyped data set
        Dataset<Row> ds = session.read().json("src/main/resources/people.json");

        //use reflection to convert to Person objects (cannot handle null values)
        Dataset<Person> data1 = ds.as(Encoders.bean(Person.class));

        Dataset<Person> sorted = data1.sort("age");
        data1.filter((FilterFunction<Person>)  p -> p.getName().startsWith("S")).show();

        //data1.sort("age").javaRDD().filter(p -> p.getAge() > 0).collect().forEach(System.out::println);

        //use specific schema to convert to Person objects (name order must be alphabetical!)
        List<StructField> fields = new ArrayList<>();
        fields.add(DataTypes.createStructField("age", DataTypes.LongType, true));
        fields.add(DataTypes.createStructField("name", DataTypes.StringType, true));
        StructType schema = DataTypes.createStructType(fields);

        Dataset<Person> data2 = session.createDataFrame(ds.rdd(), schema).as(Encoders.bean(Person.class));
        data2.show();

        data2.sort("age").show();

        data2.printSchema();

        //data2.map(p -> p.setAge(p.getAge() + 1)).write().format("json").save("src/main/resources/older-people");

        session.stop();
    }

}
