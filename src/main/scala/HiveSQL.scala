import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object HiveSQL {

    def main(args: Array[String]) {

        val sparkConf = new SparkConf
        sparkConf.setAppName("titanic-analytics")

        val spark: SparkSession = SparkSession.builder
                .config(sparkConf)
                .enableHiveSupport()
                .getOrCreate()

        import spark.implicits._
        import spark.sql
        // Aggregation queries are also supported.
        sql("SELECT COUNT(*) FROM tbl_titanic_1").show()


    }
}
