name := "SparkSamples"

version := "0.1"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-unchecked", "-deprecation")

libraryDependencies+= "org.apache.spark" %% "spark-core" % "2.3.0"

libraryDependencies+= "org.apache.spark" % "spark-streaming-kafka-0-8_2.11" % "2.2.0"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.3.0"
//libraryDependencies+= "org.apache.spark" % "spark-streaming" % "2.2.0"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.0"
//libraryDependencies+= "org.apache.spark" % "spark-sql_2.11" % "2.3.1"

libraryDependencies += "org.apache.spark" %% "spark-hive" % "2.3.0"

//libraryDependencies += "org.apache.spark" %% "spark-graphx" % "2.3.1"

//libraryDependencies+= "org.apache.spark" % "spark-hive_2.11" % "2.3.1"libraryDependencies ++= {
//  val sparkVer = "2.1.0"
//  Seq(
//    "org.apache.spark" %% "spark-core" % sparkVer % "provided" withSources()
//  )
//}


assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}