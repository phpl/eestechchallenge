import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
import com.datastax.spark.connector.japi.CassandraJavaUtil;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BatchRunner {

    public static final String CASSANDRA_KEYSPACE = "keyspace_name";

    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf(true);
        sparkConf.setAppName("twitter-hackaton-mashup");
        sparkConf.setMaster("local[*]");
        sparkConf.set("spark.cassandra.connection.host", "localhost");
        sparkConf.set("spark.cassandra.connection.port", "9042");


        SparkContext sparkContext = new SparkContext(sparkConf);
        DateFormat formatter;
        Date date;
        formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            userList(sparkConf, sparkContext, "trump", formatter.parse("2018-04-04"), formatter.parse("2018-04-08"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void exampleJob(SparkConf sparkConf, SparkContext sparkContext) {
//        long counter = CassandraJavaUtil.javaFunctions(sparkContext)
//                .cassandraTable(CASSANDRA_KEYSPACE, "sample_table")
//                .count();
//
//        System.out.println("Size of processed resultset: " + counter);

//        SparkSession sparkSession = SparkSession
//                .builder()
//                .config(sparkConf)
//                .getOrCreate();
//
//        CassandraConnector connector = CassandraConnector.apply(sparkSession.sparkContext().conf());
//        Session session = connector.openSession();
////        BoundStatement bs = session.prepare("insert into keyspace_name.sample_table" +
////                "(id, keyword, tweet_date, language, country, followers_len) " +
////                "values (?,?,?,?,?,?)")
////                .bind(3L, "test", new Date(), "test","test",3);
//        session.execute(bs);
//        session.close();
    }

    public static void userList(SparkConf sparkConf, SparkContext sparkContext, String tag, Date from, Date to) {


        SparkSession sparkSession = SparkSession
                .builder()
                .config(sparkConf)
                .getOrCreate();

        CassandraConnector connector = CassandraConnector.apply(sparkSession.sparkContext().conf());
        Session session = connector.openSession();


        /*List<Tuple2<Long, Integer>> tuples = */
        List<Tuple2<Integer, Integer>> collect = CassandraJavaUtil.javaFunctions(sparkContext)
                .cassandraTable(CASSANDRA_KEYSPACE, "test")
//                .select("id", "username_id", "tweet", "created_date")
//                .mapToPair(o -> new Tuple2<>(
//                        new Tuple2<>(o.getString("username_id"), o.getString("tweet")),
//                        o.getDate("created_date"))).collect();
                .mapToPair(o -> new Tuple2<>(
                        o.getInt("id"),
                        1))
                .reduceByKey((x, y) -> x + y).collect();

        collect.stream()
                .forEach(System.out::println);

        /*
        tuples.stream()
                .filter(o ->  o._2.after(from) && o._2.before(to) && o._1._2.toLowerCase().contains(tag.toLowerCase()))
                .forEach(o-> System.out.println(o._1._1));*/

//        tuples.forEach(o -> System.out.println(o._1));
//        ResultSet result =
//                session.execute("SELECT username_id, tweet FROM keyspace_name.sample_table WHERE created_date >" + from + "AND created_date <=" + to + ";");
//
//        for (Row row : result) {
//            row.
//        }
        session.close();
    }
}
