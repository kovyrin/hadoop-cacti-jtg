JTG_CLASSPATH="`dirname $0`/../../hadoop-cacti-jtg.jar"
JTG_CLASSNAME="com.jointhegrid.hadoopjmx.hadoop_0_20.trending.datanode.FSDatasetState"

jmxURL=$1
user=$2
pass=$3
object=$4
java -cp ${JTG_CLASSPATH} ${JTG_CLASSNAME} $jmxURL $user $pass $object