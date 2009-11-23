JTG_CLASSPATH="`dirname $0`/../../hadoop-cacti-jtg.jar"
JTG_CLASSNAME="com.jointhegrid.hadoopjmx.jobclient.JobClientFactory"

HADOOP_VERSION="0_20"
HADOOP_CORE="/path/to/hadoop-core.jar"
HADOOP_EXTRA_LIBS="/path/tohadoop/lib"
HADOOP_CONF=$1

if [ not -f $HADOOP_CORE ] ; then
  echo "$HADOOP_CORE not found"
  exit 1
fi

if [ -d $HADOOP_EXTRA_LIBS ] ; then
  echo "$HADOOP_EXTRA_LIBS not found"
  exit 2
fi

for f in $HADOOP_EXTRA_LIBS/* ; do
  JTG_CLASSPATH=$f:$JTG_CLASSPATH
done

JTG_CLASSPATH=$HADOOP_CORE:$JTG_CLASSPATH

java -cp ${JTG_CLASSPATH} ${JTG_CLASSNAME} $HADOOP_VERSION $1