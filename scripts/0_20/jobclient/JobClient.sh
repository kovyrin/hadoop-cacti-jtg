JTG_CLASSPATH="`dirname $0`/../../hadoop-cacti-jtg.jar"
JTG_CLASSNAME="com.jointhegrid.hadoopjmx.jobclient.JobClientFactory"

HADOOP_VERSION="0_20"
#change these to point at your hadoop path
HADOOP_CORE="`dirname $0`/../../hadoop-0.20/hadoop-0.20-core.jar"
HADOOP_EXTRA_LIBS="`dirname $0`/../../hadoop-0.20/lib"
HADOOP_CONF=$1

if [ ! -f $HADOOP_CORE ] ; then
  echo "$HADOOP_CORE not found"
  exit 1
fi

if [ ! -d $HADOOP_EXTRA_LIBS ] ; then
  echo "$HADOOP_EXTRA_LIBS not found"
  exit 2
fi

for f in ${HADOOP_EXTRA_LIBS}/*.jar ; do
  JTG_CLASSPATH=${JTG_CLASSPATH}:$f
done

JTG_CLASSPATH=${HADOOP_CORE}:${JTG_CLASSPATH}:.:`dirname $0`/../..

java -cp ${JTG_CLASSPATH} ${JTG_CLASSNAME} $HADOOP_VERSION "$@"
