/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jointhegrid.hadoopjmx.hadoop_0_20.alerting.namenode;

import com.jointhegrid.hadoopjmx.JMXBaseException;
import com.jointhegrid.hadoopjmx.Nagios;
import com.jointhegrid.hadoopjmx.hadoop_0_20.trending.namenode.FSNamesystemState;

/**
 *
 * @author ecapriolo
 */
public class FSNamesystemStateFilesTotalCheck extends FSNamesystemState {

  private long warnThresh;
  private long errorThresh;

  public FSNamesystemStateFilesTotalCheck(){
    super();
  }
  
  public void setup(String [] args){

    setJmxURL(args[0]);
    setUser(args[1]);
    setPass(args[2]);
    setObjectName(args[3]);
    warnThresh = Long.parseLong(args[4]);
    errorThresh = Long.parseLong(args[5]);
  }

  public static void main(String [] args) throws JMXBaseException {
    FSNamesystemStateFilesTotalCheck c = new FSNamesystemStateFilesTotalCheck();

    if (args.length != 6) {
      System.err.println("Wrong number of arguments. url user pass object warn crit");
      System.exit(Nagios.NAGIOS_UNKNOWN);
    }
    
    String [] testargs = new String [] {
      "service:jmx:rmi:///jndi/rmi://localhost:10001/jmxrmi" ,
      "controlRole",
      "password",
      "hadoop:service=NameNode,name=FSNamesystemState",
      "13", "20"};
    c.setup(args);
    c.fetch();

    long currentFiles= ((Long) c.getWantedVarResultsRO().get("FilesTotal"));

    if ( currentFiles > c.errorThresh ) {
      System.out.println("FilesTotal FAILED: CurrentFiles:"+currentFiles);
      System.exit(Nagios.NAGIOS_FAILED);
    }
    if ( currentFiles > c.warnThresh ){
      System.out.println("FilesTotal WARN: CurrentFiles:"+currentFiles);
      System.exit(Nagios.NAGIOS_WARNING);
    }
    System.out.println("FilesTotal OK: CurrentFiles:"+currentFiles);
    System.exit(Nagios.NAGIOS_OK);
  }
}
