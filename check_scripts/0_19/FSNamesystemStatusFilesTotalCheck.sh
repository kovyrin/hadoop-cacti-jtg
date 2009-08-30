#!/bin/sh

JTG_CLASSPATH="`dirname $0`/hadoop-cacti-jtg.jar"
JTG_CLASSNAME="com.jointhegrid.hadoopjmx.alerting.FSNamesystemStatusFilesTotalCheck"

jmxURL=$1
user=$2
pass=$3
threshold=$4
java -cp ${JTG_CLASSPATH} ${JTG_CLASSNAME} $jmxURL $user $pass $threshold
