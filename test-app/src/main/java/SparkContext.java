import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

public class SparkContext {

    private static volatile SparkSession spark;
    private SparkContext () {}

    public static SparkSession getInstance() {
        if (spark == null) {
            synchronized (SparkContext.class) {
                if (spark == null) {
                    spark = SparkSession.builder()
                            .config(getSparkConfig())
                            .getOrCreate();
                }
            }
        }

        return spark;
    }

    private static SparkConf getSparkConfig() {
        return new SparkConfig().getSparkConfig();
    }
}
