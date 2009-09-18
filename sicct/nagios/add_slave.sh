BASEFILE=base.config.xxx
jtgDomain=""
if [ -z "$1" ] ; then
  echo "Enter the name of the new slave"
  read newhost
else
  newhost="$1"
fi

if [ -z "$newhost" ] ; then
  echo "You need to enter a hostname"
  exit 1
fi

if [ -f  "${newhost}.cfg" ] ; then
  echo "Cowardly refusing to clobber existing file. ${newhost}.cfg"
  exit 2
fi

ip=`dig +short ${newhost}${jtgDomain}`
if [ -z "$ip" ] ; then
  echo "lookup failed fix your DNS"
  exit 3
fi

full="${newhost}${jtgDomain}"

sed "s/HOSTNAMESHORT/$newhost/; s/HOSTIP/$ip/; s/HOSTNAMELONG/$full/" $BASEFILE > ${newhost}.cfg
