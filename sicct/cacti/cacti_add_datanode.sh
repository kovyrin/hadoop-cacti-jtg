# This code is HIGHLY specific to my environment and may not work in yours.
# It is currently intended as a baseline 
# For our cacti datanode template is this id
DATA_NODE_TEMPLATE_ID=28

# These will probably be different for yoru deployment! You have been warned
# FULL CPU USAGE LOAD AVERAGE MEMORY USAGE RAWIO RAWSWAP
CG_AR=( 145 11 13 71 72 )

#This is yoru SNMP community
SNMP_COM=XXXX

#This is your domain
tDOMAIN=".jtg.com"

DESCRIPTION=""

if [ -z "$1" ] ; then
  DESCRIPTION=$1
else
  echo "Please enter description (hadoopdata2)"
  read DESCRIPTION
  echo $DESCRIPTION
fi

tHOSTNAME=${DESCRIPTION}.${tDOMAIN}

tres=`php -q add_device.php --description=$DESCRIPTION --ip=$tHOSTNAME --template=28 --community=$SNMP_COM`
echo "php -q add_device.php --description=$DESCRIPTION --ip=$tHOSTNAME --template=28 --community=$SNMP_COM"

# Here we find the ID that was created
HO=`php add_graphs.php --list-hosts | grep $tHOSTNAME`
#1       127.0.0.1       8       Localhost
HO_AR=( $HO )

for i in  ${CG_AR[@]}  ; do
  echo php -q add_graphs.php --host-id=${HO_AR[0]} --graph-type=cg --graph-template-id=$i
  RES=`php -q add_graphs.php --host-id=${HO_AR[0]} --graph-type=cg --graph-template-id=$i`
done

#Interface Graphs
GRAPH_TEMP_ID=`php -q add_graphs.php --list-graph-templates | grep "Interface - Traffic (bits/sec)" | cut -f1`
INTERFACE_ID=`php -q add_graphs.php --host-id=${HO_AR[0]} --list-snmp-queries | grep "SNMP - Interface Statistics" | cut -f1`
INOUT95_ID=`php -q add_graphs.php --snmp-query-id=$INTERFACE_ID --list-query-types | grep "In/Out Bits with 95th Percentile" | cut -f1`

echo "php -q add_graphs.php --host-id=${HO_AR[0]} --graph-type=ds --graph-template-id=$GRAPH_TEMP_ID --snmp-query-id=$INTERFACE_ID --snmp-query-type-id=$INOUT95_ID --snmp-field=ifOperStatus --snmp-value=Up"

RES=`php -q add_graphs.php --host-id=${HO_AR[0]} --graph-type=ds --graph-template-id=$GRAPH_TEMP_ID --snmp-query-id=$INTERFACE_ID --snmp-query-type-id=$INOUT95_ID --snmp-field=ifOperStatus --snmp-value=Up`

# Now we wish to add disk performance graphs for EACH disk in the cluster
DGRAPH_TEMP_ID=`php -q add_graphs.php --list-graph-templates | grep "Host - Disk IO - Transactions per Second" | cut -f1`
DISK_IO_ID=`php -q add_graphs.php --host-id=${HO_AR[0]} --list-snmp-queries | grep "SNMP - Get Disk IO" | cut -f1`
BperS=`php -q add_graphs.php --snmp-query-id=$DISK_IO_ID --list-query-types | grep "bytes per second" | cut -f1`
DIOT=`php -q add_graphs.php --snmp-query-id=$DISK_IO_ID --list-query-types | grep "Disk IO Transactions" | cut -f1`

# Here is list/map of mount points and their device names
DEV_AR=( "cciss/c0d0p5" "cciss/c0d1p5" "cciss/c0d2p1" "cciss/c0d3p1" "cciss/c0d4p1" "cciss/c0d5p1" "cciss/c0d6p1" "cciss/c0d7p1" )
DEV_NM=( "/mnt/disk0" "/mnt/disk1" "/mnt/disk2" "/mnt/disk3" "/mnt/disk4" "/mnt/disk5" "/mnt/disk6" "/mnt/disk7" )


for (( i = 0 ; i < ${#DEV_AR[@]} ; i++ ))
do
  echo "php -q add_graphs.php --host-id=${HO_AR[0]} --graph-type=ds --graph-template-id=$DGRAPH_TEMP_ID --snmp-query-id=$DISK_IO_ID --snmp-query-type-id=$DIOT --snmp-field=hrDiskIODescr --snmp-value=${DEV_AR[$i]} --graph-title=\"|host_description| - Disk IO Bytes - |query_hrDiskIODescr| ${DEV_NM[$i]}\" "

  RES=`php -q add_graphs.php --host-id=${HO_AR[0]} --graph-type=ds --graph-template-id=$DGRAPH_TEMP_ID --snmp-query-id=$DISK_IO_ID --snmp-query-type-id=$DIOT --snmp-field=hrDiskIODescr --snmp-value=${DEV_AR[$i]} --graph-title="|host_description| - Disk IO Trans - |query_hrDiskIODescr| ${DEV_NM[$i]}"`

  echo "php -q add_graphs.php --host-id=${HO_AR[0]} --graph-type=ds --graph-template-id=$DGRAPH_TEMP_ID --snmp-query-id=$DISK_IO_ID --snmp-query-type-id=$BperS --snmp-field=hrDiskIODescr --snmp-value=${DEV_AR[$i]} --graph-title=\"|host_description| - Disk IO Bytes - |query_hrDiskIODescr| ${DEV_NM[$i]}\" "

  RES=`php -q add_graphs.php --host-id=${HO_AR[0]} --graph-type=ds --graph-template-id=$DGRAPH_TEMP_ID --snmp-query-id=$DISK_IO_ID --snmp-query-type-id=$BperS --snmp-field=hrDiskIODescr --snmp-value=${DEV_AR[$i]} --graph-title="|host_description| - Disk IO Bytes - |query_hrDiskIODescr| ${DEV_NM[$i]}"`
done

DEV_SIZE=( "/" "/var" "/mnt/disk0" "/mnt/disk1" "/mnt/disk2" "/mnt/disk3" "/mnt/disk4" "/mnt/disk5" "/mnt/disk6" "/mnt/disk7" )

DSIZE_TEMP_ID=`php -q add_graphs.php --list-graph-templates | grep "ucd/net - Available Disk Space" | cut -f1`
DISK_SIZE=`php -q add_graphs.php --host-id=${HO_AR[0]} --list-snmp-queries | grep "ucd/net -  Get Monitored Partitions" | cut -f1`
DISK_AVAIL=`php -q add_graphs.php --snmp-query-id=$DISK_SIZE --list-query-types | grep "Available/Used Disk Space" | cut -f1`

for (( i = 0 ; i < ${#DEV_SIZE[@]} ; i++ ))
do
  echo "php -q add_graphs.php --host-id=${HO_AR[0]} --graph-type=ds --graph-template-id=$DSIZE_TEMP_ID --snmp-query-id=$DISK_SIZE --snmp-query-type-id=$DISK_AVAIL --snmp-field=dskPath --snmp-value=${DEV_SIZE[$i]}"
  res=`php -q add_graphs.php --host-id=${HO_AR[0]} --graph-type=ds --graph-template-id=$DSIZE_TEMP_ID --snmp-query-id=$DISK_SIZE --snmp-query-type-id=$DISK_AVAIL --snmp-field=dskPath --snmp-value=${DEV_SIZE[$i]}`
done

