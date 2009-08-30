/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jointhegrid.hadoopjmx.hadoop_0_19.alerting.namenode;

import com.jointhegrid.hadoopjmx.hadoop_0_19.trending.namenode.FSNamesystemStatus;
import com.jointhegrid.hadoopjmx.JMXBaseException;
import com.jointhegrid.hadoopjmx.Nagios;

/**
 *
 * @author ecapriolo
 */
public class FSNamesystemStatusFilesTotalCheck extends FSNamesystemStatus {

  private long warnThresh;
  private long errorThresh;

  public FSNamesystemStatusFilesTotalCheck() {
    super();
  }

  public static void main(String[] args) throws JMXBaseException {
    FSNamesystemStatusFilesTotalCheck c = new FSNamesystemStatusFilesTotalCheck();

    if (args.length != 5) {
      System.err.println("Wrong number of arguments. url user pass filethresh");
      System.exit(3);
    }
    c.setJmxURL(args[0]);
    c.setUser(args[1]);
    c.setPass(args[2]);
    c.warnThresh = Long.parseLong(args[3]);
    c.errorThresh = Long.parseLong(args[4]);
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
