package fscalls

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, from_unixtime, unix_timestamp, year}
import org.apache.spark.sql.types._

object FireServiceCallAnalysisHSQL {

    def main(args: Array[String]) {
        // SET THE LOG LEVEL TO ONLY PRINT INFO
        Logger.getLogger("org").setLevel(Level.INFO)

        // CREATE SPARK SESSION
        val spark = SparkSession.builder.enableHiveSupport().getOrCreate()


        // PRINT SCHEMA
        // ????

        // NUMBER OF RECORDS IN THE FILE
        val totalRecords = spark.sql("SELECT COUNT(*) from fireServiceCallsView")
        println(s"Number of records in the data file")
        totalRecords.show()

        // Q1: HOW MANY DIFFERENT TYPES OF CALLS WERE MADE TO THE FIRE SERVICE DEPARTMENT?
        println(s"Q1: HOW MANY DIFFERENT TYPES OF CALLS WERE MADE TO THE FIRE SERVICE DEPARTMENT?")
        val distinctTypesOfCallsDF = spark.sql("SELECT DISTINCT CallType from fireServiceCallsView")
        distinctTypesOfCallsDF.collect().foreach(println)

        // Q2: HOW MANY INCIDEDNTS OF EACH CALL TYPE WHERE THERE?
        println(s"Q2: HOW MANY INCIDEDNTS OF EACH CALL TYPE WHERE THERE?")
        val distinctTypesOfCallsSortedDF = spark.sql("SELECT CallType, COUNT(CallType) as count from fireServiceCallsView GROUP BY CallType ORDER BY count desc")
        distinctTypesOfCallsSortedDF.collect().foreach(println)

        // Q3: HOW MANY YEARS OF FIRE SERVICE CALLS IS IN THE DATA FILES AND INCIDENTS PER YEAR?
        println(s"Q3: HOW MANY YEARS OF FIRE SERVICE CALLS IS IN THE DATA FILES AND INCIDENTS PER YEAR?")
        val fireServiceCallYearsDF = spark.sql("SELECT CallYear, COUNT(CallYear) as count from fireServiceCallsView GROUP BY CallYear ORDER BY count desc")
        fireServiceCallYearsDF.show()

        // Q4: HOW MANY SERVICE CALLS WERE LOGGED IN THE PAST 7 DAYS?
        println(s"Q4: HOW MANY SERVICE CALLS WERE LOGGED IN THE PAST 7 DAYS?")
        val last7DaysServiceCallDF = spark.sql("SELECT CallDateTS, COUNT(CallDateTS) as count from fireServiceCallsView GROUP BY CallDateTS ORDER BY CallDateTS desc")
        last7DaysServiceCallDF.show(7)

        // Q5: WHICH NEIGHBORHOOD IN SF GENERATED THE MOST CALLS LAST YEAR?
        println(s"Q5: WHICH NEIGHBORHOOD IN SF GENERATED THE MOST CALLS LAST YEAR?")
        val neighborhoodDistrictCallsDF = spark.sql("SELECT NeighborhooodsDistrict, COUNT(NeighborhooodsDistrict) as count from fireServiceCallsView WHERE CallYear == 2016 GROUP BY NeighborhooodsDistrict ORDER BY count desc")
        neighborhoodDistrictCallsDF.collect().foreach(println)
    }
}
