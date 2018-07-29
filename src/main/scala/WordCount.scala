import org.apache.spark.{SparkConf, SparkContext};


object WordCount {

    def main(args: Array[String]) {

        val sparkConf = new SparkConf
        sparkConf.setAppName("sample")

        sparkConf.setMaster("local[*]")

        val sc = new SparkContext(sparkConf)
        val textFile = sc.textFile(args(0))
        val counts = textFile.flatMap(line => line.split(" "))
                .map(word => (word, 1))
                .reduceByKey(_ + _)
        counts.saveAsTextFile(args(1))

    }
}

/*
    Check these in project
    1. plugin sbt
    2. build sbt
    To run: log into the machine where jar is uploaded and had spark2 client
    sudo -u hdfs /usr/hdp/current/spark2-client/bin/spark-submit --master yarn --deploy-mode client --class WordCount SparkSamples-assembly-0.1.jar  hdfs://ip-172-31-14-184.ec2.internal:8020/test-data/aesop.txt hdfs://ip-172-31-14-184.ec2.internal:8020/test-data/output/out1

 */
